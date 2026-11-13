package com.discord.addon.modules;

import com.discord.addon.Addon;
import com.google.gson.JsonObject;
import meteordevelopment.meteorclient.events.game.ReceiveMessageEvent;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.settings.StringSetting;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class MinecraftToDiscord extends Module {

    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<String> webhookUrl = sgGeneral.add(new StringSetting.Builder()
        .name("webhook-url")
        .description("The Discord webhook URL.")
        .defaultValue("")
        .build()
    );

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public MinecraftToDiscord() {
        super(Addon.CATEGORY, "MinecraftToDiscord", "Sends chat messages to a discord server using webhooks.");
    }

    @EventHandler
    private void onReceiveMessage(ReceiveMessageEvent event) {
        String message = event.getMessage().getString();
        String url = webhookUrl.get();

        if (url.isEmpty() || message.isBlank()) return;

        JsonObject json = new JsonObject();
        json.addProperty("content", message);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
            .build();

        CompletableFuture.runAsync(() -> {
            try {
                httpClient.send(request, HttpResponse.BodyHandlers.discarding());
            } catch (Exception ignored) {
            }
        });
    }
}
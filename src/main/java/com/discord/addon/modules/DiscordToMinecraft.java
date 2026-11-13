package com.discord.addon.modules;

import com.discord.addon.Addon;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.settings.StringSetting;
import meteordevelopment.meteorclient.systems.modules.Module;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class DiscordToMinecraft extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<String> botToken = sgGeneral.add(new StringSetting.Builder()
        .name("bot-token")
        .description("Your Discord Bot Token.")
        .defaultValue("")
        .build()
    );

    private final Setting<String> channelId = sgGeneral.add(new StringSetting.Builder()
        .name("channel-id")
        .description("Target Discord Channel ID.")
        .defaultValue("")
        .build()
    );

    private JDA jda;

    public DiscordToMinecraft() {
        super(Addon.CATEGORY, "discord-to-minecraft", "Sends messages from a Discord channel to Minecraft as you");
    }

    @Override
    public void onActivate() {
        String token = botToken.get();
        if (token.isBlank()) {
            error("Bot token is empty!");
            toggle();
            return;
        }

        new Thread(() -> {
            try {
                jda = JDABuilder.createDefault(token)
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .addEventListeners(new DiscordListener())
                    .build();
            } catch (Exception e) {
                error("Failed to connect to Discord Bot.");
            }
        }).start();
    }

    @Override
    public void onDeactivate() {
        if (jda != null) {
            jda.shutdownNow();
            jda = null;
        }
    }

    private class DiscordListener extends ListenerAdapter {
        @Override
        public void onMessageReceived(MessageReceivedEvent event) {
            if (event.getAuthor().isBot()) return;
            if (!event.getChannel().getId().equals(channelId.get())) return;
            if (mc.player == null) return;

            String content = event.getMessage().getContentDisplay();

            mc.execute(() -> {
                mc.player.networkHandler.sendChatMessage(content);
            });
        }
    }
}
package com.discord.addon;

import com.discord.addon.commands.*;
import com.discord.addon.modules.*;
import com.mojang.logging.LogUtils;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.commands.Commands;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;

public class Addon extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category CATEGORY = new Category("Discord Addon");

    @Override
    public void onInitialize() {
        LOG.info("Initialising Discord Addon v1.1");

        // Modules

        Modules.get().add(new MinecraftToDiscord());
        Modules.get().add(new DiscordToMinecraft());

    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(CATEGORY);
    }

    @Override
    public String getPackage() {
        return "com.discord.addon";
    }
}

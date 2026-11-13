<p align="center">
  <img src="https://cdn.discordapp.com/attachments/1215320296391835708/1570817063151345746/image.png?ex=6af878e4&is=6af72764&hm=71acbdeed4464c5f4f065a7484c34e33c7faaa1a558d3e093d4567be0f754f7f&" alt="discordaddon" width="256" />
</p>

<h1 align="center">Discord Addon</h1>
<p align="center">Created by QrystalGG</p>

<p align="center">
  A custom Meteor Client addon featuring seamless two-way Discord and Minecraft chat integration.
</p>

<p align="center">
  <a href="https://discord.gg/uXJTM6Q5Gp">Client Hub</a>
  •
  <a href="https://github.com/ItsQrystal">GitHub</a>
</p>

<hr />

# How to Use
1. Download the latest build JAR.
2. Put the `.jar` file in your `.minecraft/mods` folder with Meteor Client installed.
3. Ensure you are running Minecraft 1.21.1.
4. Open the Meteor ClickGUI in-game, navigate to the modules, and configure your settings.

---

## Modules

- [MinecraftToDiscord](#minecrafttodiscord)
- [DiscordToMinecraft](#discordtominecraft)

---

### MinecraftToDiscord
Bridges your in-game Minecraft chat directly into a Discord text channel using webhooks. Every message sent in-game gets instantly forwarded to your Discord server.

**How to Use & Configure:**
1. Create a Discord Webhook in your target Discord channel and copy its webhook URL.
2. Enable the module in-game and open its settings:
   - **webhook-url**: Paste your Discord Webhook URL here.
3. Chat normally in your Minecraft game, and the bot will forward your messages straight to Discord without any lag.

---

### DiscordToMinecraft
Bridges a Discord text channel directly into your game chat using JDA. Messages sent in the configured Discord channel will be sent as you in Minecraft chat.

**How to Use & Configure:**
1. Create a Discord Bot on the [Discord Developer Portal](https://discord.com/developers/applications).
2. Go to the **Bot** tab, turn **ON** the **Message Content Intent** under Privileged Gateway Intents, and copy your **Bot Token**.
3. Enable the module in-game and open its settings:
   - **bot-token**: Paste your Discord bot token here.
   - **channel-id**: Paste the target Discord text channel ID where the bot will listen.
4. Make sure your bot is invited to your Discord server with permissions to read message history and view channels.

<hr />

# Credits
- Made by QrystalGG
- Join [Client Hub](https://discord.gg/uXJTM6Q5Gp) for support.
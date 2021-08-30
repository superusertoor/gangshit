package de.toor.gangshit.events;

import de.toor.gangshit.handler.FileHandler;
import de.toor.gangshit.server.Server;
import de.toor.gangshit.server.ServerHandler;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotReadyEvent extends ListenerAdapter {

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        FileHandler.createFiles(event.getGuild());
        ServerHandler.addServer(new Server(event.getGuild()));
    }
}
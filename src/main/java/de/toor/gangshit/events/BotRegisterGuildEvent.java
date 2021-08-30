package de.toor.gangshit.events;

import de.toor.gangshit.handler.FileHandler;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotRegisterGuildEvent extends ListenerAdapter {

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        FileHandler.createFiles(event.getGuild());
    }


    @Override
    public void onGuildLeave(GuildLeaveEvent event) {
        FileHandler.deleteFiles(event.getGuild());
    }
}
package de.toor.gangshit.events.music;

import de.toor.gangshit.main.GangShit;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ListeningPartyJoinEvent extends ListenerAdapter {

    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
        if(GangShit.getListeningParty() != null) {
            if(event.getChannelJoined().getName().equalsIgnoreCase(GangShit.getListeningParty().getVoiceChannel().getName())) {
                System.out.println(event.getMember().getUser().getAsTag() + " hat die Party betreten");
            }
        }
    }
}
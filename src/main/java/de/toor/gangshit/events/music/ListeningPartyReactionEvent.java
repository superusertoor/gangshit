package de.toor.gangshit.events.music;

import de.toor.gangshit.main.GangShit;
import de.toor.gangshit.utils.music.ListeningParty;
import de.toor.gangshit.utils.music.PlaylistLoader;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ListeningPartyReactionEvent extends ListenerAdapter {

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if(event.getMember().getUser().isBot()) {
            return;
        }
        if (event.getReactionEmote().getName().equalsIgnoreCase("2️⃣")) {
            PlaylistLoader.loadPlayList("https://www.youtube.com/playlist?list=PLVWg-YzuVsMbR6WWI8bEI_D7wXAwTZQvN", event.getTextChannel(), event.getMember());
            ListeningParty p = GangShit.getListeningParty();
            GangShit.listeningParty = new ListeningParty(event.getMember());
            GangShit.listeningParty.setVoiceChannel(event.getMember().getVoiceState().getChannel());
            event.getTextChannel().sendMessage("two").queue();
            event.getReaction().removeReaction(event.getUser()).queue();
        }
    }
}
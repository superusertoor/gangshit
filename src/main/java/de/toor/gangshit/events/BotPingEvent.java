package de.toor.gangshit.events;

import de.toor.gangshit.main.GangShit;
import de.toor.gangshit.server.Server;
import de.toor.gangshit.server.ServerHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class BotPingEvent extends ListenerAdapter {


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        final Server server = ServerHandler.getServer(event.getGuild());
        final String pre = server.getPrefix();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.pink);
        embedBuilder.setAuthor(GangShit.getJda().getSelfUser().getName());
        embedBuilder.setDescription("> Befehle\n\n");
        embedBuilder.appendDescription(pre+"`play <track>` \nSpielt einen Track (Queue)\n\n");
        embedBuilder.appendDescription(pre+"`forceplay <track>` \nSpielt einen Track sofort\n\n");
        embedBuilder.appendDescription(pre+"`skip` \nSkippt einen Track\n\n");
        embedBuilder.appendDescription(pre+"`stop` \nStoppt die Musik\n\n");
        embedBuilder.appendDescription(pre+"`loop` \nLoopt die Queue\n\n");
        embedBuilder.appendDescription(pre+"`party` \nStartet eine Party\n\n");
        embedBuilder.appendDescription(pre+"`dj <playlist>` \nFügt dich als DJ hinzu\n\n");
        embedBuilder.appendDescription(pre+"`dj playlist <playlist>` \nAktualisiert deine Playlist\n\n");
        embedBuilder.appendDescription(pre+"`dj` \nHebt deinen Status als DJ auf\n\n");
        embedBuilder.appendDescription(pre+"`prefix <prefix>` \nLege einen Prefix für alle Befehle fest\n\n");
        embedBuilder.appendDescription(pre+"`av @name` \nZeigt den Avatar an\n\n");
        embedBuilder.appendDescription(pre+"`clear (i)` \nLöscht eine bestimmte Anzahl an Nachrichten\n\n");
        embedBuilder.appendDescription(pre+"`info @name` \nZeigt Informationen über einen User an\n\n");

        if(event.getMessage().getContentRaw().equalsIgnoreCase("<@!865715780057956412>")) {
            event.getTextChannel().sendMessage(embedBuilder.build()).queue();
        }
    }
}
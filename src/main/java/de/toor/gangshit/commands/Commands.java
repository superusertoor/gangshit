package de.toor.gangshit.commands;

import de.toor.gangshit.objects.SetupHandler;
import de.toor.gangshit.objects.UserInfo;
import de.toor.gangshit.server.Server;
import de.toor.gangshit.server.ServerHandler;
import de.toor.gangshit.utils.MessageHistoryRetriever;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class Commands extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        final String display = event.getMessage().getContentDisplay();
        final Message message = event.getMessage();
        final Server server = ServerHandler.getServer(event.getGuild());
        final TextChannel textChannel = event.getTextChannel();

        if(display.equalsIgnoreCase(".stats setup")) {
            SetupHandler handler = ServerHandler.getServer(event.getGuild()).getSetupHandler();
            if(!handler.isStatsSetup()) {
                handler.createServerStats();
            }
            return;
        }

        if(display.equalsIgnoreCase(".info")) {
            new UserInfo(event.getMember()).send(textChannel);
            return;
        }

        if(display.startsWith(".info ")) {
            if(message.getMentionedMembers().size() > 0) {
                Member target = message.getMentionedMembers().get(0);
                new UserInfo(target).send(textChannel);
            }
            return;
        }

        if(display.equalsIgnoreCase(server.getPrefix()+"av")) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setColor(Color.pink);
            embedBuilder.setAuthor(event.getMember().getUser().getAsTag() + "'s Avatar");
            embedBuilder.setImage(event.getMember().getUser().getAvatarUrl() + "?size=512");
            event.getTextChannel().sendMessage(embedBuilder.build()).queue();
            return;
        }

        if(display.startsWith(server.getPrefix()+"clear ")) {
            int i = Integer.parseInt(display.split(".clear ")[1]) +1;
            MessageHistoryRetriever.retrieveMessages(event.getTextChannel(), i).forEach(msg -> {
                msg.delete().queue();
            });
            return;
        }

        if(display.startsWith(server.getPrefix()+"av ")) {
            if (message.getMentionedMembers().size() == 1) {
                final Member target = message.getMentionedMembers().get(0);
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setColor(Color.pink);
                embedBuilder.setAuthor(target.getUser().getAsTag() + "'s Avatar");
                embedBuilder.setImage(target.getUser().getAvatarUrl() + "?size=512");
                event.getTextChannel().sendMessage(embedBuilder.build()).queue();
            }
        }
    }
}
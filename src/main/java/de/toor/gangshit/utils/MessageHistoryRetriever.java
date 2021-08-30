package de.toor.gangshit.utils;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class MessageHistoryRetriever {

    public static List<Message> retrieveMessages(TextChannel textChannel, int i) {
        MessageHistory history = new MessageHistory(textChannel);
        new MessageHistory(textChannel).retrievePast(100).complete();
        List<Message> msgs;
        msgs = history.retrievePast(i).complete();
        return msgs;
    }
}

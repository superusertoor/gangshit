package de.toor.gangshit.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import de.toor.gangshit.exceptions.NoDJsFoundException;
import de.toor.gangshit.exceptions.TooManyDJsException;
import de.toor.gangshit.handler.MusicHandler;
import de.toor.gangshit.main.GangShit;
import de.toor.gangshit.server.Server;
import de.toor.gangshit.server.ServerHandler;
import de.toor.gangshit.utils.music.audio.AudioLoadResult;
import de.toor.gangshit.utils.music.audio.MusicController;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import java.awt.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MusicCommands extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        final Message message = event.getMessage();
        final String display = message.getContentDisplay();
        final Guild guild = event.getGuild();
        final Server server = ServerHandler.getServer(event.getGuild());

        if(display.startsWith(server.getPrefix()+"dj playlist ")) {
            if (MusicHandler.isDj(event.getMember())) {
                final String playList = display.split(".dj playlist ")[1];
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setColor(Color.PINK);
                embedBuilder.setDescription("> ✅ Deine Playlist wurde aktualisiert");
                embedBuilder.appendDescription("\n\n\uD83C\uDFB5 Neue Playlist: `" + playList + "`");
                event.getTextChannel().sendMessage(embedBuilder.build()).queue();
            }else {
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setColor(Color.RED);
                embedBuilder.setDescription("> ❌ Du bist kein DJ auf `" + event.getGuild().getName() + "`");
                event.getTextChannel().sendMessage(embedBuilder.build()).queue();
            }
            return;
        }

        if(display.startsWith(server.getPrefix()+"dj ")) {
            try {
                final String playList = display.split(".dj ")[1];
                MusicHandler.addDj(event.getMember(),playList);
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setColor(Color.PINK);
                embedBuilder.setDescription("> ✅ Du bist nun ein DJ auf `" + guild.getName() + "`");
                embedBuilder.appendDescription("\n\n\uD83C\uDFB5 Deine Playlist: `" + playList + "`");
                event.getTextChannel().sendMessage(embedBuilder.build()).queue();
            } catch (TooManyDJsException e) {
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setColor(Color.RED);
                embedBuilder.setDescription("> ❌ " + e.getMessage());
                event.getTextChannel().sendMessage(embedBuilder.build()).queue();
            }
            return;
        }

        if(display.equalsIgnoreCase(server.getPrefix()+"dj")) {
            if(MusicHandler.isDj(event.getMember())) {
                MusicHandler.removeDj(event.getMember());
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setColor(Color.PINK);
                embedBuilder.setDescription("> ✅ Dein Status als DJ ist hiermit aufgehoben!");
                event.getTextChannel().sendMessage(embedBuilder.build()).queue();
            }
        }

        if(display.equalsIgnoreCase(server.getPrefix()+"party")) {
            List<Member> membersList;
            try {
                membersList = MusicHandler.getAvailableDJs(event.getGuild());
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setAuthor("gangshit");
                embedBuilder.setDescription("> Party - wähle eine Playlist aus\n\n");
                embedBuilder.setColor(Color.PINK);
                AtomicInteger i = new AtomicInteger();
                membersList.forEach(dj -> {
                    i.getAndIncrement();
                    embedBuilder.appendDescription(i + " " + dj.getAsMention());
                });
                Message msg = event.getTextChannel().sendMessage(embedBuilder.build()).complete();
            } catch (NoDJsFoundException e) {
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setColor(Color.RED);
                embedBuilder.setDescription("> ❌ " + e.getMessage());
                event.getTextChannel().sendMessage(embedBuilder.build()).queue();
            }
            return;
        }

        if(display.startsWith(server.getPrefix()+"play ")) {
            if(event.getMember().getVoiceState().inVoiceChannel()) {
                MusicController controller = GangShit.playerManager.getController(guild);
                AudioPlayerManager manager = GangShit.audioPlayerManager;
                AudioManager audioManager = guild.getAudioManager();
                audioManager.openAudioConnection(event.getMember().getVoiceState().getChannel());
                StringBuilder sb = new StringBuilder();
                String[] args = display.split(" ");
                for(int i = 1; i < args.length; i++) {
                    sb.append(args[i] + " ");
                }
                String url = sb.toString().trim();
                manager.loadItemOrdered(url, url, new AudioLoadResult(controller, event.getTextChannel(), event.getMember(), url));
            }else {
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setColor(Color.RED);
                embedBuilder.setDescription("> ❌ Du bist in keinem VoiceChannel");
                event.getTextChannel().sendMessage(embedBuilder.build()).queue();
            }
        }

        if(display.equalsIgnoreCase(server.getPrefix()+"skip")) {

        }

        if(display.equalsIgnoreCase(server.getPrefix()+"stop")) {

            event.getGuild().getAudioManager().closeAudioConnection();
        }
    }
}
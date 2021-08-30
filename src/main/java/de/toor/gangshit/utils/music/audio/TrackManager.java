package de.toor.gangshit.utils.music.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;

import java.awt.*;
import java.util.List;
import java.util.Queue;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class TrackManager extends AudioEventAdapter {

    private final AudioPlayer player;
    private final Queue<AudioInfo> queue;
    private TextChannel textChannel;

    public TrackManager(AudioPlayer player) {
        this.player = player;
        this.queue = new LinkedBlockingQueue<>();
    }

    public void queue(AudioTrack audioTrack, Member author, TextChannel textChannel) {
        AudioInfo info = new AudioInfo(audioTrack, author);
        this.textChannel = textChannel;
        queue.add(info);
        if (player.getPlayingTrack() == null) {
            System.out.println("null, playing");
            player.playTrack(audioTrack);
        }
    }

    @Override
    public void onTrackStart(AudioPlayer player, AudioTrack track) {
        System.out.println("track is starting...");
        AudioInfo info = queue.element();
        VoiceChannel vChan = info.getAuthor().getVoiceState().getChannel();
        if (vChan == null) {
            player.stopTrack();
        } else {
            info.getAuthor().getGuild().getAudioManager().openAudioConnection(vChan);
        }
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        try {
            Guild g = queue.poll().getAuthor().getGuild();
            if (queue.isEmpty()) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        EmbedBuilder embedBuilder = new EmbedBuilder();
                        embedBuilder.setDescription("> Der Musikbot hat den Channel verlassen");
                        embedBuilder.setColor(Color.RED);
                        textChannel.sendMessage(embedBuilder.build()).queue();
                        g.getAudioManager().closeAudioConnection();
                    }
                }, 5000);
            } else {
                player.playTrack(queue.element().getTrack());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void shuffleQueue() {
        List<AudioInfo> tQueue = new ArrayList<>(getQueuedTracks());
        AudioInfo current = tQueue.get(0);
        tQueue.remove(0);
        Collections.shuffle(tQueue);
        tQueue.add(0, current);
        purgeQueue();
        queue.addAll(tQueue);
    }

    public Set<AudioInfo> getQueuedTracks() {
        return new LinkedHashSet<>(queue);
    }

    public void purgeQueue() {
        queue.clear();
    }

    public AudioInfo getTrackInfo(AudioTrack track) {
        return queue.stream().filter(audioInfo -> audioInfo.getTrack().equals(track)).findFirst().orElse(null);
    }
}

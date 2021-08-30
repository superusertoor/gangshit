package de.toor.gangshit.utils.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import de.toor.gangshit.main.GangShit;
import de.toor.gangshit.utils.music.audio.AudioLoadResult;
import de.toor.gangshit.utils.music.audio.MusicController;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class PlaylistLoader {

    public static void loadPlayList(String s, TextChannel textChannel, Member member) {
        MusicController controller = GangShit.playerManager.getController(textChannel.getGuild());
        AudioPlayerManager manager = GangShit.audioPlayerManager;
        AudioManager audioManager = textChannel.getGuild().getAudioManager();
        audioManager.openAudioConnection(member.getVoiceState().getChannel());
        manager.loadItemOrdered(s, s, new AudioLoadResult(controller, textChannel, member, s));
    }
}
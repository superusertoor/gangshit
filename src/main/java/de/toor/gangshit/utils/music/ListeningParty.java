package de.toor.gangshit.utils.music;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;

import java.util.ArrayList;
import java.util.List;

public class ListeningParty {

    private VoiceChannel voiceChannel;
    private List<Member> members = new ArrayList<>();
    private Member creator;

    public ListeningParty(Member creator) {
        this.creator = creator;
    }

    public void setVoiceChannel(VoiceChannel voiceChannel) {
        this.voiceChannel = voiceChannel;
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void removeMember(Member member) {
        members.remove(member);
    }

    public VoiceChannel getVoiceChannel() {
        return voiceChannel;
    }

    public List<Member> getMembers() {
        return members;
    }

    public Member getCreator() {
        return creator;
    }
}
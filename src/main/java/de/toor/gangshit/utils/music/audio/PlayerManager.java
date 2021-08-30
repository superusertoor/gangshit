package de.toor.gangshit.utils.music.audio;

import net.dv8tion.jda.api.entities.Guild;

import java.util.concurrent.ConcurrentHashMap;

public class PlayerManager {

    public ConcurrentHashMap<Guild, MusicController> controllers = new ConcurrentHashMap<>();

    public PlayerManager() {

    }

    public MusicController getController(Guild guild) {
        if(controllers.containsKey(guild)) {
            return controllers.get(guild);
        }else {
            MusicController mc = new MusicController(guild);
            this.controllers.put(guild, mc);
            return mc;
        }
    }
}

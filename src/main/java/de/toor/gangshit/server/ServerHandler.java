package de.toor.gangshit.server;

import net.dv8tion.jda.api.entities.Guild;

import java.util.HashMap;

public class ServerHandler {

    private static HashMap<Long, Server> servers = new HashMap<>();

    public static Server getServer(Guild guild) {
        return servers.get(guild.getIdLong());
    }

    public static void addServer(Server server){
        servers.put(server.getGuild().getIdLong(), server);
    }

    public static void removeServer(Server server){
        servers.remove(server.getGuild().getIdLong());
    }

    public static HashMap<Long, Server> getServers() {
        return servers;
    }
}
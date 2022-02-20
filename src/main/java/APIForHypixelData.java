import net.hypixel.api.apache.ApacheHttpClient;
import net.hypixel.api.http.HypixelHttpClient;
import net.hypixel.api.reply.PlayerReply;
import net.hypixel.api.reply.StatusReply;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class APIForHypixelData {
    // API Key (from Hypixel /api new)
    final private static String KEY = "be0e1c79-1314-48bf-950b-cda383c93ee5";
    // UUID of Eggrollean so it can get the right player from the Hypixel API
    final private static UUID Eggrollean = UUID.fromString("09be77df-bb8a-4325-aaf9-29e4c5da8955");
    public APIForHypixelData() {

    }

    public static String formattedInfo(String user, UUID uuid) throws ExecutionException, InterruptedException {

        String raw = getInfo(uuid);
        String returnInfo = "";

        String online = raw.substring(raw.indexOf("online=") + 7, raw.indexOf("serverType=") - 2);
        String game = raw.substring(raw.indexOf("serverType=") + 11, raw.indexOf("mode=") - 2);
        String location = raw.substring(raw.indexOf("mode=") + 5, raw.indexOf("map=") - 2);

        if (online.equals("true")) {
            returnInfo += user  + " is online";
        } else {
            return user + " is offline.";
        }

        if (game.equals("SKYBLOCK")) {
            returnInfo += ", is playing Skyblock";
        } else if (game.equals("SKYWARS")) {
            returnInfo += ", is playing Skywars";
        } else if (game.equals("DUELS")) {
            returnInfo += ", is playing Duels";
        }
        System.out.println(location);
        if (location.equals("LOBBY")) {
            returnInfo += ", and is in the Lobby";
        } else if (location.equals("dynamic")) {
            returnInfo += ", and is on their Private Island";
        } else if (location.equals("hub")) {
            returnInfo += ", and is in the Hub";
        } else if (location.equals("dungeon")) {
            returnInfo += ", and is doing Dungeons";
        } else if (location.equals("foraging_1")) {
            returnInfo += ", and is in the Park";
        } else if (location.equals("combat_1")) {
            returnInfo += ", and is in the Spider's Den";
        } else if (location.equals("combat_2")) {
            returnInfo += ", and is in the Blazing Fortress";
        } else if (location.equals("combat_3")) {
            returnInfo += ", and is in the End";
        } else if (location.equals("mining_1")) {
            returnInfo += ", and is in the Gold Mines";
        } else if (location.equals("mining_2")) {
            returnInfo += ", and is in the Deep Caverns";
        } else if (location.equals("mining_3")) {
            returnInfo += ", and is in the Dwarven Mines";
        } else if (location.equals("crystal_hollows")) {
            returnInfo += ", and is in the Crystal Hollows";
        } else if (location.equals("farming_1")) {
            returnInfo += ", and is in The Barn or Mushroom Desert";
        } else {
            returnInfo += ", and I don't know where they are";
        }

        return returnInfo;
    }

    public static String getInfo(UUID uuid) throws InterruptedException, ExecutionException {
        //Use Hypixel API here
        HypixelHttpClient client = new ApacheHttpClient(UUID.fromString(KEY));
        net.hypixel.api.HypixelAPI hypixelAPI = new net.hypixel.api.HypixelAPI(client);


        // Moar info
//        CompletableFuture<SkyBlockProfilesReply> skyblockReplies = new CompletableFuture<SkyBlockProfilesReply>();
//        CompletableFuture<SkyBlockProfileReply> skyblockReply = new CompletableFuture<SkyBlockProfileReply>();
//        CompletableFuture<RecentGamesReply> recent = new CompletableFuture<RecentGamesReply>();

//        CompletableFuture<PlayerReply> apiReply;
        PlayerReply apiReply = new PlayerReply();

        // Location info (Am I on my island or in the hub)
//        CompletableFuture<StatusReply> status;
        StatusReply status = new StatusReply();



//        Map<UUID, Pair<CompletableFuture<PlayerReply>, CompletableFuture<StatusReply>>> replies;

//        for (int i = 0; i < 40; ++i) {
//            apiReply = hypixelAPI.getPlayerByUuid(uuid);
//            status = hypixelAPI.getStatus(uuid);
//            apiReply = hypixelAPI.getPlayerByUuid(uuid[i]);
//            status = hypixelAPI.getStatus(uuid[i]);
//            replies.put(uuid[i], Pair<apiReply, status);
//        }

        try {
            apiReply = hypixelAPI.getPlayerByUuid(uuid).get();
//            apiReply = hypixelAPI.getPlayerByUuid(uuid);
//            skyblockReplies = hypixelAPI.getSkyBlockProfiles(Eggrollean);
//            skyblockReply = hypixelAPI.getSkyBlockProfile("09be77dfbb8a4325aaf929e4c5da8955");
//            status = hypixelAPI.getStatus(uuid);
            status = hypixelAPI.getStatus(uuid).get();


            // Doesn't get skyblock info
//            recent = hypixelAPI.getRecentGames(Eggrollean);
        } catch (ExecutionException e) {
            System.err.println(e);
        } catch (InterruptedException e) {
            System.err.println(e);
        } finally {
            // After fetching api player data, close the hypixelAPI object
            hypixelAPI.shutdown();
        }

//        while (!apiReply.isDone() || !status.isDone()) {
//            Thread.sleep(10);
//        }

        PlayerReply.Player player = apiReply/*.get()*/.getPlayer();
        if (!player.exists()) {
            System.err.println("Player not found!");

            hypixelAPI.shutdown();
            return "";
        }

//        System.out.println(status/*.get()*/.toString());
        return status/*.get()*/.toString();
    }
}

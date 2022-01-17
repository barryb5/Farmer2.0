import discord4j.common.store.action.read.GetChannelByIdAction;
import discord4j.common.store.action.read.GetChannelsAction;
import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.MessageCreateFields;
import discord4j.rest.entity.RestChannel;
import net.hypixel.api.HypixelAPI;
import net.hypixel.api.apache.ApacheHttpClient;
import net.hypixel.api.http.HypixelHttpClient;
import net.hypixel.api.reply.PlayerReply;
import net.hypixel.api.reply.StatusReply;
import net.hypixel.api.reply.skyblock.SkyBlockProfilesReply;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class Bot {

    // API Key (from Hypixel /api new)
    final private static String KEY = "be0e1c79-1314-48bf-950b-cda383c93ee5";
    // UUID of Eggrollean so it can get the right player from the Hypixel API
    final private static UUID Eggrollean = UUID.fromString("09be77df-bb8a-4325-aaf9-29e4c5da8955");

    private static String finalUser;
    private static DiscordClient client;
    private static GatewayDiscordClient gateway;
    private RestChannel general;

    public Bot() {
        String token = "";
        String user = "";

        File configFile = new File("token.config");
        try {
            FileReader reader = new FileReader(configFile);
            Properties properties = new Properties();
            properties.load(reader);
            token = properties.getProperty("token");
            user = properties.getProperty("user");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch(NumberFormatException ex){
            ex.printStackTrace();
        }

        finalUser = user;
        client = DiscordClient.create(token);
        gateway = client.login().block();

        Snowflake generalSnowflake = Snowflake.of(861411496269709355L);
        general = client.getChannelById(generalSnowflake);
    }

    public static void main(String[] args) {
        Random random = new Random();

        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            String stringMessage = message.getContent();
            System.out.println(stringMessage);

            if (message.getContent().length() > 4 &&"!ping".equals(message.getContent())) {
                final MessageChannel channel = message.getChannel().block();
                channel.createMessage("Pong!").block();
            }

            if (message.getContent().equals("Dumb stupid bot get the snowflake id from this specific message and don't delete this message because it's necessary for finding the general channel")) {
                System.out.println(message.getId());
                System.out.println(message.getChannelId());
                System.out.println(message.getChannel());
            }

            if (message.getContent().length() > 4 && "!help".equals(message.getContent())) {
                final MessageChannel channel = message.getChannel().block();
                channel.createMessage("You can !ping, !ohhey, !dosomething, !roastme, and !help.").block();
                channel.createMessage("That's all I got").block();
            }

            if (message.getContent().length() > 5 && "!ohhey".equals(message.getContent())) {
                final MessageChannel channel = message.getChannel().block();
                int temp = random.nextInt(2);
                if (temp == 0) {
                    channel.createMessage("https://www.youtube.com/watch?v=yRfDdmd4cfQ").block();
                } else {
                    channel.createMessage("Oh hey, I was just boutta go to bed. I know we couldn't skype tonight, but that's alright. Goodnight girl, I'll see you tomorrow").block();
                }
                System.exit(0);
            }

            if (message.getContent().length() > 2 && "!f3".equals(message.getContent()) && message.getAuthor().toString().contains(finalUser)) {
                Robot robot = null;

                try {
                    robot = new Robot();
                } catch (AWTException e) {
                    e.printStackTrace();
                }

                robot.keyPress(KeyEvent.VK_F3);
            }

            if (message.getContent().length() > 2 && "!f1".equals(message.getContent()) && message.getAuthor().toString().contains(finalUser)) {
                Robot robot = null;

                try {
                    robot = new Robot();
                } catch (AWTException e) {
                    e.printStackTrace();
                }

                robot.keyPress(KeyEvent.VK_F1);
            }

            if (message.getContent().length() > 7 && "!unstuck".equals(message.getContent()) && message.getAuthor().toString().contains(finalUser)) {
                Robot robot = null;

                try {
                    robot = new Robot();
                } catch (AWTException e) {
                    e.printStackTrace();
                }

                robot.keyPress(KeyEvent.VK_SPACE);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                robot.keyRelease(KeyEvent.VK_SPACE);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                robot.keyPress(KeyEvent.VK_SPACE);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                robot.keyRelease(KeyEvent.VK_SPACE);

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                robot.keyPress(KeyEvent.VK_SPACE);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                robot.keyRelease(KeyEvent.VK_SPACE);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                robot.keyPress(KeyEvent.VK_SPACE);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                robot.keyRelease(KeyEvent.VK_SPACE);

                SafetyNet.resetTime();
            }

            if (message.getContent().length() > 10 && "!screenshot".equals(message.getContent()) && message.getAuthor().toString().contains(finalUser)) {
                final MessageChannel channel = message.getChannel().block();

                Robot robot = null;

                try {
                    robot = new Robot();
                } catch (AWTException e) {
                    e.printStackTrace();
                }

                Rectangle captureSize = new Rectangle(1920, 0, 1920, 1080);


                BufferedImage screenCapture = robot.createScreenCapture(captureSize);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                try {
                    ImageIO.write(screenCapture, "jpg", outputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
                channel.createMessage(messageCreateSpec ->
                {
                    messageCreateSpec.addFile("checkupScreenshot.jpg", inputStream);
                }).block();

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                BufferedImage secondScreenCapture = robot.createScreenCapture(captureSize);
                outputStream = new ByteArrayOutputStream();
                try {
                    ImageIO.write(secondScreenCapture, "jpg", outputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                InputStream anotherInputStream = new ByteArrayInputStream(outputStream.toByteArray());
                channel.createMessage(messageCreateSpec ->
                {
                    messageCreateSpec.addFile("checkupScreenshot.jpg", anotherInputStream);
                }).block();
            }

            if (message.getContent().length() > 11 && "!dosomething".equals(message.getContent())) {
                final MessageChannel channel = message.getChannel().block();
                channel.createMessage("no").block();
            }

            if (message.getContent().length() > 4 && "!info".equals(message.getContent())) {
                final MessageChannel channel = message.getChannel().block();
                try {
                    channel.createMessage(formattedInfo(finalUser, Eggrollean)).block();
                    UUID alt = UUID.fromString("33aa07ca-a902-4dd8-be19-505cf822212b");
                    channel.createMessage(formattedInfo("NotEggrolleanAlt", alt)).block();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            if (message.getContent().length() > 6 && "!roastme".equals(message.getContent())) {
                final MessageChannel channel = message.getChannel().block();
                int temp = random.nextInt(2);
                if (temp == 0) {
                    channel.createMessage("Insulting the disabled is against my programming").block();
                } else {
                    channel.createMessage("Go interact with a human").block();
                }
            }

            if (message.getContent().length() > 13 && "!specificInfo".equals(message.getContent().substring(0, 13))) {
                final MessageChannel channel = message.getChannel().block();

                //Split into username and uuid
                String name = "";
                UUID uuid = UUID.fromString("09be77df-bb8a-4325-aaf9-29e4c5da8955");


                if ("name=".equals(message.getContent().substring(14, 19))) {
                    name = message.getContent().substring(19, message.getContent().indexOf("uuid=") - 1);
                    uuid = UUID.fromString(message.getContent().substring(message.getContent().indexOf("uuid=") + 5, message.getContent().length()));

                    try {
                        channel.createMessage(formattedInfo(name, uuid)).block();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                } else {
                    channel.createMessage("Please format the message as: !specificInfo name=Username uuid=UUID").block();
                }
            }

            if (message.getContent().length() > 10 &&"!getChannel".equals(message.getContent())) {
                System.out.println(message.getChannelId());
                final MessageChannel channel = message.getChannel().block();
                channel.createMessage(message.getChannelId() + " is the channel").block();
            }

            if (message.getContent().length() > 12 && ("!emergencyoff".equals(message.getContent())) || "!emergencyOff".equals(message.getContent())) {
                final MessageChannel channel = message.getChannel().block();
                if (message.getAuthor().toString().contains(finalUser)) {
                    channel.createMessage("Shutting Down").block();
                    System.exit(0);
                } else {
                    channel.createMessage("Nice try, but this isn't " + finalUser).block();
                }
            }

        });

        gateway.onDisconnect().block();
    }

    public void sendMessage(String message) {
        client.withGateway((GatewayDiscordClient gateway) ->
                gateway.on(ReadyEvent.class, event -> {
                    general.createMessage(message).block();
                    return null;
                })).block();
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

    public static boolean speedInfo(UUID uuid) throws ExecutionException, InterruptedException {
        String raw = getInfo(uuid);

        String location = raw.substring(raw.indexOf("mode=") + 5, raw.indexOf("map=") - 2);

        if (location.equals("dynamic")) {
            return true;
        } else if (location.equals("hub")) {
            return false;
        }
        return false;
    }

    public static String getInfo(UUID uuid) throws InterruptedException, ExecutionException {
        //Use Hypixel API here


        HypixelHttpClient client = new ApacheHttpClient(UUID.fromString(KEY));
        HypixelAPI hypixelAPI = new HypixelAPI(client);


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

    public static void type(String message) throws AWTException, InterruptedException {
        int[] printValues = convertStringToASCII(message);
        Robot robot = new Robot();

        for (int j = 0; j < printValues.length; j++) {
            robot.keyPress(printValues[j]);
            Thread.sleep(20);
            robot.keyRelease(printValues[j]);
        }

        robot.keyPress(10);
        Thread.sleep(20);
        robot.keyRelease(10);
    }

    public static int[] convertStringToASCII(String string) {

        char[] stringChars = string.toCharArray();
        int[] returnValues = new int[string.length()];

        for (int i = 0; i < string.length(); i++) {

            if (stringChars[i] == '/') {
                returnValues[i] = 47;
            } else if (Character.isDigit(stringChars[i])) {
                returnValues[i] = stringChars[i] + 48;
            } else if (stringChars[i] == ' ') {
                returnValues[i] = 32;
            } else {
                returnValues[i] = stringChars[i] - 32;
            }

            if (Character.isUpperCase(stringChars[i])) {
                returnValues[i] = returnValues[i] + 32;
            }


        }

        return returnValues;
    }
}

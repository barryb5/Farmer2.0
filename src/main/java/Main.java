import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.apache.commons.io.input.ReversedLinesFileReader;
//import org.apache.xmlgraphics.image.loader.ImageInfo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.NoSuchFileException;
import java.sql.SQLOutput;
import java.util.UUID;
import java.util.concurrent.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Main {

    public static void main(String[] args) throws AWTException, IOException, InterruptedException, NoSuchFileException, ExecutionException, UnsupportedFlavorException {
        Thread botThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Bot Send thread running");
                Bot bot = new Bot();
                bot.interactiveBot();
            }
        });

        Thread botSendThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Bot thread running");
                Bot bot = new Bot();
                try {
                    bot.sendUpdates();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread safetyNetThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Safety Net thread running");

                SafetyNet safety = new SafetyNet();

                try {
                    safety.safeMacro();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (AWTException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedFlavorException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread stopAfterTimeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String time = java.time.LocalTime.now().toString();
                    System.out.println("Time: " + time);
                    if (time.substring(0, 5).equals("04:28")) {
                        System.out.println("Done now");
                        System.exit(0);

                        Runtime runtime = Runtime.getRuntime();
                        try
                        {
                            System.out.println("Shutting down the PC after 5 seconds.");
                            runtime.exec("shutdown -s -t 5");
                        }
                        catch(IOException e)
                        {
                            System.out.println("Exception: " +e);
                        }
                    }


                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread farmerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Farmer thread running");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Farmer farmer = null;

                while (true) {
                    try {
                        farmer = new Farmer();
//                    farmer.alchemy();
                        farmer.netherwart();
//                    farmer.sugarcane();
//                    farmer.pumpkin();
//                    farmer.forage();
//                    farmver.goblino();
//                    farmer.moveMouse(180, 0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (AWTException e) {
                        e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (UnsupportedFlavorException e) {
//                    e.printStackTrace();
                    }
                }


            }
        });
//        File file = new File("/home/barry/projects/java/Farmer2.0/src/main/resources/StoredData.xls");

//        Thread.sleep(4000);
//        Robot robot = new Robot();
//        robot.mouseMove(0, 0);
//        Thread.sleep(4000);
//        robot.mouseMove(400, 300);



        farmerThread.start();
        safetyNetThread.start();
        botThread.start();
//        botSendThread.start();
        stopAfterTimeThread.start();
/*
        Robot robot = new Robot();
        String format = "jpg";
        String fileName = "f3Info." + format;


        Rectangle captureSize = new Rectangle(1920, 66, 1920, 975);

        BufferedImage test = robot.createScreenCapture(captureSize);
        ImageIO.write(test, format, new File("src/main/resources/" + fileName));
*/
/*
        File imageFile = new File("src/main/resources/test.png");

//        ImageInfo origInfo = new ImageInfo("src/main/resources/test.png");
//        MagickImage image = new MagickImage(origInfo);

        System.out.println(imageFile.canRead());

        Tesseract instance = new Tesseract();
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        instance.setDatapath(tessDataFolder.getAbsolutePath());

        try {
//            instance.setDatapath("src/main/resources/tessdata");
            String result = instance.doOCR(imageFile, new Rectangle(1200, 200));
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
//            System.out.println("Error while reading image");
        }

//        Thread.sleep(10000);
//        File image = new File("C:\\home\\barry\\projects\\java\\Farmer2.0\\f3Info.jpg");
*/
    }
}
/*

        String KEY = "594c82b7-2057-4478-b0a2-90a6a654aab4";
        UUID Eggrollean = UUID.fromString("09be77df-bb8a-4325-aaf9-29e4c5da8955");



        HypixelHttpClient client = new ApacheHttpClient(UUID.fromString(KEY));
        HypixelAPI hypixelAPI = new HypixelAPI(client);


//        hypixelAPI.getPlayerByUuid("Eggrollean")
//                .exceptionally(throwable -> {
//                    // Handle exceptions here
//                    throwable.printStackTrace();
//                    return null;
//                })
//                .thenAccept(System.out::println);

        PlayerReply apiReply = new PlayerReply();

        // Moar info
//        SkyBlockProfilesReply skyblockReplies = new SkyBlockProfilesReply();
//        CompletableFuture<SkyBlockProfileReply> skyblockReply = new CompletableFuture<SkyBlockProfileReply>();
//        CompletableFuture<RecentGamesReply> recent = new CompletableFuture<RecentGamesReply>();

        // Big money here
        StatusReply status = new StatusReply();

        try {
            apiReply = hypixelAPI.getPlayerByUuid(Eggrollean).get();
//            skyblockReplies = hypixelAPI.getSkyBlockProfiles(Eggrollean).get();
//            skyblockReply = hypixelAPI.getSkyBlockProfile("09be77dfbb8a4325aaf929e4c5da8955");
            status = hypixelAPI.getStatus(Eggrollean).get();

            // Doesn't get skyblock info
//            recent = hypixelAPI.getRecentGames(Eggrollean);
        } catch (ExecutionException e) {
            System.err.println(e);
            e.getCause().printStackTrace();
        } catch (InterruptedException e) {
            // Shouldn't happen under normal circumstances.
            System.err.println("Oh no, the player fetch thread was interrupted!");
            e.printStackTrace();
            Thread.currentThread().interrupt();
            return;

        } finally {
            // After fetching api player data, close the hypixelAPI object
            hypixelAPI.shutdown();
        }

        PlayerReply.Player player = apiReply.getPlayer();
        if (!player.exists()) {
            System.err.println("Player not found!");

            hypixelAPI.shutdown();
            return;
        }

//        System.out.println(recent.get().toString());
        System.out.println(status.toString());
//        System.out.println("Skyblock Lime profile " + skyblockReplies.toString());
//        System.out.println("Skyblock Lime profile " + skyblockReply.get().toString());
//
//        System.out.println("Here are some of \"" + player.getName() + "\"'s stats!");
//        System.out.println();
//        System.out.println("UUID ----------> " + player.getUuid());
//        System.out.println("Rank ----------> " + player.getHighestRank());
//        System.out.println("On Build Team? > " + player.isOnBuildTeam());
//        System.out.println("Exact Level ---> " + player.getNetworkLevel());
//        System.out.println("Experience ----> " + player.getNetworkExp());
//        System.out.println("Karma ---------> " + player.getKarma());
//        System.out.println("MC Version ----> " + player.getLastKnownMinecraftVersion());
//        System.out.println("Last Game Type > " + player.getMostRecentGameType());
//        System.out.println("");
//        System.out.println("Raw JSON ------> " + player.getRaw());

*/
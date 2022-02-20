import org.apache.commons.io.input.ReversedLinesFileReader;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.security.Key;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class SafetyNet {
    private UUID Eggrollean = UUID.fromString("09be77df-bb8a-4325-aaf9-29e4c5da8955");
    private static int time;
    private String recentLog;
    private static boolean running = true;

    public SafetyNet() {
        recentLog = "";
        running = true;
        Bot.add("Starting");

    }

    public static boolean isRunning() {
        return running;
    }

    public void safeMacro() throws ExecutionException, InterruptedException, AWTException, IOException {
        String raw = "";
        while (true) {
            raw = APIForHypixelData.getInfo(Eggrollean);

            checkIsland(raw);
            checkIfSomeoneJoinedIslandRecently();
            accusedWhileAFKing();
//            checkMouseMoved();

            Thread.sleep(10000);
        }
    }

    public void manualCheckIfStuck() {
        if (time == 110) {
            Bot.add("Check if it's stuck");
        }
    }

    public static void resetTime() {
        time = 0;
    }


    public void checkIsland(String data) throws ExecutionException, InterruptedException, AWTException {

        String location = data.substring(data.indexOf("mode=") + 5, data.indexOf("map=") - 2);

        if (!location.equals("dynamic")) {
            System.out.println("We got warped here: " + location);
            panicLevel2();
        } else {
            System.out.println("Still on island");
        }
    }

//    public void checkMouseMoved() {
//        int currentX = (int) MouseInfo.getPointerInfo().getLocation().getY();
//        int currentY = (int) MouseInfo.getPointerInfo().getLocation().getY();
//
//        if (currentX != x || currentY != y) {
//            // Mouse got moved
//            Bot.add("Mouse got moved");
//            panicLevel1();
//        }
//    }

    public void checkIfSomeoneJoinedIslandRecently() throws IOException, InterruptedException, AWTException {
        // Check latest.log in skyclient logs to see if anyone not recognized joins island

//        File file = new File("/home/barry/.minecraft/skyclient/logs/latest.log");
        File file = new File("/home/barry/.lunarclient/logs/launcher/renderer.log");
        ReversedLinesFileReader fr = new ReversedLinesFileReader(file);
        fr.readLine();
        String latestLine = fr.readLine();
        // Is a chat message
        if (latestLine.contains("[CHAT]")) {
            // Checks if someone is visiting
            Bot.add(latestLine);
            if (latestLine.contains("is visiting") && latestLine.contains("Your Island")) {
                if (!latestLine.contains("NotEggrolleanAlt")) { // && !latestLine.contains("player_k12")) {
                    String[] extractParts = latestLine.split( " ");
                    Bot.add(extractParts[5].substring(4, extractParts[5].length() - 4) + " is visiting your island");
                    System.out.println(extractParts[5].substring(4, extractParts[5].length() - 4) + " is visiting your island");
                    panicLevel1();
                }
            }
            // Checks if tp padded is visiting
            else if (latestLine.contains("Warped from the") && latestLine.contains("End Teleport Pad") && latestLine.contains("Start Teleport Pad")) {
                System.out.println("Attempting unstuck");
                Bot.add(latestLine);
                Farmer.unstuck();
            }

            else if (latestLine.contains("This server will restart soon") && latestLine.contains("[Important]")) {
                System.out.println("Server Closing");
                Bot.add(latestLine);
                panicLevel1();
            }

            else if (latestLine.contains("You have ") && latestLine.contains("60 seconds") && latestLine.contains("to warp out!") && latestLine.contains("to warp now!")) {
                System.out.println("Server Closing");
                Bot.add(latestLine);
                panicLevel1();
            }

            else if (latestLine.contains("You fell into the void") || latestLine.contains("You died")) {
                System.out.println("Server Closing");
                Bot.add(latestLine);
                panicLevel1();
            }


        }
        if (!latestLine.equals(recentLog)) {
            recentLog = latestLine;
            System.out.println(recentLog + latestLine);
        }

    }

    public void accusedWhileAFKing() throws IOException, InterruptedException, AWTException {
//        File file = new File("/home/barry/.minecraft/skyclient/logs/latest.log");
        File file = new File("/home/barry/.lunarclient/logs/launcher/renderer.log");
        ReversedLinesFileReader fr = new ReversedLinesFileReader(file);

        for (int i = 0; i < 50; i++) {
            String latestLine = fr.readLine();

            if (latestLine.contains("[CHAT]")) {
                // Checks if someone is visiting
                if (latestLine.toLowerCase(Locale.ROOT).contains("eggrollean") || latestLine.toLowerCase(Locale.ROOT).contains("egg") || latestLine.toLowerCase(Locale.ROOT).contains("eggroll") || latestLine.toLowerCase(Locale.ROOT).contains("goblin")) {
                    Bot.add("Got mentioned: " + latestLine);
                    if (latestLine.toLowerCase(Locale.ROOT).contains("macro") || latestLine.toLowerCase(Locale.ROOT).contains("macroing") || latestLine.toLowerCase(Locale.ROOT).contains("afk") || latestLine.toLowerCase(Locale.ROOT).contains("farm")) {
                        Bot.add("Panic reason: " + latestLine);
                        panicLevel3();
                    }
                }
            }
        }
    }

    public void getLocationWithF3() throws AWTException, InterruptedException {
        Robot robot = new Robot();

        // Copy location to the clipboard
        robot.keyPress(KeyEvent.VK_F3);
        Thread.sleep(100);
        robot.keyPress(KeyEvent.VK_C);
        Thread.sleep(100);
        robot.keyRelease(KeyEvent.VK_F3);
        Thread.sleep(100);
        robot.keyRelease(KeyEvent.VK_C);

        //Extract location
        
    }

    public void panicLevel1() {
        System.out.println("Stop macro");
        running = false;
//        System.exit(0);
    }

    public void panicLevel2() throws InterruptedException, AWTException {
        System.out.println("Go to island and stop");
        Bot.type("g/is");
        running = false;

//        System.exit(0);
    }


    public void panicLevel3() throws AWTException, InterruptedException {
        // Log out
        System.out.println("End Minecraft");

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_F3);
        Thread.sleep(100);
        robot.keyPress(KeyEvent.VK_C);
        Thread.sleep(15000);
        robot.keyRelease(KeyEvent.VK_C);
        Thread.sleep(100);
        robot.keyRelease(KeyEvent.VK_F3);
    }
}

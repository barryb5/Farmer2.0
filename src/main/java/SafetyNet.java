import org.apache.commons.io.input.ReversedLinesFileReader;
import org.checkerframework.checker.units.qual.K;
import org.junit.experimental.theories.Theories;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
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
    private int previousX;
    private int previousZ;

    public SafetyNet() {
        recentLog = "";
        running = true;
        previousX = 0;
        previousZ = 0;
        Bot.add("Starting");

    }

    public static boolean isRunning() {
        return running;
    }

    public static void startRunning() {
        running = true;
    }

    public void safeMacro() throws ExecutionException, InterruptedException, AWTException, IOException, UnsupportedFlavorException {
        String raw = "";
        while (true) {
            raw = APIForHypixelData.getInfo(Eggrollean);

            checkIsland(raw);
            checkLogs();
            accusedWhileAFKing();
//            checkMouseMoved();
            Thread.sleep(1000);
            getLocationWithF3();

            Bot.add("Seems Good");

            Thread.sleep(9000);
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

            // If for some reason not running farmer while on island, start running farmer
            if (running != true) {
                running = true;
            }
        }
    }
//  Old check for if mouse moved, not very accurate and always reported changes too slight for minecraft to notice
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

    public void checkLogs() throws IOException, InterruptedException, AWTException {
        // Check latest.log in skyclient logs to see if anyone not recognized joins island

//        File file = new File("/home/barry/.minecraft/skyclient/logs/latest.log");
//        File file = new File("/home/barry/.lunarclient/logs/launcher/renderer.log");
        File file = new File("/home/barry/Downloads/projects-java-20220220T215900Z-001/Sandbox/logs/blclient/minecraft/latest.log");

        ReversedLinesFileReader fr = new ReversedLinesFileReader(file);
        fr.readLine();
        String latestLine = fr.readLine();
        // Is a chat message
        if (latestLine != null) {
            while (!latestLine.contains("[CHAT]") || latestLine.contains("Copied location to clipboard")) {
                latestLine = fr.readLine();
            }

            Bot.add(latestLine);
            if (latestLine.contains("is visiting") && latestLine.contains("Your Island")) {
                if (!latestLine.contains("NotEggrolleanAlt")) { // && !latestLine.contains("player_k12")) {
                    String[] extractParts = latestLine.split(" ");
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
            } else if (latestLine.contains("This server will restart soon") && latestLine.contains("[Important]")) {
                System.out.println("Server Closing");
                Bot.add(latestLine);
                panicLevel1();
            } else if (latestLine.contains("You have ") && latestLine.contains("60 seconds") && latestLine.contains("to warp out!") && latestLine.contains("to warp now!")) {
                System.out.println("Server Closing");
                Bot.add(latestLine);
                panicLevel1();
            } else if (latestLine.contains("You fell into the void") || latestLine.contains("You died")) {
                System.out.println("Server Closing");
                Bot.add(latestLine);
                panicLevel1();
            }


            if (!latestLine.equals(recentLog)) {
                recentLog = latestLine;
                System.out.println(recentLog + latestLine);
            }
        }

    }

    public void accusedWhileAFKing() throws IOException, InterruptedException, AWTException {
//        File file = new File("/home/barry/.minecraft/skyclient/logs/latest.log");
//        File file = new File("/home/barry/.lunarclient/logs/launcher/renderer.log");
        File file = new File("/home/barry/Downloads/projects-java-20220220T215900Z-001/Sandbox/logs/blclient/minecraft/latest.log");

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

    public void getLocationWithF3() throws AWTException, InterruptedException, IOException, UnsupportedFlavorException {
        Robot robot = new Robot();

        // Copy location to the clipboard
        robot.keyPress(KeyEvent.VK_F3);
        Thread.sleep(50);
        robot.keyPress(KeyEvent.VK_C);
        Thread.sleep(100);
        robot.keyRelease(KeyEvent.VK_C);
        Thread.sleep(50);
        robot.keyRelease(KeyEvent.VK_F3);

        //Extract data from clipboard
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        String result = (String) clipboard.getData(DataFlavor.stringFlavor);
        System.out.println("String from Clipboard:" + result);

//        String result = Farmer.getLocation();
        // /execute in minecraft:overworld run tp @s 53.28 35.00 -0.70 180.00 0.00
        // /execute in minecraft:overworld run tp @s (current location) --> 78.23 62.34 83.70   (looking in this direction) --> -164.55 24.00
        if (!result.contains("/execute in minecraft:")) {
            System.out.println("Didn't copy coords for some reason, try again");
            return;
        }

        String[] trimmed = result.substring(42, result.length()).split(" ");

        double[] location = {Double.parseDouble(trimmed[0]), Double.parseDouble(trimmed[1]), Double.parseDouble(trimmed[2])};

        double[] mouseXY = {Double.parseDouble(trimmed[3]), Double.parseDouble(trimmed[4])};

        System.out.println("The XYZ coords: X: " + location[0] + " Y: " + location[1] + " Z: " + location[2] + "\nThe mouse position: X: " + mouseXY[0] + " Y: " + mouseXY[1]);

        // If the Y value isn't a whole number, we can be pretty sure we are stuck in the ground again
        if (location[1] % 1.0 != 0 && Farmer.unstucking == false  ) {
            System.out.println(location[1] % 1.0);
            System.out.println("Stuck probably");
            Bot.add("Probably stuck in a teleporter or something");
            Farmer.unstuck();
        }
        if (result.contains("minecraft:the_end")) {
            // Probably in limbo
            Bot.type("/lobby skyblock");

            robot.keyPress(KeyEvent.VK_R);
            Thread.sleep(50);
            robot.keyRelease(KeyEvent.VK_R);
            Thread.sleep(50);
            robot.mousePress(KeyEvent.BUTTON3_DOWN_MASK);
            Thread.sleep(50);
            robot.mouseRelease(KeyEvent.BUTTON3_DOWN_MASK);
            Thread.sleep(50);
            robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(50);
            robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(4000);
            robot.keyPress(KeyEvent.VK_C);
            Thread.sleep(50);
            robot.keyRelease(KeyEvent.VK_C);
        }

//        // Compare to previous xz value, if it's the same then we know we are stuck
//        if (location[0] == previousX && location[2] == previousZ) {
//            System.out.println("Same position as 10m ago");
//            Bot.add("Probably stuck, check on farmer");
//        }
//
//
//        // Check the y value to see if we are still on one of the farming layers, if not, panic
//        if (location[1] != 45.00 || location[1] != 49.00 || location[1] != 53.00 || location[1] != 57.00) {
//            System.out.println("Not on a carrot level");
//            Bot.add("Not on a farming level");
//        }
    }

    public void panicLevel1() {
        System.out.println("Stop macro");
        running = false;
//        System.exit(0);
    }

    public void panicLevel2() throws InterruptedException, AWTException {
        running = false;
        Robot robot = new Robot();

//        Thread.sleep(80000);
        System.out.println("Go to island and stop");

        robot.keyPress(KeyEvent.VK_W);
        robot.keyPress(KeyEvent.VK_A);
        Thread.sleep(2000);
        robot.keyRelease(KeyEvent.VK_A);
        Thread.sleep(4000);
        robot.keyRelease(KeyEvent.VK_W);

        Bot.type("/is");

//        System.exit(0);
    }


    public void panicLevel3() throws AWTException, InterruptedException {
        // Log out
        System.out.println("End Minecraft");

        // Crashes Minecraft
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_F3);
        Thread.sleep(100);
        robot.keyPress(KeyEvent.VK_C);
        Thread.sleep(15000);
        robot.keyRelease(KeyEvent.VK_C);
        Thread.sleep(100);
        robot.keyRelease(KeyEvent.VK_F3);
    }

    public static void endCode() {
        System.exit(0);
    }
}

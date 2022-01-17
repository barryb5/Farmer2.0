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
    private static UUID Eggrollean = UUID.fromString("09be77df-bb8a-4325-aaf9-29e4c5da8955");
    private int x;
    private int y;
    private static int time;

    Robot robot = new Robot();


    public SafetyNet() throws AWTException {
        x = (int) MouseInfo.getPointerInfo().getLocation().getX();
        y = (int) MouseInfo.getPointerInfo().getLocation().getY();
    }

    public void safeMacro() throws ExecutionException, InterruptedException, AWTException, IOException {

        while (true) {
            String raw = "";

            raw = Bot.getInfo(Eggrollean);

            checkIsland(raw);
            checkIfSomeoneJoinedIslandRecently();
            checkMouseMoved();

            Thread.sleep(10000);
        }
    }

    public void manualCheckIfStuck() {
        if (time == 110) {
            Bot bot = new Bot();
            bot.sendMessage("Check if it's stuck");
        }
    }

    public static void resetTime() {
        time = 0;
    }


    public void checkIsland(String data) throws ExecutionException, InterruptedException, AWTException {

        String location = data.substring(data.indexOf("mode=") + 5, data.indexOf("map=") - 2);

        if (!location.equals("dynamic")) {
            panicLevel2();
        }
    }

    public void checkMouseMoved() {
        int currentX = (int) MouseInfo.getPointerInfo().getLocation().getY();
        int currentY = (int) MouseInfo.getPointerInfo().getLocation().getY();

        if (currentX != x || currentY != y) {
            // Mouse got moved
            Bot bot = new Bot();
            bot.sendMessage("Mouse got moved");
            panicLevel1();
        }
    }

    public void checkIfSomeoneJoinedIslandRecently() throws IOException, InterruptedException, AWTException {
        // Check latest.log in skyclient logs to see if anyone not recognized joins island

        File file = new File("/home/barry/.minecraft/skyclient/logs/latest.log");
        ReversedLinesFileReader fr = new ReversedLinesFileReader(file);
        String latestLine = fr.readLine();
        String lineBefore = fr.readLine();
        System.out.println(latestLine);
//        System.out.println(lineBefore);

        // Is a chat message
        if (latestLine.contains("[CHAT]")) {
            // Checks if someone is visiting
            if (latestLine.contains("§r§eis visiting §r§aYour Island§r§e!§r")) {
                if (!latestLine.contains("NotEggrolleanAlt")) { // && !latestLine.contains("player_k12")) {
                    String[] extractParts = latestLine.split( " ");
                    Bot bot = new Bot();
                    bot.sendMessage(extractParts[5].substring(4, extractParts[5].length() - 4) + " is visiting your island");
                    System.out.println(extractParts[5].substring(4, extractParts[5].length() - 4) + " is visiting your island");
                    panicLevel1();
                }
//            } else if (latestLine.contains("Warped from the §r§eF3 NW End Teleport Pad§r§a to the §r§2F1 NW Start Teleport Pad")) {
//                panicLevel1();
            }
        } else if (lineBefore.contains("[CHAT]")) {
            Bot bot = new Bot();

            // Checks if someone is visiting
            if (lineBefore.contains("§r§eis visiting §r§aYour Island§r§e!§r")) {
                if (!lineBefore.contains("NotEggrolleanAlt")) { // && !latestLine.contains("player_k12")) {
                    panicLevel1();
                    String[] extractParts = latestLine.split( " ");
                    bot.sendMessage(extractParts[5].substring(4, extractParts[5].length() - 4) + " is visiting your island");
                    System.out.println(extractParts[5].substring(4, extractParts[5].length() - 4) + " is visiting your island");
                }
            } else if (latestLine.contains("Warped from the") && latestLine.contains(" NW End Teleport Pad§r§a to the") &&  latestLine.contains("Start Teleport Pad")) {
                bot.sendMessage(latestLine);
            }
        }

    }

    public void accusedWhileAFKing() throws IOException, InterruptedException, AWTException {
        File file = new File("/home/barry/.minecraft/skyclient/logs/latest.log");
        ReversedLinesFileReader fr = new ReversedLinesFileReader(file);
        Bot bot = new Bot();


        for (int i = 0; i < 50; i++) {
            String latestLine = fr.readLine();

            if (latestLine.contains("[CHAT]")) {
                // Checks if someone is visiting
                if (latestLine.toLowerCase(Locale.ROOT).contains("eggrollean") || latestLine.toLowerCase(Locale.ROOT).contains("egg") || latestLine.toLowerCase(Locale.ROOT).contains("eggroll") || latestLine.toLowerCase(Locale.ROOT).contains("goblin")) {
                    bot.sendMessage("Got mentioned: " + latestLine);
                    if (latestLine.toLowerCase(Locale.ROOT).contains("macro") || latestLine.toLowerCase(Locale.ROOT).contains("macroing") || latestLine.toLowerCase(Locale.ROOT).contains("afk") || latestLine.toLowerCase(Locale.ROOT).contains("farm")) {
                        bot.sendMessage("Panic reason: " + latestLine);
                        panicLevel3();
                    }
                }
            }
        }
    }

    public void panicLevel1() {
        System.out.println("Stop macro");

        System.exit(0);
    }

    public void panicLevel2() throws InterruptedException, AWTException {
        System.out.println("Go to island and stop");
        Bot.type("g/is");

        System.exit(0);
    }


    public void panicLevel3() throws AWTException, InterruptedException {
        // Log out
        System.out.println("End Minecraft");

        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_ALT);
        Thread.sleep(100);
        robot.keyPress(KeyEvent.VK_F4);
        Thread.sleep(100);
        robot.keyPress(KeyEvent.VK_F4);
        Thread.sleep(100);
        robot.keyRelease(KeyEvent.VK_ALT);
    }
}

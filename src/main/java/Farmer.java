import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Key;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class Farmer {
    Robot robot;
    public static boolean unstucking;

    public Farmer() throws AWTException {
        robot = new Robot();
        unstucking = false;
    }

    public void forage() throws InterruptedException {
        int currentX = 0;
        int currentY = 0;

        Random random = new Random();


        System.out.println("Foraging Farm Thread running");



        while (true) {
            // Switch to saplings
            robot.keyPress(KeyEvent.VK_8);
            Thread.sleep(10);
            robot.keyRelease(KeyEvent.VK_8);

            // Place first two saplings
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            Thread.sleep(75);
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);

            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            Thread.sleep(75);
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);

            // Walk into end
            robot.keyPress(KeyEvent.VK_D);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            robot.keyRelease(KeyEvent.VK_D);

            // Move mouse down to place second saplings
//            currentX = a8d756a8d75a8(int) MouseInfo.getPointerInfo().getLocation().getX();
//            currentX = a8d756a8d75a86(int) MouseInfo.getPointerInfo().getLocation().getX();
//            currentY = (int) MouseInfo.getPointerInfo().getLocation().getY();
//            robot.mouseMove(currentX, currentY + 50);

            // Place second two saplings
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            Thread.sleep(50);
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            Thread.sleep(50);
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);

            // Switch to bonemeal
            robot.keyPress(KeyEvent.VK_7);
            Thread.sleep(50);
            robot.keyRelease(KeyEvent.VK_7);

            // Grow trees
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            Thread.sleep(50);
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);

            // Switch to treecap
            robot.keyPress(KeyEvent.VK_6);
            Thread.sleep(50);
            robot.keyRelease(KeyEvent.VK_6);

            // Let grow
            Thread.sleep(400);

            // Break trees
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(400);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            Thread.sleep(400);

            // Autopet rod swap
            robot.keyPress(KeyEvent.VK_V);
            Thread.sleep(50);
            robot.keyRelease(KeyEvent.VK_V);
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            Thread.sleep(50);
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
            Thread.sleep(50);


            // Move mouse back up
//            currentX = (int) MouseInfo.getPointerInfo().getLocation().getX();
//            currentY = (int) MouseInfo.getPointerInfo().getLocation().getY();
//            robot.mouseMove(currentX, currentY - 50);

            // Walk back to pressure plate
            robot.keyPress(KeyEvent.VK_A);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            robot.keyRelease(KeyEvent.VK_A);

            // Reel rod back
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            Thread.sleep(50);
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);

            Thread.sleep(150);
        }
    }

    public static void unstuck() {
        if (unstucking == true) {
            System.out.println("Already unstucking");
            return;
        } else {
            System.out.println("Set unstucking to true");
            unstucking = true;
        }

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
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.keyRelease(KeyEvent.VK_SPACE);


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

        robot.keyPress(KeyEvent.VK_SHIFT);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.keyRelease(KeyEvent.VK_SHIFT);

        unstucking = false;
    }

    public void sugarcane() throws ExecutionException, InterruptedException, AWTException {
        Random random = new Random();


        System.out.println("Sugarcane Farm Thread running");

        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);

        while (true) {
            // Stop running into block
            robot.keyPress(KeyEvent.VK_D);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            robot.keyRelease(KeyEvent.VK_D);

            robot.keyPress(KeyEvent.VK_W);
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            robot.keyRelease(KeyEvent.VK_W);


            robot.keyPress(KeyEvent.VK_S);
            try {
                Thread.sleep(16750 + (random.nextInt(1000) + 250));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            robot.keyRelease(KeyEvent.VK_S);

            // Stop running into block
            robot.keyPress(KeyEvent.VK_D);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            robot.keyRelease(KeyEvent.VK_D);

            robot.keyPress(KeyEvent.VK_S);
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            robot.keyRelease(KeyEvent.VK_S);
            robot.keyPress(KeyEvent.VK_A);
            try {
                Thread.sleep(16750 + (random.nextInt(1000) + 250));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            robot.keyRelease(KeyEvent.VK_A);
        }
    }

    public void alchemy() throws InterruptedException {

        System.out.println("Alchemy Starting");

        Thread.sleep(3000);

        int currentX = 0;
        int currentY = 0;

        while (true) {

            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            Thread.sleep(50);
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);

            Thread.sleep(1000);

            // Begin collecting potions
            currentX = (int) MouseInfo.getPointerInfo().getLocation().getX();
            currentY = (int) MouseInfo.getPointerInfo().getLocation().getY();
            robot.mouseMove(currentX, currentY - 50);

            Thread.sleep(1000);


            robot.keyPress(KeyEvent.VK_SHIFT);
            Thread.sleep(100);

            // Shift click potions into inv
            for (int i = 0; i < 27; i++) {
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                Thread.sleep(50);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

                Thread.sleep(380);
            }

            robot.keyRelease(KeyEvent.VK_SHIFT);

            robot.keyPress(KeyEvent.VK_ESCAPE);
            Thread.sleep(100);
            robot.keyRelease(KeyEvent.VK_ESCAPE);


            // Open Inventory
            robot.keyPress(KeyEvent.VK_E);
            Thread.sleep(200);
            robot.keyRelease(KeyEvent.VK_E);

            Thread.sleep(250);

            // Open menu
            robot.mouseMove(3100, 750);

            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(50);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            Thread.sleep(250);

            // Go to trade sell area
            robot.mouseMove(2880, 400);

            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(50);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            Thread.sleep(100);

            // Move to first spot
            robot.mouseMove(2880, 650);

            Thread.sleep(150);

            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(75);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            for (int i = 0; i < 4; i++) {
                currentX = (int) MouseInfo.getPointerInfo().getLocation().getX();
                currentY = (int) MouseInfo.getPointerInfo().getLocation().getY();
                robot.mouseMove(currentX + 50, currentY);

                Thread.sleep(150);

                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                Thread.sleep(75);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            }

            // Move to second row
            currentX = (int) MouseInfo.getPointerInfo().getLocation().getX();
            currentY = (int) MouseInfo.getPointerInfo().getLocation().getY();
            robot.mouseMove(currentX, currentY + 50);

            Thread.sleep(150);

            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(75);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            for (int i = 0; i < 8; i++) {
                currentX = (int) MouseInfo.getPointerInfo().getLocation().getX();
                currentY = (int) MouseInfo.getPointerInfo().getLocation().getY();
                robot.mouseMove(currentX - 50, currentY);

                Thread.sleep(150);

                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                Thread.sleep(75);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            }

            // Move down to third row
            currentX = (int) MouseInfo.getPointerInfo().getLocation().getX();
            currentY = (int) MouseInfo.getPointerInfo().getLocation().getY();
            robot.mouseMove(currentX, currentY + 50);

            Thread.sleep(150);

            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(75);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            // Empty third row
            for (int i = 0; i < 7; i++) {
                currentX = (int) MouseInfo.getPointerInfo().getLocation().getX();
                currentY = (int) MouseInfo.getPointerInfo().getLocation().getY();
                robot.mouseMove(currentX + 50, currentY);

                Thread.sleep(150);

                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                Thread.sleep(75);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            }

            // Move to fourth row
            currentX = (int) MouseInfo.getPointerInfo().getLocation().getX();
            currentY = (int) MouseInfo.getPointerInfo().getLocation().getY();
            robot.mouseMove(currentX, currentY + 65);

            Thread.sleep(150);

            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(75);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            //Empty fourth row
            for (int i = 0; i < 2; i++) {
                currentX = (int) MouseInfo.getPointerInfo().getLocation().getX();
                currentY = (int) MouseInfo.getPointerInfo().getLocation().getY();
                robot.mouseMove(currentX - 50, currentY);

                Thread.sleep(150);

                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                Thread.sleep(75);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            }

            robot.keyPress(KeyEvent.VK_ESCAPE);
            Thread.sleep(100);
            robot.keyRelease(KeyEvent.VK_ESCAPE);

            Thread.sleep(2500);
        }
    }

    public void netherwart() throws InterruptedException {
        Random random = new Random();

        System.out.println("Netherwart Farm Thread running");



        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(200);

        while (SafetyNet.isRunning()) {
            // Walk left
            robot.keyPress(KeyEvent.VK_A);
            Thread.sleep(34500 + (random.nextInt(100) + 250));
            robot.keyRelease(KeyEvent.VK_A);

            robot.keyPress(KeyEvent.VK_CONTROL);
            Thread.sleep(100);
            robot.keyPress(KeyEvent.VK_W);
            Thread.sleep(50);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(1000);
            robot.keyRelease(KeyEvent.VK_W);

            // Walk right
            robot.keyPress(KeyEvent.VK_D);
            try {
                Thread.sleep(34500 + (random.nextInt(100) + 250));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            robot.keyRelease(KeyEvent.VK_D);

            robot.keyPress(KeyEvent.VK_CONTROL);
            Thread.sleep(100);
            robot.keyPress(KeyEvent.VK_W);
            Thread.sleep(50);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(1000);
            robot.keyRelease(KeyEvent.VK_W);

        }
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        System.out.println("Not Running");
    }
    public void pumpkin() throws ExecutionException, InterruptedException, AWTException {
        robot.mouseMove(0, 0);

        Thread.sleep(2000);

        int currentX = 0;
        int currentY = 0;

        while (true) {
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.keyPress(KeyEvent.VK_A);
            Thread.sleep(18000);
            robot.keyRelease(KeyEvent.VK_A);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.keyPress(KeyEvent.VK_W);
            Thread.sleep(500);
            robot.keyRelease(KeyEvent.VK_W);

            robot.keyPress(KeyEvent.VK_D);
            Thread.sleep(500);
            robot.keyRelease(KeyEvent.VK_D);

            robot.keyPress(KeyEvent.VK_S);
            Thread.sleep(500);
            robot.keyRelease(KeyEvent.VK_S);

            currentX = (int) MouseInfo.getPointerInfo().getLocation().getX();
            currentY = (int) MouseInfo.getPointerInfo().getLocation().getY();
            System.out.println(currentX + ", " +  currentY);
            robot.mouseMove(currentX + 875, currentY);

            robot.keyPress(KeyEvent.VK_D);
            Thread.sleep(200);
            robot.keyRelease(KeyEvent.VK_D);

            Thread.sleep(1000);

            // Second half

            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.keyPress(KeyEvent.VK_D);
            Thread.sleep(18000);
            robot.keyRelease(KeyEvent.VK_D);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.keyPress(KeyEvent.VK_W);
            Thread.sleep(500);
            robot.keyRelease(KeyEvent.VK_W);

            robot.keyPress(KeyEvent.VK_A);
            Thread.sleep(500);
            robot.keyRelease(KeyEvent.VK_A);

            robot.keyPress(KeyEvent.VK_S);
            Thread.sleep(500);
            robot.keyRelease(KeyEvent.VK_S);

            currentX = (int) MouseInfo.getPointerInfo().getLocation().getX();
            currentY = (int) MouseInfo.getPointerInfo().getLocation().getY();
            System.out.println(currentX + ", " +  currentY);
            robot.mouseMove(currentX - 875, currentY);

            robot.keyPress(KeyEvent.VK_A);
            Thread.sleep(200);
            robot.keyRelease(KeyEvent.VK_A);
        }
    }

    public void cobblestone() {
        robot.keyPress(KeyEvent.VK_W);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void melody() {
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);

        robot.mouseMove(800, 800);

    }

    public void goblino() throws InterruptedException {
        Random random = new Random();

        while (true) {
            robot.keyPress(KeyEvent.VK_8);
            Thread.sleep(50 + random.nextInt(100));
            robot.keyRelease(KeyEvent.VK_8);

            robot.mousePress(MouseEvent.BUTTON3_DOWN_MASK);
            Thread.sleep(50 + random.nextInt(100));
            robot.mouseRelease(MouseEvent.BUTTON3_DOWN_MASK);

            robot.keyPress(KeyEvent.VK_F);
            Thread.sleep(50 + random.nextInt(100));
            robot.keyRelease(KeyEvent.VK_F);

            Thread.sleep(12000 + random.nextInt(1000));
        }
    }

    public void moveMouse(double outcomeX, double outcomeY) throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        System.out.println("Want to go to " + outcomeX + ", " + outcomeY);

        robot.keyPress(KeyEvent.VK_F3);
        Thread.sleep(100);
        robot.keyPress(KeyEvent.VK_C);
        Thread.sleep(100);
        robot.keyRelease(KeyEvent.VK_C);
        Thread.sleep(100);
        robot.keyRelease(KeyEvent.VK_F3);

        while (true) {
            // Get location stats
            robot.keyPress(KeyEvent.VK_F3);
            Thread.sleep(100);
            robot.keyPress(KeyEvent.VK_C);
            Thread.sleep(100);
            robot.keyRelease(KeyEvent.VK_C);
            Thread.sleep(100);
            robot.keyRelease(KeyEvent.VK_F3);

            //Extract data from clipboard
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Clipboard clipboard = toolkit.getSystemClipboard();
            String result = (String) clipboard.getData(DataFlavor.stringFlavor);
            System.out.println("String from Clipboard:" + result);

            String[] trimmedValues = result.substring(60, result.length()).split(" ");

            // Convert to 360 rotation system
            double currentMinecraftX = Math.abs((double)((int)(Double.parseDouble(trimmedValues[0]) * 10))/10);
            double currentMinecraftY = (double)((int)(Double.parseDouble(trimmedValues[1]) * 10))/10;

            System.out.println("Minecraft X: " + currentMinecraftX + " Minecraft Y: " + currentMinecraftY);
            // Actual mouse on screen coords
            int currentX = (int) MouseInfo.getPointerInfo().getLocation().getX();
            int currentY = (int) MouseInfo.getPointerInfo().getLocation().getY();

            // Figure out which way to move the mouse to correct for x and y, should be easy for everything but 0 and 180 because of how the xy coords become negative instead of going to 360
            if (currentMinecraftX < outcomeX) {
                System.out.println("Move mouse left");
                robot.mouseMove(currentX - 10, currentY);
            } else if (currentMinecraftX > outcomeX) {
                System.out.println("Move mouse right");
                robot.mouseMove(currentX + 10, currentY);
            } else {
                System.out.println("X is good enough");
            }

            if (currentMinecraftY < outcomeY) {
                System.out.println("Move mouse down");
                robot.mouseMove(currentX, currentY + 10);
            } else if (currentMinecraftY > outcomeY) {
                System.out.println("Move mouse up");
                robot.mouseMove(currentX, currentY - 10);
            } else {
                System.out.println("Y is good enough");
            }


            // checks if good enough
            if (currentMinecraftX == outcomeX && currentMinecraftY == outcomeY) {
                System.out.println("It's at a good enough accuracy");
                return;
            }
        }
    }

    public static String f3Information() throws AWTException {
        Robot robot = new Robot();

        // Gets screen
//        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
//        BufferedImage capture = new Robot().createScreenCapture(screenRect);


        return "";
    }

    public static String getLocation() throws InterruptedException, IOException, UnsupportedFlavorException, AWTException {
        Robot robot = new Robot();

        // Copy location to the clipboard
        robot.keyPress(KeyEvent.VK_F3);
        Thread.sleep(100);
        robot.keyPress(KeyEvent.VK_C);
        Thread.sleep(100);
        robot.keyRelease(KeyEvent.VK_F3);
        Thread.sleep(100);
        robot.keyRelease(KeyEvent.VK_C);

        //Extract data from clipboard
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        String result = (String) clipboard.getData(DataFlavor.stringFlavor);
        System.out.println("String from Clipboard:" + result);

        return result;
    }

    public void releaseAll() {
        for (int i = 65; i < 122; i++) {
            robot.keyRelease(i);
        }
        robot.mousePress(1);
        robot.mouseRelease(1);
    }

    public static void mouseDown() throws AWTException {
        Robot robot = new Robot();
        robot.mousePress(1);
    }
}

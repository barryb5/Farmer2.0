import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class OnlineLogger {

    final private static String KEY = "be0e1c79-1314-48bf-950b-cda383c93ee5";
    // UUID of Eggrollean so it can get the right player from the Hypixel API
    final private static UUID Eggrollean = UUID.fromString("09be77df-bb8a-4325-aaf9-29e4c5da8955");

    final private static String[] users = {"Deathstreeks", "56ms", "Cybercactus", "RoccoDL"};
    final private static UUID[] uuids = {UUID.fromString("fb3d9649-8a5b-4d5b-91b7-63db14b195ad"), UUID.fromString("1277d71f-3380-46e2-98d9-0c9fe4055f00"), UUID.fromString("0738b6b4-55e6-45ba-853d-a98a705bcbb5"), UUID.fromString("19e11796-f966-4993-8818-5e79a95c6a42")};

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        while (true) {
            for (int i = 0; i < users.length; i++) {
                String info = APIForHypixelData.getInfo(uuids[i]);

                updateInfo(info, users[i]);


            }
        }
    }

    public static void updateInfo(String raw, String user) {
        String[] returnData = new String[5];

        String online = raw.substring(raw.indexOf("online=") + 7, raw.indexOf("serverType=") - 2);
        String game = raw.substring(raw.indexOf("serverType=") + 11, raw.indexOf("mode=") - 2);
        String location = raw.substring(raw.indexOf("mode=") + 5, raw.indexOf("map=") - 2);


        returnData[0] = java.time.LocalDateTime.now().toString();

        if (online.equals("true")) {
            returnData[1] = "1";
        } else {
            returnData[1] = "0";
        }

        returnData[2] = game;

        returnData[3] = location;



//        CollectorRegistry registry = new CollectorRegistry();
    }
}

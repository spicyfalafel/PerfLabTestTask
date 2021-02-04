package util;

import java.time.LocalDateTime;
import java.util.Random;

public class GenerateLog {

    public static void main(String[] args) {
        String ish = "2020-01-01T12:51:32";
        LocalDateTime time = DateHandler.parseTimeFromString(ish);
        Random random = new Random();
        for (int i = 0; i < 50000; i++) {
            time = time.plusMinutes(2);
            String action = random.nextInt(2) == 1 ? "top up" : "scoop";
            String success = random.nextInt(2) == 1 ? "(успех)" : "(фейл)";
            System.out.println(time.format(DateHandler.dtf) + ".124Z" + "-[username1] - wanna " + action
                    + " " + random.nextInt(100) + "l " + success);
        }
    }
}

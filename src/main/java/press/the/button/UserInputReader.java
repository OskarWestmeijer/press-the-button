package press.the.button;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class UserInputReader {

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private int countdown = 10;

    private Scanner scanner = new Scanner(System.in);

    public void readForActivity() {

        String keyInput = "";
        while (!keyInput.toLowerCase().equals("lost")) {
            restartCountdown();
            printRetryText();
            keyInput = scanner.nextLine();
            scheduler.shutdown();
            countdown = 10;
        }
        System.out.println("Correct password. You broke the loop. Good job Hurley, you are free now.");
    }

    private void restartCountdown() {

        Runnable runnable = new Runnable() {

            public void run() {

                System.out.println(countdown);
                countdown--;

                if (countdown <= 0) {
                    universalShutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }

    private void printRetryText() {
        System.out.println("Wrong password. Countdown reset! ENTER the correct password.");
    }

    private void universalShutdown() {
        scanner.close();
        scheduler.shutdown();
        System.out.println("You did not save the universe. Too bad Hurley.");

    }

}

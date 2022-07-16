package press.the.button;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class UserInputReader {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private static final int DEFAULT_COUNTDOWN_LENGTH = 20;

    private int currentCountdown = 20;

    private String userInput = "";

    private int resetCounter = 0;

    private Scanner scanner = new Scanner(System.in);

    public void readForActivity() {
        printInitialText();
        startCountdown();

        while (!userInput.toLowerCase().equals("lost") && currentCountdown > 0) {
            if (resetCounter > 0) {
                printRetryText();
            }
            userInput = scanner.nextLine();
            currentCountdown = DEFAULT_COUNTDOWN_LENGTH;
            resetCounter++;
        }
        victoryShutdown();
    }

    /**
     * This will start counting down from the set default time. Every 5 seconds will be printed.
     * If the countdown is not reset, it will trigger a application shutdown.
     */
    private void startCountdown() {
        Runnable runnable = new Runnable() {

            public void run() {

                // print every 5 seconds
                if ((currentCountdown % 5) == 0) {
                    System.out.println(currentCountdown);
                }
                currentCountdown--;

                if (currentCountdown <= 0) {
                    universalShutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }

    private void printInitialText() {
        System.out.println("Countdown initiated. Please ENTER the correct password.");
    }

    private void printRetryText() {
        System.out.println("Input: " + userInput + " was the wrong password. Countdown reset! Reset count: " + resetCounter);
        System.out.println("ENTER the correct password.");
    }

    private void victoryShutdown() {
        scheduler.shutdown();
        scanner.close();
        System.out.println("Correct password. You broke the loop. Good job Hurley, you are free now.");
    }

    private void universalShutdown() {
        System.out.println("You did not provide any password. Now you will go down together with this island. Too bad Hurley.");
        scheduler.shutdown();
        System.exit(1057);
    }

}

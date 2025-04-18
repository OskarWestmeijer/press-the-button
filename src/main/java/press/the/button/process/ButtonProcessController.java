package press.the.button.process;

import java.util.List;
import java.util.Scanner;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ButtonProcessController {

  private static ButtonProcessController INSTANCE;
  private final Countdown countdown = Countdown.getInstance();
  private static final String VICTORY_PASSWORD = "lost";
  private static final String HINT_ONE = "1";
  private static final String HINT_TWO = "2";
  private static final Integer OCEANIC_FLIGHT_NUMBER = 815;

  public static ButtonProcessController getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new ButtonProcessController();
    }
    return INSTANCE;
  }

  public void initiateProcess() {
    TerminalWriter.printInstructions();
    countdown.startCountdown();
    String userInput = "";

    // blocking call
    try (var scanner = new Scanner(System.in)) {
      while (true) {
        userInput = scanner.nextLine().trim();
        if (VICTORY_PASSWORD.equalsIgnoreCase(userInput)) {
          initiateVictory(scanner);
        } else if (HINT_ONE.equals(userInput)) {
          TerminalWriter.printHint1();
        } else if (HINT_TWO.equals(userInput)) {
          TerminalWriter.printHint2();
        }

        resetLoop(userInput);
      }
    } catch (Exception e) {
      System.out.println("Exception thrown." + e);
      TerminalWriter.printFailure();
      shutdownProcess();
    }
  }

  private void resetLoop(String userInput) {
    if (List.of(HINT_ONE, HINT_TWO).contains(userInput)) {
      TerminalWriter.printInstructions();
    } else {
      TerminalWriter.printRetry(userInput);
    }
    countdown.resetCountdown();
  }

  private void initiateVictory(Scanner scanner) {
    countdown.abortCountdown();
    scanner.close();
    TerminalWriter.printVictory();
    shutdownProcess();
  }

  static void shutdownProcess() {
    System.exit(OCEANIC_FLIGHT_NUMBER);
  }

}

package push.the.button.process;

final class TerminalWriter {

  static void printInstructions() {
    System.out.println("Countdown initiated. ENTER the correct password. For hints enter: '1', '2'");
  }

  static void printRemainingTime(Integer time) {
    System.out.println("Time remaining: %s seconds".formatted(time));
  }

  static void printRetry(String userInput) {
    System.out.println("Input: '%s' was the wrong password. Countdown reset!".formatted(userInput));
    printInstructions();
  }

  static void printVictory() {
    System.out.println("Correct password. You broke the loop. Good job Hurley, you are free now.");
  }

  static void printFailure() {
    System.out.println("You did not provide any password. Now you will stay forever on this island. Too bad Hurley.");
  }

  static void printHint1() {
    System.out.println("A popular TV show, which first aired in 2004.");
  }

  static void printHint2() {
    System.out.println("An airplane crashes on a remote island.");
  }

}

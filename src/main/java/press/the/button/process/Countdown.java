package press.the.button.process;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class Countdown implements Runnable {

  private static Countdown INSTANCE;
  private final Integer COUNTDOWN_MAXIMUM = 20;
  private int currentCountdown = COUNTDOWN_MAXIMUM;
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

  static Countdown getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new Countdown();
    }
    return INSTANCE;
  }

  @Override
  public void run() {
    if (currentCountdown < 10 || (currentCountdown % 5) == 0) {
      TerminalWriter.printRemainingTime(currentCountdown);
    }
    currentCountdown--;

    if (currentCountdown < 0) {
      universalShutdown();
    }
  }

  void startCountdown() {
    scheduler.scheduleAtFixedRate(this, 0, 1, SECONDS);
  }

  void resetCountdown() {
    currentCountdown = COUNTDOWN_MAXIMUM;
  }

  void abortCountdown() {
    scheduler.shutdown();
  }

  private void universalShutdown() {
    TerminalWriter.printFailure();
    scheduler.shutdown();
    ButtonProcessController.shutdownProcess();
  }

}

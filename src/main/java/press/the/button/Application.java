package press.the.button;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Application {


    public static void main(String[] args) {
        UserInputReader userInputReader = new UserInputReader();
        userInputReader.readForActivity();
    }


}

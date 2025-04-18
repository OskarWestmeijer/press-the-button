package press.the.button;

import press.the.button.process.ButtonProcessController;

public class Application {


  public static void main(String[] args) {
    var controller = ButtonProcessController.getInstance();
    controller.initiateProcess();
  }


}

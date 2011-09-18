package controllers;

public class EchoSocket extends BaseWebSocketController {
  public static void echo() { 
    handle(new EventHandler(){
      public void handleMessage(String msg){
        if(msg.equals("quit")) {
          send("Bye!");
          close();
        }
        else send(new StringBuffer(msg).reverse().toString());
      }
    }); 
  }
}

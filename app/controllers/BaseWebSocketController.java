package controllers;

import play.mvc.WebSocketController;

import static play.libs.F.*;
import static play.libs.F.Matcher.*;
import static play.mvc.Http.WebSocketEvent.*;
import play.mvc.Http.WebSocketEvent;
import play.mvc.Http.WebSocketClose;

public class BaseWebSocketController extends WebSocketController {

  static void handle(EventHandler handler){ 
    while(inbound.isOpen()) { handler.handle(await(inbound.nextEvent())); }
  }

  static abstract class EventHandler {
    public void handle(WebSocketEvent e){
       for(String msg: TextFrame.match(e)) { handleMessage(msg); }
       for(WebSocketClose closed: SocketClosed.match(e)) { handleClose(); }
    }
    public void send(String message){ outbound.send(message); }
    public void close(){ disconnect(); }
    public void handleClose(){ System.out.println("Socket closed!"); }
    public abstract void handleMessage(String msg);
  }
}

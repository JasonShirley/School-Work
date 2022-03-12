/*
* Jason Shirley
* CS 311 Winter 2018
* Message will build message object that will hold string values. The object will
* also store a boolean value to see if the message is a terminate value.
 */
class Message{
  String message;
  boolean terminate;
  public Message(){
    message = null;
  }
  public Message(String mess, boolean term){
    terminate = term;
    message = mess;
  }
  public boolean isTerminate(){
      return terminate;
  }
  @Override
  public String toString(){
    if (terminate == true){
      message = "quitter: true";
      return message;
    }
    else if (terminate == false){
      return message;
    }
    return null;
  }
}

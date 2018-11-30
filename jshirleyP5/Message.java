
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

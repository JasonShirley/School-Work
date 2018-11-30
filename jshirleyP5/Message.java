
class Message{
  String message;
  boolean terminate = false;
  public Message(){
    message = null;
  }
  public Message(String mess){
    message = mess;
  }
  public boolean isTerminate(){
    if (this.message.equals("")) {
      terminate = true;
      return terminate;
    }
    else {
      terminate = false;
      return terminate;
    }
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

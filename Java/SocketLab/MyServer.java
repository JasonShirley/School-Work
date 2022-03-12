import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    public static void main(String[] args) throws Exception {
        System.out.println("The server is starting....");

        int port = 0;

        if (args.length ==1) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[0] + " must be an integer.");
                System.exit(1);
            }
        }
        else{
            System.out.println("Usage: java MyServer port\n");
            System.exit(1);
        }

        int clientNumber = 0;
        ServerSocket listener = new ServerSocket(port);
        Socket socket;

        try {
            while (true) {
                socket = listener.accept();   //blocks until a new connection arrives
                clientNumber++;

                //start a new thread to handle this connection
                Thread serverThread = new Thread(new ServerThread(socket, clientNumber));
                serverThread.start();
            }
        } finally {
            listener.close();
        }

        //System.out.println("The server is stopping");
    }

}



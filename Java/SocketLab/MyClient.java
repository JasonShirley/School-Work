import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyClient {

    public static void main(String[] args) throws Exception {
        System.out.println("The client is starting....");

        String serverIp = args[0];

        int port = 0;
        if (args.length ==3) {
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Argument " + args[0] + " must be an integer.");
                System.exit(1);
            }
        }
        else{
            System.out.println("Usage: java MyClient ip port message\n");
            System.exit(1);
        }

        String messageToSend = args[2];

        Socket socket = new Socket(serverIp, port);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        System.out.println("sending message to: " + serverIp + ":" + port + ", " + messageToSend);
        out.println(messageToSend);
        out.println(".");

        String messageReceived = "";
        messageReceived = in.readLine();
        System.out.println("got message back from server: " + messageReceived); 

        System.out.println("closing socket");
        socket.close();

        System.out.println("client done");

    }

}

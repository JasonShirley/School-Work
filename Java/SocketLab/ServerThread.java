import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {

    private Socket socket;
    private int clientNumber;

    ServerThread(Socket socket, int clientNumber){
        this.socket = socket;
        this.clientNumber = clientNumber;
    }

    public void run() {

        System.out.println("Server thread starting, serving client: " + clientNumber);

        try {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String input = in.readLine();
                System.out.println("\tserver read a line: " + input);
                if (input == null || input.equals(".")) {
                    break;
                }

                out.println("Hello, you are client #" + clientNumber + ".");
            }
        }
        catch(Exception e){
            System.out.println("caught exeception: " + e);
        }

        System.out.println("\tserver thread done");

    }


}


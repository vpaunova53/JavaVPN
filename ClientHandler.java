import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received via VPN tunnel: " + inputLine);
                String response = "[Proxy IP] Encrypted Response: " + inputLine.toUpperCase();
                out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import java.io.*;
import java.net.*;

public class VPNClient {
    public static void main(String[] args) throws IOException {
        String vpnHost = "localhost";
        int vpnPort = 9999;

        try (
                Socket socket = new Socket(vpnHost, vpnPort);
                BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            System.out.println("Connected to VPN server at " + vpnHost + ":" + vpnPort);

            while (true) {
                System.out.print("Enter message (or 'exit'): ");
                String userInput = console.readLine();
                if ("exit".equalsIgnoreCase(userInput)) break;

                out.println(userInput);
                String response = in.readLine();
                System.out.println("VPN Response: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

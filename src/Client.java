import java.net.*;
import java.io.*;
import java.util.Scanner;
public class Client {
	private Socket socket = null;
    private InputStreamReader in = null;
    private OutputStreamWriter out = null;
    private BufferedReader reader = null;
    private BufferedWriter writer = null;
	
	public Client(String address, int port) {
		try {
			socket = new Socket(address, port);
			System.out.println("Client connected");
			
			in = new InputStreamReader(socket.getInputStream());
			out = new OutputStreamWriter(socket.getOutputStream());
			
			reader = new BufferedReader(in);
			writer = new BufferedWriter(out);
			
			Scanner scanner = new Scanner(System.in);
			
			String accNum = "";
			String pin = "";
			
			System.out.println("Enter card number: ");
			accNum = scanner.nextLine();
			writer.write(accNum);
			writer.newLine();
			writer.flush();
			
			System.out.println("Enter PIN: ");
			pin = scanner.nextLine();
			writer.write(pin);
			writer.newLine();
			writer.flush();
			
			System.out.println("Server: " + reader.readLine());
		}
		catch(IOException e) {
			System.out.println(e);
		}
		try {
			if (socket != null) {
				socket.close();
			}
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
			if (writer != null) {
				writer.close();
			}
			if (reader != null) {
				reader.close();
			}
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public static void main(String args[]) {
		Client client = new Client("localhost", 1234);
	}
}

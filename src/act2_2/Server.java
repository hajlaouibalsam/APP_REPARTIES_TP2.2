 package act2_2;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			
			
			ServerSocket serverSocket = new ServerSocket(1234);
			Socket socket = serverSocket.accept();
			
			System.out.println("Client connecté");
			
			
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			
			
			operation op = (operation) ois.readObject();
			
			
			int resultat = op.op1;
			switch(op.operation) {
			case '+':
				resultat += op.op2;
				break;
			case '-':
				resultat -= op.op2;
				break;
			case '*':
				resultat *= op.op2;
				break;
			case '/':
				resultat /= op.op2;
				break;
			}
		
			op.setResultat(resultat);
			
			
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(op);
			
			
			serverSocket.close();
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
} 
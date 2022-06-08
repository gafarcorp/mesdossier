package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.function.Consumer;


public class ChatImplementation extends UnicastRemoteObject implements ChatInterface{
	
	 static String message="";
	 static String messages="";
	 private Socket sochet;
	 final static int port = 9000;
		private ObjectOutputStream out;
	private static final long serialVersionUID = 1L;
	protected ChatImplementation() throws RemoteException {
		super();
		message="Debut de message: "+new Date();
	}

	@Override
	public String EnvoiMessage(String UserName, String UserMessage) throws RemoteException {
		
		 messages += UserName+" : "+ UserMessage+ " \n";
		 
		 return messages;
		
	}


	
	
}

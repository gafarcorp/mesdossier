package serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatInterface extends Remote{
	
	public String EnvoiMessage(String UserName, String UserMessage) throws RemoteException;

}

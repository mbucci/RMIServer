/*
 * Catalog skeleton
 * Distributed Systems - Sean Barker
 *
 * Max Bucci, Julia Hogan
 * Created: 3/2/15
 */ 
 
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Catalog extends Remote {
	
	public String subjectQuery(String subject) throws RemoteException;
	public String itemQuery(int itemNumber) throws RemoteException;
	
	public void updateItem(int itemNum, double priceFlag, int quantityFlag) throws RemoteException;
}

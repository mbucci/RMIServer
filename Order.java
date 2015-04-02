/*
 * Order Skeleton
 * Distributed Systems - Sean Barker
 *
 * Max Bucci, Julia Hogan
 * Created: 3/2/15
 */

 
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Order extends Remote {
	
	public String buy(int itemNum) throws RemoteException;
}

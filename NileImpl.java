/*
 * Nile Implementation
 * Distributed Systems - Sean Barker
 *
 * Max Bucci, Julia Hogan
 * Created: 3/2/15
 */

 
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class NileImpl implements Nile {
	
	private static Catalog cstub = null;
	private static Order ostub = null;
	
	//Overloaded constructor
	public NileImpl() {
		
		try {
			Registry creg = LocateRegistry.getRegistry("localhost", Constants.CATALOG_PORT);
			Registry oreg = LocateRegistry.getRegistry("localhost", Constants.ORDER_PORT);
			cstub = (Catalog) creg.lookup("Catalog");
			ostub = (Order) oreg.lookup("Order");
			
		} catch (Exception e) {
			System.err.println("Nile Front End exception thrown: " + e.toString());
			e.printStackTrace();
		}	
	}
	
	
	public String search(String topic) throws RemoteException {
		return cstub.subjectQuery(topic);
	}
	
	
	public String lookup(int itemNum) throws RemoteException {
		return cstub.itemQuery(itemNum);
	}
	
	
	public String buy(int itemNum) throws RemoteException {
		return ostub.buy(itemNum);
	}
}

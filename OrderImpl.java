/*
 * Order Implementation
 * Distributed Systems - Sean Barker
 *
 * Max Bucci, Julia Hogan
 * Created: 3/2/15
 */
 
 
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class OrderImpl implements Order {
	
	private static Catalog stub = null;
	
	
	//Buys a given itemNumber if the item is in exists and is in stock.
	//Returns a message stating if the buy was unsuccessful due to 
	//a non-existent item, out of stock, or unknown error
	public synchronized String buy(int itemNum) throws RemoteException {
		
		try {
			Registry reg = LocateRegistry.getRegistry("localhost", Constants.CATALOG_PORT);
			stub = (Catalog) reg.lookup("Catalog");
	    
		} catch (Exception e) {
			System.err.println("Order exception thrown: " + e.toString());
			e.printStackTrace();
		}
		return syncBuy(itemNum);
	}

	//Syncrhonized buy method, allows for multiple buy()s to be processed
	//but synchronizes the buy itself
	private synchronized String syncBuy(int itemNum) throws RemoteException {

		String queryRet = stub.itemQuery(itemNum);
		if (queryRet.contains("IN STOCK")) {
			stub.updateItem(itemNum, 0, -1);
			return "Buy Successful!\n";
			
		} else if (queryRet.contains("OUT OF STOCK")) {
			return "Buy Unsuccessful: Item Out of Stock\n";
		} else if (queryRet.contains("No Item Found")) {
			return "Buy Unsuccessful: Item Not in Database\n";
		} else{
			return "Buy Unsuccessful: Unknown Error\n";	
		}
	}
}

/*
 * Nile Order Server
 * Distributed Systems - Sean Barker
 *
 * Max Bucci, Julia Hogan
 * Created: 3/2/15
 */

 
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
	
public class OrderServer {
		
	public OrderServer() {}
	
	//Server binds to "Order"
	public static void main(String args[]) {
		
		try {
			OrderImpl obj = new OrderImpl();
			//Start the registry
			Registry registry = LocateRegistry.createRegistry(Constants.ORDER_PORT);
			
			Order stub = (Order) UnicastRemoteObject.exportObject(obj, Constants.ORDER_PORT);
			registry.rebind("Order", stub);
			
			System.out.println("Order Server is ready to listen...");
			
		} catch (Exception e) {
			System.err.println("Order Server exception thrown: " + e.toString());
			e.printStackTrace();
		}
	}
}

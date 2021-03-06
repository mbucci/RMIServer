/*
 * Nile.com Front End Server
 * Distributed Systems - Sean Barker
 *
 * Max Bucci, Julia Hogan
 * Created: 3/2/15
 */

 
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
	
public class NileServer {

	public NileServer() {}

	//Server binds to "Nile"
	public static void main(String args[]) {
	
		try {
			NileImpl obj = new NileImpl();
			//Start the registry
			Registry registry = LocateRegistry.createRegistry(Constants.NILE_PORT);
			
			Nile stub = (Nile) UnicastRemoteObject.exportObject(obj, Constants.NILE_PORT);
			registry.rebind("Nile", stub);
			
			System.out.println("Nile Front End Server is ready to listen...");

		} catch (Exception e) {
			System.err.println("Nile Front End Server exception thrown: " + e.toString());
			e.printStackTrace();
		}
	}
}

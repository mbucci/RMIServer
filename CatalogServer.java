/*
 * Nile.com Catalog Server
 * Distributed Systems - Sean Barker
 *
 * Max Bucci, Julia Hogan
 * Created: 3/2/15
 */

 
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
	
public class CatalogServer {
	
	public CatalogServer() {}

	//Server binds to "Catalog"
	public static void main(String args[]) {
		
		try {
			CatalogImpl obj = new CatalogImpl();
			//Start the registry
			Registry registry = LocateRegistry.createRegistry(Constants.CATALOG_PORT);
			
			Catalog stub = (Catalog) UnicastRemoteObject.exportObject(obj, Constants.CATALOG_PORT);
			registry.rebind("Catalog", stub);
			
			System.out.println("Catalog Server is ready to listen...");
			
		} catch (Exception e) {
			System.err.println("Catalog Server exception thrown: " + e.toString());
			e.printStackTrace();
		}
	}
}

/*
 * Nile.com Client
 * Distributed Systems - Sean Barker
 *
 * Max Bucci, Julia Hogan
 * Created: 3/2/15
 */


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private static Nile stub = null;
    
    private Client() {}

    public static void main(String[] args) {

	try {
		Registry reg = LocateRegistry.getRegistry("localhost", 9001);
		stub = (Nile) reg.lookup("Nile");
	    
	} catch (Exception e) {
		System.err.println("Client exception thrown: " + e.toString());
		e.printStackTrace();
	}
	
	try {
		System.out.println(stub.search("distributed systems"));
		System.out.println(stub.lookup(57471));
		System.out.println(stub.search("college life"));
		System.out.println(stub.buy(57471));
		System.out.println(stub.lookup(57471));
		System.out.println(stub.lookup(11111));
		System.out.println(stub.buy(11111));

		
	} catch (Exception e) {
		System.err.println("Client exception thrown: " + e.toString());
		e.printStackTrace();
	}
    }
}

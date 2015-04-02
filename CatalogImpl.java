/*
 * Catalog Implementation
 * Distributed Systems - Sean Barker
 *
 * Max Bucci, Julia Hogan
 * Created: 3/2/15
 */
 
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CatalogImpl implements Catalog {
	
	//Used by timer;
	private final int MINUTE = 60 * 1000;
	
	private List<Book> books = new ArrayList<Book>();
	private Timer timer = new Timer();
	
	//Overloaded constructor, initializes catalog with 4 books by default
	public CatalogImpl() {
		
		Book book1 = new Book(
			"Achieving Less Bugs with More Hugs in CSCI 3325",
			57471,
			"distributed systems",
			53.25,
			10);
		this.books.add(book1);
		
		Book book2 = new Book(
			"Distributed Systems for Dummies",
			58574,
			"distributed systems",
			36.99,
			6);
		this.books.add(book2);
		
		Book book3 = new Book(
			"Surviving College",
			12395,
			"college life",
			20.00,
			15);
		this.books.add(book3);
		
		Book book4 = new Book(
			"Cooking for the Impatient Undergraduate",
			13298,
			"college life",
			65.00,
			3);
		this.books.add(book4);
		
		//Restock books every minute;
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				reStock();
			}
		}, MINUTE, MINUTE);
	}
	
	
	
	//Private method to restock all books.
	private void reStock() {
		
		System.out.println("Books Restocked");
		for (int i = 0; i < this.books.size(); i++) {
			this.books.get(i).incrementQuantity();	
		}
	}
	
	
	//Queries book database with book topic. Returns all (if any) books
	//in that topic
	public String subjectQuery(String topic) throws RemoteException {
		
		List<Book> found = new ArrayList<Book>();
		for (int i = 0; i < this.books.size(); i++) {
			Book temp = this.books.get(i);
			if (topic.equals(temp.getTopic())) found.add(temp);
		}
		
		return subjectFormat(found);
	} 
	
	
	//Queries book database with item number. Returns all associated
	//information for the item number if it is in the database.
	public String itemQuery(int itemNumber) throws RemoteException {
		
		Book found = null;
		for (int i = 0; i < this.books.size(); i++) {
			Book temp = this.books.get(i);
			if (itemNumber == temp.getItemNum()) found = temp;
		}
		
		return itemFormat(found);
	}
	
	
	//Update a given item using its specific item number. If either flag is 0,
	//don't change that value. Else, change the price of the item to the provided
	//value, and increment the quanity if the flag is 1 or decrement it if the 
	//flag is -1.
	public void updateItem(int itemNum, double priceFlag, int quantityFlag) throws RemoteException{
		
		//Temporay Book object used to search using the given itemNumber
		for (int i = 0; i < this.books.size(); i++) {
			if (this.books.get(i).getItemNum() == itemNum) {
				if (priceFlag != 0) {
					if (priceFlag >= 0.01) this.books.get(i).setPrice(priceFlag);	
				}
				if (quantityFlag != 0) {
					if (quantityFlag == 1) this.books.get(i).incrementQuantity();
					else if (quantityFlag == -1){
						if (this.books.get(i).getQuantity() > 0) this.books.get(i).decrementQuantity();
					}
				}
			}
		}
	}

	
	//Return format for subject query
    	public String subjectFormat(List<Book> bs) {
    		
    		if (!bs.isEmpty()) {
    			String ret =  String.format(bs.size() + " Entries found:\n");
    			for (int i = 0; i < bs.size(); i++) {
    				Book temp = bs.get(i);
    				ret = String.format(ret + temp.getName() + "\n");
    			}

    			return ret;	
    		} else return "No Items Found for Subject";	  		
    	}
    	
    	
    	//Return format for item query
    	public String itemFormat(Book b) {
    		if (b != null) {
    			String ret = "Entry found:\n";
    			if (b.getQuantity() >= 1) ret = String.format(ret + "IN STOCK\n");
    			else ret = String.format(ret + "OUT OF STOCK\n");
    			ret = String.format(ret + 
    				"Name: " + b.getName() + "\n" + 
    				"Item #: " + b.getItemNum() + "\n" +
    				"Topic: " + b.getTopic() + "\n" +
    				"Price: $" + b.getPrice() + "\n" +
    				"Quantity: " + b.getQuantity() + "\n");
    			
    			return ret;
    		} else return "No Item Found for Item Number";	
	}
}

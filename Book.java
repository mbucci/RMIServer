/*
 * Book class
 * Distributed Systems - Sean Barker
 *
 * Max Bucci, Julia Hogan
 * Created: 3/2/15
 */

public class Book {

	private String name;
	private int itemNumber;
	private String topic;
	private double price;
	private int quantity;

	//Overloaded constructor
	public Book(String n, int i, String t, double p, int q) {
		this.name = n;
		this.itemNumber = i;
		this.topic = t;
		this.price = p;
		this.quantity = q;
	}
	
	
	//Overrides compareTo to compare if two books have the same itemNumber
	public int compareTo(Book b) {
		return this.name.compareTo(b.getName());
	}
			
	public String getName(){ return this.name; }
	public int getItemNum(){ return this.itemNumber; }
	public String getTopic(){ return this.topic; }
	public double getPrice(){ return this.price; }
	public int getQuantity(){ return this.quantity; }

	//Updates to book information are done synchronously to maintain consistency 
	public synchronized void incrementQuantity(){ this.quantity++; }
	public synchronized void decrementQuantity(){ this.quantity--; }
	public synchronized void setPrice(double newP){ this.price = newP; }
}

/*	PizzaRec
	
	* Updated 03/10/17
	* PizzaRec now uses Richard Croft's solution to load information.
	* New information takes pizza type into consideration.
*/
  
public class PizzaRec
{
	private int order;
	private String customer;
	private int pizza;
	private String orderTime;
	private String deliveryTime;
	private Time differenceTime = new Time ();
	

	/*	Constructors

		One default constructor. Set order to zero,
		name to Doe and pizza to cheese.

		Other constructor accepts values for order, name, and pizza.
	*/

	public PizzaRec ( )	// default constructor
	{
		this.order = 0;
		this.customer = "Doe";
		this.pizza = 0;		//	cheese
		this.orderTime = "invalid order time";
		this.deliveryTime = "invalid delivery time";


	}

	public PizzaRec (int newOrder, String newName, int newPizza, String orderTime, String deliveryTime, Time difference )
	{
		this.order = newOrder;
		this.customer = newName;
		this.pizza = newPizza;
		this.orderTime = orderTime;
		this.deliveryTime= deliveryTime;
		this.differenceTime= difference;


		
	}

	/*	Accessor (get) methods	*/

	public int getOrder()
	{
		return this.order;
	}

	public String getCustomer()
	{
		return this.customer;
	}

	public int getPizza()
	{
		return this.pizza;
	}

	/*	Mutator (set) methods	*/

	public void setOrder(int newOrder)
	{
		this.order = newOrder;
	}

	public void setCustomer(String newName)
	{
		this.customer = newName;
	}

	public void setPizza(int newPizza)
	{
		this.pizza = newPizza;
	}

	/*	toString

		Return a formatted String giving all three values, translating
		the pizza code to the matching type name.
	*/

	public String toString ()
	{
		String [ ] pizzaChoices = { "Cheese", "Pepperoni", 
			"Sausage & Mushroom", "Marlon's Special", "Custom" };

		return "Order: " + this.order + " \tCustomer: " + 
			String.format("%-10s",this.customer) + 
			" Pizza: " + pizzaChoices [this.pizza] + " Order Time: " + orderTime + " Delivery Time: " + deliveryTime +
			" Delivered: " + differenceTime;
			
			
//		return "Order: " + this.order + "\tCustomer: " + this.customer +
//			"\tPizza: " + pizzaChoices [this.pizza];
	}

}


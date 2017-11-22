/**
 * @author Ashley Ridout
 * @date August 1, 2017
 */
package cs622Project;

//import ArrayList & Objects classes
import java.util.ArrayList;
import java.util.Objects;

public class KeeperItem<E> implements Keeper<E> {
	
		//variables
	    private String name;
	    private Double price;
	    private Integer quantity;

	    //empty default constructor
	    public KeeperItem(){}
	    
	    //constructor with 4 arguments
	    public KeeperItem(String name, Double price, Integer quantity){
	        this.name = name;
	        this.price = price;
	        this.quantity = quantity;
	    }

	    @Override
	    //get name method
	    public String getName() {
	        return name;
	    }
	    
	    @Override
	    //set name method
	    public void setName(String name) {
	        this.name = name;
	    }
	   
	    @Override
	    //get price method
	    public Double getPrice() {
	        return price;
	    }
	    
	    @Override
	    //set price method
	    public void setPrice(Double price) {
	        this.price = price;
	    }
	    
	    @Override
	    //get quantity method
	    public Integer getQuantity() {
	        return quantity;
	    }
	    
	    @Override
	    //set quantity method
	    public void setQuantity(Integer quantity) {
	        this.quantity = quantity;
	    }
	    
	    
	    @Override
	    //to String method to print arguments of constructor 
	    public String toString(){
	    	return "\nItem Name: " + getName()
	                + " Price: $" + String.format("%.2f", getPrice())
	                + " Quantity: " + getQuantity() ;
	    }
	    
	    /**method to add just the name from an Item ArrayList to an itemNameList String ArrayList
	     * 
	     * @param itemList is an ArrayList holding Item objects
	     * @return itemNameList is an ArrayList holding Item names
	     */
	    @Override
	    public ArrayList<String> getNamesFromItemList(ArrayList<E> itemList){
	        ArrayList<String> itemNameList = new ArrayList<String>();

	        for(int i=0; i< itemList.size(); i++)
	        {
	            itemNameList.add(i, ((Keeper<E>) itemList.get(i)).getName());
	        }

	        return itemNameList;
	    }
	    
	  //override methods for contains method to work correctly in AskerRent class
	    @Override
	    public boolean equals(Object o) {

	        if (o == this) return true;
	        if (!(o instanceof KeeperItem)) {
	            return false;
	        }
	        KeeperItem keeper = (KeeperItem) o;
	        return price == keeper.price &&
	        	   quantity == keeper.quantity &&
	                Objects.equals(name, keeper.name);
	    }
	    
	    @Override
	    public int hashCode() {
	        return Objects.hash(name, price, quantity);
	    }

}

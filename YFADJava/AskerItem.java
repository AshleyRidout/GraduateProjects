/**
 * @author Ashley Ridout
 * @date August 1, 2017
 */
package cs622Project;

//import ArrayList & Objects classes
import java.util.ArrayList;
import java.util.Objects;

public class AskerItem<E> extends Asker<E> {
	//variables
    private String name;
    private Double price;
    private Integer priority;
    private Integer quantity;

    //empty default constructor
    public AskerItem(){}
    
    //constructor with 4 arguments
    public AskerItem(String name, Double price, Integer priority, Integer quantity){
        this.name = name;
        this.price = price;
        this.priority = priority;
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
    //get priority method
    public Integer getPriority() {
        return priority;
    }
    
    @Override
    //set priority method
    public void setPriority(Integer priority) {
        this.priority = priority;
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
    	return "Item Name: " + getName()
                + " Price: $" + String.format("%.2f", getPrice())
                + "\n" ;
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
            itemNameList.add(i, ((Asker<E>) itemList.get(i)).getName());
        }

        return itemNameList;
    }
    
    //override methods for contains method to work correctly in AskerRent class
    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof AskerItem)) {
            return false;
        }
        AskerItem asker = (AskerItem) o;
        return priority == asker.priority &&
        	   price == asker.price &&
        	   quantity == asker.quantity &&
                Objects.equals(name, asker.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, priority, price, quantity);
    }
}



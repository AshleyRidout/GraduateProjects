/**
 * @author Ashley Ridout
 * @date August 17, 2017
 * @reference https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html
 */

package cs622Project;

// import java ArrayList class
import java.util.ArrayList;

// abstract class to hold Asker methods
public abstract class Asker<E> {
	public abstract String getName();

	public abstract void setName(String name);

	public abstract Double getPrice();

	public abstract void setPrice(Double price);
	
	public abstract Integer getPriority();
	
	public abstract void setPriority(Integer priority);

	public abstract Integer getQuantity();

	public abstract void setQuantity(Integer quantity);

	public abstract ArrayList<String> getNamesFromItemList(ArrayList<E> askName);
}


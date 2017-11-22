/**
 * @author Ashley Ridout
 * @date August 17, 2017
 * @reference https://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html
 */
package cs622Project;

// import java ArrayList class
import java.util.ArrayList;

// interface to hold Keeper methods 
public interface Keeper<E> {
	String getName();

	void setName(String name);

	Double getPrice();

	void setPrice(Double price);

	Integer getQuantity();

	void setQuantity(Integer quantity);

	ArrayList<String> getNamesFromItemList(ArrayList<E> keepName);
}

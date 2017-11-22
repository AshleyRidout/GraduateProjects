/**
 * @author Ashley Ridout
 * @date August 14, 2017
 * @reference https://stackoverflow.com/questions/8121176/java-sort-array-list-using-bubblesort
 * 
 */
package cs622Project;

// import ArrayList class
import java.util.ArrayList;

// class to hold method to sort ArrayList by priority
public class AskerPriority<E> {

    // bubbleSort method to sort ArrayList
    public ArrayList<AskerItem> bubbleSort(ArrayList<AskerItem> unsorted){
        int i;
        boolean flag = true;
        AskerItem temp;

        while(flag)
        {
            flag = false;
            for(i = 0; i <unsorted.size()-1; i++)
            {
            	// if priority of unsorted ArrayList is greater than next index priority
            	// switch them
                if(unsorted.get(i).getPriority() > unsorted.get(i+1).getPriority())
                {
                    temp = unsorted.get(i);
                    unsorted.set(i, unsorted.get(i+1));
                    unsorted.set(i+1, temp);
                    flag = true;
                }
            }
        }
        return unsorted;
    }
    
    public ArrayList<String> getSortedName (ArrayList<AskerItem> sorted){
    	ArrayList<String> name = new ArrayList<String>();
    	for(AskerItem a: sorted){
    		String n = a.getName();
    		name.add(n);
    		System.out.print(name);  		
    	}
    	return name;
    }
   
    
    public ArrayList<Double> getSortedPrice (ArrayList<AskerItem> sorted){
    	ArrayList<Double> price = new ArrayList<Double>();
    	for(AskerItem a: sorted){
    		Double d = a.getPrice();
    		price.add(d);
    		System.out.print(price);
    	}
    	return price;
    }
  	
    
    public ArrayList<Integer> getSortedQuantity (ArrayList<AskerItem> sorted){
    	ArrayList<Integer> quan = new ArrayList<Integer>();
    	for(AskerItem a: sorted){
    		Integer i = a.getQuantity();
    		quan.add(i);
    		System.out.print(quan);
    	}
    	return quan;
    }
    
}



/**
 * @author Ashley Ridout
 * @date August 15, 2017
 * 
 */
package cs622Project;

// import classes from util, io and swing packages
import java.util.ArrayList;
import java.io.*;
import java.util.List;
import javax.swing.JOptionPane;

// class to hold methods to rent items based on budget, 
// keep unrented and rented ArrayLists with correct elements
// and sent unrented item ArrayList to external text file
public class AskerRent {
	//variables
    AskerItem item = new AskerItem();
    public ArrayList<AskerItem> itemRentalList = new ArrayList<AskerItem>();
    
    private Double remainder;
    private String stringRemainder;
   
    //double get remainder method
    public Double getRemainder() {
        return remainder;
    }

    //set remainder method
    public void setRemainder(Double remainder) {
        this.remainder = remainder;
    }

    //String get remainder method
    public String getStringRemainder() {
        return stringRemainder;
    }

    //set remainder method
    public void setStringRemainder(String stringRemainder) {
        this.stringRemainder = stringRemainder;
    }

    /**rental method to rent an item from the wanted item list based on budget
     * 
     * @param wantedItemList is an ArrayList of Items the Asker wants to rent
     * @param budget is the Double total current budget the Asker is willing to pay
     * @returnrentedItems is the ArrayList of Items the Asker will rent within budget
     */
    public ArrayList<KeeperItem> rental (ArrayList<KeeperItem> wantedItemList, Double budget){
    	
        ArrayList<KeeperItem> rentedItems = new ArrayList<KeeperItem>();
        
        //traverse ArrayList of wanted items
        for(int i=0; i < wantedItemList.size(); i++)
        {
        	//if budget is greater than the price of the current item and budget is not zero
            if(budget >= wantedItemList.get(i).getPrice() && !(budget == 0))
            {
            	//new budget equals old budget minus the price of item multiplied by the quantity
                budget = budget - (wantedItemList.get(i).getPrice() * wantedItemList.get(i).getQuantity());
                
                //if budget is not less than zero, move all the wanted item to the rented items ArrayList
                if(!(budget < 0)){
                    rentedItems.add(i, wantedItemList.get(i));
                    //remainder after transaction equals new budget
                    remainder = budget; 
                }     
            } else if (budget <= 0){
            	JOptionPane.showMessageDialog(null, "Please retry with a positive number for budget");
            	break;
            }        
        }
        //return items rented
        return rentedItems;
    }
    
    // method to remove items that were rented from the wanted item list
    public ArrayList<KeeperItem> removeDuplicate (ArrayList<KeeperItem> wantedItemList, ArrayList<KeeperItem> rentedItems){
    	 for(KeeperItem ai: rentedItems){
         	if(wantedItemList.contains(ai))
         		wantedItemList.remove(ai);
         }
    	 return wantedItemList;
    }
    
    // method to send String of unrented AskerItem(s) to a txt file
    // and print this to the JOptionPane
    public String sendToTxtFile(ArrayList<KeeperItem> outputToFile){
    	String text = outputToFile.toString();
    	//Getting the output stream of the file for writing
    	try{
    	        File file = new File("UnrentedRecord.txt");
    	        FileOutputStream fos = new FileOutputStream(file);
    	        PrintWriter pw = new PrintWriter(fos);
    	        
    	        JOptionPane.showMessageDialog(null, text + " was written to  UnrentedRecord.txt file.");
    	
    	        pw.write(text);
    	        pw.flush();
    	        fos.close();	
    	        pw.close();
    	}catch(FileNotFoundException fnf){
    	fnf.printStackTrace();
    	}catch(IOException ioe){
    		ioe.printStackTrace();
    	}  	
    	return text;
    }
}



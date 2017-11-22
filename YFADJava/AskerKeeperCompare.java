/**
 * @author Ashley Ridout
 * @date August 16, 2017
 * 
 */
package cs622Project;

//import ArrayList class
import java.util.ArrayList;

import javax.swing.JOptionPane;

// class to hold methods to compare Asker wanted items to Keeper available items
public class AskerKeeperCompare {

	// empty constructor
	public AskerKeeperCompare() {
	};

	// KeeperItem ArrayList to hold ArrayList from YFAD database
	ArrayList<KeeperItem> compare = KeeperImportSQL.importDatabase();

	ArrayList<String> names;
	ArrayList<Double> prices;
	ArrayList<Integer> quantities;

	// method to get name of item from database
	public ArrayList<String> getNameFromDB() {
		String dbName;
		ArrayList<String> nameArray = new ArrayList<String>();

		for (int i = 0; i < compare.size(); i++) {
			dbName = compare.get(i).getName();
			nameArray.add(dbName);
		}
		return nameArray;
	}

	// method to get price of item from database
	public ArrayList<Double> getPriceFromDB() {
		double dbPrice;
		ArrayList<Double> priceArray = new ArrayList<Double>();

		for (int i = 0; i < compare.size(); i++) {
			dbPrice = compare.get(i).getPrice();
			priceArray.add(dbPrice);
		}
		return priceArray;
	}

	// method to compare the inputed name and price to the KeeperItem available
	// items
	public ArrayList<KeeperItem> compares1(ArrayList<AskerItem> askArray, ArrayList<KeeperItem> keepArray) {
		ArrayList<KeeperItem> askerMatchesKeeper = new ArrayList<KeeperItem>();
		KeeperItem k = new KeeperItem();
		AskerItem a = new AskerItem();
		for (AskerItem aa : askArray) {
			for (int i = 0; i < keepArray.size(); i++) {

				if (aa.getName().equalsIgnoreCase(keepArray.get(i).getName())
						&& aa.getPrice() >= keepArray.get(i).getPrice()
						&& aa.getQuantity() <= keepArray.get(i).getQuantity()) {
					k = keepArray.get(i);
					k.setQuantity(aa.getQuantity());
					askerMatchesKeeper.add(k);
					System.out.println("\nMATCH " + askerMatchesKeeper);
				} 
			}
		}
		return askerMatchesKeeper;
	}

}

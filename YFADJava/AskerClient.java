/**
 * @author Ashley Ridout
 * @date August 15, 2017
 * @references https://docs.oracle.com/javase/tutorial/uiswing/events/intro.html
 * 				https://docs.oracle.com/javase/tutorial/uiswing/components/button.html
 * 				https://docs.oracle.com/javase/tutorial/i18n/format/numberFormat.html
 */

package cs622Project;

//import appropriate awt, swing, beans, text and util packages
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.text.*;
import java.util.ArrayList;

//class to run Gui and YFAD methods
public class AskerClient extends JPanel implements PropertyChangeListener, ActionListener {
	// variables
	JButton button;
	JButton button2;
	ArrayList<AskerItem> askerArray = new ArrayList<AskerItem>();
	ArrayList<KeeperItem> askerMatchesKeeper = new ArrayList<KeeperItem>();
	

	// initial variables for textfields
	private String name = " ";
	private double price = 0.00;
	private int priority = 1;
	private int quantity = 1;

	// labels to identify fields
	private JLabel nameLabel;
	private JLabel priceLabel;
	private JLabel priorityLabel;
	private JLabel quantityLabel;
	private JLabel paymentLabel;

	// message for the labels
	private static String nameString = "Name of Item:";
	private static String priceString = "Willing to Pay: ";
	private static String priorityString = "Priority: ";
	private static String quantityString = "Quantity: ";
	private static String paymentString = "Total Cost for Item: ";

	// fields for data entry
	private JFormattedTextField nameField;
	private JFormattedTextField priceField;
	private JFormattedTextField priorityField;
	private JFormattedTextField quantityField;
	private JFormattedTextField paymentField;

	// number formats to format and parse numbers
	private NumberFormat priceFormat;
	private NumberFormat priorityFormat;
	private NumberFormat quantityFormat;
	private NumberFormat paymentFormat;

	// constructor that sets up layout
	public AskerClient() {
		// main panel
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		setUpFormats();

		// subpanel
		JPanel subPanel = new JPanel();

		// button 1
		button = new JButton("Add to Wanted List");
		button.setPreferredSize(new Dimension(150, 40));
		subPanel.add(button);
		button.addActionListener(this);

		// button 2
		button2 = new JButton("Get my Rentals");
		button2.setPreferredSize(new Dimension(150, 40));
		subPanel.add(button2);
		button2.addActionListener(new Action2());

		// add subpanel with buttons
		panel1.add(subPanel, BorderLayout.EAST);

		// execute compute payment method 
		double payment = computePayment(price, quantity);

		// create labels.
		nameLabel = new JLabel(nameString);
		priceLabel = new JLabel(priceString);
		priorityLabel = new JLabel(priorityString);
		quantityLabel = new JLabel(quantityString);
		paymentLabel = new JLabel(paymentString);

		// set up text fields
		nameField = new JFormattedTextField();
		nameField.setValue(new String(name));
		nameField.setColumns(10);
		nameField.addPropertyChangeListener("value", this);

		priceField = new JFormattedTextField(priceFormat);
		priceField.setValue(new Double(price));
		priceField.setColumns(10);
		priceField.addPropertyChangeListener("value", this);

		priorityField = new JFormattedTextField(priorityFormat);
		priorityField.setValue(new Integer(priority));
		priorityField.setColumns(10);
		priorityField.addPropertyChangeListener("value", this);

		quantityField = new JFormattedTextField(quantityFormat);
		quantityField.setValue(new Integer(quantity));
		quantityField.setColumns(10);
		quantityField.addPropertyChangeListener("value", this);

		paymentField = new JFormattedTextField(paymentFormat);
		paymentField.setValue(new Double(payment));
		paymentField.setColumns(10);
		paymentField.setEditable(false);
		paymentField.setForeground(Color.blue);

		// pair up labels and textfields
		nameLabel.setLabelFor(nameField);
		priceLabel.setLabelFor(priceField);
		priorityLabel.setLabelFor(priorityField);
		quantityLabel.setLabelFor(quantityField);
		paymentLabel.setLabelFor(paymentField);

		// lay out the labels in a panel.
		JPanel labelPane = new JPanel(new GridLayout(0, 1));
		labelPane.add(nameLabel);
		labelPane.add(priceLabel);
		labelPane.add(priorityLabel);
		labelPane.add(quantityLabel);
		labelPane.add(paymentLabel);

		// layout the text fields in a panel.
		JPanel fieldPane = new JPanel(new GridLayout(0, 1));
		fieldPane.add(nameField);
		fieldPane.add(priceField);
		fieldPane.add(priorityField);
		fieldPane.add(quantityField);
		fieldPane.add(paymentField);

		// labels on left, text fields at end of label, and buttons to the right
		setBorder(BorderFactory.createEmptyBorder(60, 20, 60, 20));
		add(labelPane, BorderLayout.CENTER);
		add(fieldPane, BorderLayout.LINE_END);
		add(subPanel, BorderLayout.EAST);
	}

	// called when a field's value property changes
	public void propertyChange(PropertyChangeEvent e) {
		Object source = e.getSource();
		if (source == nameField) {
			name = ((String) nameField.getValue());
		} else if (source == priceField) {
			price = ((Number) priceField.getValue()).doubleValue();
		} else if (source == priorityField) {
			priority = ((Number) priorityField.getValue()).intValue();
		} else if (source == quantityField) {
			quantity = ((Number) quantityField.getValue()).intValue();
		}

		//change computePayment as field's value property changes
		double payment = computePayment(price, quantity);
		paymentField.setValue(new Double(payment));
	}

	// action listener for button 1
	public void actionPerformed(ActionEvent e) {
		try {
			// store input as AskerItem
			AskerItem askIt3 = new AskerItem(name, price, priority, quantity);

			// store each AskerItem in an ArrayList
			askerArray.add(askIt3);
			System.out.println("ArrayList of Items\n" + askerArray);
			
			// reset textfield to blank
			nameField.setText("");
			priceField.setText("");
			priorityField.setText("");
			quantityField.setText(" ");
			
			
			// sort asker list
			AskerPriority askPri = new AskerPriority();
			ArrayList<AskerItem> sortedArray = askPri.bubbleSort(askerArray);
			System.out.println("Sorted array:\n" + sortedArray);

			// import database keeper list
			KeeperImportSQL kee = new KeeperImportSQL();
			ArrayList<KeeperItem> keepArray = kee.importDatabase();

			// Names & prices of available items
			AskerKeeperCompare akc = new AskerKeeperCompare();

			ArrayList<String> dbNames = akc.getNameFromDB();
			ArrayList<Double> dbPrices = akc.getPriceFromDB();
			System.out.print("\nNames of available items: " + dbNames);
			System.out.print("\nPrices of available items: " + dbPrices);

			// store keeper items that matches asker items as an ArrayList
			askerMatchesKeeper = akc.compares1(sortedArray, keepArray);
			
			// catch null pointer exception, print message and print location of error
		} catch(NullPointerException np){
			System.out.println("Please enter valid input.");
			np.printStackTrace();
			// catch exception and print location of error
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	class Action2 implements ActionListener {
		//action listener for button 2
		public void actionPerformed(ActionEvent f) {
			try {
				// get keeper names that match
				
				KeeperItem askIte = new KeeperItem();
				
				ArrayList<String> namesAskerItem = askIte.getNamesFromItemList(askerMatchesKeeper);
				
				//output JOptionPane to user
				String dialogString = JOptionPane.showInputDialog(null,
						"You have chosen " + namesAskerItem
								+ ". Please enter a total budget to purchase highest priority items first.",
						"YFD Input Budget", JOptionPane.PLAIN_MESSAGE);

				// parse input to double for processing
				double budgetInput = Double.parseDouble(dialogString);
				System.out.println("budget is " + budgetInput);

				// AskerRent object to rent object based on budget
				AskerRent askRen = new AskerRent();

				// get budget, rented items, unrented items and remaining funds
				// of keeper/asker matching rentals
				ArrayList<KeeperItem> askerBudgetArray = askRen.rental(askerMatchesKeeper, budgetInput);
				ArrayList<KeeperItem> unrentedArray = askRen.removeDuplicate(askerMatchesKeeper, askerBudgetArray);
				
				
				JOptionPane.showMessageDialog(null,
						"Budget is $" + budgetInput + ".\nRented items are " + askerBudgetArray.toString()
								+ ".\nUnrented items are " + unrentedArray.toString() + ".\nRemaining funds are $"
								+ String.format("%.2f", askRen.getRemainder()));
				
				// send unrented items with price and quantity to a txt file
				askRen.sendToTxtFile(unrentedArray);
				// catch null pointer exception, print message and print location of error
			} catch(NullPointerException np){
				System.out.println("Please enter valid input.");
				np.printStackTrace();
				// catch all other exceptions and print location of error
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	private static void createAndShowGUI() {
		try {
			// Create and set up the window.
			JFrame frame = new JFrame("Welcome to YFAD");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// Add contents to the window.
			frame.add(new AskerClient());

			// Create and set up the content pane.
			JComponent newContentPane = new AskerClient();
			newContentPane.setOpaque(true); // content panes must be opaque
			frame.setContentPane(newContentPane);

			// Display the window.
			frame.pack();
			frame.setVisible(true);
			// catch exception and print location of error
		} catch (Exception c) {
			c.printStackTrace();
		}
	}

	// main method to run GUI
	public static void main(String[] args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	// method to compute the total cost for item based on the price and quantity
	double computePayment(double price, int quantity) {
		double answer = price * quantity;
		return answer;
	}

	// method to create number formats that also parse numbers input by user
	private void setUpFormats() {
		priceFormat = NumberFormat.getNumberInstance();
		priceFormat.setMinimumFractionDigits(2);

		priorityFormat = NumberFormat.getNumberInstance();

		quantityFormat = NumberFormat.getNumberInstance();

		paymentFormat = NumberFormat.getCurrencyInstance();
	}
}

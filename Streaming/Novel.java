/**
 * @author Ashley.Ridout
 * @date July 30, 2017
 */
package homework4;

//class to hold Novel constructor, get, set and toString methods
public class Novel {
	// declare variables
	public String title;
	public String author;
	public int year;

	// constructor with title, author and year as arguments
	public Novel(String title, String author, int year) {
		this.title = title;
		this.author = author;
		this.year = year;
	}

	// getTitle method returns title
	public String getTitle() {
		return title;
	}

	// setTitle method assigns title
	public void setTitle(String title) {
		this.title = title;
	}

	// getAuthor method returns author
	public String getAuthor() {
		return author;
	}

	// setAuthor method assigns author
	public void setAuthor(String author) {
		this.author = author;
	}

	// getYear method returns year
	public int getYear() {
		return year;
	}

	// setYear method assigns year
	public void setYear(int year) {
		this.year = year;
	}

	// overrides Java's toString method to an appropriate message
	@Override
	public String toString() {
		return "The title of this book is " + getTitle() + " by " + getAuthor() + " and was published in " + getYear();
	}
}

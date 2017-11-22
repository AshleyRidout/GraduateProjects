/**
 * @author Ashley.Ridout
 * @date July 29, 2017
 */
package homework4;

//import java io package class Serializable
import java.io.Serializable;

//class to hold Student constructors, get, set and toString methods
public class Student implements Serializable {

	/**
	 * declare serial version id 1L
	 */
	private static final long serialVersionUID = 1L;
	// declare variables
	public int id;
	public String name;
	public int score;

	// empty constructor
	public Student() {

	}

	// constructor with id, name and score arguments
	public Student(int id, String name, int score) {
		this.id = id;
		this.name = name;
		this.score = score;
	}

	// getID method returns id
	public int getId() {
		return id;
	}

	// setID method assigns id
	public void setID(int id) {
		this.id = id;
	}

	// getName method returns name
	public String getName() {
		return name;
	}

	// setName method assigns name
	public void setName(String name) {
		this.name = name;
	}

	// getScore method returns score
	public int getScore() {
		return score;
	}

	// setScore method assigns score
	public void setScore(int score) {
		this.score = score;
	}

	// overrides Java's toString method to an appropriate message
	@Override
	public String toString() {
		return "Student ID is " + getId() + ", Student name is " + getName() + ", Student score is " + getScore();
	}
}

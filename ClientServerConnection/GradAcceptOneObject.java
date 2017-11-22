/**
 * @author Ashley Ridout
 * @data August 14, 2017
 * @reference Boston University CS 622 Summer 2 2017 Module 6 
 */
package homework6;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//class to hold processInput method to get acceptance decision and composite score
public class GradAcceptOneObject implements Serializable {
	// serial version for serializable
	private static final long serialVersionUID = 1L;

	// variables for constructor
	private Double GPA;
	private Double GRE;
	private Double LOR1;
	private Double LOR2;

	// Acceptance decision strings that are printed to the console based on the
	// composite score
	private String[] accepted = { ". You've been accepted!", ". You are on a waitlist.",
			". Unfortunately you are rejected." };

	// constructor with 4 arguments
	public GradAcceptOneObject(Double GPA, Double GRE, Double LOR1, Double LOR2) {
		this.GPA = GPA;
		this.GRE = GRE;
		this.LOR1 = LOR1;
		this.LOR2 = LOR2;
	}

	// get GPA method
	public Double getGPA() {
		return GPA;
	}

	// set GPA method
	public void setGPA(Double GPA) {
		this.GPA = GPA;
	}

	// get GRE method
	public Double getGRE() {
		return GRE;
	}

	// set GRE method
	public void setGRE(Double GRE) {
		this.GRE = GRE;
	}

	// get Letter of Recommendation 1 method
	public Double getLOR1() {
		return LOR1;
	}

	// set Letter of Recommendation 1 method
	public void setLOR1(Double LOR1) {
		this.LOR1 = LOR1;
	}

	// get Letter of Recommendation 2 method
	public Double getLOR2() {
		return LOR2;
	}

	// set Letter of Recommendation 2 method
	public void setLOR2(Double LOR2) {
		this.LOR2 = LOR2;
	}

	// to String method to print each part of constructor
	public String toString() {
		return "GPA = " + getGPA() + " ; GRE = " + getGRE() + " ; LOR1 = " + getLOR1() + " ; LOR2 = " + getLOR2();
	}

	/**
	 * Method to process input and produce a composite score and acceptance
	 * decision
	 * 
	 * @param score
	 *            is an int that holds the score for the current category
	 * @param scoreList
	 *            is and Integer ArrayList that holds the score for each
	 *            category: GPA, GRE, LOR1 & LOR2
	 * @return output is a string that prints to the client class an acceptance
	 *            message
	 */
	public String processInput() {
		int score = 0;
		List<Integer> scoreList = new ArrayList<>();

		String output = null;

		if (this.GPA < 2.5) {
			score = 0;
		} else if (this.GPA >= 2.5 && this.GPA < 3.0) {
			score = 25;
		} else if (this.GPA >= 3.0 && this.GPA < 3.5) {
			score = 30;
		} else if (this.GPA >= 3.5 && this.GPA < 4.0) {
			score = 35;
		} else if (this.GPA >= 4.0) {
			score = 40;
		}
		// add GPA score to scoreList ArrayList
		scoreList.add(score);

		if (this.GRE < 140) {
			score = 0;
		} else if (this.GRE >= 140 && this.GRE < 145) {
			score = 20;
		} else if (this.GRE >= 145 && this.GRE < 150) {
			score = 25;
		} else if (this.GRE >= 150 && this.GRE < 155) {
			score = 30;
		} else if (this.GRE >= 155 && this.GRE < 160) {
			score = 35;
		} else if (this.GRE >= 160) {
			score = 40;
		} // add GRE score to scoreList ArrayList
		scoreList.add(score);

		if (this.LOR1 <= 1) {
			score = 0;
		} else if (this.LOR1 > 1 && this.LOR1 <= 2) {
			score = 2;
		} else if (this.LOR1 > 2 && this.LOR1 <= 3) {
			score = 5;
		} else if (this.LOR1 > 3 && this.LOR1 <= 4) {
			score = 8;
		} else if (this.LOR1 > 4) {
			score = 10;
		} // add LOR1 score to scoreList ArrayList
		scoreList.add(score);

		if (this.LOR2 <= 1) {
			score = 0;
		} else if (this.LOR2 > 1 && this.LOR2 <= 2) {
			score = 2;
		} else if (this.LOR2 > 2 && this.LOR2 <= 3) {
			score = 5;
		} else if (this.LOR2 > 3 && this.LOR2 <= 4) {
			score = 8;
		} else if (this.LOR2 > 4) {
			score = 10;
		} // add LOR2 score to scoreList ArrayList
		scoreList.add(score);

		//find sum of all scores
		Integer sum = 0;
		for (int s : scoreList)
			sum += s;
		// assign acceptance decision based on composite score
		if (sum >= 85) {
			return "Your composite score is " + sum.toString() + accepted[0];
		} else if (sum >= 75 && sum < 85) {
			return "Your composite score is " + sum.toString() + accepted[1];
		} else if (sum < 75) {
			return "Your composite score is " + sum.toString() + accepted[2];
		}

		// return output to client
		return output;
	}
}

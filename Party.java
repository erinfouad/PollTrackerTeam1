import java.awt.Color;
import java.util.ArrayList;

/**
 * The Party class represents a single political party by its name, projected
 * number of seats and percentage of votes it is expected to win, and its
 * colour.
 * 
 * Public methods in this class get and set all instance variables, calculate
 * the projected percentage of seats the political party is expected to win,
 * visually represent the political party's projected votes or seats in
 * comparison to the amount required to win, and create a string representation
 * of a party's instance variables.
 * 
 * @author erinf
 * @version 1.0
 * @since 2021-11-01
 */

public class Party {
	private String name;
	private float projectedNumberOfSeats;
	private float projectedPercentageOfVotes;
	private Color partyColour;

	/**
	 * Creates a political party with the specified name.
	 * 
	 * @param aName This parameter is the name of the political party
	 */
	public Party(String aName) {
		name = aName;
	}

	/**
	 * This method creates a political party with the specified name, projected
	 * number of seats, and projected percentage of votes.
	 * 
	 * @param aName                This parameter is the name of the political party
	 * @param aProjNumSeats        This parameter is the projected number of seats
	 *                             the party is expected to win and must be greater
	 *                             than zero.
	 * @param aProjPercentageVotes This parameter is the projected percentage of
	 *                             votes the party is expected to win and must be
	 *                             between zero and one (inclusive).
	 */
	public Party(String aName, float aProjNumSeats, float aProjPercentageVotes) {
		name = aName;
		// set projected number of seats and percentage of votes if valid inputs
		setProjectedNumberOfSeats(aProjNumSeats);
		setProjectedPercentageOfVotes(aProjPercentageVotes);
	}

	/**
	 * This method prints "Invalid Input" to indicate a condition has not been met
	 * by a parameter.
	 */
	private void errorStatement() {
		System.out.println("Invalid input");
	}

	/**
	 * This method gets the name of the political party.
	 * 
	 * @return This returns a string representing the name of the political party.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * This method sets the name of the political party.
	 * 
	 * @param name This parameter is the string assigned to this party's name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method gets the number of seats the political party is projected to win.
	 * 
	 * @return This returns the float value of the projected number of seats this
	 *         party is projected to win.
	 */
	public float getProjectedNumberOfSeats() {
		return this.projectedNumberOfSeats;
	}

	/**
	 * This method sets the political party's projected number of seats if it is a
	 * valid input and prints and error statement otherwise.
	 * 
	 * @param aProjNumSeats This parameter is the projected number of seats the
	 *                      party is expected to win and must be greater than zero.
	 */
	public void setProjectedNumberOfSeats(float aProjNumSeats) {
		if (aProjNumSeats >= 0) {
			this.projectedNumberOfSeats = aProjNumSeats;
		} else {
			errorStatement();
		}
	}

	/**
	 * This method gets the percentage of votes the political party is projected to
	 * win.
	 * 
	 * @return This returns the float value of the projected percentage of votes
	 *         this party is projected to win
	 */
	public float getProjectedPercentageOfVotes() {
		return this.projectedPercentageOfVotes;
	}

	/**
	 * This method sets the political party's projected percentage of votes if it is
	 * a valid input and prints and error statement otherwise.
	 * 
	 * @param aProjPercentageVotes This parameter is the projected percentage of
	 *                             votes the party is expected to win and must be
	 *                             between zero and one (inclusive).
	 */
	public void setProjectedPercentageOfVotes(float aProjPercentageVotes) {
		if (aProjPercentageVotes >= 0 && aProjPercentageVotes <= 1) {
			this.projectedPercentageOfVotes = aProjPercentageVotes;
		} else {
			errorStatement();
		}
	}

	/**
	 * This method gets the Color of this political party.
	 * 
	 * @return This returns the name of the colour of this political party, as an
	 *         instance of the Color class.
	 */
	public Color getColour() {
		return this.partyColour;
	}

	/**
	 * This method sets the Color of this political party.
	 * 
	 * @param partyColour This parameter is an instance of the Color class.
	 */
	public void setColour(Color partyColour) {
		this.partyColour = partyColour;
	}

	/**
	 * This method determines the percentage of seats the political party is
	 * expected to win and will print an error statement if the argument is an
	 * invalid input.
	 * 
	 * @param totalNumofParlSeats This parameter is the total number of seats
	 *                            available in parliament, which should be a
	 *                            positive number.
	 * @return double This returns the quotient of the political party's projected
	 *         number of seats and total number of seats. If the totalNumofParlSeats
	 *         is negative, this method will return 0.
	 */
	public double projectedPercentOfSeats(int totalNumofParlSeats) {
		double percentOfSeats = 0;
		if (totalNumofParlSeats > 0) {
			percentOfSeats = this.projectedNumberOfSeats / totalNumofParlSeats;
		} else {
			errorStatement();
		}

		return percentOfSeats;
	}

	/**
	 * This method creates a string of a row of stars indicated by the first
	 * parameter, a bar that marks the halfway point of the maximum number of stars
	 * (2nd parameter), and a party's toString representation. This method is called
	 * to represent a party's expected number of seats or votes, indicated by the
	 * stars, and compared to the number of seats needed for a majority in
	 * parliament, indicated by the bar.
	 * 
	 * @param numOfStars This parameter is the number of stars to add to the string
	 * @param maxStars   This parameter is the maximum number of stars that can be
	 *                   displayed on a single line
	 * @return This returns a string of stars, a bar, and the toString
	 *         representation of that party
	 */
	private String starArray(int numOfStars, int maxStars) {
		// the bar is placed at the halfway point of the maximum number of stars,
		// rounded up.
		int barIndex = (int) Math.ceil(maxStars / 2.0);
		// An ArrayList is created to loop through and set string values at indices.
		// Spaces are used to fill all non-star or bar indices, so the list is filled
		// with
		// " " and necessary indices are replaced with *, |, and toString
		// representation.
		ArrayList<String> starArray = new ArrayList<String>(maxStars + 2);
		for (int i = 0; i < maxStars + 1; i++) {
			starArray.add(i, " ");
		}
		for (int j = 0; j < numOfStars; j++) {
			starArray.set(j, "*");
		}

		starArray.add(barIndex, "|");
		starArray.add(maxStars + 2, this.toString());

		String starString = String.join("", starArray);
		return starString;
	}

	/**
	 * This method creates a visual representation a party's expected number of
	 * seats, indicated by *s, so it can be compared to the number of seats needed
	 * for a majority in parliament, indicated by a |, as well as that party's
	 * toString representation.
	 * 
	 * @param maxStars          This parameter is the maximum number of stars that
	 *                          can be displayed in one line and must be a positive
	 *                          number.
	 * @param numOfSeatsPerStar This parameter is the number of seats represented by
	 *                          each star and must be a positive number.
	 * @return This returns a string of stars and a bar as as well as that party's
	 *         toString representation.This will return blank if either parameter
	 *         input is not positive.
	 */
	public String textVisualizationBySeats(int maxStars, double numOfSeatsPerStar) {
		String textVisualization;
		// Validate that parameters are positive. Else, return blank string.
		if (maxStars > 0 && numOfSeatsPerStar > 0) {
			// The number of stars is the projected number of seats proportional to
			// the number of seats represented by each star, rounded down.
			int numOfStars = (int) (this.projectedNumberOfSeats / numOfSeatsPerStar);
			// Call starArray method to create the string representation.
			textVisualization = starArray(numOfStars, maxStars);
		} else {
			errorStatement();
			textVisualization = "";
		}
		return textVisualization;
	}

	/**
	 * This method creates a visual representation a party's expected number of
	 * seats, indicated by *s, so it can be compared to the number of seats needed
	 * for a majority in parliament, indicated by a |, as well as that party's
	 * toString representation.
	 * 
	 * @param maxStars          This parameter is the maximum number of stars that
	 *                          can be displayed in one line and must be a positive
	 *                          number.
	 * @param numOfVotesPerStar This parameter is the number of votes represented by
	 *                          each star and must be a positive number.
	 * @return This returns a string of stars and a bar as as well as that party's
	 *         toString representation. This will return blank if either parameter
	 *         input is not positive.
	 */
	public String textVisualizationByVotes(int maxStars, double numOfVotesPerStar) {
		String textVisualization;
		// Validate that parameters are positive. Else, return blank string.
		if (maxStars > 0 && numOfVotesPerStar > 0) {
			// The number of stars is the projected percentage of votes proportional to
			// the number of seats represented by each star, rounded down.
			int numOfStars = (int) ((this.projectedPercentageOfVotes * 100) / numOfVotesPerStar);
			// Call starArray method to create the string representation.
			textVisualization = starArray(numOfStars, maxStars);
		} else {
			errorStatement();
			textVisualization = "";
		}
		return textVisualization;
	}

	/**
	 * This method creates a string representation of a party's instance variables.
	 * If there is no Color assigned to the party, the method will not include the
	 * partyColour instance variable in the string representation.
	 * 
	 * @return This returns a string representation of the party's instance
	 *         variables
	 */
	public String toString() {
		String toString;
		if (partyColour != null) {
			toString = name + " ([" + partyColour.getRed() + "," + partyColour.getGreen() + "," + partyColour.getBlue()
					+ "], " + Math.round(projectedPercentageOfVotes * 100) + "% of votes, " + projectedNumberOfSeats
					+ " seats)";
		} else {
			toString = name + " (" + Math.round(projectedPercentageOfVotes * 100) + "% of votes, "
					+ projectedNumberOfSeats + " seats)";
		}
		return toString;
	}

}

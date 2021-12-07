/**
 * The Poll class represents a single poll. It allows the user to name the poll,
 * add parties to the poll, and retrieve information about parties that have been
 * added to the poll.
 * 
 * @author Sebastian Reinberg-Abernethy
 * @version 1.0
 * @since 2021/11/22
 *
 */
public class Poll {

	private String name;
	private Party[] parties;
	private int partiesInPoll = 0;

	/**
	 * 
	 * @param aName
	 * @param maxParties This is the constructor for the poll class, it initializes
	 *                   the poll, sets the name of the poll, and sets the maximum
	 *                   number of parties allowed in the poll.
	 */
	public Poll(String aName, int maxParties) {

		/**
		 * This statement ensures that the maximum number of parties is not more than 10
		 * or less than 0. If the user gives an integer outside this range then the
		 * maximum number of parties is set to 10.
		 */

		if (maxParties > 10 || maxParties < 1) {
			maxParties = 10;
		}
		name = aName;
		parties = new Party[maxParties];
	}

	/**
	 * This getter method returns the name of the poll
	 * 
	 * @return name
	 */

	public String getPollName() {
		return name;
	}

	/**
	 * This getter method returns the array of the parties in the poll
	 * 
	 * @return parties
	 */

	public Party[] getParties() {
		return parties;
	}

	/**
	 * This getter method returns the number of parties inside the poll
	 * 
	 * @return partiesInPoll
	 */

	public int getPartiesInPoll() {
		return partiesInPoll;
	}

	/**
	 * This method takes the name of a party as an argument and checks whether that
	 * party is in the poll. If the party is in the poll the method will return that
	 * party, otherwise the method will return null
	 * 
	 * @param partyName
	 * @return aParty or null
	 */

	public Party getParty(String partyName) {

		for (Party aParty : parties) {

			// This checks whether there is a party in the poll with the same name as the
			// name given by the user

			if (aParty.getName().equalsIgnoreCase(partyName)) {
				return aParty;
			}
		}
		return null;
	}

	/**
	 * This method returns the number of parties currently in the poll
	 * 
	 * @return numParties
	 */

	public int getNumberOfParties() {
		int numParties = 0;
		for (Party aParty : parties) {

			// Check to ensure that null parties are not added to the count

			if (aParty != null) {
				numParties++;
			}
		}
		return numParties;
	}

	/**
	 * This method adds parties to the poll, and takes a party as a parameter.
	 * 
	 * If the given party has the same name as another party already in the parties
	 * array, the existing party is replaced by the party provided as an argument.
	 * If no party with the same name exists in the poll, this party is added at the
	 * end of the list and the partiesInPoll instance variable is incremented by
	 * one. If there is no room left in the parties array, there is an error message
	 * that the poll is full and no further parties can be added.
	 * 
	 * @param aParty
	 */

	public void addParty(Party aParty) {

		// Check to ensure the given party isn't null

		if (aParty == null) {
			System.out.print("Error, Party is Null.");
		}

		// Check to ensure that the given party isn't already in the poll

		if (checkDuplicate(aParty)) {

			// Next if statements ensure that the parties array isn't full

		} else if (partiesInPoll < parties.length) {
			parties[partiesInPoll] = aParty;
			partiesInPoll++;
		} else if (partiesInPoll > parties.length) {
			System.out.print("Error, Poll is full and further parties can't be added.");
		}
	}

	/**
	 * Method returns a visual representation of the parties in the poll and their
	 * data, as determined by the number of seats a party is expected to win
	 * 
	 * The parameters maxStars and numOfSeatsPerStar are the two arguments that need
	 * to be passed to the party’s textVisualizationBySeats method
	 * 
	 * @param maxStars
	 * @param numOfSeatsPerStar
	 * @return data
	 */

	public String textVisualizationBySeats(int maxStars, double numOfSeatsPerStar) {
		String partyInfo = "\n";

		// This will add the string representation of the parties and their data to the
		// local variable "data"

		if (partiesInPoll > 1) {
			for (Party aParty : parties) {
				partyInfo = partyInfo + aParty.textVisualizationBySeats(maxStars, numOfSeatsPerStar) + "\n";
			}

			// Special case for if there is only one party in the poll, as in this case the
			// party array at index 1 will be null

		} else if (partiesInPoll == 1) {
			partyInfo = partyInfo + parties[0].textVisualizationBySeats(maxStars, numOfSeatsPerStar);
		}

		return name + partyInfo;
	}

	/**
	 * Method returns a visual representation of the parties in the poll and their
	 * data, as determined by the number of votes a party is expected to get
	 * 
	 * @param maxStars
	 * @param percentOfVotesPerStar
	 * @return data
	 */

	public String textVisualizationByVotes(int maxStars, double percentOfVotesPerStar) {

		String partyInfo = "\n";

		// This will add the string representation of the parties and their data to the
		// local variable "data"

		if (partiesInPoll > 1) {
			for (Party aParty : parties) {
				partyInfo = partyInfo + aParty.textVisualizationByVotes(maxStars, percentOfVotesPerStar) + "\n";
			}

			// Special case for if there is only one party in the poll, as in this case the
			// party array at index 1 will be null

		} else if (partiesInPoll == 1) {
			partyInfo = partyInfo + parties[0].textVisualizationByVotes(maxStars, percentOfVotesPerStar);
		}

		return name + partyInfo;
	}

	/**
	 * Method returns the name of the poll and the names of the parties inside the
	 * poll
	 */

	public String toString() {
		String partyNames = "\n";

		// This will add the names of the parties to the local variable "names"

		if (partiesInPoll > 1) {
			for (Party aParty : parties) {
				partyNames = partyNames + aParty.toString() + "\n";
			}

			// Special case for if there is only one party in the poll, as in this case the
			// party array at index 1 will be null

		} else if (partiesInPoll == 1) {
			partyNames = partyNames + parties[0].getName();
		}
		return name + partyNames;
	}

	/**
	 * Private method to check whether a party is inside a poll
	 * 
	 * @param partyToCheck
	 * @return boolean
	 */

	private boolean checkDuplicate(Party partyToCheck) {
		int index = 0;
		while (index < partiesInPoll) {

			// If statement checks whether there is a party in the poll with the same name
			// as the party given as an argument

			if (parties[index].getName().equalsIgnoreCase(partyToCheck.getName())) {
				parties[index] = partyToCheck;
				return true;
			} else {
				index++;
			}
		}
		return false;
	}
}

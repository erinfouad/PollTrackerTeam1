import java.util.Scanner;

/**
 * Displays a visual representation of a poll list based on user input. Polls
 * can be user created or randomly generated.
 * 
 * @author gabe
 * @version 0.1
 * @since 2021-12-06
 */
public class TextApplication {
	private PollList polls;

	/**
	 * This method provides a text visualization of the parties in a given poll's
	 * data based on the amount of seats the parties receive
	 * 
	 * @param aPoll The parameter is a poll object
	 */
	public void displayPollDataBySeat(Poll aPoll) {
		int seatsPerStar = this.polls.getAmountPerStar("seats");
		System.out.println(aPoll.textVisualizationBySeats(this.polls.MAX_STARS_FOR_VISUALIZATION, seatsPerStar));
		return;
	}

	/**
	 * Provides a text visualization of the each poll in the poll list based in the
	 * amount of projected seats for each party.
	 * 
	 * @param partyNames The parties to display in the visualization.
	 */
	public void displayPollsBySeat(String[] partyNames) {
		for (Poll aPoll : this.polls.polls) {
			displayPollDataBySeat(aPoll);
		}

		displayPollDataBySeat(polls.getAggregatePoll(partyNames));
		return;
	}

	/**
	 * This method provides a text visualization of a given poll's data based on the
	 * amount of votes the parties receive
	 * 
	 * @param aPoll The poll to display.
	 */
	public void displayPollDataByVote(Poll aPoll) {
		int percentPerStar = this.polls.getAmountPerStar("votes");
		System.out.println(aPoll.textVisualizationByVotes(this.polls.MAX_STARS_FOR_VISUALIZATION, percentPerStar));
		return;
	}

	/**
	 * Provides a text visualization of the each poll in the poll list based in the
	 * amount of votes for each party.
	 * 
	 * @param partyNames The parties to display in the visualization.
	 */
	public void displayPollsByVote(String[] partyNames) {
		for (Poll aPoll : this.polls.polls) {
			displayPollDataByVote(aPoll);
		}

		displayPollDataByVote(polls.getAggregatePoll(partyNames));
	}

	/**
	 * Runs the program, calling the appropriate methods to create different
	 * visualizations of polls based on the user's input.
	 */
	private void run() {
		Scanner myScanner = new Scanner(System.in);

		// These next statements prompt the user for the number of seats, party
		// names, number of polls, allows the user to create polls manually or randomly,
		// and allows the user to choose to display the parties by seats or votes.
		System.out.print("How many seats are available in the election? ");
		int numOfSeats = myScanner.nextInt();

		System.out.println("Which parties are in the election (provide names, comma separated):");
		String partyNames = myScanner.next();
		String[] partyNamesArray = partyNames.split(",", -1);

		System.out.print("How many polls do you want to track with this application? ");
		int numOfPolls = myScanner.nextInt();

		System.out.print("Would you like me to create a random set of polls? ");
		String createRandomPolls = myScanner.next().toLowerCase();

		System.out.print("Would you like to visualize by seats or by votes? ");
		String visualizationMode = myScanner.next().toLowerCase();

		// sets the choice to true to indicate random polls or false for user created.
		boolean choice = true;
		if (createRandomPolls.equals("yes"))
			choice = true;
		else if (createRandomPolls.equals("no"))
			choice = false;

		createPollsRandomOrManually(choice, numOfSeats, partyNamesArray, numOfPolls);

		// The display method will be called repeatedly until the user enters "quit"
		// inside the display method.
		boolean running = true;
		while (running)
			running = display(visualizationMode, partyNamesArray);
		myScanner.close();
		System.exit(0);
	}

	/**
	 * Creates and runs the text application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TextApplication aTextApplication = new TextApplication();
		System.out.println("Welcome to the poll tracker");
		aTextApplication.run();
	}

	/**
	 * Prompts and gets input from the user in the form of an integer.
	 * 
	 * @param prompt A string indicating to the user what to enter
	 * @return The integer entered by the user.
	 */

	private int getIntInput(String prompt) {

		System.out.print(prompt);
		Scanner myScanner = new Scanner(System.in);
		int input = myScanner.nextInt();
		myScanner.close();
		return input;
	}

	/**
	 * This method prompts the user for all the information needed to create a new
	 * set of polls
	 *
	 * @param partyNamesArray The parameter is an array of all the party names,
	 *                        which the user has already provided
	 */

	private void promptForPollList(String[] partyNamesArray) {
		Scanner myScanner = new Scanner(System.in);
		for (int i = 0; i < this.polls.polls.length; i++) {
			System.out.print("Enter the name of poll " + (i + 1) + ": ");
			String name = myScanner.next();
			Poll pollToAdd = new Poll(name, partyNamesArray.length);
			for (String partyName : partyNamesArray) {
				System.out.print("Enter the expected number of seats for " + partyName + ": ");
				float expectedSeats = myScanner.nextFloat();
				System.out.print("Enter the expected percentage of the vote for " + partyName + ": ");
				float expectedVotes = myScanner.nextFloat();
				pollToAdd.addParty(new Party(partyName, expectedSeats, expectedVotes));
			}
			this.polls.addPoll(pollToAdd);
		}
	}

	/**
	 * Takes user input for the option of visualization to print and displays it.
	 * 
	 * @param visualizationMode The method to display visuals, either by votes or
	 *                          seats.
	 * @param partyNamesArray   The names the parties that will be displayed.
	 * @return A boolean value indicating whether to call the function again or quit
	 *         out the program.
	 * 
	 */
	private boolean display(String visualizationMode, String[] partyNamesArray) {

		boolean keepRunning = true;
		Scanner myScanner = new Scanner(System.in);
		System.out.print("\nOptions: all (show result of all polls), aggregate (show aggregate result), "
				+ "quit (end application) \nChoose an option: ");
		String optionSelected = myScanner.next().toLowerCase();

		// Calls the appropriate display method based on previously entered options
		// (seat/vote & aggregate/all)
		switch (optionSelected) {
		case "aggregate":
			if (visualizationMode.equals("seats"))
				displayPollDataBySeat(this.polls.getAggregatePoll(partyNamesArray));
			if (visualizationMode.equals("votes"))
				displayPollDataByVote(this.polls.getAggregatePoll(partyNamesArray));
			break;
		case "all":
			if (visualizationMode.equals("seats"))
				displayPollsBySeat(partyNamesArray);
			if (visualizationMode.equals("votes"))
				displayPollsByVote(partyNamesArray);
			break;
		case "quit":
			keepRunning = false;
			break;
		}
		return keepRunning;
	}

	/**
	 * Uses the Factory class to generate a poll list to be used in the election
	 * visualization.
	 * 
	 * @param numOfSeats      The number of seats available in the election.
	 * @param partyNamesArray The parties to be added to each poll.
	 * @param numOfPolls      The number of polls to be added to the list.
	 */
	private void createRandomPoll(int numOfSeats, String[] partyNamesArray, int numOfPolls) {
		Factory pollFactory = new Factory(numOfSeats);
		pollFactory.setPartyNames(partyNamesArray);
		this.polls = pollFactory.createRandomPollList(numOfPolls);
	}

	/**
	 * Calls the appropriate method to either create polls randomly or from user
	 * input. This method does not take any user input directly, instead prior
	 * entered input must be passed in as parameters.
	 * 
	 * @param choice          Either true, indicating random polls will be
	 *                        generated, or false, allowing for user entered polls.
	 * @param numOfSeats      The number of seats that will be available in the poll
	 *                        list.
	 * @param partyNamesArray The names of the parties to be included in the polls.
	 * @param numOfPolls      The number of polls to create.
	 */
	private void createPollsRandomOrManually(boolean choice, int numOfSeats, String[] partyNamesArray, int numOfPolls) {
		// This is for if the user chooses to generate polls randomly
		if (choice)
			createRandomPoll(numOfSeats, partyNamesArray, numOfPolls);

		// This is for if the user wants to generate the polls manually
		else {
			this.polls = new PollList(numOfPolls, numOfSeats);
			promptForPollList(partyNamesArray);
		}
	}
}

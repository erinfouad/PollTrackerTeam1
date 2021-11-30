import java.util.Scanner;

/**
 * @author gabe
 * @version 0.1
 */
public class TextApplication {
	private PollList polls;

	public void displayPollDataBySeat(Poll aPoll) {
		int seatsPerStar = this.polls.getNumOfSeats() / this.polls.MAX_STARS_FOR_VISUALIZATION; // duplicate from pollList, fix later
		if (seatsPerStar < 1.0*this.polls.getNumOfSeats() / this.polls.MAX_STARS_FOR_VISUALIZATION) seatsPerStar++;
		System.out.println(aPoll.textVisualizationBySeats(this.polls.MAX_STARS_FOR_VISUALIZATION, seatsPerStar));
		return;
	}

	public void displayPollsBySeat(String[] partyNames) {
		for (Poll aPoll : this.polls.polls) {
			displayPollDataBySeat(aPoll);
		}
		
		displayPollDataBySeat(polls.getAggregatePoll(partyNames));		
		return;
	}
	
	public void displayPollDataByVote(Poll aPoll) {
		int percentPerStar = 100 / this.polls.MAX_STARS_FOR_VISUALIZATION; // duplicate from pollList, fix later
		if (percentPerStar < 1.0*this.polls.getNumOfSeats() / this.polls.MAX_STARS_FOR_VISUALIZATION) percentPerStar++;
		System.out.println(aPoll.textVisualizationByVotes(this.polls.MAX_STARS_FOR_VISUALIZATION, percentPerStar));
		return;
	}
	
	public void displayPollsByVote(String[] partyNames) {
		for (Poll aPoll : this.polls.polls) {
			displayPollDataByVote(aPoll);
		}
		
		displayPollDataByVote(polls.getAggregatePoll(partyNames));
	}
	
	public void promptForPollList(String[] partyNamesArray) {
		Scanner myScanner = new Scanner(System.in);
		for(int i = 0; i < this.polls.polls.length; i++) {
			System.out.print("Enter the name of poll " + (i+1) + ": ");
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
	
	private void run() {
		System.out.println("Welcome to the poll tracker");
		Scanner myScanner = new Scanner(System.in);
		
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
		
		if (createRandomPolls.equals("yes")) {
			Factory pollFactory = new Factory(numOfSeats);
			pollFactory.setPartyNames(partyNamesArray);
			this.polls = pollFactory.createRandomPollList(numOfPolls);
		} else if (createRandomPolls.equals("no")) {
			this.polls = new PollList(numOfPolls, numOfSeats);
			promptForPollList(partyNamesArray);
		}
		
		boolean running = true;
		while (running) {
			System.out.print("\nOptions: all (show result of all polls), aggregate (show aggregate result), "
					+ "quit (end application) \nChoose an option: ");
			String optionSelected = myScanner.next().toLowerCase();
			switch(optionSelected) {
				case "aggregate":
					if (visualizationMode.equals("seats")) displayPollDataBySeat(this.polls.getAggregatePoll(partyNamesArray));
					if (visualizationMode.equals("votes")) displayPollDataByVote(this.polls.getAggregatePoll(partyNamesArray));
					break;
				case "all":
					if (visualizationMode.equals("seats")) displayPollsBySeat(partyNamesArray);
					if (visualizationMode.equals("votes")) displayPollsByVote(partyNamesArray);
					break;
				case "quit":
					System.exit(0);
			}
		}
	}
	
	
	public static void main(String[] args) {
		TextApplication aTextApplication = new TextApplication();
		
		aTextApplication.run();
	}

}

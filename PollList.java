/**
 * This class combines a number of polls from the same election. It can be used to extract aggregate data about the
 * polls or data relating to parties in the polls.
 * 
 * @author gabe
 *
 */
public class PollList {
	// Used for text visualizations of poll/party data.
	public int MAX_STARS_FOR_VISUALIZATION = 18;
	public Poll[] polls = new Poll[5];
	// The total number of seats available across all polls.
	private int numOfSeats = 10;
	
	/**
	 * Creates an new, empty PollList with a specified number of seats and polls.
	 * @param numOfPolls This is the number of polls in the election represented by the PollList object
	 * @param numOfSeats This is of seats available in the election
	 */
	public PollList(int numOfPolls, int numOfSeats) {
		if (numOfPolls > 0) this.polls = new Poll[numOfPolls];
		if (numOfSeats > 0) this.numOfSeats = numOfSeats;
	}
	

	public Poll[] toArray() {
		return this.polls;
	}
	
	/**
	 * Displays the total number of seats in the election as well as a visual representation of each poll
	 */
	public String toString() {
		return "Number of seats: " + this.getNumOfSeats() + "\n" +
			this.textVisualizationBySeats();
	}
	
	public int getNumOfSeats() {
		return this.numOfSeats;
	}
	
	
	/**
	 * Displays a visual representation of each poll, based on the projected number of seats attained.
	 * @return string The complete, multi-line visualization of each poll, based on seats
	 */
	public String textVisualizationBySeats() {
		int seatsPerStar = this.numOfSeats / this.MAX_STARS_FOR_VISUALIZATION;
		if (seatsPerStar < 1.0*this.numOfSeats / this.MAX_STARS_FOR_VISUALIZATION) seatsPerStar++;
		String visualization = "";
		for (int i = 0; i < polls.length; i++) {
			if (polls[i] != null) {
				visualization += (polls[i].textVisualizationBySeats(this.MAX_STARS_FOR_VISUALIZATION, seatsPerStar)+"\n");
			} else {
				System.out.println("Poll "+ i + " is empty");
			}
		}
		return visualization;
	}
	/**
	 * Displays a visual representation of each poll, based on projected percentage of votes attained.
	 * @return string The complete, multi-line visualization of each poll, based on percentage of votes.
	 */
	public String textVisualizationByVotes() {
		int percentPerStar = 100 / this.MAX_STARS_FOR_VISUALIZATION;
		if (percentPerStar < 1.0*this.numOfSeats / this.MAX_STARS_FOR_VISUALIZATION) percentPerStar++;
		String visualization = "";
		for (int i = 0; i < polls.length; i++) {
			if (polls[i] != null) {
				visualization += (polls[i].textVisualizationByVotes(this.MAX_STARS_FOR_VISUALIZATION, percentPerStar)+"\n");
			} else {
				System.out.println("Poll "+ i + " is empty");
			}
		}
		return visualization;
	}
	/**
	 * See the overall performance of a party across all polls that the party is included in.
	 * @param partyName The name of the party to find average data for.
	 * @return Party The average party data of the party.
	 */
	public Party getAveragePartyData(String partyName) {
		Party avgParty = new Party(partyName);
		float seats, seatsPolls, votesPercent, votesPolls;
		seats = seatsPolls = votesPercent = votesPolls = 0;
		// Adds party data from each poll to the count, but only for polls the party is in.
		for (int poll = 0; poll < this.polls.length; poll++) {
			try {
				Party party = this.polls[poll].getParty(partyName);
				if (party.seats > 0) {
					seats += party.seats;
					seatsPolls++;
				}
				if (party.votes > 0) {
					votesPercent += party.votes;
					votesPolls++;
				}
			} catch (Exception e) {
				System.out.println("The party is not in " + this.polls[poll].name);
			}
		}
		if (seatsPolls > 0) avgParty.seats = seats / seatsPolls;
		if (votesPolls > 0) avgParty.votes = votesPercent / votesPolls;
		return avgParty;
	}
	/**
	 * Combine all the polls in the election into one using average data from each party to see overall outcomes of 
	 * the election.
	 * @param partyNamesList The parties to retrieve data on.
	 * @return A poll showing the average data of all the parties.
	 */
	public Poll getAggregatePoll(String[] partyNamesList) {
		Poll aggregate = new Poll("Aggregate", partyNamesList.length);
		for (int partyName = 0; partyName < partyNamesList.length; partyName++) {
			aggregate.parties[partyName] = this.getAveragePartyData(partyNamesList[partyName]);
		}
		
		return aggregate;
	}
	/**
	 * Fills the next empty spot in the list with a specified poll.
	 * @param aPoll The poll to add.
	 */
	public void addPoll(Poll aPoll) {
		if (aPoll == null) {
			System.out.println("Error, input poll is null");
			return;
		}
		for (int i = 0; i < this.polls.length; i++) {
			if (this.polls[i] == null) {
				this.polls[i] = aPoll;
				return;
			}
		}
		System.out.println("Error: List full, no further polls may be added");
	}	
}

import static org.junit.Assert.*;

import org.junit.Test;

public class PollListTest {
	class PollMock extends Poll {
		String name;
		PartyMock[] parties;
		int nextIndex = 0;
		double expectedSeatsPerStar;
		double expectedVotesPerStar;
		int expectedMaxStars;
		
		public PollMock(String name, int maxNumberOfParties) {
			super(name, maxNumberOfParties);
			this.name = name;
			parties = new PartyMock[maxNumberOfParties];
		}
		public String getName() {return name;}
		public void addParties(PartyMock[] parties) {
			this.parties = parties;
			nextIndex = 0;
		}
		public Party getParty(String partyName) {
			Party next = parties[nextIndex];
			nextIndex = (nextIndex + 1) % parties.length;
			return next;
		}
		public String textVisualizationBySeats(int maxStars, double numOfSeatsPerStar) {
			assertEquals("Unexpected value of maxStats passed to Poll.textVisualizationBySeats", expectedMaxStars, maxStars);
			assertEquals("Unexpected value of numOfSeatsPerStar passed to Poll.textVisualizationBySeats", expectedSeatsPerStar, numOfSeatsPerStar,0.00001);
			return name + " seats";
		}
		
		public String textVisualizationByVotes(int maxStars, double percentOfVotesPerStar) {
			assertEquals("Unexpected value of maxStats passed to Poll.textVisualizationByVotes", expectedMaxStars, maxStars);
			assertEquals("Unexpected value of numOfVotesPerStar passed to Poll.textVisualizationByVotes", expectedVotesPerStar, percentOfVotesPerStar,0.00001);
			return name + " votes";
		}
	}
	
	class PartyMock extends Party {
		public PartyMock(String partyName) {
			super(partyName);
		}
		
		public PartyMock(String partyName, float seats, float votes) {
			super(partyName, seats, votes);
		}
	}

	@Test
	public void test_Constructor_zeroPollsAndSeats() {
		PollList pl = new PollList(0,0);
		int expectedListLength = 5;
		int expectedNumOfSeats = 10;
		
		assertEquals("If number of polls is zero or less, expect number of polls to be adjusted to 5", expectedListLength, pl.toArray().length);
		assertEquals("If number of seats is zero or less, expect number of seats to be adjusted to 10", expectedNumOfSeats, pl.getNumOfSeats());
	}

	@Test
	public void test_Constructor_negativePollsAndSeats() {
		PollList pl = new PollList(-1,-2);
		int expectedListLength = 5;
		int expectedNumOfSeats = 10;
		
		assertEquals("If number of polls is zero or less, expect number of polls to be adjusted to 5", expectedListLength, pl.toArray().length);
		assertEquals("If number of seats is zero or less, expect number of seats to be adjusted to 10", expectedNumOfSeats, pl.getNumOfSeats());
	}

	@Test
	public void test_Constructor_onePollsAndtwoSeats() {
		PollList pl = new PollList(1,2);
		int expectedListLength = 1;
		int expectedNumOfSeats = 2;
		
		assertEquals("CreatedPollList for 1 poll", expectedListLength, pl.toArray().length);
		assertEquals("CreatedPollList for 2 seats", expectedNumOfSeats, pl.getNumOfSeats());
	}

	@Test
	public void test_Constructor_tenPollsAndoneSeats() {
		PollList pl = new PollList(10,1);
		int expectedListLength = 10;
		int expectedNumOfSeats = 1;
		
		assertEquals("CreatedPollList for 10 polls", expectedListLength, pl.toArray().length);
		assertEquals("CreatedPollList for 1 seat", expectedNumOfSeats, pl.getNumOfSeats());
	}
	
	@Test
	public void test_addPoll_toEmptyList() {
		Poll p = new PollMock("test", 5);
		PollList pl = new PollList(5, 20);
		
		pl.addPoll(p);
		
		assertEquals("Added poll to empty list, expected first poll in list to the poll added.", p, pl.toArray()[0]);
		assertNull("Added poll to empty list, expected second poll in list to be null.", pl.toArray()[1]);
		assertNull("Added poll to empty list, expected third poll in list to be null.", pl.toArray()[2]);
		assertNull("Added poll to empty list, expected fourth poll in list to be null.", pl.toArray()[3]);
		assertNull("Added poll to empty list, expected fifth poll in list to be null.", pl.toArray()[4]);
		
	}

	@Test
	public void test_addPoll_addingNull() {
		Poll p = new PollMock("test", 5);
		PollList pl = new PollList(5, 20);
		
		pl.addPoll(p);
		pl.addPoll(null);
		
		assertEquals("Added null, should leave list unchanged.  testing index 0.", p, pl.toArray()[0]);
		assertNull("Added null, should leave list unchanged.  testing index 1.", pl.toArray()[1]);
		assertNull("Added null, should leave list unchanged.  testing index 2.", pl.toArray()[2]);
		assertNull("Added null, should leave list unchanged.  testing index 3.", pl.toArray()[3]);
		assertNull("Added null, should leave list unchanged.  testing index 4.", pl.toArray()[4]);
		
	}

	@Test
	public void test_addPoll_fillList() {
		Poll p1 = new PollMock("test1", 1);
		Poll p2 = new PollMock("test2", 2);
		Poll p3 = new PollMock("test3", 3);
		PollList pl = new PollList(3, 45);
		
		pl.addPoll(p1);
		pl.addPoll(p2);
		pl.addPoll(p3);
		
		assertEquals("Added three polls to fill the list of size 3.  Testing poll at index 0", p1, pl.toArray()[0]);
		assertEquals("Added three polls to fill the list of size 3.  Testing poll at index 1", p2, pl.toArray()[1]);
		assertEquals("Added three polls to fill the list of size 3.  Testing poll at index 2", p3, pl.toArray()[2]);
	}

	@Test
	public void test_addPoll_listIsFull() {
		Poll p1 = new PollMock("test1", 1);
		Poll p2 = new PollMock("test2", 2);
		Poll p3 = new PollMock("test3", 3);
		PollList pl = new PollList(3, 45);
		
		pl.addPoll(p1);
		pl.addPoll(p2);
		pl.addPoll(p3);
		
		Poll p4 = new PollMock("duplicate", 234);
		pl.addPoll(p4);
		
		assertEquals("Adding fourth poll to full list should leave list unchanged.  Testing at index 0.", p1, pl.toArray()[0]);
		assertEquals("Adding fourth poll to full list should leave list unchanged.  Testing at index 1.", p2, pl.toArray()[1]);
		assertEquals("Adding fourth poll to full list should leave list unchanged.  Testing at index 2.", p3, pl.toArray()[2]);
	}
	
	@Test
	public void test_getAveragePartyData_PartyInAllPolls() {
		// setup first poll
		PartyMock[] parties1 = new PartyMock[3];
		parties1[0] = new PartyMock("zero", 100f, .25f);
		parties1[1] = new PartyMock("one", 200f, .5f);
		parties1[2] = new PartyMock("two", 100f, .25f);
		
		PollMock p1 = new PollMock("poll1", 400);
		p1.addParties(parties1);
		
		// setup second poll
		PartyMock[] parties2 = new PartyMock[3];
		parties2[1] = new PartyMock("zero", 50f, .1f);
		parties2[2] = new PartyMock("one", 300f, .75f);
		parties2[0] = new PartyMock("two", 50f, .15f);
		
		PollMock p2 = new PollMock("poll2", 400);
		p2.addParties(parties2);
		p2.nextIndex = 1;
		
		// setup third poll
		PartyMock[] parties3 = new PartyMock[3];
		parties3[2] = new PartyMock("zero", 200f, .1f);
		parties3[0] = new PartyMock("one", 100f, .75f);
		parties3[1] = new PartyMock("two", 100f, .15f);
		
		PollMock p3 = new PollMock("poll3", 400);
		p3.addParties(parties3);
		p3.nextIndex = 2;
		
		PollList pl = new PollList(3,400);
		pl.addPoll(p1);
		pl.addPoll(p2);
		pl.addPoll(p3);
		
		Party actual = pl.getAveragePartyData("zero");
		
		assertEquals("Party was in all polls in the list.  Testing average projected seats.", 116.66666, actual.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("Party was in all polls in the list.  Testing average projected votes.", .45f/3, actual.getProjectedPercentageOfVotes(), 0.0001);		
	}

	@Test
	public void test_getAveragePartyData_PartyInSomePolls() {
		// setup first poll
		PartyMock[] parties1 = new PartyMock[3];
		parties1[0] = new PartyMock("zero", 100f, .25f);
		parties1[1] = new PartyMock("one", 200f, .5f);
		parties1[2] = new PartyMock("two", 100f, .25f);
		
		PollMock p1 = new PollMock("poll1", 400);
		p1.addParties(parties1);
		p1.nextIndex = 1;
		
		// setup second poll
		PartyMock[] parties2 = new PartyMock[3];
		parties2[1] = new PartyMock("zero", 50f, .1f);
		parties2[2] = null;
		parties2[0] = new PartyMock("two", 50f, .15f);
		
		PollMock p2 = new PollMock("poll2", 400);
		p2.addParties(parties2);
		p2.nextIndex = 2;
		
		// setup third poll
		PartyMock[] parties3 = new PartyMock[3];
		parties3[2] = new PartyMock("zero", 200f, .1f);
		parties3[0] = new PartyMock("one", 100f, .75f);
		parties3[1] = new PartyMock("two", 100f, .15f);
		
		PollMock p3 = new PollMock("poll3", 400);
		p3.addParties(parties3);
		p3.nextIndex = 0;
		
		PollList pl = new PollList(3,400);
		pl.addPoll(p1);
		pl.addPoll(p2);
		pl.addPoll(p3);
		
		Party actual = pl.getAveragePartyData("one");
		
		assertEquals("Party was in two out of three polls in the list.  Testing average projected seats.", 150, actual.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("Party was in two out of three polls in the list.  Testing average projected votes.", 1.25f/2, actual.getProjectedPercentageOfVotes(), 0.0001);		
	}

	@Test
	public void test_getAveragePartyData_PartyInNoneOfThePolls() {
		// setup first poll
		PartyMock[] parties1 = new PartyMock[3];
		parties1[0] = null;
		parties1[1] = null;
		parties1[2] = null;
		
		PollMock p1 = new PollMock("poll1", 400);
		p1.addParties(parties1);
		p1.nextIndex = 1;
		
		// setup second poll
		PartyMock[] parties2 = new PartyMock[3];
		parties2[1] = null;
		parties2[2] = null;
		parties2[0] = null;
		
		PollMock p2 = new PollMock("poll2", 400);
		p2.addParties(parties2);
		p2.nextIndex = 2;
		
		// setup third poll
		PartyMock[] parties3 = new PartyMock[3];
		parties3[2] = null;
		parties3[0] = null;
		parties3[1] = null;
		
		PollMock p3 = new PollMock("poll3", 400);
		p3.addParties(parties3);
		p3.nextIndex = 0;
		
		PollList pl = new PollList(3,400);
		pl.addPoll(p1);
		pl.addPoll(p2);
		pl.addPoll(p3);
		
		Party actual = pl.getAveragePartyData("one");
		
		assertEquals("Party was in none of three polls in the list.  Testing average projected seats.", 0, actual.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("Party was in none of three polls in the list.  Testing average projected votes.", 0, actual.getProjectedPercentageOfVotes(), 0.0001);		
	}

	@Test
	public void test_getAggregatePoll_AllPollsSameSize() {
		// setup first poll
		PartyMock[] parties1 = new PartyMock[3];
		parties1[0] = new PartyMock("zero", 100f, .25f);
		parties1[1] = new PartyMock("one", 200f, .5f);
		parties1[2] = new PartyMock("two", 100f, .25f);
		
		PollMock p1 = new PollMock("poll1", 400);
		p1.addParties(parties1);
		
		// setup second poll
		PartyMock[] parties2 = new PartyMock[3];
		parties2[1] = new PartyMock("zero", 50f, .1f);
		parties2[2] = new PartyMock("one", 300f, .75f);
		parties2[0] = new PartyMock("two", 50f, .15f);
		
		PollMock p2 = new PollMock("poll2", 400);
		p2.addParties(parties2);
		p2.nextIndex = 1;
		
		// setup third poll
		PartyMock[] parties3 = new PartyMock[3];
		parties3[2] = new PartyMock("zero", 200f, .1f);
		parties3[0] = new PartyMock("one", 100f, .75f);
		parties3[1] = new PartyMock("two", 100f, .15f);
		
		PollMock p3 = new PollMock("poll3", 400);
		p3.addParties(parties3);
		p3.nextIndex = 2;
		
		PollList pl = new PollList(3,400);
		pl.addPoll(p1);
		pl.addPoll(p2);
		pl.addPoll(p3);
		
		String[] partyNames = {"zero","one","two"};
		
		Poll aggregate = pl.getAggregatePoll(partyNames);
		Party[] actualParties = aggregate.getParties();
		Party actualZero = null;
		Party actualOne = null;
		Party actualTwo = null;
		for (Party p : actualParties) {
			if (p.getName().equals("zero")) {
				actualZero = p;
			} else if (p.getName().equals("one")) {
				actualOne = p;
			} else if (p.getName().equals("two")) {
				actualTwo = p;
			}
		}
		
		assertEquals("Aggregate poll over three polls each the same size and same set of parties, testing aggregate poll name", "Aggregate", aggregate.getPollName());
		assertEquals("Aggregate poll over three polls each the same size and same set of parties, testing party 'zero' avg seats (avg of 100,50,200)", 116.6666f, actualZero.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("Aggregate poll over three polls each the same size and same set of parties, testing party 'zero' avg votes (avg of .25,.1,.1)", .15f, actualZero.getProjectedPercentageOfVotes(), 0.0001);
		assertEquals("Aggregate poll over three polls each the same size and same set of parties, testing party 'one' avg seats (avg of 200,300,100)", 200, actualOne.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("Aggregate poll over three polls each the same size and same set of parties, testing party 'one' avg votes (avg of .5,.75,.75)", 0.66667f, actualOne.getProjectedPercentageOfVotes(), 0.0001);
		assertEquals("Aggregate poll over three polls each the same size and same set of parties, testing party 'two' avg seats (avg of 100,50,100)", 83.33333f, actualTwo.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("Aggregate poll over three polls each the same size and same set of parties, testing party 'two' avg votes (avg of .25,.15,.15)", 0.18333f, actualTwo.getProjectedPercentageOfVotes(), 0.0001);
	}

	@Test
	public void test_getAggregatePoll_somePollsMissingParties() {
		// setup first poll
		PartyMock[] parties1 = new PartyMock[3];
		parties1[0] = new PartyMock("zero", 100f, .25f);
		parties1[1] = null;
		parties1[2] = new PartyMock("two", 100f, .25f);
		
		PollMock p1 = new PollMock("poll1", 400);
		p1.addParties(parties1);
		
		// setup second poll
		PartyMock[] parties2 = new PartyMock[3];
		parties2[1] = new PartyMock("zero", 50f, .1f);
		parties2[2] = new PartyMock("one", 300f, .5f);
		parties2[0] = new PartyMock("two", 50f, .15f);
		
		PollMock p2 = new PollMock("poll2", 400);
		p2.addParties(parties2);
		p2.nextIndex = 1;
		
		// setup third poll
		PartyMock[] parties3 = new PartyMock[3];
		parties3[2] = null;
		parties3[0] = new PartyMock("one", 100f, .75f);
		parties3[1] = new PartyMock("two", 100f, .15f);
		
		PollMock p3 = new PollMock("poll3", 400);
		p3.addParties(parties3);
		p3.nextIndex = 2;
		
		PollList pl = new PollList(3,400);
		pl.addPoll(p1);
		pl.addPoll(p2);
		pl.addPoll(p3);
		
		String[] partyNames = {"zero","one","two"};
		
		Poll aggregate = pl.getAggregatePoll(partyNames);
		Party[] actualParties = aggregate.getParties();
		Party actualZero = null;
		Party actualOne = null;
		Party actualTwo = null;
		for (Party p : actualParties) {
			if (p.getName().equals("zero")) {
				actualZero = p;
			} else if (p.getName().equals("one")) {
				actualOne = p;
			} else if (p.getName().equals("two")) {
				actualTwo = p;
			}
		}
		
		assertEquals("testing aggregate poll name", "Aggregate", aggregate.getPollName());
		assertEquals("testing party 'zero' avg seats (avg of 100,50)", 75, actualZero.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("testing party 'zero' avg votes (avg of .25,.1)", .175f, actualZero.getProjectedPercentageOfVotes(), 0.0001);
		assertEquals("testing party 'one' avg seats (avg of 300,100) (One poll is missing this party)", 200, actualOne.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("testing party 'one' avg votes (avg of .5,.75)(One poll is missing this party)", 0.625f, actualOne.getProjectedPercentageOfVotes(), 0.0001);
		assertEquals("testing party 'two' avg seats (avg of 100,50,100)", 83.33333f, actualTwo.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("testing party 'two' avg votes (avg of .25,.15,.15)", 0.18333f, actualTwo.getProjectedPercentageOfVotes(), 0.0001);
	}

	@Test
	public void test_toString_emptyList() {
		PollList pl = new PollList(5, 50);
		
		assertEquals("Number of seats: 50\n", pl.toString());
	}

	@Test
	public void test_toString_nonEmpytList() {
		PollList pl = new PollList(5, 180);
		PollMock p = new PollMock("poll1", 3);
		p.expectedMaxStars = 18;
		p.expectedSeatsPerStar = 10;
		pl.addPoll(p);
		
		PollMock p2 = new PollMock("poll2", 4);
		p2.expectedMaxStars = 18;
		p2.expectedSeatsPerStar = 10;
		pl.addPoll(p2);

		PollMock p3 = new PollMock("poll3", 5);
		p3.expectedMaxStars = 18;
		p3.expectedSeatsPerStar = 10;
		pl.addPoll(p3);

		String actual = pl.toString();
		String expectedLine0 = "Number of seats: 180";
		String expectedLine1 = "poll1 seats";
		String expectedLine2 = "poll2 seats";
		String expectedLine3 = "poll3 seats";
		String[] actualLines = actual.split("\n");
		
		assertEquals("Expected four lines of text from toString, one for number of seats and one for each poll in the list", 
				4, actualLines.length);
		assertEquals("Unexpected first line in visualization with three polls", 
				expectedLine0, actualLines[0]);
		assertEquals("Unexpected second line in visualization with three polls", 
				expectedLine1, actualLines[1]);
		assertEquals("Unexpected third line in visualization with three polls", 
				expectedLine2, actualLines[2]);
		assertEquals("Unexpected fourth line in visualization with three polls", 
				expectedLine3, actualLines[3]);
	}
	
	@Test
	public void test_textVisualizationByVotes_zeroPollsInList() {
		PollList pl = new PollList(10, 200);
		
		String actual = pl.textVisualizationByVotes();
		String expected = "";
		assertEquals("Expected visualization of empty poll list to be the empty string", 
				expected, actual);
	}

	@Test
	public void test_textVisualizationByVotes_onePollsInList() {
		PollList pl = new PollList(10, 200);
		PollMock p = new PollMock("poll1", 3);
		p.expectedMaxStars = 18;
		p.expectedVotesPerStar = 6;
		pl.addPoll(p);
		
		String actual = pl.textVisualizationByVotes();
		String expectedLine0 = "poll1 votes";
		String[] actualLines = actual.split("\n");
		
		assertEquals("Expected one line of text in vizualization of Poll list, one for the single poll", 
				1, actualLines.length);
		assertEquals("Unexpected first line in visualization with one poll", 
				expectedLine0, actualLines[0]);
	}

	@Test
	public void test_textVisualizationByVotes_manyPollsInList() {
		PollList pl = new PollList(3, 150);
		PollMock p = new PollMock("poll1", 3);
		p.expectedMaxStars = 18;
		p.expectedVotesPerStar = 6;
		pl.addPoll(p);
		
		PollMock p2 = new PollMock("poll2", 4);
		p2.expectedMaxStars = 18;
		p2.expectedVotesPerStar = 6;
		pl.addPoll(p2);

		PollMock p3 = new PollMock("poll3", 5);
		p3.expectedMaxStars = 18;
		p3.expectedVotesPerStar = 6;
		pl.addPoll(p3);

		String actual = pl.textVisualizationByVotes();
		String expectedLine0 = "poll1 votes";
		String expectedLine1 = "poll2 votes";
		String expectedLine2 = "poll3 votes";
		String[] actualLines = actual.split("\n");
		
		assertEquals("Expected three lines of text in vizualization of Poll list, one for each poll in the list", 
				3, actualLines.length);
		assertEquals("Unexpected first line in visualization with three polls", 
				expectedLine0, actualLines[0]);
		assertEquals("Unexpected second line in visualization with three polls", 
				expectedLine1, actualLines[1]);
		assertEquals("Unexpected third line in visualization with three polls", 
				expectedLine2, actualLines[2]);
	}


	@Test
	public void test_textVisualizationBySeats_zeroPollsInList() {
		PollList pl = new PollList(10, 200);
		
		String actual = pl.textVisualizationBySeats();
		String expected = "";
		assertEquals("Expected visualization of empty poll list to be the empty string", 
				expected, actual);
	}

	@Test
	public void test_textVisualizationBySeats_onePollsInList() {
		PollList pl = new PollList(10, 200);
		PollMock p = new PollMock("poll1", 3);
		p.expectedMaxStars = 18;
		p.expectedSeatsPerStar = 12;
		pl.addPoll(p);
		
		String actual = pl.textVisualizationBySeats();
		String expectedLine0 = "poll1 seats";
		String[] actualLines = actual.split("\n");
		
		assertEquals("Expected one line of text in vizualization of Poll list, one for the single poll", 
				1, actualLines.length);
		assertEquals("Unexpected first line in visualization with one poll", 
				expectedLine0, actualLines[0]);
	}

	@Test
	public void test_textVisualizationBySeats_manyPollsInList() {
		PollList pl = new PollList(5, 160);
		PollMock p = new PollMock("poll1", 3);
		p.expectedMaxStars = 18;
		p.expectedSeatsPerStar = 9;
		pl.addPoll(p);
		
		PollMock p2 = new PollMock("poll2", 4);
		p2.expectedMaxStars = 18;
		p2.expectedSeatsPerStar = 9;
		pl.addPoll(p2);

		PollMock p3 = new PollMock("poll3", 5);
		p3.expectedMaxStars = 18;
		p3.expectedSeatsPerStar = 9;
		pl.addPoll(p3);

		String actual = pl.textVisualizationBySeats();
		String expectedLine0 = "poll1 seats";
		String expectedLine1 = "poll2 seats";
		String expectedLine2 = "poll3 seats";
		String[] actualLines = actual.split("\n");
		
		assertEquals("Expected three lines of text in vizualization of Poll list, one for each poll in the list", 
				3, actualLines.length);
		assertEquals("Unexpected first line in visualization with three polls", 
				expectedLine0, actualLines[0]);
		assertEquals("Unexpected second line in visualization with three polls", 
				expectedLine1, actualLines[1]);
		assertEquals("Unexpected third line in visualization with three polls", 
				expectedLine2, actualLines[2]);
	}


}

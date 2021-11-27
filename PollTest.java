import static org.junit.Assert.*;

import org.junit.Test;

public class PollTest {
	class MockParty extends Party {
		String stringRepresentation;
		public MockParty(String partyName) {
			super(partyName);
			stringRepresentation = partyName;
		}
		public String toString() {
			return stringRepresentation;
		}
		public String getName() {
			return stringRepresentation;
		}
		public String textVisualizationBySeats(int maxStars, double numOfSeatsPerStar) {
			return "value from viz method in Party class with name " + 
					stringRepresentation +
					", maxStars " + maxStars + ", numOfSeatsPerStar " + numOfSeatsPerStar;
					
		}
		public String textVisualizationByVotes(int maxStars, double percentOfVotesPerStar) {
			return "value from votes viz method in Party class with name " + 
					stringRepresentation +
					", maxStars " + maxStars + ", percentOfVotesPerStar " + percentOfVotesPerStar;
					
		}
	}

	@Test
	public void test_constructor_validPollSize() {
		Poll p = new Poll("Poll Test", 5);
		assertEquals("Created poll with name 'Poll Test'", "Poll Test", p.getPollName());
		assertEquals("Created poll with room for 5 parties but did not add any parties yet", 0, p.getNumberOfParties());
	}

	@Test
	public void test_constructor_invalidZeroParties() {
		Poll p = new Poll("Poll Test 2", 0);
		assertEquals("Created poll with name 'Poll Test'", "Poll Test 2", p.getPollName());
		
		// should be able to add 10 parties
		Party p1 = new MockParty("p1");
		Party p2 = new MockParty("p2");
		Party p3 = new MockParty("p3");
		Party p4 = new MockParty("p4");
		Party p5 = new MockParty("p5");
		Party p6 = new MockParty("p6");
		Party p7 = new MockParty("p7");
		Party p8 = new MockParty("p8");
		Party p9 = new MockParty("p9");
		Party p10 = new MockParty("p10");
		
		p.addParty(p1);
		p.addParty(p2);
		p.addParty(p3);
		p.addParty(p4);
		p.addParty(p5);
		p.addParty(p6);
		p.addParty(p7);
		p.addParty(p8);
		p.addParty(p9);
		p.addParty(p10);
				

		assertEquals("Created poll with room for 0 parties, should be able to add 10 parties", 10, p.getNumberOfParties());
	}

	@Test
	public void test_constructor_invalidNegativeParties() {
		Poll p = new Poll("Poll Test", -1);
		assertEquals("Created poll with name 'Poll Test'", "Poll Test", p.getPollName());
		// should be able to add 10 parties
		Party p1 = new MockParty("p1");
		Party p2 = new MockParty("p2");
		Party p3 = new MockParty("p3");
		Party p4 = new MockParty("p4");
		Party p5 = new MockParty("p5");
		Party p6 = new MockParty("p6");
		Party p7 = new MockParty("p7");
		Party p8 = new MockParty("p8");
		Party p9 = new MockParty("p9");
		Party p10 = new MockParty("p10");
		
		p.addParty(p1);
		p.addParty(p2);
		p.addParty(p3);
		p.addParty(p4);
		p.addParty(p5);
		p.addParty(p6);
		p.addParty(p7);
		p.addParty(p8);
		p.addParty(p9);
		p.addParty(p10);
				

		assertEquals("Created poll with room for -1 parties, should be able to add 10 parties", 10, p.getNumberOfParties());
	}

	@Test
	public void test_addParty_firstAdd() {
		Poll p = new Poll("Poll Test", 4);
		Party party = new MockParty("PartyOne");
		p.addParty(party);
		assertEquals("Created poll with single party, expected number of parties to be one", 1, p.getNumberOfParties());
		assertEquals("Created poll with single party, expected that party to be found in getParty method.", party, p.getParty("PartyOne"));
	}

	@Test
	public void test_addParty_addTwoEachWithTheirOwnName() {
		Poll p = new Poll("Poll Test", 7);
		Party p1 = new MockParty("PartyOne");
		Party p2 = new MockParty("Party Two");
		p.addParty(p1);
		p.addParty(p2);
		assertEquals("Created poll with two unique parties, expected number of parties to be two", 2, p.getNumberOfParties());
		assertEquals("Created poll with two parties, expected party 'PartyOne' to be found in getParty method.", p1, p.getParty("PartyOne"));
		assertEquals("Created poll with two parties, expected party 'Party Two' to be found in getParty method.", p2, p.getParty("Party Two"));
	}

	@Test
	public void test_addParty_addUntilFullAllUnique() {
		Poll p = new Poll("Poll Test", 6);
		Party p1 = new MockParty("PartyOne");
		Party p2 = new MockParty("Party Two");
		Party p3 = new MockParty("Party3");
		Party p4 = new MockParty("Party 4");
		Party p5 = new MockParty("Fifth Party");
		Party p6 = new MockParty("6th Party");
		p.addParty(p1);
		p.addParty(p2);
		p.addParty(p3);
		p.addParty(p4);
		p.addParty(p5);
		p.addParty(p6);
		assertEquals("Created poll with six parties, expected number of parties to be six", 6, p.getNumberOfParties());
		assertEquals("Created poll with six parties, expected 'PartyOne' by getParty method.", p1, p.getParty("PartyOne"));
		assertEquals("Created poll with six parties, expected 'Party Two' by getParty method.", p2, p.getParty("Party Two"));
		assertEquals("Created poll with six parties, expected 'Party3' by getParty method.", p3, p.getParty("Party3"));
		assertEquals("Created poll with six parties, expected 'Party 4' by getParty method.", p4, p.getParty("Party 4"));
		assertEquals("Created poll with six parties, expected 'Fifth Party' by getParty method.", p5, p.getParty("Fifth Party"));
		assertEquals("Created poll with six parties, expected '6th Party' by getParty method.", p6, p.getParty("6th Party"));
	}

	@Test
	public void test_addParty_addOneToManyAllUnique() {
		Poll p = new Poll("Poll Test", 6);
		Party p1 = new MockParty("PartyOne");
		Party p2 = new MockParty("Party Two");
		Party p3 = new MockParty("Party3");
		Party p4 = new MockParty("Party 4");
		Party p5 = new MockParty("Fifth Party");
		Party p6 = new MockParty("6th Party");
		Party p7 = new MockParty("No room for 7th");
				
		p.addParty(p1);
		p.addParty(p2);
		p.addParty(p3);
		p.addParty(p4);
		p.addParty(p5);
		p.addParty(p6);
		p.addParty(p7);;
		
		assertEquals("Created poll with six parties, expected number of parties to be six", 6, p.getNumberOfParties());
		assertEquals("Created poll with six parties, expected 'PartyOne' by getParty method.", p1, p.getParty("PartyOne"));
		assertEquals("Created poll with six parties, expected 'Party Two' by getParty method.", p2, p.getParty("Party Two"));
		assertEquals("Created poll with six parties, expected 'Party3' by getParty method.", p3, p.getParty("Party3"));
		assertEquals("Created poll with six parties, expected 'Party 4' by getParty method.", p4, p.getParty("Party 4"));
		assertEquals("Created poll with six parties, expected 'Fifth Party' by getParty method.", p5, p.getParty("Fifth Party"));
		assertEquals("Created poll with six parties, expected '6th Party' by getParty method.", p6, p.getParty("6th Party"));
		assertNull("Created poll with six parties, expected 7th party add to fail, so getParty should return null", p.getParty("No room for 7th"));
	}

	@Test
	public void test_addParty_addDuplicate() {
		Poll p = new Poll("Poll Test", 7);
		Party p1 = new MockParty("PartyOne");
		Party p2 = new MockParty("PartyOne");
		p.addParty(p1);
		p.addParty(p2);
		assertEquals("Created poll with two parties with the same name, expected number of parties to be one", 1, p.getNumberOfParties());
		assertEquals("Created poll with two parties, expected party 'PartyOne' to be found in getParty method.", p2, p.getParty("PartyOne"));
	}

	@Test
	public void test_addParty_addDuplicateCaseInsensitive() {
		Poll p = new Poll("Poll Test", 7);
		Party p1 = new MockParty("PartyOne");
		Party p2 = new MockParty("partyONE");
		p.addParty(p1);
		p.addParty(p2);
		assertEquals("Created poll with two parties with the same name, expected number of parties to be one", 1, p.getNumberOfParties());
		assertEquals("Created poll with two parties, expected party 'partyONE' (second, duplicate) to be found in getParty method.", p2, p.getParty("PartyOne"));
	}

	@Test
	public void test_addParty_addDuplicateFirst() {
		Poll p = new Poll("Poll Test", 6);
		Party p1 = new MockParty("PartyOne");
		Party p2 = new MockParty("Party Two");
		Party p3 = new MockParty("Party3");
		Party p4 = new MockParty("Party 4");
		Party p5 = new MockParty("Fifth Party");
		Party p6 = new MockParty("6th Party");
		Party p7 = new MockParty("PartyOne");
				
		p.addParty(p1);
		p.addParty(p2);
		p.addParty(p3);
		p.addParty(p4);
		p.addParty(p5);
		p.addParty(p6);
		p.addParty(p7);;
		
		assertEquals("Created poll with six parties, expected number of parties to be six", 6, p.getNumberOfParties());
		assertEquals("Created poll with six parties, expected 'PartyOne' by getParty method to be duplicate.", p7, p.getParty("PartyOne"));
		assertEquals("Created poll with six parties, expected 'Party Two' by getParty method.", p2, p.getParty("Party Two"));
		assertEquals("Created poll with six parties, expected 'Party3' by getParty method.", p3, p.getParty("Party3"));
		assertEquals("Created poll with six parties, expected 'Party 4' by getParty method.", p4, p.getParty("Party 4"));
		assertEquals("Created poll with six parties, expected 'Fifth Party' by getParty method.", p5, p.getParty("Fifth Party"));
		assertEquals("Created poll with six parties, expected '6th Party' by getParty method.", p6, p.getParty("6th Party"));
	}

	@Test
	public void test_addParty_addDuplicateLast() {
		Poll p = new Poll("Poll Test", 6);
		Party p1 = new MockParty("PartyOne");
		Party p2 = new MockParty("Party Two");
		Party p3 = new MockParty("Party3");
		Party p4 = new MockParty("Party 4");
		Party p5 = new MockParty("Fifth Party");
		Party p6 = new MockParty("6th Party");
		Party p7 = new MockParty("6th Party");
				
		p.addParty(p1);
		p.addParty(p2);
		p.addParty(p3);
		p.addParty(p4);
		p.addParty(p5);
		p.addParty(p6);
		p.addParty(p7);;
		
		assertEquals("Created poll with six parties, expected number of parties to be six", 6, p.getNumberOfParties());
		assertEquals("Created poll with six parties, expected 'PartyOne' by getParty method.", p1, p.getParty("PartyOne"));
		assertEquals("Created poll with six parties, expected 'Party Two' by getParty method.", p2, p.getParty("Party Two"));
		assertEquals("Created poll with six parties, expected 'Party3' by getParty method.", p3, p.getParty("Party3"));
		assertEquals("Created poll with six parties, expected 'Party 4' by getParty method.", p4, p.getParty("Party 4"));
		assertEquals("Created poll with six parties, expected 'Fifth Party' by getParty method.", p5, p.getParty("Fifth Party"));
		assertEquals("Created poll with six parties, expected '6th Party' to be replaced by duplicate add.", p7, p.getParty("6th Party"));
	}

	@Test
	public void test_addParty_null() {
		Poll p = new Poll("Poll Test", 6);
		Party p1 = new MockParty("PartyOne");
		assertEquals("Call addParty with value of argument null.  Should leave array unchanged.", 0, p.getNumberOfParties());		
	}

	@Test
	public void test_toString_emptyPoll() {
		Poll p = new Poll("Poll test toString", 4);
		assertEquals("getting string representation of empty poll with name 'Poll test toString'", "Poll test toString", p.toString().trim());
	}

	@Test
	public void test_toString_oneParty() {
		Poll p = new Poll("Poll name", 4);
		Party p1 = new MockParty("p1");
		p.addParty(p1);
		assertEquals("getting string representation of poll with name 'Poll name' and one party", "Poll name\np1", p.toString().trim());
	}

	@Test
	public void test_toString_manyParties() {
		Poll p = new Poll("Poll name too", 3);
		Party p1 = new MockParty("p1");
		Party p2 = new MockParty("p2");
		Party p3 = new MockParty("p3");

		p.addParty(p1);
		p.addParty(p2);
		p.addParty(p3);

		assertEquals("getting string representation of poll with name 'Poll name' and three parties", "Poll name too\np1\np2\np3", p.toString().trim());
	}

	@Test
	public void test_textVisualizationBySeats_zeroPartiesInPoll() {
		Poll p = new Poll("Test Viz by Seats", 5);
		String actual = p.textVisualizationBySeats(10, 12);
		String expected = "Test Viz by Seats\n";
		assertEquals("Expected poll without parties to just have the poll name in the vizualization followed by a newline.", 
				expected, actual);
	}

	@Test
	public void test_textVisualizationBySeats_onePartyInPoll()
	{
		Poll p = new Poll("Test Viz by Seats", 5);
		Party p1 = new MockParty("Party1");
		p.addParty(p1);
		String actual = p.textVisualizationBySeats(10, 12.0);
		String expectedLine1 = "Test Viz by Seats";
		String expectedLine2 = "value from viz method in Party class with name Party1, maxStars 10, numOfSeatsPerStar 12.0"; 

		String[] actualLines = actual.split("\n");
		assertEquals("Expected text vizualization to have two lines, one for poll name, one for info about single party in poll",
				2, actualLines.length);
		assertEquals("Expected for first line of text vizualization to have poll name",
				
				expectedLine1, actualLines[0]);
		assertEquals("Expected second line of text vizualization to have data returned by party vizualization method",
				expectedLine2, actualLines[1]);
	}
	
	@Test
	public void test_textVisualizationBySeats_manyPartiesInPoll()
	{
		Poll p = new Poll("Test Viz by Seats", 5);
		Party p1 = new MockParty("Party1");
		Party p2 = new MockParty("Party2");
		Party p3 = new MockParty("Party3");
		Party p4 = new MockParty("Party4");
		Party p5 = new MockParty("Party5");
		p.addParty(p1);
		p.addParty(p2);
		p.addParty(p3);
		p.addParty(p4);
		p.addParty(p5);
		
		String actual = p.textVisualizationBySeats(10, 12.0);
		
		String expectedLine0 = "Test Viz by Seats";
		String expectedLine1 = "value from viz method in Party class with name Party1, maxStars 10, numOfSeatsPerStar 12.0"; 
		String expectedLine2 = "value from viz method in Party class with name Party2, maxStars 10, numOfSeatsPerStar 12.0"; 
		String expectedLine3 = "value from viz method in Party class with name Party3, maxStars 10, numOfSeatsPerStar 12.0"; 
		String expectedLine4 = "value from viz method in Party class with name Party4, maxStars 10, numOfSeatsPerStar 12.0"; 
		String expectedLine5 = "value from viz method in Party class with name Party5, maxStars 10, numOfSeatsPerStar 12.0"; 

		String[] actualLines = actual.split("\n");
		assertEquals("Expected text vizualization to have six lines, one for poll name, one for info about each of the five parties in poll",
				6, actualLines.length);
		assertEquals("Expected for first line of text vizualization to have poll name",
				
				expectedLine0, actualLines[0]);
		assertEquals("Expected second line of text vizualization to have data returned by party vizualization method",
				expectedLine1, actualLines[1]);
		assertEquals("Expected second line of text vizualization to have data returned by party vizualization method",
				expectedLine2, actualLines[2]);
		assertEquals("Expected second line of text vizualization to have data returned by party vizualization method",
				expectedLine3, actualLines[3]);
		assertEquals("Expected second line of text vizualization to have data returned by party vizualization method",
				expectedLine4, actualLines[4]);
		assertEquals("Expected second line of text vizualization to have data returned by party vizualization method",
				expectedLine5, actualLines[5]);
	}
	

	@Test
	public void test_textVisualizationByVotes_zeroPartiesInPoll() {
		Poll p = new Poll("Test Viz by Votes", 5);
		String actual = p.textVisualizationByVotes(10, 12);
		String expected = "Test Viz by Votes\n";
		assertEquals("Expected poll without parties to just have the poll name in the vizualization followed by a newline.", 
				expected, actual);
	}

	@Test
	public void test_textVisualizationByVotes_onePartyInPoll()
	{
		Poll p = new Poll("Test Viz by Votes", 5);
		Party p1 = new MockParty("Party1");
		p.addParty(p1);
		String actual = p.textVisualizationByVotes(10, 12.0);
		String expectedLine1 = "Test Viz by Votes";
		String expectedLine2 = "value from votes viz method in Party class with name Party1, maxStars 10, percentOfVotesPerStar 12.0"; 

		String[] actualLines = actual.split("\n");
		assertEquals("Expected text vizualization to have two lines, one for poll name, one for info about single party in poll",
				2, actualLines.length);
		assertEquals("Expected for first line of text vizualization to have poll name",
				
				expectedLine1, actualLines[0]);
		assertEquals("Expected second line of text vizualization to have data returned by party vizualization method",
				expectedLine2, actualLines[1]);
	}
	
	@Test
	public void test_textVisualizationByVotes_manyPartiesInPoll()
	{
		Poll p = new Poll("Test Viz by Votes", 5);
		Party p1 = new MockParty("Party1");
		Party p2 = new MockParty("Party2");
		Party p3 = new MockParty("Party3");
		Party p4 = new MockParty("Party4");
		Party p5 = new MockParty("Party5");
		p.addParty(p1);
		p.addParty(p2);
		p.addParty(p3);
		p.addParty(p4);
		p.addParty(p5);
		
		String actual = p.textVisualizationByVotes(10, 12.0);
		
		String expectedLine0 = "Test Viz by Votes";
		String expectedLine1 = "value from votes viz method in Party class with name Party1, maxStars 10, percentOfVotesPerStar 12.0"; 
		String expectedLine2 = "value from votes viz method in Party class with name Party2, maxStars 10, percentOfVotesPerStar 12.0"; 
		String expectedLine3 = "value from votes viz method in Party class with name Party3, maxStars 10, percentOfVotesPerStar 12.0"; 
		String expectedLine4 = "value from votes viz method in Party class with name Party4, maxStars 10, percentOfVotesPerStar 12.0"; 
		String expectedLine5 = "value from votes viz method in Party class with name Party5, maxStars 10, percentOfVotesPerStar 12.0"; 

		String[] actualLines = actual.split("\n");
		assertEquals("Expected text vizualization to have six lines, one for poll name, one for info about each of the five parties in poll",
				6, actualLines.length);
		assertEquals("Expected for first line of text vizualization to have poll name",
				
				expectedLine0, actualLines[0]);
		assertEquals("Expected second line of text vizualization to have data returned by party vizualization method",
				expectedLine1, actualLines[1]);
		assertEquals("Expected second line of text vizualization to have data returned by party vizualization method",
				expectedLine2, actualLines[2]);
		assertEquals("Expected second line of text vizualization to have data returned by party vizualization method",
				expectedLine3, actualLines[3]);
		assertEquals("Expected second line of text vizualization to have data returned by party vizualization method",
				expectedLine4, actualLines[4]);
		assertEquals("Expected second line of text vizualization to have data returned by party vizualization method",
				expectedLine5, actualLines[5]);
	}



}

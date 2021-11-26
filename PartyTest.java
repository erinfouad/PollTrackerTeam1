import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class PartyTest {

	// Testing constructors
	@Test 
	public void test_Constructor_oneArgument()
	{
		Party p = new Party("Party1");
		assertEquals("Created party with name 'Party1'", "Party1", p.getName());
		assertEquals("Created party without provided value for projected number of seats", 0.0, p.getProjectedNumberOfSeats(), 0.000001);
		assertEquals("Created party without providing value for projected percentage of the vote", 0.0, p.getProjectedPercentageOfVotes(), 0.0000001);
	}
	
	@Test
	public void test_Contructor_threeArguments_zeroSeatsAndVotes() 
	{
		Party p = new Party("Party two", 0.0f, 0.0f);
		assertEquals("Created party with name 'Party two'", "Party two", p.getName());
		assertEquals("Created party with 0.0 projected number of seats", 0.0, p.getProjectedNumberOfSeats(), 0.000001);
		assertEquals("Created party with 0.0 % of vote", 0.0, p.getProjectedPercentageOfVotes(), 0.0000001);		
	}

	@Test
	public void test_Contructor_threeArguments_positiveSeats_allVotes() 
	{
		Party p = new Party("Party three", 50.0f, 1.0f);
		assertEquals("Created party with name 'Party three'", "Party three", p.getName());
		assertEquals("Created party with 50.0 projected seats", 50.0, p.getProjectedNumberOfSeats(), 0.000001);
		assertEquals("Created party with 100% or 1.0 percent of vote", 1.0, p.getProjectedPercentageOfVotes(), 0.0000001);		
	}
	
	@Test
	public void test_Contructor_threeArguments_positiveSeats_moreThan100PercentVotes() 
	{
		Party p = new Party("Another party", 150.0f, 1.0001f);
		assertEquals("Created party with name 'Another party", "Another party", p.getName());
		assertEquals("Created party with 150 expected seats", 150.0, p.getProjectedNumberOfSeats(), 0.000001);
		assertEquals("Created party with 100.01% (or 1.0001) projected votes", 0.0, p.getProjectedPercentageOfVotes(), 0.0000001);		
	}

	@Test
	public void test_Contructor_threeArguments_positiveSeats_lessThanZeroPercentVotes() 
	{
		Party p = new Party("A long party name", 231.0f, -.25f);
		assertEquals("Created party with name 'A long party name'", "A long party name", p.getName());
		assertEquals("Created party with 231 expected seats", 231.0, p.getProjectedNumberOfSeats(), 0.000001);
		assertEquals("Created party with -25% (or -0.25) projected votes", 0.0, p.getProjectedPercentageOfVotes(), 0.0000001);		
	}
	
	@Test
	public void test_Contructor_threeArguments_negativeSeats_validVotes() 
	{
		Party p = new Party("This is a political party", -.025f, .25f);
		assertEquals("Created party with name 'This is a political party'", "This is a political party", p.getName());
		assertEquals("Created party with -0.025 projected seats", 0.0, p.getProjectedNumberOfSeats(), 0.0001);
		assertEquals("Created party with 25% (or .25) projected votes", 0.25, p.getProjectedPercentageOfVotes(), 0.0001);		
	}
	
	// Testing setter methods
	@Test
	public void test_setProjectedNumberOfSeats_zeroSeats() 
	{
		Party p = new Party("", 231.0f, .75f);
		p.setProjectedNumberOfSeats(0.0f);
		assertEquals(0.0, p.getProjectedNumberOfSeats(), 0.0001);
	}

	@Test
	public void test_setProjectedNumberOfSeats_positiveSeats() 
	{
		Party p = new Party("", 1234.59f, .9f);
		p.setProjectedNumberOfSeats(456.71f);
		assertEquals(456.71, p.getProjectedNumberOfSeats(), 0.0001);
	}

	@Test
	public void test_setProjectedNumberOfSeats_negativeSeats() 
	{
		Party p = new Party("", 1234.59f, .9f);
		p.setProjectedNumberOfSeats(-21f);
		assertEquals("When trying to set number of seats to a negative number, number of seats should be left unchanged.", 
					1234.59, p.getProjectedNumberOfSeats(), 0.0001);
	}
	
	@Test
	public void test_setProjectedPercentageOfVotes_zeroVotes() 
	{
		Party p = new Party("", 231.0f, .75f);
		p.setProjectedPercentageOfVotes(0.0f);
		assertEquals(0.0, p.getProjectedPercentageOfVotes(), 0.0001);		
	}

	@Test
	public void test_setProjectedPercentageOfVotes_allVotes() 
	{
		Party p = new Party("", 231.0f, .75f);
		p.setProjectedPercentageOfVotes(1.0f);
		assertEquals(1.0, p.getProjectedPercentageOfVotes(), 0.0001);		
	}
	
	@Test
	public void test_setProjectedPercentageOfVotes_moreThan100PercentVotes() 
	{
		Party p = new Party("", 231.0f, .75f);
		p.setProjectedPercentageOfVotes(1.001f);
		assertEquals(.75, p.getProjectedPercentageOfVotes(), 0.0001);		
	}
	
	@Test
	public void test_setProjectedPercentageOfVotes_negativeVotes() 
	{
		Party p = new Party("", 231.0f, .54f);
		p.setProjectedPercentageOfVotes(-.235f);
		assertEquals("Percentage of votes should remain unchanged when trying to set to negative",
					.54, p.getProjectedPercentageOfVotes(), 0.0001);		
	}
	
	// testing toString
	
	@Test
	public void test_toString_NoColourSet() 
	{
		Party p = new Party("Name of Party", 123.2f, 0.32f);
		assertEquals("Name of Party (32% of votes, 123.2 seats)", p.toString());
	}
	
	@Test
	public void test_toString_colourRed() 
	{
		Party p = new Party("Name of Party", 123.2f, 0.32f);
		p.setColour(Color.RED);
		assertEquals("Name of Party ([255,0,0], 32% of votes, 123.2 seats)", p.toString());
	}
	
	@Test
	public void test_toString_colourCyan() 
	{
		Party p = new Party("Name of Party", 123.2f, 0.32f);
		p.setColour(Color.CYAN);
		assertEquals("Name of Party ([0,255,255], 32% of votes, 123.2 seats)", p.toString());
	}
	
	// testing projectedPercentOfSeats
	@Test
	public void test_projectedPercentOfSeats_MoreSeatsAvailableThanProjected() 
	{
		Party p = new Party("", 51f, 0.49f);
		double expected = 0.3923; // value of 51/130
		double actual = p.projectedPercentOfSeats(130);
		assertEquals("Percent of seats should be projected seats/total available seats",
				expected, actual, 0.0001);
	}
	
	@Test
	public void test_projectedPercentOfSeats_LessSeatsAvailableThanProjected() 
	{
		Party p = new Party("", 400f, 0.49f);
		double expected = 1.7316; // value of 400/231
		double actual = p.projectedPercentOfSeats(231);
		assertEquals("Percent of seats should be projected seats/total available seats",
				expected, actual, 0.0001);
	}
	
	@Test
	public void test_projectedPercentOfSeats_zeroSeatsAvailable() 
	{
		Party p = new Party("", 400f, 0.49f);
		double expected = 0.0; // can't divide by zero
		double actual = p.projectedPercentOfSeats(0);
		assertEquals("Can't divide by zero: if this error is encountered, expecting method to return 0.",
				expected, actual, 0.0001);
	}
	
	@Test
	public void test_projectedPercentOfSeats_negativeSeatsAvailable() 
	{
		Party p = new Party("", 400f, 0.49f);
		double expected = 0.0; // can't have negative seats available
		double actual = p.projectedPercentOfSeats(-500);
		assertEquals("Can't have negative number of seats in election: if this error is encountered, expecting method to return 0.",
				expected, actual, 0.0001);
	}
	
	// testing textVisualizationBySeats
	@Test
	public void test_textVisualizationBySeats_zeroProjectedSeats()
	{
		Party p = new Party("Visualization Test", 0.0f, .05f);
		String expected = "     |      Visualization Test (5% of votes, 0.0 seats)";
		String actual = p.textVisualizationBySeats(10, 25);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_textVisualizationBySeats_minority_roundDown()
	{
		Party p = new Party("Visualization Test", 46f, .35f);
		String expected = "**** |      Visualization Test (35% of votes, 46.0 seats)";
		String actual = p.textVisualizationBySeats(10, 11);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_textVisualizationBySeats_exactlyHalf_MoreThanPointFiveRemainder()
	{
		Party p = new Party("Visualization Test", 64f, .45f);
		String expected = "**********|           Visualization Test (45% of votes, 64.0 seats)";
		String actual = p.textVisualizationBySeats(20, 6);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_textVisualizationBySeats_majority()
	{
		Party p = new Party("Visualization Test", 64f, .45f);
		String expected = "**********|           Visualization Test (45% of votes, 64.0 seats)";
		String actual = p.textVisualizationBySeats(20, 6);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_textVisualizationBySeats_allSeats()
	{
		Party p = new Party("Visualization Test", 180f, .95f);
		String expected = "********|******* Visualization Test (95% of votes, 180.0 seats)";
		String actual = p.textVisualizationBySeats(15, 12);
		assertEquals(expected, actual);
	}
	
	
	// testing textVisualizationByVotes
	@Test
	public void test_textVisualizationByVotes_zeroProjectedVotes()
	{
		Party p = new Party("Visualization Test", 2.0f, 0.0f);
		String expected = "     |      Visualization Test (0% of votes, 2.0 seats)";
		String actual = p.textVisualizationByVotes(10, 10);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_textVisualizationByVotes_minority_roundDown()
	{
		Party p = new Party("Visualization Test", 46f, .36f);
		String expected = "*******   |           Visualization Test (36% of votes, 46.0 seats)";
		String actual = p.textVisualizationByVotes(20, 5);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_textVisualizationByVotes_exactlyHalf_MoreThanPointFiveRemainder()
	{
		Party p = new Party("Visualization Test", 64f, .55f);
		String expected = "*************|             Visualization Test (55% of votes, 64.0 seats)";
		String actual = p.textVisualizationByVotes(25, 4);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_textVisualizationByVotes_majority()
	{
		Party p = new Party("Visualization Test", 64f, .75f);
		String expected = "**********|*****      Visualization Test (75% of votes, 64.0 seats)";
		String actual = p.textVisualizationByVotes(20, 5);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_textVisualizationByVotes_allVotes()
	{
		Party p = new Party("Visualization Test", 180f, 1.0f);
		String expected = "********|******** Visualization Test (100% of votes, 180.0 seats)";
		String actual = p.textVisualizationByVotes(16, 6.25);
		assertEquals(expected, actual);
	}
	
}

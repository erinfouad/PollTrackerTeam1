/**
 * @author gabe
 * @version 0.1
 */
public class TextApplication {
	private PollList polls;

	public void displayPollDataBySeat(Poll aPoll) {
		aPoll.textVisualizationBySeats(this.polls.MAX_STARS_FOR_VISUALIZATION, 0);
		return;
	}

	public void displayPollsBySeat(String[] stuff) {
		
		return;
	}
	
	private void run() {
		return;
	}
	
	
	public static void main(String[] args) {
		TextApplication aTextApplication = new TextApplication();
		
		aTextApplication.run();
	}

}
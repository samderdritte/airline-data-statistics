import java.util.HashMap;
import java.util.Map.Entry;
/**
 * Main class to generate the answers for the assignment.
 * @author samuelspycher
 *
 */
public class AirlineDataRunner {
	
	public static void main(String[] args) {
		
		//String filename = "flights_small.csv";
		String filename = "flights.csv";
		FlightDataReader fdr = new FlightDataReader(filename);
		AirportDataReader adr = new AirportDataReader(filename);
				
		/// Answers to assignment questions
		
		//Initialize the assignment for output
		FormattedOutput assignment5 = new FormattedOutput();

		// Question 1
		// Which carrier has the highest percentage of cancelled flights? Output the 2-letter
		// Carrier ID and the chance of a cancelled flight, as a percentage (Example: AA,1.22%).
		HashMap<String, Integer> totalFlightsPerCarrier = fdr.numberOfFlightsPerCarrier();
		HashMap<String, Integer> totalCancelledFlightsPerCarrier = fdr.numberOfCancelledFlightsPerCarrier();
		HashMap<String, Double> percentageOfCancelledFlightsPerCarrier = FlightStatisticsHelper.dividePercentageValuesOfTwoMaps(totalCancelledFlightsPerCarrier, totalFlightsPerCarrier);

		Entry<String, Double> mostCancelledFlights = FlightStatisticsHelper.getHighestValueDouble(percentageOfCancelledFlightsPerCarrier);
		
		String answer1 = mostCancelledFlights.getKey() +","+ mostCancelledFlights.getValue() +"%";
		
		System.out.println("Answer 1: " + answer1);
		assignment5.addAnswer(1, answer1);
		
		
		// Question 2
		// What’s the most common cause of cancellations? Output the one-letter code.
		HashMap<String, Integer> totalCancellationsPerCode = fdr.countCancellationsPerCode();
		Entry<String, Integer> mostCommonCancellation = FlightStatisticsHelper.getHighestValue(totalCancellationsPerCode);
		String answer2 = mostCommonCancellation.getKey();
		System.out.println("Answer 2: " + answer2);
		assignment5.addAnswer(2, answer2);
		
		// Question 3
		// Which plane (tail number) flew the furthest (most miles)? Output the complete
		// tailnumber (Example: N775AJ).
		HashMap<String, Integer> longestDistancePerPlane = fdr.distanceTravelledPerPlane();
		Entry<String, Integer> planeWithLongestDistance = FlightStatisticsHelper.getHighestValue(longestDistancePerPlane);
		
		String answer3 = planeWithLongestDistance.getKey();
		
		System.out.println("Answer 3: " + answer3);
		assignment5.addAnswer(3, answer3);
		
		// Question 4
		// Which airport is the busiest by total number of flights in and out? Use the number
		// OriginAirportID (Example: 12478).
		int answer4 = adr.getBusiestAirport().getAirportID();
		System.out.println("Answer 4: " + answer4);
		assignment5.addAnswer(4, answer4);
		
		// Question 5
		// You need planes to put people on! Which airport is the biggest “source” of airplanes?
		// Use the difference between arrivals and departures to compute this value. Output the
		// OriginAirportID (Example: 12478).
		int answer5 = adr.getBiggestSourceOfAirplanes().getAirportID();
		System.out.println("Answer 5: " + answer5);
		assignment5.addAnswer(5, answer5);
		
		// Question 6
		// Which airport is the biggest “sink” of airplanes? Again, use the difference between
		// arrivals and departures, outputting the OriginAirportID (Example: 12478).
		int answer6 = adr.getBiggestSinkOfAirplanes().getAirportID();
		System.out.println("Answer 6: " + answer6);
		assignment5.addAnswer(6, answer6);
		
		// Question 7
		// How many American Airlines (Unique Carrier ID ‘AA’) flights were delayed by 60 minutes
		// or more? If a flight was delayed departing and arriving, only count that as 1. Output an
		// integer.
		int answer7 = fdr.bigDelaysPerCarrier("AA", 60);
		System.out.println("Answer 7: " + answer7);
		assignment5.addAnswer(7, answer7);
		
		// Question 8
		// What was the largest delay that was made up (arrived early/on time)? Output the Day
		// of Month (the number), departure delay (as a number), and the tail-number. Example:
		// (10,30,N947JB).
		Flight fWLD = fdr.flightWithLargestMadeupDelay();
		String answer8 = fWLD.getFlightDate().getDayOfMonth()+","+fWLD.getDepDelay()+","+fWLD.getTailNum();
		System.out.println("Answer 8: " + answer8);
		assignment5.addAnswer(8, answer8);
		
		// Question 9
		// Come up with a question of your own and answer it!
		// Which Airport had the most departure delays? (DepDelay > 0)
		
		Airport mostDelays = adr.getAirportWithMostDelaysAtDeparture();
		int answer9 = mostDelays.getAirportID();
		System.out.println("Answer 9: " + answer9);
		assignment5.addAnswer(9, answer9);	
				
				
		// write the answers to the answers.txt file
		assignment5.writeAnswers();
		
		System.out.println("\nThe answers have been saved to: answers.txt");
	}
}

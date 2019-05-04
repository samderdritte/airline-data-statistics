/**
 * An Airport is used to group statistical data in relation to the
 * corresponding airportID.
 * @author samuelspycher
 *
 */
public class Airport {
	private int AirportID;
	private String AirportName;
	private String AirportState;
	private int numberOfOriginFlights;
	private int numberOfDestFlights;
	private int numberOfDelayedFlightsAtDeparture;
	private int numberOfDivertedFlights;
	private int numberOfCancelledFlights;
	
	public Airport(int AirportID, String AirportName, String AirportState) {
		this.AirportID = AirportID;
		this.AirportName = AirportName;
		this.AirportState = AirportState;
		numberOfOriginFlights = 0;
		numberOfDestFlights = 0;
		numberOfDelayedFlightsAtDeparture = 0;
		numberOfDivertedFlights = 0;
		numberOfCancelledFlights = 0;
	}

	public int getAirportID() {
		return AirportID;
	}

	public String getAirportName() {
		return AirportName;
	}
	
	public String getAirportState() {
		return AirportState;
	}
	
	public int getNumberOfOriginFlights() {
		return numberOfOriginFlights;
	}

	public int getNumberOfDestFlights() {
		return numberOfDestFlights;
	}

	public int getNumberOfDelayedFlightsAtDeparture() {
		return numberOfDelayedFlightsAtDeparture;
	}

	public int getNumberOfDivertedFlights() {
		return numberOfDivertedFlights;
	}

	public int getNumberOfCancelledFlights() {
		return numberOfCancelledFlights;
	}
	
	public void addOriginFlight() {
		numberOfOriginFlights++;
	}
	
	public void addDestFlight() {
		numberOfDestFlights++;
	}
	
	public void addDelayedFlightAtDeparture() {
		numberOfDelayedFlightsAtDeparture++;
	}
	
	public void addCancelledFlight() {
		numberOfCancelledFlights++;
	}
	
	public void addDivertedFlight() {
		numberOfDivertedFlights++;
	}
	
	/**
	 * calculates the difference between flights for which this airport
	 * is origin and for which it is a destination
	 * can be negative if more planes arrive than depart
	 * @return
	 */
	public int sourceOfAirplanes() {
		return numberOfOriginFlights-numberOfDestFlights;
	}
	
	/**
	 * calculates the difference between flights for which this airport
	 * is a destination and for which it is a origin
	 * can be negative if more planes depart than arrive
	 * @return
	 */
	public int sinkOfAirplanes() {
		return numberOfDestFlights-numberOfOriginFlights;
	}
}

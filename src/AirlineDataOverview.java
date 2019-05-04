public class AirlineDataOverview {

	public static void main(String[] args) {
		
		//String filename = "flights_small.csv";
		String filename = "flights.csv";
		FlightDataReader fdr = new FlightDataReader(filename);
		AirportDataReader adr = new AirportDataReader(filename);
		
		System.out.println("Total number of airports: " + adr.getAirportData().size());
		
		
		
		for(int airportID : adr.getAirportData().keySet()) {
			Airport currentAirport = adr.getAirportData().get(airportID);
			System.out.println(currentAirport.getAirportID() 
			+ " - "+ currentAirport.getAirportName() + " - " + currentAirport.getAirportState()
			+ " - "+ currentAirport.getNumberOfCancelledFlights() );
		}
		
		System.out.println("Total number of flights: " + fdr.getFlightData().size());
		
		int countDelays = 0;
		for(int flightID : fdr.getFlightData().keySet()) {
			Flight currentFlight = fdr.getFlightData().get(flightID);
			//System.out.println(flightID+ "-" +currentFlight.getTailNum());
		}
		
		System.out.println(adr.getAirportByName("BWI").getNumberOfCancelledFlights());
		System.out.println(adr.getAirportByName("BWI").getNumberOfDelayedFlightsAtDeparture());
		System.out.println(adr.getAirportByName("BWI").getNumberOfOriginFlights());
		System.out.println(adr.getAirportByName("BWI").getNumberOfDestFlights());
		//System.out.println(adr.getAirportWithMostDelaysAtDeparture().getNumberOfDelayedFlightsAtDeparture());
		System.out.println();
		System.out.println(fdr.numberOfFlightsPerCarrier());
		System.out.println(fdr.numberOfCancelledFlightsPerCarrier());
		System.out.println();
		System.out.println(fdr.countCancellationsPerCode());
		System.out.println(fdr.distanceTravelledPerPlane().get("N790AA"));
		System.out.println();
		System.out.println(adr.getBusiestAirport().getAirportName());
		System.out.println();
		System.out.println(fdr.bigDelaysPerCarrier("AA", 60));
		System.out.println(fdr.flightWithLargestMadeupDelay().getTailNum());
		System.out.println();
		System.out.println(adr.getAirportData());
		for (int airportID : adr.getAirportData().keySet()) {
			Airport currentAirport = adr.getAirportData().get(airportID);
			System.out.println(currentAirport.getAirportName()+ " "+currentAirport.getNumberOfOriginFlights()+ " "+currentAirport.getNumberOfDestFlights());
		}
	}
}

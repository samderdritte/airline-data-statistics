import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
/**
 * This class reads data from the csv-file and groups airport-related
 * data to a HashMap containing the Airport-objects.
 * @author samuelspycher
 *
 */
public class AirportDataReader {
	
	private HashMap<Integer, Airport> airportData;
	
	public AirportDataReader(String filename) {
		File file = new File(filename);
		airportData = new HashMap<Integer, Airport>();
		try {
			Scanner scanner = new Scanner(file);
			scanner.nextLine();
			while(scanner.hasNextLine()) {
				String flightRow = scanner.nextLine();
				String[] columnData = flightRow.split(",");
				/*
				 * 6th column for OriginAirportID
				 * 7th column for Origin
				 * 8th column for OriginStateName
				 * 9th column for DestAirportID
				 * 10th column for Dest
				 * 11th column for DestStateName
				 * 13th column for DepDelay
				 * 18th column for Cancelled
				 * 20th column for Diverted
				 */
				int originAirportID = Integer.parseInt(columnData[5]);
				String originAirportName = columnData[6];
				String originStateName = columnData[7];
				int destAirportID = Integer.parseInt(columnData[8]);
				String destAirportName = columnData[9];
				String destStateName = columnData[10];
				
				int isCancelled = Integer.parseInt(columnData[17]);
				int isDiverted = Integer.parseInt(columnData[19]);
				
				if(!airportData.containsKey(originAirportID)) {
					Airport o = new Airport(originAirportID, originAirportName, originStateName);
					airportData.put(originAirportID, o);
				}
				if(!airportData.containsKey(destAirportID)) {
					Airport d = new Airport(destAirportID, destAirportName, destStateName);
					airportData.put(destAirportID, d);
				}
				
				airportData.get(originAirportID).addOriginFlight();
				airportData.get(destAirportID).addDestFlight();
				
				if(!columnData[12].equals("")) {
					int depDelay = Integer.parseInt(columnData[12]);
					if(depDelay > 0 && isCancelled == 0) {
						airportData.get(originAirportID).addDelayedFlightAtDeparture();
					}
				}
				
				if(isCancelled == 1) {
					airportData.get(originAirportID).addCancelledFlight();
				}
				if(isDiverted == 1) {
					airportData.get(originAirportID).addDivertedFlight();
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public HashMap<Integer, Airport> getAirportData() {
		return airportData;
	}
	
	/**
	 * Find an Airport by its Name (IATA-Code)
	 * @param 	AirportName
	 * @return	Airport
	 */
	public Airport getAirportByName(String AirportName) {
		Airport foundAirport = null;
		for (int airportID : airportData.keySet()) {
			if(airportData.get(airportID).getAirportName().equals(AirportName)){
				foundAirport = airportData.get(airportID);
			}
		}
		return foundAirport;
	}
	
	/**
	 * Find the airport with the most flight in and out
	 * 
	 * @return Airport with most flights in and out
	 */
	public Airport getBusiestAirport() {
		Airport busiest = null;
		int mostFlightsInOut = 0;
		for (int airportID : airportData.keySet()) {
			Airport currentAirport = airportData.get(airportID);
			int originFlights = currentAirport.getNumberOfOriginFlights();
			int destFlights = currentAirport.getNumberOfDestFlights();
			
			if(originFlights+destFlights > mostFlightsInOut) {
				mostFlightsInOut = originFlights+destFlights;
				busiest = currentAirport;
			}
		}
		return busiest;
	}
	
	/**
	 * Find the Airport which is the biggest "source" of airports
	 * i.e. more planes depart from this airport than have it as destination
	 * @return	Airport with the biggest "source" of planes
	 */
	public Airport getBiggestSourceOfAirplanes() {
		Airport biggestSource = null;
		int source = 0;
		for (int AirportID : airportData.keySet()) {
			Airport currentAirport = airportData.get(AirportID);
			if(currentAirport.sourceOfAirplanes() > source) {
				source = currentAirport.sourceOfAirplanes();
				biggestSource = currentAirport;
			}
		}
		return biggestSource;
	}
	
	/**
	 * Find the Airport which is the biggest "sink" of airports
	 * i.e. more planes land at this airport than have it as origin
	 * @return Airport with biggest "sink" of planes
	 */
	public Airport getBiggestSinkOfAirplanes() {
		Airport biggestSink = null;
		int sink = 0;
		for (int AirportID : airportData.keySet()) {
			Airport currentAirport = airportData.get(AirportID);
			if(currentAirport.sinkOfAirplanes() > sink) {
				sink = currentAirport.sinkOfAirplanes();
				biggestSink = currentAirport;
			}
		}
		return biggestSink;
	}
	
	/**
	 * Find the airport with the most delayed flights at departure
	 * Counts all delays where DepDelay > 0
	 * @return	Airport with most delayed departures
	 */
	public Airport getAirportWithMostDelaysAtDeparture() {
		Airport mostDelays = null;
		int delays = 0;
		for (int AirportID : airportData.keySet()) {
			Airport currentAirport = airportData.get(AirportID);
			if(currentAirport.getNumberOfDelayedFlightsAtDeparture() > delays) {
				delays = currentAirport.getNumberOfDelayedFlightsAtDeparture();
				mostDelays = currentAirport;
			}
		}
		return mostDelays;
	}
}

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;
/**
 * This class reads data from a given csv-file and stores the rows of data
 * to a HashMap containing the different Flight-objects.
 * @author samuelspycher
 *
 */
public class FlightDataReader {
	
	private HashMap<Integer, Flight> flightData;
	
	public FlightDataReader(String filename) {
		File file = new File(filename);
		flightData = new HashMap<Integer, Flight>();
		try {
			Scanner scanner = new Scanner(file);
			scanner.nextLine();
			int flightID = 1;
			while(scanner.hasNextLine()) {
				String flightRow = scanner.nextLine();
				String[] columnData = flightRow.split(",");
				/*
				 * 1st column for DayofMonth
				 * 2nd column for DayOfWeek
				 * 3rd column for FlightDate
				 * 4th column for UniqueCarrier
				 * 5th column for TailNum
				 * 6th column for OriginAirportID
				 * 7th column for Origin
				 * 8th column for OriginStateName
				 * 9th column for DestAirportID
				 * 10th column for Dest
				 * 11th column for DestStateName
				 * 12th column for DepTime
				 * 13th column for DepDelay
				 * 14th column for WheelsOff
				 * 15th column for WheelsOn
				 * 16th column for ArrTime
				 * 17th column for ArrDelay
				 * 18th column for Cancelled
				 * 19th column for CancellationCode
				 * 20th column for Diverted
				 * 21st column for AirTime
				 * 22nd column for Distance
				 */
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
				LocalDate FlightDate = LocalDate.parse(columnData[2], formatter);
				String UniqueCarrier = columnData[3];
				String TailNum = columnData[4];
				int OriginAirportID = Integer.parseInt(columnData[5]);
				int DestAirportID = Integer.parseInt(columnData[8]);
				int DepTime = -1;
				if (!columnData[11].equals("")) {
					DepTime = Integer.parseInt(columnData[11]);
				}
				int DepDelay = -1;
				if (!columnData[12].equals("")) {
					DepDelay = Integer.parseInt(columnData[12]);
				}
				int WheelsOff = -1;
				if (!columnData[13].equals("")) {
					WheelsOff = Integer.parseInt(columnData[13]);
				}
				int WheelsOn = -1;
				if (!columnData[14].equals("")) {
					WheelsOn = Integer.parseInt(columnData[14]);
				}
				int ArrTime = -1;
				if (!columnData[15].equals("")) {
					ArrTime = Integer.parseInt(columnData[15]);
				}
				int ArrDelay = -1;
				if (!columnData[16].equals("")) {
					ArrDelay = Integer.parseInt(columnData[16]);
				}
				boolean Cancelled = false;
				if (columnData[17].equals("1")){
					Cancelled = true;
				}
				String CancellationCode = columnData[18];
				boolean Diverted = false;
				if(columnData[19].equals("1")) {
					Diverted = true;
				}
				int AirTime = -1;
				if (!columnData[20].equals("")) {
					AirTime = Integer.parseInt(columnData[20]);
				}
				int Distance = Integer.parseInt(columnData[21]);
				
				Flight f = new Flight(FlightDate, UniqueCarrier, 
						TailNum, OriginAirportID, DestAirportID, DepTime, DepDelay, WheelsOff, 
						WheelsOn, ArrTime, ArrDelay, Cancelled, CancellationCode, 
						Diverted, AirTime, Distance);
				
				flightData.put(flightID, f);
				flightID++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public HashMap<Integer, Flight> getFlightData() {
		return flightData;
	}
	
	/**
	 * Returns all flights from a selected Carrier
	 * @param carrierName (String) CarrierName
	 * @return a HashMap with all the flights from the selected carrier
	 */
	public HashMap<Integer, Flight> flightsByCarrierName(String carrierName){
		
		HashMap<Integer, Flight> selectedFlights = new HashMap<Integer, Flight>();
		
		for (int flightID : flightData.keySet()) {
			if(flightData.get(flightID).getUniqueCarrier().equals(carrierName)) {
				selectedFlights.put(flightID, flightData.get(flightID));
			}
		}
		return selectedFlights;
	}
	
	/**
	 * Counts all the flights per carrier
	 * @return	A HashMap with all the UniqueCarriers and the count of their flights
	 */
	public HashMap<String, Integer> numberOfFlightsPerCarrier(){
		
		HashMap<String, Integer> flightsPerCarrier = new HashMap<String, Integer>();
		
		for (int flightID : flightData.keySet()) {
			String currentCarrierName = flightData.get(flightID).getUniqueCarrier();
			if(!flightsPerCarrier.containsKey(currentCarrierName)) {
				flightsPerCarrier.put(currentCarrierName, 1);
			} else {
				int count = flightsPerCarrier.get(currentCarrierName);
				flightsPerCarrier.put(currentCarrierName, count+1);
			}
		}
		return flightsPerCarrier;
	}
	
	/**
	 * Returns the total number of cancelled flights per carrier.
	 * @return A HashMap with UniqueCarrier (Key) and the total of cancelled flights per carrier
	 */
	public HashMap<String, Integer> numberOfCancelledFlightsPerCarrier(){
		
		HashMap<String, Integer> cancelledFlightsPerCarrier = new HashMap<String, Integer>();
		
		for (int flightID : flightData.keySet()) {
			String currentCarrierName = flightData.get(flightID).getUniqueCarrier();
			boolean isCancelled = flightData.get(flightID).isCancelled();
			if(isCancelled) {
				if(!cancelledFlightsPerCarrier.containsKey(currentCarrierName)) {
					cancelledFlightsPerCarrier.put(currentCarrierName, 1);
				} else {
					int count = cancelledFlightsPerCarrier.get(currentCarrierName);
					cancelledFlightsPerCarrier.put(currentCarrierName, count+1);
				}
			}
		}
		return cancelledFlightsPerCarrier;
	}
	
	/**
	 * Counts the number of cancellations per cancellations code
	 * @return A HashMap with Cancellation Code (Key) and the count of cancellations (Value)
	 */
	public HashMap<String, Integer> countCancellationsPerCode(){
		HashMap<String, Integer> cancellationsPerCode = new HashMap<String, Integer>();
		
		for (int flightID : flightData.keySet()){
			String currentCancellationCode = flightData.get(flightID).getCancellationCode();
			if(currentCancellationCode.equals("")) {
				continue;
			} else {
				if(!cancellationsPerCode.containsKey(currentCancellationCode)){
					cancellationsPerCode.put(currentCancellationCode, 1);
				} else {
					int count = cancellationsPerCode.get(currentCancellationCode);
					cancellationsPerCode.put(currentCancellationCode, count+1);
				}	
			}
		}
		return cancellationsPerCode;
	}
	
	/**
	 * Calculates the total distance traveled per plane (TailNum)
	 * Cancelled flights will be ignored.
	 * @return A HashMap with all the TailNumbers (Key) and the sum of the traveled distance (Value)
	 */
	public HashMap<String, Integer> distanceTravelledPerPlane(){
		HashMap<String, Integer> distancePerPlane = new HashMap<String, Integer>();
		
		for (int flightID : flightData.keySet()) {
			Flight currentFlight = flightData.get(flightID);
			
			String currentTailNum = currentFlight.getTailNum();
			if(currentFlight.isCancelled()){ 
				continue;
			} else {
				if(!distancePerPlane.containsKey(currentTailNum)) {
					
					distancePerPlane.put(currentTailNum, currentFlight.getDistance());
				} else {
					int distance = distancePerPlane.get(currentTailNum);
					distancePerPlane.put(currentTailNum, distance+currentFlight.getDistance());
				}
			}
		}
		return distancePerPlane;
	}
	
	/**
	 * Calculates the delays per carrier above a certain delay threshold.
	 * Returns all the flights for which the delay is >= delayThreshold.
	 * 
	 * @param UniqueCarrier		The Name (UniqueCarrier) of the carrier.
	 * @param delayThreshold	The threshold of the delay
	 * @return	(int) The number of delays for the selected carrier.
	 */
	public int bigDelaysPerCarrier(String UniqueCarrier, int delayThreshold){
		
		int numDelays = 0;
		
		for (int flightID : flightData.keySet()) {
			Flight currentFlight = flightData.get(flightID);
			if(!currentFlight.isCancelled() && !currentFlight.isDiverted()
					&& currentFlight.getUniqueCarrier().equals(UniqueCarrier) && 
					((currentFlight.getDepDelay() >= delayThreshold) || (currentFlight.getArrDelay() >= delayThreshold))) {
				numDelays++;
			}
		}
		return numDelays;
	}
	
	/**
     * Returns the flight of all flights which has made up the largest delay.
     * DepDelay must be bigger than zero, ArrDelay 0 or negative.
     * Returns the biggest DepDelay which was made up
     * @return Flight with largest made up delay
     */
     public Flight flightWithLargestMadeupDelay(){
            Flight returnedFlight = flightData.get(1);
            for (int flightID : flightData.keySet()) {
                   boolean isCancelled = flightData.get(flightID).isCancelled();
                   if(!isCancelled && flightData.get(flightID).getDepDelay() > 0 && flightData.get(flightID).getArrDelay() <=0 ) {
                         if(flightData.get(flightID).madeUpDelay() > returnedFlight.madeUpDelay()) {
                                returnedFlight = flightData.get(flightID);
                         }                   
                   }
            }
            return returnedFlight;
     }
	
	/**
	 * Returns the flight with the longest single leg.
	 * @return	Flight with longest distance.
	 */
	public Flight longestFlight() {
		Flight longestFlight = null;
		int longestDistance = 0;
		for (int flightID : flightData.keySet()) {
			Flight currentFlight = flightData.get(flightID);
			if(!currentFlight.isCancelled()) {
				if(currentFlight.getDistance() > longestDistance) {
					longestDistance = currentFlight.getDistance();
					longestFlight = currentFlight;
				}
			}
		}
		return longestFlight;
	}
}

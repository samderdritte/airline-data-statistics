import java.time.LocalDate;
/**
 * A flight is a collection of data for a single row of the csv-file.
 * @author samuelspycher
 *
 */
public class Flight {
	private LocalDate FlightDate;
	private String UniqueCarrier;
	private String TailNum;
	private int	OriginAirportID;
	private int DestAirportID;
	private int DepTime;
	private int DepDelay;
	private int WheelsOff;
	private int WheelsOn;
	private int	ArrTime;
	private int ArrDelay;
	private boolean Cancelled;
	private String CancellationCode;
	private boolean Diverted;
	private int AirTime;
	private int Distance;
	
	public Flight(LocalDate FlightDate, String UniqueCarrier, 
			String TailNum, int	OriginAirportID, int DestAirportID, int DepTime, int DepDelay, 
			int WheelsOff, int WheelsOn, int ArrTime, int ArrDelay, boolean Cancelled, 
			String CancellationCode, boolean Diverted, int AirTime, int Distance) {
		this.FlightDate = FlightDate;
		this.UniqueCarrier = UniqueCarrier;
		this.TailNum = TailNum;
		this.OriginAirportID = OriginAirportID;
		this.DestAirportID = DestAirportID;
		this.DepTime = DepTime;
		this.DepDelay = DepDelay;
		this.WheelsOff = WheelsOff;
		this.WheelsOn = WheelsOn;
		this.ArrTime = ArrTime;
		this.ArrDelay = ArrDelay;
		this.Cancelled = Cancelled;
		this.CancellationCode = CancellationCode;
		this.Diverted = Diverted;
		this.AirTime = AirTime;
		this.Distance = Distance;
	}

	public LocalDate getFlightDate() {
		return FlightDate;
	}

	public String getUniqueCarrier() {
		return UniqueCarrier;
	}

	public String getTailNum() {
		return TailNum;
	}

	public int getOriginAirportID() {
		return OriginAirportID;
	}

	public int getDestAirportID() {
		return DestAirportID;
	}

	public int getDepTime() {
		return DepTime;
	}

	public int getDepDelay() {
		return DepDelay;
	}

	public int getWheelsOff() {
		return WheelsOff;
	}

	public int getWheelsOn() {
		return WheelsOn;
	}

	public int getArrTime() {
		return ArrTime;
	}

	public int getArrDelay() {
		return ArrDelay;
	}

	public boolean isCancelled() {
		return Cancelled;
	}

	public String getCancellationCode() {
		return CancellationCode;
	}

	public boolean isDiverted() {
		return Diverted;
	}

	public int getAirTime() {
		return AirTime;
	}

	public int getDistance() {
		return Distance;
	}
	/**
	 * Returns the DepDelay that a plane has made up
	 * @return
	 */
	public int madeUpDelay() {
        if(Cancelled == false && Diverted == false) {
               if(DepDelay > 0 && ArrDelay >= 0) {
                     return DepDelay - ArrDelay;
               } else if (DepDelay > 0 && ArrDelay < 0){
                     return DepDelay;
               } else {
                     return 0;
               }
        } else {
               return 0;
        }   
	}
}

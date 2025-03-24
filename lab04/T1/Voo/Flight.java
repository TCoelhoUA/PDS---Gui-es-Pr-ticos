import java.util.ArrayList;

public class Flight {
    private String code;
    private Plane plane;
    private int currentBooking;
    private int[] touristSpots;
    private int[] executiveSpots;


    public Flight(String code, int[] touristSpots, int[] executiveSpots) {
        this.code = code;
        this.plane = new Plane(touristSpots, executiveSpots);
        this.currentBooking = 1;
        this.touristSpots=touristSpots;
        this.executiveSpots=executiveSpots;
    }

    public String getCode(){
        return code;
    }

    public int getLastBooking(){
        return currentBooking-1;
    }

    public int[] getTouristSpots(){
        return touristSpots;
    }

    public int[] getExecutiveSpots(){
        return executiveSpots;
    }

    public ArrayList<String> bookSeat(FlightClass flightClass, int numberOfSeats){
        if (numberOfSeats < 1){
            System.out.printf("ERROR: book at least one seat on %s %d",FlightClass.flightClassToString(flightClass), numberOfSeats);
            return null;
        }
        ArrayList<String> booked = plane.bookSeat(flightClass, numberOfSeats, currentBooking);
        if (!(booked==null)) currentBooking++;
        return booked;
    }

    public boolean cancelBookings(int bookingCode){
        return plane.cancelBookings(bookingCode);
    }



    public int[][] getMap(FlightClass fc){
        Spots spot;
        if(fc==FlightClass.TOURIST) spot = plane.getTouristSpots();
        else spot = plane.getExecutiveSpots();
        int rows = spot.getNumRows();
        int seatsPerRow = spot.getSitsPerRow();
        int[][] map = new int[seatsPerRow][rows];
        for (int currRow = 0; currRow<rows; currRow++){
            Row row = spot.getRowAt(currRow);
            for (int currSeat = 0; currSeat<seatsPerRow; currSeat++){
                map[currSeat][currRow] = row.getSeat(currSeat).getCode();
            }
        }
        return map;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Flight other = (Flight) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        return true;
    }
}

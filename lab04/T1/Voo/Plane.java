import java.util.ArrayList;

public class Plane {
    private Spots touristSpots;
    private Spots executiveSpots;


    public Plane(int[] touristSpots, int[] executiveSpots) {
        this.touristSpots = new Spots(FlightClass.TOURIST, createRows(touristSpots[0], touristSpots[1]));
        this.executiveSpots = new Spots(FlightClass.EXECUTIVE, createRows(executiveSpots[0], executiveSpots[1]));
    }

    private Seat[] createSeats(int rowNo, int numSeats){
        Seat[] seats = new Seat[numSeats];
        char letter = 'A';
        for (int i = 0; i<numSeats; i++){
            seats[i] = new Seat(rowNo+1, letter++);
        }
        return seats;
    }
    private Row[] createRows(int numRows, int numSeats){
        Row[] rows = new Row[numRows];
        for (int i = 0; i<numRows; i++){
            Seat[] seats = createSeats(i, numSeats);
            rows[i] = new Row(seats);
        }
        return rows;
    }

    public Spots getTouristSpots() {
        return touristSpots;
    }

    public Spots getExecutiveSpots() {
        return executiveSpots;
    }

    public ArrayList<String> bookSeat(FlightClass flightClass, int numberOfSeats, int bookingCode){
        if (flightClass == FlightClass.EXECUTIVE)
            return executiveSpots.bookSeat(numberOfSeats, bookingCode);
        else
            return touristSpots.bookSeat(numberOfSeats, bookingCode);
    }

    public boolean cancelBookings(int bookingCode){
        return executiveSpots.cancelBookings(bookingCode) || touristSpots.cancelBookings(bookingCode);
    }
    
}

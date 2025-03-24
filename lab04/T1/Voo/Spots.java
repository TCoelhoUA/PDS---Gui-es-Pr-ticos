import java.util.ArrayList;

public class Spots {
    private Row[] rows;
    private FlightClass flightClass;

    public Spots(FlightClass flightClass, Row[] rows) {
        this.flightClass = flightClass;
        this.rows = rows;
    }

    public FlightClass getFlightClass(){
        return flightClass;
    }

    public int getSitsPerRow(){
        if (rows.length==0) return 0;
        return rows[0].getNumSeats();
    }

    public Row getRowAt(int i){
        return rows[i];
    }

    public int getNumRows(){
        return rows.length;
    }

    public ArrayList<String> bookSeat(int remainingBookings, int bookingCode){
        ArrayList<String> seatsAdded = new ArrayList<>();
        //search for empty rows
        for (Row row : rows){
            if (row.isEmpty()){
                for (int i = 0; i<row.getNumSeats() && remainingBookings>0; i++){
                    Seat seat = row.getSeat(i);
                    seat.bookSeat(bookingCode);
                    seatsAdded.add(seat.getPlace());
                    remainingBookings--;
                }
            }
        }
        //search for non empty rows
        if(remainingBookings>0){
            for (Row row : rows){
                for (int i = 0; i<row.getNumSeats() && remainingBookings>0; i++){
                    Seat seat = row.getSeat(i);
                    if (seat.getCode()==0){
                        seat.bookSeat(bookingCode);
                        seatsAdded.add(seat.getPlace());
                        remainingBookings--;
                    }
                }
            }
        }
        //no seats reamaining
        if (remainingBookings>0){
            cancelBookings(bookingCode);
            return null;
        }
        return seatsAdded;
    }

    public boolean cancelBookings(int bookingCode){
        //cancels all books with code equal to booking code
        int numCancels = 0; //to see if there were bookings canceled
        for (Row row : rows){
            for (int i = 0; i<row.getNumSeats(); i++){
                Seat seat = row.getSeat(i);
                if (seat.getCode()==bookingCode){
                    seat.cancelBook();
                    numCancels++;
                }
            }
        }
        if (numCancels == 0) return false;
        return true;
    }

    public int getSeatAt(int row, int seat){
        return rows[row].getSeat(seat).getCode();
    }

}

public class Row {
    private Seat[] seats;

    public Row(Seat[] seats) {
        this.seats = seats;
    }

    public int getNumSeats(){
        return seats.length;
    }

    public Seat getSeat(int index){
        return seats[index];
    }

    public boolean isEmpty(){
        for (int i = 0; i<getNumSeats(); i++){
            if (seats[i].isBooked()) return false;
        }
        return true;
    }


}

public class Seat {
    private int code;
    private String place;

    public Seat(int number, char letter) {
        this.code = 0;
        this.place = number + String.valueOf(letter);
    }
    
    public void bookSeat(int code){
        this.code = code;
    }

    public void cancelBook(){
        code = 0;
    }

    public String getPlace(){
        return place;
    }

    public boolean isBooked(){
        return code != 0;
    }

    public int getCode(){
        return code;
    }
}

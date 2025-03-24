public enum FlightClass {
    TOURIST,
    EXECUTIVE;

    public static String flightClassToString(FlightClass fc){
        if (fc==TOURIST)
            return "T";
        return "E";
    }

    public static FlightClass stringToFlightClass(String string){
        if (string.equals("T")){
            return TOURIST;
        }
        return EXECUTIVE;
    }
}

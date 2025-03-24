import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FlightManager {

    private static final Map<String, Flight> flightCache = new HashMap<>();

    public static void printMenu() {
        System.out.println(" _______________________________________________________________");
        System.out.println("|                                                               |");
        System.out.println("|                    FLIGHT MANAGER                             |");
        System.out.println("|_______________________________________________________________|");
        System.out.println("|                                                               |");
        System.out.println("|  H                                     - Show   Menu Options  |");
        System.out.println("|  R <filename>                          - Read   Flight File   |");
        System.out.println("|  P <flightcode>                        - Show   Flight Plan   |");  
        System.out.println("|  S <flightcode> <class> <number_seats> - Add    Reservation   |");     
        System.out.println("|  C <flightcode>:<reservation_code>     - Cancel Reservation   |");     
        System.out.println("|  Q                                     - Exit                 |");
        System.out.println("|_______________________________________________________________|");
    }
    

    public static boolean validateFile(String filename) throws IOException {
        String header = getFileHeader(filename);
        if (header == null) return false;
        if (!header.startsWith(":")) return false;
        if (!header.contains(":")) return false;
        String[] parts = getSplitHeader(header);
        return parts.length >=3 ; 
    }

    public static String[] getSplitHeader(String header){
        String[] parts = header.split(":");
        return parts;
    }

    public static String getFileHeader(String filename) throws IOException{
        String header;
        try (FileReader fr = new FileReader(filename)) {
            Scanner sc = new Scanner(fr);
            if (!sc.hasNextLine()){
                sc.close();
                return null;
            }   header = sc.nextLine();
            sc.close();
        }
        return header;
    }

    public static String getFlightCode(String[] parts){
        return parts[1];
    }


    public static int[] getSpotArray(String part){
        String dimensions = part;
        String[] divided = dimensions.split("x");
        int [] array = {Integer.parseInt(divided[0]),Integer.parseInt(divided[1])};
        return array;      
    }


    public static void printHeaderInformation(String filename) throws IOException {
        String header = getFileHeader(filename);
        String[] parts = getSplitHeader(header);
        String flightCode = parts[1]; //parts[0] will be a blank string
    
        String executiveSeats = parts.length > 3 ? parts[2] : null;
        String touristSeats = parts.length > 3 ? parts[3] : parts[2];

        int numberExecutiveSeats = (executiveSeats != null) ? parseSeats(executiveSeats) : 0;
        int numberTouristSeats = parseSeats(touristSeats);

        printFlightInformation(flightCode, executiveSeats, numberExecutiveSeats, touristSeats, numberTouristSeats);
    }

    public static int parseSeats(String seatData) {
        String[] dimensions = seatData.split("x");
        return Integer.parseInt(dimensions[0]) * Integer.parseInt(dimensions[1]);
    }

    public static void printFlightInformation(String flightCode, String executiveSeats, int numberExecutiveSeats, String touristSeats, int numberTouristSeats){
        System.out.println("\n                    Flight Information            ");
        System.out.println("________________________________________________________");
        System.out.printf("| Flight Code: %-15s                         |\n", flightCode);

        if (executiveSeats!=null){
            System.out.printf("| Executive:   %-15s (%d)seats                |\n", executiveSeats, numberExecutiveSeats);
        }
        System.out.printf("| Tourist:     %-15s (%d)seats               |\n",touristSeats,numberTouristSeats);
    
        System.out.println("|______________________________________________________|");
    }


    public static String printFlight(Flight flight){
        int[][] executiveMap = flight.getMap(FlightClass.EXECUTIVE);
        int[][] touristMap = flight.getMap(FlightClass.TOURIST);
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        int[] executiveSpots = flight.getExecutiveSpots();
        int[] touristSpots = flight.getTouristSpots();
        int rows = executiveSpots[0]+touristSpots[0];
        int seats = executiveSpots[1]>touristSpots[1] ? executiveSpots[1] : touristSpots[1];
        for (int i = 1 ; i <= rows ; i++){
            sb.append(i).append("\t");
        }
        sb.append("\n");
        char character = 'A';
        for (int i = 0 ; i < seats ; i++){
            sb.append(character++).append(" ");
            for (int j = 0 ; j < rows ; j++){
                if (j<executiveSpots[0]){
                    if (i<executiveSpots[1]) sb.append(executiveMap[i][j]);
                    else sb.append(" ");
                }
                else {
                    if (i<touristSpots[1]) sb.append(touristMap[i][j-executiveSpots[0]]);
                    else sb.append(" ");
                }
                sb.append("\t");
            }   
            sb.append("\n");
        }

        return sb.toString();
    }

    public static Flight getFlight(String flightCode){
        if (!flightCache.containsKey(flightCode)) return null;
        return flightCache.get(flightCode);
    }


    public static ArrayList<String> addReservation(String flightCode,FlightClass flightClass,int numberOfSpots) throws IOException{
        Flight flight = flightCache.get(flightCode);
        if (flight==null) return null;
        return flight.bookSeat(flightClass,numberOfSpots);
    }

    public static void fillPlaneSpots(String filename) throws IOException{
        Scanner sc;
        try (FileReader fr = new FileReader(filename)) {
            sc = new Scanner(fr);
            if (!sc.hasNextLine()){
                sc.close();
            }   String header = getFileHeader(filename);
            String [] divisions = getSplitHeader(header);
            String flightCode = getFlightCode(divisions);
            Flight flight;
            if (divisions.length==3){
                int [] array = {0,0};
                flight = new Flight(flightCode,getSpotArray(divisions[2]),array);
            }else{
                flight = new Flight(flightCode,getSpotArray(divisions[3]),getSpotArray(divisions[2]));
            }   sc.nextLine();
            while (sc.hasNextLine()){
                String line = sc.nextLine().trim();
                String[] parts = line.split(" ");
                if (parts.length!=2){
                    System.out.println("Invalid line format. Skipping over...");
                    continue;
                }
                String category = parts[0];
                int ammount = Integer.parseInt(parts[1]);
                FlightClass flightClass = FlightClass.stringToFlightClass(category);
                if (flight.bookSeat(flightClass, ammount)==null){
                    System.out.println("It's not possible to book for reserve: "+parts[0]+" "+parts[1]);
                }
                else System.out.println("Booked "+parts[0]+" "+parts[1]+" successfully.");
            }   flightCache.put(flightCode, flight);
        }
        sc.close();
    }
        
}

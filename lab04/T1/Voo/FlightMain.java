import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FlightMain {

    public static boolean runCommands(String line) throws IOException {
        System.out.println("Processing command: " + line);
        String[] input = line.split(" ");
        char command = 0;
        String filename;
        File file;
        
        if (input[0].length() != 1) {
            System.out.println("ERROR: invalid command!");
        } else {
            command = input[0].toUpperCase().charAt(0);
        }
        
        switch (command) {
            case 'Q':
                System.out.println("Exiting Flight Manager...");
                return false;  
            case 'H':
                FlightManager.printMenu();
                break;
            case 'R':
                if (input.length != 2) {
                    System.err.println("ERROR: Invalid input. Correct format: R <filename>");
                    break;
                }
                filename = input[1].trim();
                file = new File(filename);
                if (!file.exists()) {
                    System.out.println("File does not exist...");
                } else {
                    if (!FlightManager.validateFile(filename)) {
                        System.out.println("File provided is invalid...");
                    } else {
                        System.out.println("File provided is valid. Printing flight information...");
                        FlightManager.printHeaderInformation(filename);
                        System.out.print("\n");
                        FlightManager.fillPlaneSpots(filename);
                        System.out.print("\n");
                    }
                }
                break;
            case 'P':
                if (input.length != 2) {
                    System.out.println("ERROR: Invalid input. Correct format: P <flightCode>");
                    break;
                }
                String flightCode = input[1].trim();
                Flight flightToPrint = FlightManager.getFlight(flightCode);
                if (flightToPrint != null) {
                    System.out.println("Flight code found! Printing flight blueprint...");
                    System.out.println(FlightManager.printFlight(flightToPrint));
                } else {
                    System.out.println("This flight is not recorded!");
                }
                break;
            case 'S':
                if (input.length < 4) {
                    System.out.println("ERROR: Invalid input. Correct format: S <flightCode> <class> <seats>");
                    break;
                }
                flightCode = input[1].trim();
                String flightClassString = input[2].trim();
                FlightClass flightClass = FlightClass.stringToFlightClass(flightClassString);
                int numberOfSeats = Integer.parseInt(input[3].trim());
                ArrayList<String> list = FlightManager.addReservation(flightCode, flightClass, numberOfSeats);
                if (list != null) {
                    Flight flight = FlightManager.getFlight(flightCode);
                    StringBuilder sb = new StringBuilder();
                    sb.append(flightCode).append(" : ").append(flight.getLastBooking()).append(" | ");
                    System.out.println("Successfully added " + flightClassString + " " + numberOfSeats + " to " + flightCode);
                    System.out.println("Printing new flight blueprint...");
                    System.out.println(FlightManager.printFlight(flight));
                    for (String place : list) {
                        int row = Integer.parseInt(place.substring(0, 1));
                        int newRow = row + flight.getExecutiveSpots()[0];
                        sb.append(String.format(newRow + place.substring(1)));
                        sb.append(" | ");
                    }
                    System.out.println("Reserved seats:");
                    System.out.println(sb.toString());
                } else {
                    System.out.println("Not possible to book seats as requested.");
                }
                break;
            case 'C':
                if (input.length!=2){
                    System.out.println("ERROR: Invalid input. Correct format: C <reservation_code>");
                    break;
                }
                String reservationCode = input[1];
                String[] parts = reservationCode.split(":");
                flightCode = parts[0];
                int seats = Integer.parseInt(parts[1]);
                Flight flight = FlightManager.getFlight(flightCode);
                if (flight!=null){
                    if (flight.cancelBookings(seats)){
                        System.out.println("Reservation successfully canceled\nNew Flight blueprint...");
                        System.out.println(FlightManager.printFlight(flight));
                    } else {
                        System.out.println("Reservation not found");
                    }
                }else{
                    System.out.println("This flight is not recorded!");
                }
                break;
            default:
                System.err.println("Invalid Command...");
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        boolean proceed = true;
        Scanner sc = new Scanner(System.in);
        FlightManager.printMenu();
    
        if (args.length == 1) {
            String commandFile = args[0];
            try (BufferedReader br = new BufferedReader(new FileReader(commandFile))) {
                String line;
                while ((line = br.readLine()) != null && proceed) {
                    proceed = runCommands(line);  
                }
            } catch (IOException e) {
                System.err.println("Error reading command file: " + e.getMessage());
            }
        } else {
            while (proceed) {
                System.out.print("Enter command (H for help...): ");
                String line = sc.nextLine();  
                proceed = runCommands(line);  
            }
        }
        
        sc.close();
    }
    
}

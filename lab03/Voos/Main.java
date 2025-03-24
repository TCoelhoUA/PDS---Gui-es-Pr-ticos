package Voos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {

            System.out.println("\nEnter a command: (H for help)");
            System.out.print("Command: ");
            
            String input = sc.nextLine().strip();

            String[] command_args = Utils.isValidInput(input);

            for (String arg: command_args) {
                System.out.println(arg);
            }
            try {
                String filename;
                String flight_code;
                String classe;
                int number_seats;
                String reservation_code;
                
                String command_type = command_args[0];

                switch(command_type) {  //avaliação de cada código
                    case "H":  //H
                        FlightManager.listMenuOptions();
                        break;

                    case "R":  //R
                        filename = command_args[1];
                        FlightManager.readFile(filename);
                        break;

                    case "P":  //P
                        flight_code = command_args[1];
                        FlightManager.printFlight(flight_code);
                        break;

                    case "S":  //S
                        flight_code = command_args[1];
                        classe = command_args[2];
                        number_seats = Integer.parseInt(command_args[3]);
                        FlightManager.reserveTickets(flight_code, classe, number_seats);
                        break;

                    case "C":  //C
                        reservation_code = command_args[1];
                        FlightManager.cancelReservation(reservation_code);
                        break;

                    case "Q":  //Q
                        sc.close();
                        System.out.println("Exiting program...");
                        System.exit(0);
                        break;
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid command!");
            }
        }
    }
}

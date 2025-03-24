package Voos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FlightManager {
    private static HashMap<String, Flight> flights = new HashMap<>();

    public static void listMenuOptions() {
        String menu = String.format("""

            %-32s - Apresenta as opções do menu.
            %-40s - Lê um ficheiro de texto contento informação sobre um voo.
            %-40s - Exibe o plano das reservas de um voo.
            %-40s - Acrescenta uma nova reserva a um voo, com indicação do código do voo, da classe (T / E), e do número de lugares.
            %-40s - Cancela uma reserva.
            %-32s - Termina o programa.
            """, 
            "H", 
            "R " + "\033[3mfilename\033[0m", 
            "P " + "\033[3mflight_code\033[0m", 
            "S " + "\033[3mflight_code class number_seats\033[0m", 
            "C " + "\033[3mreservation_code\033[0m", 
            "Q"
        );
        System.out.print(menu);
    }

    public static void readFile(String filename) {
        try {
            File file = new File(filename);
            Scanner file_sc = new Scanner(file);

            Flight flight;
            String flight_code;
            int e_cols, e_rows, t_cols, t_rows;

            if (file_sc.hasNextLine()) {
                String line = file_sc.nextLine().strip();
                String[] line_args = line.split(":");
                
                if (line.matches(":[A-Z]{2,3}[0-9]{4}:[1-9][0-9]*x[1-9][0-9]*:[1-9][0-9]*x[1-9][0-9]*")) {
                    flight_code = line_args[1];
                    e_rows = Integer.parseInt(line_args[2].split("x")[0]);
                    e_cols = Integer.parseInt(line_args[2].split("x")[1]);
                    t_rows = Integer.parseInt(line_args[3].split("x")[0]);
                    t_cols = Integer.parseInt(line_args[3].split("x")[1]);
                }
                else if (line.matches(":[A-Z]{2,3}[0-9]{4}:[1-9][0-9]*x[1-9][0-9]*")) {
                    flight_code = line_args[1];
                    e_rows = 0;
                    e_cols = 0;
                    t_rows = Integer.parseInt(line_args[2].split("x")[0]);
                    t_cols = Integer.parseInt(line_args[2].split("x")[1]);
                }
                else {
                    file_sc.close();
                    System.out.println("ERROR: Invalid first line!");
                    return;
                }
                flight = new Flight(flight_code, e_rows, e_cols, t_rows, t_cols);

                flights.put(flight_code, flight);
                System.out.printf("Voo %s criado com sucesso!\n", flight_code);

                while (file_sc.hasNextLine()) {
                    line = file_sc.nextLine().strip();
                    line_args = line.split(" ");

                    String classe;
                    int number_seats;
                    if (line.matches("[TE] [1-9][0-9]*")) {
                        classe = line_args[0];
                        number_seats = Integer.parseInt(line_args[1]);

                        flight = flights.get(flight_code);

                        // check availability
                        if (ticketsAvailable(flight, classe, number_seats)) {
                            ArrayList<String> seats = flight.reserveTickets(classe, number_seats);
                            System.out.println(flight);
                            System.out.printf("%s:%d = %s ", flight_code, flight.getReservation_code(), seats.get(0));
                            for (int i=1; i<seats.size(); i++) {
                                System.out.printf("| %s ", seats.get(i));
                            }
                        }
                        else {
                            System.out.print("Não foi possível obter lugares para a reserva: " + line);
                        }
                    }
                }
            }
            else {
                file_sc.close();
                System.out.println("ERROR: Empty first line!");
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found!");
        }
    }

    public static void printFlight(String flight_code) {
        Flight flight = flights.get(flight_code);
        System.out.println(flight);
    }

    public static void reserveTickets(String flight_code, String classe, int number_seats) {
        Flight flight = flights.get(flight_code);

        // check availability
        if (ticketsAvailable(flight, classe, number_seats)) {
            System.out.println("FOI VALIDADO :SOB:");
            ArrayList<String> seats = flight.reserveTickets(classe, number_seats);
            System.out.println(flight);
            System.out.printf("%s:%d = %s ", flight_code, flight.getReservation_code(), seats.get(0));
            for (int i=1; i<seats.size(); i++) {
                System.out.printf("| %s ", seats.get(i));
            }
        }
        else {
            System.out.println("""
                We were unable to find any tickets that match your request at this time.
                Please try a different request or attempt again later.
                """);
        }
    }

    private static boolean ticketsAvailable(Flight flight, String classe, int number_seats) {
        int num_avail_seats = -1;
        switch (classe) {
            case "E":
                num_avail_seats = flight.getRemainingExecSeats();
                break;

            case "T":
                num_avail_seats = flight.getRemainingTourSeats();
                break;
        }
        System.out.println("FROM VERIFICATION:");
        System.out.println("AVAILABLE: "+num_avail_seats);
        System.out.println("REQUESTED: "+number_seats);
        return num_avail_seats >= number_seats;
    }

    public static Flight getFlight(String flight_code){
        return FlightManager.flights.get(flight_code);
    }

    public static void cancelReservation(String reservation_code){
        if (Utils.isValidCancelCode(reservation_code)) {
            String[] code_args = reservation_code.split(":");

            String flight_code = code_args[0];
            int code = Integer.parseInt(code_args[1]);

            Flight flight = flights.get(flight_code);

            // check executive class
            int[][] exec_seats = flight.getPlane().getExec_seats();
            try {
                for (int i=0; i<exec_seats.length; i++) {
                    for (int j=0; j<exec_seats[0].length; j++) {
                        if (exec_seats[i][j] == code) {
                            exec_seats[i][j] = 0;
                        }
                    }
                }
            }
            catch (IndexOutOfBoundsException e) {
                // do nothing
            }
            int[][] tour_seats = flight.getPlane().getTour_seats();
            for (int i=0; i<tour_seats.length; i++) {
                for (int j=0; j<tour_seats[0].length; j++) {
                    if (tour_seats[i][j] == code) {
                        tour_seats[i][j] = 0;
                    }
                }
            }
            System.out.println(flight);
        }
        else {
            System.out.println("ERROR: Invalid reservation code!");
        }
    }
}

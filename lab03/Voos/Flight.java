package Voos;

import java.util.ArrayList;

public class Flight {
    private String flight_code;
    private Plane plane;
    private int remainingExecSeats;
    private int remainingTourSeats;
    public static int reservation_code = 0;

    public Flight(String flight_code, int e_rows, int e_cols, int t_rows, int t_cols) {
        this.flight_code = flight_code;
        this.plane = new Plane(e_rows, e_cols, t_rows, t_cols);
        this.remainingExecSeats = e_rows*e_cols;
        this.remainingTourSeats = t_rows*t_cols;
    }

    public ArrayList<String> reserveTickets(String classe, int number_seats) {
        
        ArrayList<String> seats = new ArrayList<>();

        reservation_code++; //identificador de seats reservados
        int prefered_size, cntrl_idx, consec_avail_seats;
        int[][] seats_aux; //array dos seats
        switch (classe) {
            case "E":
                remainingExecSeats -= number_seats;
                seats_aux = plane.getExec_seats();  //arranjar o array 2d dos seats do plane
                int e_rows = seats_aux[0].length; //número de "rows" do avião - colunas da matriz seats_aux
                int e_cols = seats_aux.length;  //número de "letras" - rows da matriz seats_aux
                prefered_size = Math.min(number_seats, e_cols);
                cntrl_idx = 0;
                consec_avail_seats = 0; // consecutive available seats per i-th row

                while (number_seats != 0) { //se não houver mais lugares n se reserva
                    boolean flag = false;
                    for (int i=0; i<e_rows; i++) { //- por row
                        if(flag){
                            break;
                        }
                        for (int j=0; j<e_cols; j++) {//-por letra
                            if (seats_aux[j][i] == 0 && number_seats != 0) {//se estiver livre e ainda houver lugares
                                if (cntrl_idx+consec_avail_seats == j) {
                                    consec_avail_seats++;
                                }
                                else {
                                    consec_avail_seats = 1;
                                    cntrl_idx = j;
                                }

                                // if we reach desired "group" reservation we book the tickets
                                if (consec_avail_seats == prefered_size) {
                                    for (int k=cntrl_idx; k<cntrl_idx+consec_avail_seats; k++) {
                                        seats_aux[k][i] = reservation_code;
                                        seats.add(String.format("%d%c", i+1, 'A'+k));
                                    }
                                    number_seats -= consec_avail_seats;
                                    consec_avail_seats = 0;
                                    cntrl_idx = 0;
                                    prefered_size = Math.min(number_seats, e_cols);
                                }

                                if(number_seats == 0){
                                    flag = true;
                                    break;
                                }
                            }
                        }
                    }
                    prefered_size--;    // we "give up" trying to look for a certain "group size" so we decrease and try again
                }
                plane.setExec_seats(seats_aux);
                break;

            case "T":
                remainingTourSeats -= number_seats;
                seats_aux = plane.getTour_seats();  //arranjar o array 2d dos seats do plane
                int t_rows = seats_aux[0].length; //número de "rows" do avião - colunas da matriz seats_aux
                int t_cols = seats_aux.length;  //número de "letras" - rows da matriz seats_aux
                prefered_size = Math.min(number_seats, t_cols);
                cntrl_idx = 0;
                consec_avail_seats = 0; // consecutive available seats per i-th row

                // loop until all seats are set (this method assumes FlightManager deals with verifying if the reservation is possible)
                while (number_seats != 0) { //se não houver mais lugares n se reserva
                    boolean flag = false;
                    for (int i=0; i<t_rows; i++) { //- por row
                        if(flag){
                            break;
                        }
                        for (int j=0; j<t_cols; j++) {//-por letra
                            if (seats_aux[j][i] == 0 && number_seats != 0) {//se estiver livre e ainda houver lugares
                                if (cntrl_idx+consec_avail_seats == j) {
                                    consec_avail_seats++;
                                }
                                else {
                                    consec_avail_seats = 1;
                                    cntrl_idx = j;
                                }

                                // if we reach desired "group" reservation we book the tickets
                                if (consec_avail_seats == prefered_size) {
                                    for (int k=cntrl_idx; k<cntrl_idx+consec_avail_seats; k++) {
                                        seats_aux[k][i] = reservation_code;
                                        seats.add(String.format("%d%c", i+1, 'A'+k));
                                    }
                                    number_seats -= consec_avail_seats;
                                    consec_avail_seats = 0;
                                    cntrl_idx = 0;
                                    prefered_size = Math.min(number_seats, t_cols);
                                }

                                if(number_seats == 0){
                                    flag = true;
                                    break;
                                }
                            }
                        }
                    }
                    prefered_size--;    // we "give up" trying to look for a certain "group size" so we decrease and try again
                }
                plane.setTour_seats(seats_aux);
                break;
        }
        return seats;
    }

    public String getCode() {
        return flight_code;
    }

    public void setCode(String flight_code) {
        this.flight_code = flight_code;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public int getReservation_code() {
        return reservation_code;
    }

    public String getFlight_code() {
        return flight_code;
    }

    public int getRemainingExecSeats() {
        return remainingExecSeats;
    }

    public int getRemainingTourSeats() {
        return remainingTourSeats;
    }

    @Override
    public String toString() {
        return plane.toString();
    }
}

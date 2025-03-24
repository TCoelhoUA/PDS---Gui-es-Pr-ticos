package Voos;

public class Plane {
    private int[][] exec_seats;
    private int[][] tour_seats;

    public Plane(int e_rows, int e_cols, int t_rows, int t_cols) {
        /*
             R1 R2 R3 (...)
           C1 .  .  .  .  .  .
           C2 .  .  .  .  .  .
           C3 .  .  .  .  .  .
        (...)
        */
        this.exec_seats = new int[e_cols][e_rows];
        this.tour_seats = new int[t_cols][t_rows];
    }

    public String toString() {
        String result = "";
        
        boolean tourIsWider = (exec_seats.length < tour_seats.length) ? true:false;
        int min_cols = Math.min(exec_seats.length, tour_seats.length);
        int max_cols = Math.max(exec_seats.length, tour_seats.length);
        
        int i=0;
        // matching columns
        for (   ; i<min_cols; i++) {
            // i-th column from executive class
            for (int j=0; j<exec_seats[0].length; j++) {
                result += String.format("%-4d", exec_seats[i][j]);
            }
            // i-th column from tourist class
            for (int j=0; j<tour_seats[0].length; j++) {
                result += String.format("%-4d", tour_seats[i][j]);
            }
            result += "\n";
        }

        // possible extra columns
        for (   ; i<max_cols; i++) {
            if (tourIsWider) {
                for (int j=0; j<exec_seats[0].length; j++) {
                    result += "    ";
                }
                // i-th column from tourist class
                for (int j=0; j<tour_seats[0].length; j++) {
                    result += String.format("%-4d", tour_seats[i][j]);
                }
            }
            else {
                for (int j=0; j<exec_seats[0].length; j++) {
                    result += String.format("%-4d", exec_seats[i][j]);
                }
                // i-th column from tourist class 
                for (int j=0; j<tour_seats[0].length; j++) {
                    result += "    ";
                }
            }
            result += "\n";
        }

        return result;
    }

    public int[][] getExec_seats() {
        return exec_seats;
    }

    public void setExec_seats(int[][] exec_seats) {
        this.exec_seats = exec_seats;
    }

    public int[][] getTour_seats() {
        return tour_seats;
    }

    public void setTour_seats(int[][] tour_seats) {
        this.tour_seats = tour_seats;
    }
}

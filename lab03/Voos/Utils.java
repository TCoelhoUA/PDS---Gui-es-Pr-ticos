package Voos;

public class Utils {
    public static String[] isValidInput(String input) {
        String[] arguments = input.strip().split("\\s+");   // strips input then splits it by one or more spaces

        try {
            arguments[0] = arguments[0].toUpperCase();
            if (input.equals("H") || 
                input.matches("[rR]\\s+.*") || 
                input.matches("P [A-Z]{2,3}[0-9]{1,4}") || 
                input.matches("S [A-Z]{2,3}[0-9]{1,4} [TE] [1-9][0-9]*") || 
                input.matches("C [A-Z]{2,3}[0-9]{1,4}:[1-9][0-9]*") || 
                input.equals("Q")) {
                return arguments; // returns String[]
            }
        } catch (IndexOutOfBoundsException e) {
            // do nothing
        }


        return new String[0]; // returns empty String[] (main program deals with this exception)
    }

    public static boolean isValidCancelCode(String code){
        if(code.matches("[A-Z]{2,3}[0-9]{1,4}:[1-9][0-9]*")){
            return true;
        }
        return false;
    }

    /////prototypes/////
    public static int[][] markBiggest(int[][] matrix,int num_marks,int code){

        //count null spaces
        int free_spaces = 0;
        for(int i = 0;i<=matrix.length;i++){
            for(int j = 0;j<=matrix[0].length;j++){
                if(matrix[i][j] == 0){
                    free_spaces++;
                }
            }
        }
        //if no free spaces
        if(free_spaces == 0){
            return matrix;
        }
        //if free spaces
        //search for free columns
        boolean free_col = true;
        for(int j = 0;j<=matrix[0].length;j++){//for each column
            free_col = true;
            for(int i = 0;i<=matrix.length;i++){//for each row
                if(matrix[i][j] != 0){
                    free_col = false; //if occupied the column is not free
                    break;
                }
            }

            if(!free_col){//if column is not fully free, skip to next column
                continue;
            }else{
                int row = 0;
                while(row <= matrix.length){//while not exceeding the rows of the matrix
                    matrix[row][j] = code; //mark the place
                    row++; //increase the row
                    num_marks--; //decrease the number of leftover marks
                    if(num_marks == 0){ //if all marks are done, end
                        break;
                    }
                }
            }
        }
        //mark free spaces sequentially
        boolean done = false;
        if(num_marks != 0){ //if leftover marks to make
            for(int i = 0;i<=matrix.length;i++){
                if(done){
                    break;
                }
                for(int j = 0;j<=matrix[0].length;j++){
                    if(matrix[i][j] == 0){
                        matrix[i][j] = code; //replace 0 with code
                        num_marks--;
                        if(num_marks == 0){  //if no more marks to make we are done
                            done = true;
                        }
                    }
                    if(done){
                        break;
                    }
                }
            }
        }

        return matrix;
    }

    public static void printConcMatrices(int[][] matrixA,int[][] matrixB){
        //determine maximum number of rows
        int max_rows = Math.max(matrixA.length,matrixB.length);
        //if exceeding the number of rows of a matrix, print empty strings for as many columns that matrix has
        for(int i = 0;i<max_rows;i++){
            //print row of A
            if(i+1>matrixA.length){
                for(int j = 0;j<matrixA[0].length;j++){
                    System.out.print("  ");
                }
            }else{
                for(int j = 0;j<matrixA[0].length;j++){
                    System.out.print(matrixA[i][j] + " ");   
                }
            }
            //print row of B
            if(i+1>matrixB.length){
                for(int j = 0;j<matrixB[0].length;j++){
                    System.out.print("  ");
                }
            }else{
                for(int j = 0;j<matrixB[0].length;j++){
                    System.out.print(matrixB[i][j] + " ");   
                }
            }
        }
            //repeat for each row
        
    }
}

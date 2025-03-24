public class JGaloManager implements JGaloInterface {
    protected final int size = 3;
    private char currentPlayer;
    private char[][] matrix;
    private char result=' ';

    public JGaloManager(char startingPlayer){
        this.currentPlayer=startingPlayer;
        this.matrix = new char[size][size];
        for (int i = 0 ; i < size ; i++){
            for (int j = 0 ; j < size ; j++){
                matrix[i][j]=' ';
            }
        }
    }
    public char currentPlayer(){
        return this.currentPlayer;
    }

    public char result(){
        return this.result;
    }

    public boolean play(int i,int j){
        matrix[i-1][j-1] = currentPlayer;
        return true;
    }

    public boolean finished(){
        boolean emptySpace = false;

        // Check rows and columns
        for (int i = 0; i < size; i++) {
            char rowCharacter = matrix[i][0], colCharacter = matrix[0][i];
            boolean rowWin = (rowCharacter != ' '), colWin = (colCharacter != ' ');

            for (int j = 1; j < size; j++) {
                if (rowWin && matrix[i][j] != rowCharacter) rowWin = false;
                if (colWin && matrix[j][i] != colCharacter) colWin = false;
                if (matrix[i][j] == ' ') emptySpace = true;
            }

            if (rowWin || colWin) {
                result = currentPlayer;
                return true;
            }
        }

        // Check both diagonals
        char diag1Character = matrix[0][0], diag2Character = matrix[0][size - 1];
        boolean diag1Win = (diag1Character != ' '), diag2Win = (diag2Character != ' ');

        for (int i = 1; i < size; i++) {
            if (diag1Win && matrix[i][i] != diag1Character) diag1Win = false;
            if (diag2Win && matrix[i][size - 1 - i] != diag2Character) diag2Win = false;
        }

        if (diag1Win || diag2Win) {
            result = currentPlayer;
            return true;
        }

        if (emptySpace){
            changePlayer();
            return false;
        } else return true;
        }

    private void changePlayer(){
        if (currentPlayer == 'X') currentPlayer = 'O';
        else currentPlayer = 'X';
    }
    
}

import java.util.Random;
import java.util.Vector;

public class Puzzle {

    private char [][] characterMatrix;
    private boolean  [][] markedMatrix;
    public static final int PUZZLESIZE = 15;

    public Puzzle() {
        this.characterMatrix = createBlankPuzzle();
        this.markedMatrix = new boolean[PUZZLESIZE][PUZZLESIZE];
        setFalseMarkedMatrix();
    }

    public void resetPuzzle(){
        this.characterMatrix = createBlankPuzzle();
        setFalseMarkedMatrix();
    }

    private static char[][] createBlankPuzzle(){ 
        char[][] blankMatrix = new char[PUZZLESIZE][PUZZLESIZE];

        for (int i = 0; i < PUZZLESIZE; i++) {
            for (int j = 0; j < PUZZLESIZE; j++) {
                blankMatrix[i][j] = '_';
            }
        }

        return blankMatrix;
    }

    public char[][] getCharacterMatrix() {
        return characterMatrix;
    }

    public boolean isMarkedAt(int i , int j) {
        return markedMatrix[i][j];
    }

    private void setFalseMarkedMatrix(){ 
        for (int i = 0; i < PUZZLESIZE; i++) {
            for (int j = 0; j < PUZZLESIZE; j++) {
                this.markedMatrix[i][j] = false;
            }
        }
    }

    public void printMatrix(){ 
        for (int i = 0; i<PUZZLESIZE; i++){
            for (int j = 0; j<PUZZLESIZE; j++){
                System.out.printf("%c ", this.characterMatrix[i][j]);
            }
            System.out.println();
        }
    }


    /*
    public Vector<Vector<Integer>> reserveSpace(PuzzleWord puzzleWord) {
        Vector<Vector<Integer>> reservedSpace = new Vector<>();
        String word = puzzleWord.getWord();
        int i = puzzleWord.getPosition().get(0);
        int j = puzzleWord.getPosition().get(1);
        Directions direction = puzzleWord.getDirection();
    
        for (int k = 0; k < word.length(); k++) {
            if (i < 0 || i > PUZZLESIZE || j < 0 || j > PUZZLESIZE) {
                return null;
            }
            Vector<Integer> position = new Vector<>();
            position.add(i);
            position.add(j);
            reservedSpace.add(position);
    
            i = Directions.moveI(direction, i);
            j = Directions.moveJ(direction, j);
        }
        return reservedSpace;
    }
    */
    private boolean checkMargin(String word, Directions direction, int i, int j){
        //If it would try to write "word" starting at (i, j) with said direction, it would leave bounderies 
        int downAndRightMargin = PUZZLESIZE-word.length();
        int upAndLeftMargin = PUZZLESIZE-downAndRightMargin-1;

        switch (direction) {
            case UP:
                if (i<upAndLeftMargin) return false;
                break;
            case DOWN:
                if (i>downAndRightMargin) return false;
                break;
            case LEFT:
                if (j<upAndLeftMargin) return false;
                break;
            case RIGHT:
                if (j>downAndRightMargin) return false;
                break;
            case UPLEFT:
                if (i<upAndLeftMargin || j<upAndLeftMargin) return false;
                break;
            case UPRIGHT:
                if (i<upAndLeftMargin || j>downAndRightMargin) return false;
                break;
            case DOWNLEFT:
                if (i>downAndRightMargin || j<upAndLeftMargin) return false;
                break;
            case DOWNRIGHT:
                if (i>downAndRightMargin || j>downAndRightMargin) return false;
                break;  
            default:
                throw new AssertionError("Invalid direction.Terminating program...");
        }
        return true;
    }



    public boolean checkWord(PuzzleWord puzzleWord){
        //Determine if a word can be written
        String word = puzzleWord.getWord();
        Directions direction = puzzleWord.getDirection();
        Vector<Integer> position = puzzleWord.getPosition();
        int i = position.get(0);
        int j = position.get(1);

        if(!checkMargin(word, direction, i, j)) return false;

        for (int currentPos = 0; currentPos<word.length(); currentPos++){
            char currentChar = word.charAt(currentPos);
            if (markedMatrix[i][j]==true && characterMatrix[i][j] != currentChar)
                return false; //matrix has a different character on that slot
            i = Directions.moveI(direction, i);
            j = Directions.moveJ(direction, j);
        }
        return true;
    }

    public void writeWord(PuzzleWord puzzleWord){
        String word = puzzleWord.getWord();
        Directions direction = puzzleWord.getDirection();
        Vector<Integer> position = puzzleWord.getPosition();
        int i = position.get(0);
        int j = position.get(1);

        for (int currentPos = 0; currentPos<word.length(); currentPos++){
            characterMatrix[i][j] = word.charAt(currentPos);
            markedMatrix[i][j] = true;
            i = Directions.moveI(direction, i);
            j = Directions.moveJ(direction, j);
        }
    }

    public char getRandomCharacter(){
        Random randomGen = new Random();
        char character = (char)('a'+randomGen.nextInt(26));
        return character;
    }


    public void complete(){
        for (int i = 0 ; i < PUZZLESIZE ; i++){
            for (int j = 0 ; j < PUZZLESIZE ; j++){
                if (markedMatrix[i][j]==false){ 
                    characterMatrix[i][j] = getRandomCharacter();
                }
            }
        }
    }
}

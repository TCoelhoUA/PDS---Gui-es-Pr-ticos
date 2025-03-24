import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WSSolver{
    // Alter for flexible puzzle size
    static final int PUZZLESIZE=15; 
    static final int SKIPLINE=PUZZLESIZE+1;
    static final String[]DIRECTIONS = {"UpRight", "UpLeft", "Up", "Right", "Left", "DownRight", "DownLeft", "Down"};
    static final int LOWERLIMIT = 0;


    // Getter for the puzzle matrix

    public static boolean possiblePuzzle(Puzzle puzzle, ArrayList<String> words){
        if (searchWords(puzzle.getCharacterMatrix(), words) == null) return false;
        return true;
    }

    private static char[][] getPuzzleMatrix(Scanner scanner) throws IOException{ //If the puzzle is invalid it should return null
        char[][]matrixCharacters = new char[PUZZLESIZE][PUZZLESIZE];
        int row = 0;

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            line = line.split("#")[0].trim(); //Ignores coments
            
            if (row < PUZZLESIZE) {
                if (line.isEmpty()) return null;
                if (!Character.isLowerCase(line.charAt(0))) {
                    return null; //Checks if that line is a puzzle line
                }
                if (line.length() < PUZZLESIZE) {
                    return null; // Not enough columns
                }

                for (int col = 0 ; col<line.length() ; col++){
                    if(col < PUZZLESIZE && Character.isLowerCase(line.charAt(col))){
                        matrixCharacters[row][col]=line.charAt(col);
                    } else
                        return null; //not a lowercase char or current index >= size
                }
                row++;
            } else { //Reached PUZZLESIZE rows
                if (line.isEmpty()) continue;
                if (Character.isLowerCase(line.charAt(0))) { //If next row is suposed to be for the puzzle, its invalid
                    return null;
                }
                break; //Stops after reading PUZZLESIZE
            }
        }

        return row == PUZZLESIZE ? matrixCharacters : null; //Is invalid if scanner stopped before rows=PUZZLESIZE
    };


    // Getter for the keywords
    
    private static ArrayList<String> getWords(Scanner scanner) throws IOException{
        ArrayList<String>arrayWords = new ArrayList<>();
        
        for (int i = 1; i < SKIPLINE && scanner.hasNextLine();i++){
            scanner.nextLine(); // Skips the first 15 lines, which don't contain any key-words
        }
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] arrayLine = line.split("[ :,;]+");  // Splits line by blank space/semi-colon and adds the key-words to the array
            for (int i = 0 ; i < arrayLine.length ; i++){
                if (arrayLine[i].matches("[a-zA-Z]+") && Character.isUpperCase(arrayLine[i].charAt(0)) && arrayLine[i].length()>=3) { //Only adds words that start with a capital letter and don't contain any symbols/numbers/etc
                    if (!arrayWords.contains(arrayLine[i])){
                        arrayWords.add(arrayLine[i]);
                    }
                }
            }
        }
        arrayWords=removeSubstrings(arrayWords);    
        return arrayWords;
    }
    


    private static ArrayList<String> removeSubstrings(ArrayList<String>words){ // Filters each value list by removing the substrings
        ArrayList<String> filteredWords = new ArrayList<String>();
        for (String word1 : words){
            boolean isSubstring = false;
            for(String word2: words){
                if(!word1.equals(word2) && word2.toLowerCase().contains(word1.toLowerCase())){
                    isSubstring = true;
                }
            }
            if (!isSubstring){
                filteredWords.add(word1);
            }
        }
        return filteredWords;
    }

    // Matrix generator functions

    // Prints a given 2d array onto the console
    private static void printMatrix(char[][] matrix){ 
        for (int i = 0; i<PUZZLESIZE; i++){
            for (int j = 0; j<PUZZLESIZE; j++){
                System.out.printf("%c ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static void printCheat(ArrayList<FoundWord> wordStats){
        for(FoundWord word: wordStats){
            System.out.printf("%-20s%-10d%-10s%-10s\n", word.getWord(), word.getWord().length(), word.getPos(), word.getDirection());
        }
    }

    // Creates a matrix where every entry is "_"
    private static char[][] createBlankPuzzle(){ 
        char[][] blankMatrix = new char[PUZZLESIZE][PUZZLESIZE];

        for (int i = 0; i < PUZZLESIZE; i++) {
            for (int j = 0; j < PUZZLESIZE; j++) {
                blankMatrix[i][j] = '_';
            }
        }

        return blankMatrix;
    }

    // Recursively tries to find a word in a given direction
    private static boolean findWord(char[][] puzzleMatrix, int i, int j, String word, String direction, int currentPos){
        if (currentPos==word.length()) return true;

        if (i<LOWERLIMIT || j <LOWERLIMIT || i>=PUZZLESIZE || j>=PUZZLESIZE) return false;

        if (puzzleMatrix[i][j] != word.charAt(currentPos)) return false;
  
        switch (direction) {
            case "UpRight":
                return findWord(puzzleMatrix, i-1, j+1, word, direction, currentPos+1);
            case "UpLeft":
                return findWord(puzzleMatrix, i-1, j-1, word, direction, currentPos+1);
            case "Up":
                return findWord(puzzleMatrix, i-1, j, word, direction, currentPos+1);
            case "Right":
                return findWord(puzzleMatrix, i, j+1, word, direction, currentPos+1);
            case "Left":
                return findWord(puzzleMatrix, i, j-1, word, direction, currentPos+1);
            case "DownRight":
                return findWord(puzzleMatrix, i+1, j+1, word, direction, currentPos+1);
            case "DownLeft":
                return findWord(puzzleMatrix, i+1, j-1, word, direction, currentPos+1);
            case "Down":
                return findWord(puzzleMatrix, i+1, j, word, direction, currentPos+1);
            default:
                System.err.println("ERROR: Invalid Direction Argument");
                return false;
        }
    }

    // Validates words in a given array
    private static boolean validateWords(ArrayList<FoundWord> words){
        boolean valid = true;
        for (FoundWord word : words){
            if (!word.isValid()){
                valid = false;
                break;
            }
        }
        return valid;
    }

    // Fills in the blank matrix with the word in the right position and direction
    private static char[][] fillInWord(char[][] matrix, String word, String direction, int i, int j){
        int[] vector = new int[2];
        
        switch (direction) {
            case "UpRight":
                vector[0] = -1;
                vector[1] = 1;
                break;
            case "UpLeft":
                vector[0] = -1;
                vector[1] = -1;
                break;
            case "Up":
                vector[0] = -1;
                vector[1] = 0;
                break;
            case "Right":
                vector[0] = 0;
                vector[1] = 1;
                break;
            case "Left":
                vector[0] = 0;
                vector[1] = -1;
                break;
            case "DownRight":
                vector[0] = 1;
                vector[1] = 1;
                break;
            case "DownLeft":
                vector[0] = 1;
                vector[1] = -1;
                break;
            case "Down":
                vector[0] = 1;
                vector[1] = 0;
                break;
            default:
                System.err.println("ERROR: Invalid Direction Argument");
                System.exit(1);
        }

        for (int currentPos = 0; currentPos<word.length(); currentPos++){
            matrix[i][j] = word.toUpperCase().charAt(currentPos);
            i += vector[0];
            j += vector[1];
        }

        return matrix;
    }

    // Getter for the solution matrix to the puzzle
    private static char[][] getMatrixSolution(ArrayList<FoundWord> solutionArray){
        char[][] solutionMatrix = createBlankPuzzle();
        for (FoundWord foundWord: solutionArray){
            solutionMatrix = fillInWord(solutionMatrix, foundWord.getWord(), foundWord.getDirection(), foundWord.getX(), foundWord.getY());
        }
        return solutionMatrix;
    }



    private static ArrayList<FoundWord> searchWords(char[][] puzzleMatrix, ArrayList<String> words){
        ArrayList<FoundWord> wordStats = new ArrayList<>();

        for (String word: words){
            FoundWord wordStat = new FoundWord(word.toLowerCase());
            wordStats.add(wordStat);
        }

        for (int i = 0; i<PUZZLESIZE; i++){
            for (int j = 0; j<PUZZLESIZE; j++){
                for (int w = 0; w<words.size(); w++){
                    String word = words.get(w).toLowerCase();
                    if (puzzleMatrix[i][j] == word.charAt(0)){
                        FoundWord wordStat = wordStats.get(w);
                        for (String direction : DIRECTIONS){
                            if (findWord(puzzleMatrix, i, j, word, direction, 0)){
                                wordStat.incrementAppearances();
                                wordStat.setDirection(direction);
                                wordStat.setPos(i, j);
                            }
                        }
                    }
                }
            }
        }
        if (!validateWords(wordStats)){
            return null;
        }
        return wordStats;
    }


    // Writing functions
    // Writes cheat info onto a file
    private static void writeCheatOntoFile(String filename,ArrayList<FoundWord> wordStats) throws IOException{
        FileWriter writer = new FileWriter(filename);
        writer.write("Word data:\n");
        writer.write("----------\n");
        String header = String.format("%-20s%-10s%-10s%-10s%n","Word","Length","(i,j)","Direction");
        writer.write(header);
        for(FoundWord word: wordStats){
            String formattedString = String.format("%-20s%-10d%-10s%-10s%n", word.getWord(), word.getWord().length(), word.getPos(), word.getDirection());
            writer.write(formattedString);
        }
        
        writer.close();
    }

    // Writes solution matrix onto a file
    private static void writeSolutionMatrixOntoFile(String filename,char[][] solutionMatrix) throws IOException{
        FileWriter writer = new FileWriter(filename,true);
        for (int i = 0 ; i < solutionMatrix.length; i++){
            writer.write("\n");
            for (int j = 0 ; j < solutionMatrix.length; j++){
                writer.write(solutionMatrix[i][j] + " ");
            }
        }
        writer.close();
    }
    
    // Calls both writing methods
    private static void writeSolution(String filename, char [][] solutionMatrix,ArrayList<FoundWord> wordStats) throws IOException {
        writeCheatOntoFile(filename, wordStats);
        writeSolutionMatrixOntoFile(filename, solutionMatrix);
    }

    // Main function
    
    public static void main(String[] args) throws IOException{
        if (args.length<1){
            System.err.println("Error, insufficient number of arguments.");
        }
        try {
            Scanner scanner = new Scanner(new File(args[0]));
            char[][]matrixCharacters = WSSolver.getPuzzleMatrix(scanner);
            if (matrixCharacters == null){
                System.out.println("Invalid puzzle!");
                return;
            }
            
            scanner.close();
            scanner = new Scanner(new File(args[0]));
            WSSolver.printMatrix(matrixCharacters);
            ArrayList<String> arrayWords =WSSolver.getWords(scanner);
            System.out.println("Keywords are the following:");
            System.out.println(arrayWords);
            scanner.close();
            

           ArrayList<FoundWord> wordStats = searchWords(matrixCharacters, arrayWords);
           if (wordStats==null){
            System.err.println("ERROR: Words arent valid for this puzzle!");
            System.exit(1);
           }

           char[][] solutionMatrix = getMatrixSolution(wordStats);
           printCheat(wordStats);
           printMatrix(solutionMatrix);
           String fileName=args[0].replace(".txt", "_result.txt");
           writeSolution(fileName,solutionMatrix,wordStats);
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        }
    }
}

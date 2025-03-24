import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class WSGenerator {

    private static final int NUMBER_OF_TRIES = 5;

    private static ArrayList<String> getWords(String filename) throws FileNotFoundException {
        ArrayList<String> listOfWords = new ArrayList<>();
    
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);
    
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("[ :,;]+");
                for (String word : parts) {

                    if (word.matches("[a-zA-Z]+") && Character.isUpperCase(word.charAt(0)) 
                        && word.length() >= 3 && !listOfWords.contains(word)) {

                        if (word.length()<=Puzzle.PUZZLESIZE)
                            listOfWords.add(word.toLowerCase());
                        else 
                            System.out.println("Word "+word+" does not fit. It will not be considered.");

                    } else{
                        System.out.println("Word "+word+" not considered.");
                    }
                }
            }
            sc.close();
            listOfWords = removeSubstrings(listOfWords);
            return listOfWords;
    
        } catch (FileNotFoundException exception) {
            System.err.println("File not found. Terminating program...\n");
            System.exit(1);
        }
        return null;
    }
    

    private static ArrayList<String> removeSubstrings(ArrayList<String>words){ 
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
            else{
                System.out.println("Word "+ word1+ " not considered.\n");
            }
        }
        return filteredWords;
    }

    private static List<Vector<Integer>> getAllPositions(){
        List<Vector<Integer>> positions = new ArrayList<>();
        for (int i = 0; i < Puzzle.PUZZLESIZE; i++) {
            for (int j = 0; j < Puzzle.PUZZLESIZE; j++) {
                Vector<Integer> position = new Vector<>(2);
                position.add(i);
                position.add(j);
                positions.add(position);
            }
        }
        return positions;
    }

    public static void writePuzzleToFile(Puzzle puzzle,ArrayList<String>words,String filename) throws IOException{
        FileWriter fw = new FileWriter(filename);
        for (int i = 0; i<Puzzle.PUZZLESIZE; i++){
            for (int j = 0; j<Puzzle.PUZZLESIZE; j++){
                fw.write(puzzle.getCharacterMatrix()[i][j]);
            }
            fw.write("\n");
        }
        for (int i = 0 ; i < words.size() ; i++){
            String word = words.get(i); 
            String capWord = word.substring(0, 1).toUpperCase() + word.substring(1);
            fw.write(capWord);
            if (i < words.size() - 1) { 
                fw.write("\n");
            }
        }
        fw.flush();
        fw.close();
    }

    private static void printWords(ArrayList<String>words){
        for (int i = 0 ; i < words.size() ; i++){
            String word = words.get(i); 
            String capWord = word.substring(0, 1).toUpperCase() + word.substring(1);
            System.out.println(capWord);
        }
    }

    public static int handleVerification(String[]args){
        int length = args.length;
        if (length!=2 && length!= 4) return 0;
        if (!args[0].equals("-w")) return 0;
        if (length==2) return 1;
        if (!args[2].equals("-s"))return 0;
        return 2;
    }

    public static void main(String[] args) throws IOException {
        int flagProcessing = handleVerification(args);
        if (flagProcessing==0){
            System.err.println("Invalid format. Try - > java WSGenerator -w <word_file_name.txt> (-s <word_file_name.txt>).\n");
            System.err.println("Terminating program...");
            System.exit(1);
        }
        String filename = args[1];
        ArrayList<String> words = getWords(filename);
        if (words.isEmpty()){
            System.out.println("Word file is completely empty...Terminating program.");
            System.exit(1);
        }

        System.out.println("The words are the following: \n");
        //List Check
        boolean foundPuzzle = false;
        Puzzle puzzle = new Puzzle();
        
        List<Directions> directions = Arrays.asList(Directions.values());
        List<Vector<Integer>> positions = getAllPositions();
        ArrayList<PuzzleWord> wordStats = new ArrayList<PuzzleWord>();
        
        for (String word : words){
            PuzzleWord wordStat = new PuzzleWord(word);
            wordStats.add(wordStat);
        }

        for (int tryNumber = 0; tryNumber<NUMBER_OF_TRIES; tryNumber++){
            wordLoop:
            for (String word : words){
                Collections.shuffle(directions);
                PuzzleWord wordStat = wordStats.get(words.indexOf(word));

                for (Directions direction : directions){
                    wordStat.setDirection(direction);
                    Collections.shuffle(positions);

                    for (Vector<Integer> startingPostion : positions){
                        wordStat.setPosition(startingPostion);
                        if (puzzle.checkWord(wordStat)){ // done-ish
                            puzzle.writeWord(wordStat); // donezos
                            System.out.println("Word: " + word + " | Direction: " + direction + " | Starting Position: " + startingPostion);
                            wordStat.incrementAppearances();
                            continue wordLoop;
                        }
                    }
                }
            }

            if (WSSolver.possiblePuzzle(puzzle, words)){
                foundPuzzle = true;
                break; //Puzzle found
            }

            //Try again
            for (PuzzleWord wordStat : wordStats) wordStat.resetPuzzleWord();
            puzzle.resetPuzzle();
            
        }
        
        if (!foundPuzzle){
            System.err.println("This puzzle is not possible to do");
            System.exit(1);
        }

        while (true) {
            puzzle.complete(); //to do
            if (WSSolver.possiblePuzzle(puzzle, words)){
                puzzle.printMatrix();
                printWords(words);
                if (flagProcessing==2){
                    writePuzzleToFile(puzzle,words,args[3]);
                }

                break;
            }
                
        }
    }

}

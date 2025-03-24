import java.util.Vector;

public class PuzzleWord {

    private String word;
    private Vector<Integer> position;
    private int appearances;
    private Directions direction;

    public PuzzleWord(String word) {
        this.word = word;
        this.appearances = 0;
    }

    public void resetPuzzleWord(){
        this.appearances = 0;
        this.direction = null;
        this.position = null;
    }

    public String getWord() {
        return word;
    }

    public Vector<Integer> getPosition(){
        return position;
    }

    public int getAppearances() {
        return appearances;
    }

    public Directions getDirection() {
        return direction;
    }

    public void setPosition(Vector<Integer> pos) {
        this.position = pos;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    public void incrementAppearances() {
        this.appearances++;
    }

    public boolean isValid(){
        return appearances==1;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((word == null) ? 0 : word.hashCode());
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        result = prime * result + appearances;
        result = prime * result + ((direction == null) ? 0 : direction.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PuzzleWord other = (PuzzleWord) obj;
        if (word == null) {
            if (other.word != null)
                return false;
        } else if (!word.equals(other.word))
            return false;
        if (position == null) {
            if (other.position != null)
                return false;
        } else if (!position.equals(other.position))
            return false;
        if (appearances != other.appearances)
            return false;
        if (direction != other.direction)
            return false;
        return true;
    }
}
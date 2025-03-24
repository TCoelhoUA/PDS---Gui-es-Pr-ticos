public class FoundWord {

    private String word;
    private int x;
    private int y;
    private int appearances;
    private String direction;

    public FoundWord(String word) {
        this.word = word;
        this.appearances = 0;
    }

    public String getWord() {
        return word;
    }

    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getPos(){
        return String.format("%d,%d", x+1, y+1);
    }

    public int getAppearances() {
        return appearances;
    }

    public String getDirection() {
        return direction;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void incrementAppearances() {
        this.appearances++;
    }

    public boolean isValid(){
        if (isPalindrome()) return appearances==2;
        return appearances==1;
    }

    public boolean isPalindrome(){
        int size = word.length();
        for (int i = 0; i<(size/2); i++){
            if (word.charAt(i) != word.charAt(size-i-1))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((word == null) ? 0 : word.hashCode());
        result = prime * result + x;
        result = prime * result + y;
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
        FoundWord other = (FoundWord) obj;
        if (word == null) {
            if (other.word != null)
                return false;
        } else if (!word.equals(other.word))
            return false;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        if (appearances != other.appearances)
            return false;
        if (direction == null) {
            if (other.direction != null)
                return false;
        } else if (!direction.equals(other.direction))
            return false;
        return true;
    }

    
    

}
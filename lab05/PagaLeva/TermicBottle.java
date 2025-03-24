public class TermicBottle extends Container{
    public TermicBottle(Portion portion) {
        super(portion);
    }

    @Override
    public String toString() {
        return "TermicBottle with portion = " + portion;
    }
}

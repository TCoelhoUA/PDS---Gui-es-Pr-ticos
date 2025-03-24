public enum Directions {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    UPRIGHT,
    UPLEFT,
    DOWNRIGHT,
    DOWNLEFT;

    public static int moveI(Directions direction, int i){
        if (direction==UP || direction == UPLEFT || direction == UPRIGHT)
            return i-1;
        if (direction==DOWN || direction==DOWNLEFT || direction==DOWNRIGHT)
            return i+1;
        return i;
    }

    public static int moveJ(Directions direction, int j){
        if (direction==RIGHT || direction == DOWNRIGHT || direction == UPRIGHT)
            return j+1;
        if (direction==LEFT || direction==DOWNLEFT || direction==UPLEFT)
            return j-1;
        return j;
    }
}

import java.util.ArrayList;
import java.util.Arrays;

public class Sopa {
    private char[][] caracteres = new char[15][15];
    private ArrayList<String> palavras = new ArrayList<String>();

    public Sopa(char[][] caracteres, ArrayList<String> palavras) {
        this.caracteres = caracteres;
        this.palavras = palavras;
    }

    public char[][] getCaracteres() {
        return caracteres;
    }

    public void setCaracteres(char[][] caracteres) {
        this.caracteres = caracteres;
    }

    public ArrayList<String> getPalavras() {
        return palavras;
    }

    public void setPalavras(ArrayList<String> palavras) {
        this.palavras = palavras;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(caracteres);
        result = prime * result + ((palavras == null) ? 0 : palavras.hashCode());
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
        Sopa other = (Sopa) obj;
        if (!Arrays.deepEquals(caracteres, other.caracteres))
            return false;
        if (palavras == null) {
            if (other.palavras != null)
                return false;
        } else if (!palavras.equals(other.palavras))
            return false;
        return true;
    }

    @Override
    public String toString() {
        String result = "";

        for (int i=0; i<15; i++) {
            for (int j=0; j<15; j++) {
                //System.out.print(caracteres[i][j]);
                result += caracteres[i][j] + " ";
            }
            result += "\n";
        }

        return result;
    }
}

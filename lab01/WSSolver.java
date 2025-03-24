import java.util.ArrayList;

public class WSSolver {
    private Sopa sopa;
    private ArrayList<String> palavras;
    private char[][] solucao;

    public WSSolver(Sopa sopa) {        
        this.sopa = sopa;
        this.palavras = sopa.getPalavras();  // (Vale a pena? - Por Pensar)
        this.solucao = new char[15][15];
        this.solucao = findWords(sopa);
    }

    public Sopa getSopa() {
        return sopa;
    }

    public void setSopa(Sopa sopa) {
        this.sopa = sopa;
    }

    public ArrayList<String> getPalavras() {
        return palavras;
    }

    public void setPalavras(ArrayList<String> palavras) {
        this.palavras = palavras;
    }

    public char[][] getSolucao() {
        return solucao;
    }

    public void setSolucao(char[][] solucao) {
        this.solucao = solucao;
    }

    public char[][] findWords(Sopa sopa) {
        // Inicialização da tabela vazia
        char[][] result = new char[15][15];
        for (int i=0; i<15; i++) {
            for (int j=0; j<15; j++) {
                result[i][j] = '_';
            }
        }

        // Pesquisa das palavras
        for (String palavra : palavras) {
            for (int i=0; i<15; i++) {
                for (int j=0; j<15; j++) {
                    for (Position pos : Position.values()) {
                        if (isValid(palavra, pos, i, j)) {
                            int comprimento = palavra.length();
                            int x = i;
                            int y = j;

                            System.out.printf("%-15s %3d    %-5s   %-10s \n", palavra, comprimento, String.format((i+1) + "," + (j+1)), pos.toString().toLowerCase());
                            // Escrita da palavra na solução
                            for (int n=0; n<comprimento; n++) {
                                result[x][y] = palavra.charAt(n);
                                x += pos.x;
                                y += pos.y;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    // Verifica se para a posição pretendida, a palavra coincide com a palavra pretendida
    private boolean matches(String palavra, Position position, int x, int y) {
        int comprimento = palavra.length();
        char[][] caracteres = sopa.getCaracteres();

        // Verifica todas as posições dos caracteres
        for (int i=0; i<comprimento; i++) {
            if (caracteres[x][y] != palavra.charAt(i)) {
                return false;
            }

            x += position.x;
            y += position.y;
        }

        return true;
    }

    // Verifica se para a posição pretendida, a palavra tem espaço
    private boolean hasRoom(String palavra, Position position, int x, int y) {
        int comprimento = palavra.length();

        // Soma N vezes o vetor unitário correspondente à posição
        for (int i=0; i<comprimento; i++) {
            x += position.x;
            y += position.y;
        }

        // Verifica se as novas coordenadas estão fora dos limites
        return (0 <= x & x <= 14 & 0 <= y & y <= 14);
    }

    // Verifica se a palavra tem espaço para ser escrita na posição indicada
    // Verifica que a palavra não sobrepões outras palavras. Se o fizer, os caracteres têm de coincidir
    private boolean isValid(String palavra, Position position, int x, int y) {
        return hasRoom(palavra, position, x, y) && matches(palavra, position, x, y);
    }

    @Override
    public String toString() {
        String result = "\n";

        for (int i=0; i<15; i++) {
            for (int j=0; j<15; j++) {
                result += solucao[i][j] + " ";
            }
            result += "\n";
        }

        return result;
    }
}

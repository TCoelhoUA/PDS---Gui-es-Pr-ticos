import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ex1 {
    public static void main(String[] args) {
        // Validação de argumentos
        String filename = "";

        int nargs = args.length;
        if (nargs == 1) {
            filename = args[0];
        }
        else {
            System.err.println("Número de argumentos incorretos!");
            System.exit(0);
        }

        try {
            Scanner file = new Scanner(new File(filename));

            char[][] caracteres = new char[15][15];
            ArrayList<String> palavras = new ArrayList<String>();

            int counter = 0;
            // Tratamento dos caracteres da sopa
            while (file.hasNextLine() && counter<15) {
                String line = file.nextLine();
                
                // Verificar se a linha é vazia
                if (line.length() == 0) {
                    System.out.println("Linha vazia!");
                    System.exit(0);
                }

                // Separação de possíveis comentários
                line = line.split("#")[0];
                line = line.trim();

                // Verificar se todos os caracteres são alfabéticos, minúsculos e o comprimento é de 15 caracteres
                if (!line.matches("[a-z]{15}")) {
                    System.out.println("Caracter inválido detetado!");
                    System.exit(0);
                }

                // Adição dos caracteres ao array bi-dimensional
                for (int c=0; c<15; c++) {
                    caracteres[counter][c] = line.charAt(c);
                }

                counter++;
                //System.out.println(line);   // Teste
            }

            // Tratamento das palavras da sopa
            while (file.hasNextLine()) {
                String line = file.nextLine();
                String[] words = line.split("[,;\s]");

                for (int i=0; i<words.length; i++) {
                    // Verificar que a primeira letra é maiúscula
                    if (!Character.isUpperCase(words[i].charAt(0))) {
                        System.out.print("A primeira letra da palavra não é maiúscula!");
                        System.exit(0);
                    }

                    // Verificar se o tamanho é igual ou maior a 3
                    if (words[i].length() < 3) {
                        System.out.print("A palavra tem de ter pelo menos 3 caracteres!");
                        System.exit(0);
                    }

                    // Adicionar ao ArrayList se a palavra não estiver lá
                    if (!palavras.contains(words[i])) {
                        palavras.add(words[i].toLowerCase());
                    }
                }
            }

            file.close();

            // Criação da Sopa
            Sopa sopa = new Sopa(caracteres, palavras);
            
            // Sopa
            //System.out.println(sopa.toString());

            // Criação do resolvedor da sopa
            WSSolver wsgen = new WSSolver(sopa);

            // Impressão da solução
            System.out.println(wsgen.toString());
            

        }
        catch (FileNotFoundException e) {
            System.err.printf("ERROR opening %s\n", filename);
            System.exit(0);
        }

    }
}

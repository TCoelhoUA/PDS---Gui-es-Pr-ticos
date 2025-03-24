import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ex2 {
    public static void main(String[] args) {
        // Validação de argumentos
        String filename = "";
        String soupname = "";

        
        int nargs = args.length;
        switch (nargs) {
            case 2:
                if (!args[0].equals("-w")) {
                    System.err.printf("%s desconhecido.\nEsperado: -w\n", args[0]);
                    System.exit(0);
                }
                filename = args[1];
                break;

            case 4:
                if (!args[0].equals("-w") || !args[2].equals("-s")) {
                    System.err.printf("%s desconhecido.\nEsperado: -w path/to/source -s path/to/destination\n", args[0]);
                    System.exit(0);
                }
                filename = args[1];
                soupname = args[3];
                break;

            default:
                System.err.println("Número de argumentos incorretos!");
                System.exit(0);
        }
        
        // Leitura do ficheiro
        try {
            File file = new File(filename);

            ArrayList<String> palavras = new ArrayList<>();

            Scanner file_sc = new Scanner(file);

            while (file_sc.hasNextLine()) {
                palavras.add(file_sc.nextLine());
            }

            file_sc.close();

            // Geração da sopa de letras
            WSGenerator wsgen = new WSGenerator(palavras);

            // Print da sopa de letras
            System.out.println(wsgen.toString());

            // Guardar ficheiro (Opcional)
            if (nargs == 4) {
                wsgen.saveFile(soupname);
            }

        } catch (FileNotFoundException e) {
            System.err.printf("Error opening %s.\n", filename);
            System.exit(0);
        }
    }
}

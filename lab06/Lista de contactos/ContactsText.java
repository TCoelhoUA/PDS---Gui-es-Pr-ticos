import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactsText implements ContactsStorageInterface{
    // Assuming text contacts like:
    //
    // Name, Number
    //
    // i.e.:
    //
    // Tiago Coelho, 966955012
    //
    // Any invalid line will be ignored
    File file;


    private ContactsText(File file) {
        this.file = file;
    }
    
    public static ContactsText getInstance(File file) {
        if (file.exists()) {
            return new ContactsText(file);
        }
        return null;
    }
    
    public List<Contact> loadContacts() {
        try (Scanner file_sc = new Scanner(this.file)) {
            List<Contact> contacts = new ArrayList<>();

            String line;
            String[] data;
            file_sc.nextLine();
            while (file_sc.hasNextLine()) {
                line = file_sc.nextLine();
                if(line.equals("}")){
                    break;
                }
                data = line.split(",");
                if (data.length != 2) {
                    continue;   // ignore invalid line
                }
                if (!data[1].matches("[0-9]{9}")) {
                    continue;   // ignore invalid line
                }
    
                contacts.add(new Contact(data[0].strip(), Integer.parseInt(data[1].strip())));
            }
            return contacts;
        } catch (FileNotFoundException e) {
            System.err.printf("Ficheiro '%s' não existe!\n", this.file.toPath());
            return null;
        }
    }

    public boolean saveContacts(List<Contact> list){
        boolean res = true;
        try(FileWriter fw = new FileWriter(this.file)){
            for(Contact c: list){
                String contString = c.getName() + ", " + String.valueOf(c.getNumber());
                fw.write(contString);
            }
        }catch (IOException e){
            System.out.println("Erro ao abrir ficheiro!\n");
            res = false;
        }
        return res;
    }
}
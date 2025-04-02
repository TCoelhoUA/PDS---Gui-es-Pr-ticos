import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactsJSON implements ContactsStorageInterface{
    private File file;

    public ContactsJSON(File file) {
        this.file = file;
    }

    public List<Contact> loadContacts(){
        try (Scanner file_sc = new Scanner(this.file)) {
            List<Contact> contacts = new ArrayList<>();

            String line;
            String[] data;
            while (file_sc.hasNextLine()) {
                line = file_sc.nextLine();
                if(line.equals("}")){
                    break;
                }
                data = line.replace(',',' ').trim().split(":");
                /*for(String s:data){
                    System.out.println(s);
                }*/
    
                if (data.length != 2) {
                    System.out.println("linha ignorada, length exceeded");
                    continue;   // ignore invalid line
                }
                if (!data[1].replace('"',' ').trim().matches("[0-9]{9}")) {
                    System.out.println("linha ignorada,formato do num incorreto");
                    continue;   // ignore invalid line
                }
                /*for(String s:data){
                    System.out.println(s);
                }*/
    
                contacts.add(new Contact(data[0].strip(), Integer.parseInt(data[1].replace('"',' ').trim())));
                /*for(Contact ct: contacts){
                    System.out.println(ct);
                }*/
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
            fw.write("{\n");
            for(Contact c: list){
                String contString = c.getName() + ":" + String.valueOf(c.getNumber())+",\n";
                fw.write(contString);
            }
            fw.write("}");
        }catch (IOException e){
            System.out.println("Erro ao abrir ficheiro!\n");
            res = false;
        }
        return res;
    }

}
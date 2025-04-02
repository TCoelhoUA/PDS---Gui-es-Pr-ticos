import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file = new File("jsonStuff.json"); //ficheiro com dados "json"
        ContactsStorageInterface jsonStor = new ContactsJSON(file); //o ficheiro está aqui somehow
        ContactList cl = new ContactList(jsonStor); //a contact list é inicializada pa lidar com json
        cl.openAndLoad(jsonStor); //deveria criar uma lista de contactos a partir do json

        for(Contact elem: cl.getList()){
            System.out.println(elem.toString()); //imprimir os contactos da lista
        }
        //System.out.println(file.toPath().toString().endsWith(".json"));
    }
}

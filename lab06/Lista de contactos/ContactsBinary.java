import java.io.File;
import java.util.List;

public class ContactsBinary extends ContactsFileManager {
    public ContactsBinary(File file) {
        super(file);
    }
    public List<Contact> loadContacts(String filename);
    public boolean saveContacts(List<Contact> list);
}
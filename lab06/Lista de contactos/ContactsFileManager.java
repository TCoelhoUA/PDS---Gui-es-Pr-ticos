import java.io.File;

public class ContactsFileManager implements ContactsStorageInterface {
    protected File file;

    public ContactsFileManager(File file) {
        this.file = file;
    }

    private ContactsFileManager create() {
        String path = file.toPath().toString();

        // if (path.endsWith(".txt")) return new ContactsText(file);
        // else if (path.endsWith(".bin")) return new ContactsBinary(file);
        // else if (path.endsWith(".json")) return new ContactsJSON(file);
        if (path.endsWith(".txt")) return ContactsText.getInstance(file);
        else if (path.endsWith(".bin")) return ContactsBinary.getInstance(file);
        else if (path.endsWith(".json")) return ContactsJSON.getInstance(file);
    }
}

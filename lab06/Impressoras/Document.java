import java.io.File;

public class Document {
    File doc;
    
    public Document(String filename) {
        this.doc = new File(filename);
    }
}

import java.util.ArrayList;
import java.util.List;

public class ContactList implements ContactsInterface {
    private List<Contact> list;
    private ContactsStorageInterface contactStore;

    public ContactList(ContactsStorageInterface contactStore) {
        this.list = new ArrayList<>();
        this.contactStore = contactStore;
    }

    public List<Contact> getList(){
        return this.list;
    }

    public void openAndLoad(ContactsStorageInterface store) {
        contactStore = store;
        list = contactStore.loadContacts();
    } 

    public void saveAndClose() {
        contactStore.saveContacts(list);
    }

    public void saveAndClose(ContactsStorageInterface store) {
        store.saveContacts(list);
    }

    public boolean exist(Contact contact) {
        return list.contains(contact);
    }

    public Contact getByName(String name) {
        for (Contact contact : list) {
            if (contact.getName().equals(name)) {
                return contact;
            }
        }
        return null;
    }

    public boolean add(Contact contact) {
        return list.add(contact);
    }

    public boolean remove(Contact contact) {
        return list.remove(contact);
    }
}

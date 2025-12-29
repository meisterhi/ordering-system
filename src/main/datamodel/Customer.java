package datamodel;

import datamodel.NameSplitter.SplitName;

public class Customer {
    
    private long id;
    private String name;
    private String firstNames;
    private String contacts;
    private final NameSplitterImp NameSplitter = NameSplitterImp.getInstance();
    private final ContactsSplitter ContactSplitter = ContactsSplitter.getInstance();

    public Customer() {
        this(-1, "", "", "");
    }

    public Customer(String name){
        this();
        var parts = NameSplitter.split(name).orElse(new SplitName("", ""));

        this.name = parts.name();
        this.firstNames = parts.firstNames();
    }

    public Customer(long id, String name, String firstNames, String contacts) {
        setId(id);
        setName(name);
        setFirstNames(firstNames);
        setContacts(contacts);
    }


    public Customer setId(long id) {
       if(id <= 0) {
            this.id = -1;
            return this;
        }
        if(this.id != -1 || id <= 0) {
            return this; // id already set, do not change
        }

        this.id = id;
        return this;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        if(name == null) {
        throw new IllegalArgumentException("name argument is null");
        }
        if(this.name != null && !this.name.isEmpty()) {
            return this; // name already set, do not change
        }
            this.name = name.trim();

        return this;
    }

    public String getFirstNames() {
        return firstNames;
    }

    public Customer setFirstNames(String firstNames) {
        if (firstNames == null) {
            throw new IllegalArgumentException("firstName argument is null");
        }
        this.firstNames = firstNames.trim();
        return this;
    }

    public String getContacts() {
        return contacts;
    }

    public String contact(int index) {
        return ContactSplitter.contact(this, index);
    }

    public Customer setContacts(String contacts) {
        if (contacts == null) {
            throw new IllegalArgumentException("contacts argument is null");
        }
        this.contacts = contacts.trim();
        return this;
    }

    public Customer addContact(String varContact) {
        if (varContact == null) {
            throw new IllegalArgumentException("contact argument is null");
        }
        ContactSplitter.addContact(this, varContact).getContacts();
        return this;
    }

    public Customer removeContact(int index) {
        ContactSplitter.removeContact(this, index);
        return this;
    }

    public Iterable<String> getContactsAsIterable() {
        return ContactSplitter.contactsAsIterable(this);
    }
}

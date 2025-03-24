package Construtor;

public class Person {
    private String name;
    private int cc;
    
    public Person(String name, int cc) {
        this.name = name;
        this.cc = cc;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCc() {
        return cc;
    }
    public void setCc(int cc) {
        this.cc = cc;
    }

    public String toString() {
        return this.getName();
    }
}

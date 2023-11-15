package org.example;

public class Passenger {
    String name;


    protected  Passenger (String n) {
        this.name = n;
    }

    public void causeFear() {
        System.out.println(this.name+ ": Aaaaa ");
    }

    @Override
    public String toString() {
        return "Passenger{"+ name + '}';
    }
}

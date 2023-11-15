package org.example;



import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Thread.sleep;


public class Main {


    public static int passengersSpeed = 60;// persons in min
    // как сделать volatile переменную
    public static int barrierSpeed = 60;// persons in min
    public static int trolleyDuration = 5;//секунд *10 группа катается на горках
    public static int globalVariable = 0;


    public static Queue<Passenger> passengersQueue = new LinkedList<Passenger>();
    public static Queue<LinkedList<Passenger>> collectList = new LinkedList<>();
    public static LinkedList<Passenger> currentPersonCollect = new LinkedList<>();


    public static void main(String args[]) throws InterruptedException {

        PassengersGenerator pg = new PassengersGenerator();
        Barrier barrier = new Barrier(5);
        Controller controller = new Controller();
        Trolley trolley = new Trolley();

        pg.start();
        barrier.start();
        controller.start();
        trolley.start();

        while (true){
            sleep((6*1000));
            System.out.println("passengersQueue size:"+ passengersQueue.size());
            System.out.println("collectList size:"+ collectList.size());
            System.out.println("currentPersonCollect size:"+ currentPersonCollect.size());
            System.out.println("");
        }

    }
}

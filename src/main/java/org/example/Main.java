package org.example;



import javax.swing.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


import static java.lang.Thread.sleep;


public class Main {


    public static final int passengersSpeed = 160;// persons in min
    public static  final int barrierSpeed = 1600;// persons in min
    public static final int trolleyDuration = 10000 ;//секунд *10 группа катается на горках
    public  static final int trolleyCapacity = 5 ;


    public static volatile Queue<Passenger> passengersQueue = new ConcurrentLinkedQueue<Passenger>();
    public static volatile Queue<LinkedList<Passenger>> collectList = new ConcurrentLinkedQueue<>();
    public static volatile Queue<Passenger> currentPersonCollect = new ConcurrentLinkedQueue<>();


    public static void main(String args[]) throws IOException, InterruptedException{

        PassengersGenerator pg = new PassengersGenerator();
        Barrier barrier = new Barrier(trolleyCapacity);
        Controller controller = new Controller();
        Trolley trolley = new Trolley();

        pg.start();
        barrier.start();
        controller.start();
        trolley.start();


        SwingUtilities.invokeLater(() -> {
            VariablesGUI gui = new VariablesGUI();
            gui.setVisible(true);
        });


    }
}

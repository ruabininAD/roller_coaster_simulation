package org.example;



import javax.swing.*;
import java.io.Console;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;


import static java.lang.Thread.sleep;


public class Main {


    public static volatile int passengersSpeed = 160;// persons in min
    // как сделать volatile переменную
    public static volatile int barrierSpeed = 160;// persons in min
    public static int trolleyDuration = 1;//секунд *10 группа катается на горках
    public static int globalVariable = 0;


    public static volatile Queue<Passenger> passengersQueue = new LinkedList<Passenger>();
    public static volatile Queue<LinkedList<Passenger>> collectList = new LinkedList<>();
    public static volatile LinkedList<Passenger> currentPersonCollect = new LinkedList<>();


    public static void main(String args[]) throws IOException, InterruptedException{

        PassengersGenerator pg = new PassengersGenerator();
        Barrier barrier = new Barrier(5);
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
//        while (true){
//
//            sleep((1000));
//
//            System.out.println("passengersQueue size:"+ passengersQueue.size());
//            System.out.println("collectList size:"+ collectList.size());
//            System.out.println("currentPersonCollect size:"+ currentPersonCollect.size());
//            System.out.println("");
//        }

    }
}

package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import static org.example.Main.*;

public class Trolley extends Thread{

    @Override
    public void run() {
        Random random = new Random(79);
        while (true){
            try{
                if (!currentPersonCollect.isEmpty()){
                    System.out.println("Поезд поехал с :"+ currentPersonCollect);
                    sleep((trolleyDuration*5*1000)/passengersSpeed);		//Приостанавливает поток на 1 секунду
                    currentPersonCollect.clear();
                }
            }catch(InterruptedException e){}


        }
    }
}

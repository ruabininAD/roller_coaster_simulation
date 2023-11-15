package org.example;

import java.util.LinkedList;

import static org.example.Main.barrierSpeed;


public class Controller extends Thread{


    public static LinkedList<Passenger> collectPerson = new LinkedList<>();

    @Override
    public void run() {
        while (true){
            try{
                sleep((60*1000)/barrierSpeed);		//Приостанавливает поток на 1 секунду
            }catch(InterruptedException ignored){}

            //System.out.println( "Main.collectList.size: "+Main.collectList.size());
            if (!Main.collectList.isEmpty() && Main.currentPersonCollect.isEmpty() ){
                Main.currentPersonCollect = Main.collectList.poll();
            }
        }
    }
}

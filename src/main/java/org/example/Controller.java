package org.example;

import java.util.LinkedList;

import static org.example.Main.barrierSpeed;
import static org.example.Main.currentPersonCollect;


public class Controller extends Thread{




    @Override
    public void run() {
        while (true) {
            try {
                sleep((60 * 1000) / barrierSpeed); // Приостанавливает поток на 1 секунду
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            }


            //System.out.println( "Main.collectList.size: "+Main.collectList.size());
            synchronized (Main.collectList) {
                synchronized (Main.currentPersonCollect) {
                    if (!Main.collectList.isEmpty() && Main.currentPersonCollect.isEmpty()) {
                        Main.currentPersonCollect.addAll(Main.collectList.poll());
                    }
                }
            }
        }
    }
}

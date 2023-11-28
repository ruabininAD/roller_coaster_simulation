package org.example;

import java.util.LinkedList;

import static org.example.Main.barrierSpeed;



public class Controller extends Thread{




    @Override
    public void run() {
        while (true) {
            try {
                sleep((60 * 1000) / barrierSpeed);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            }


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

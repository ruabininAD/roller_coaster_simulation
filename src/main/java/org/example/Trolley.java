package org.example;

import static org.example.Main.*;

public class Trolley extends Thread{

    @Override
    public void run() {
        //Random random = new Random( System.currentTimeMillis());

        while (true){

            synchronized (Main.currentPersonCollect) {
                if (!Main.currentPersonCollect.isEmpty()) {
                    System.out.println("Поезд отправляется");
                    try {
                        sleep(( trolleyDuration ));
                    } catch (InterruptedException ignored) {
                        Thread.currentThread().interrupt();
                    }
                    Main.currentPersonCollect.clear();
                }
            }
        }
    }
}

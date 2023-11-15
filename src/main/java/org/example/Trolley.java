package org.example;

import static org.example.Main.*;

public class Trolley extends Thread{

    @Override
    public void run() {
        //Random random = new Random( System.currentTimeMillis());

        while (true){

            if (!Main.currentPersonCollect.isEmpty()){
                //System.out.println("!Main.currentPersonCollect.isEmpty: "+!Main.currentPersonCollect.isEmpty());
                System.out.println("Поезд отправляется");
                try{sleep(((long) trolleyDuration *5*1000));
                }catch(InterruptedException ignored){}
                Main.currentPersonCollect.clear();
            }
        }
    }
}

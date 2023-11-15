package org.example;

import java.util.LinkedList;

import static org.example.Main.collectList;
import static org.example.Main.currentPersonCollect;

public class Controller extends Thread{

    @Override
    public void run() {
        while (true){
            LinkedList<Passenger> collectPerson = collectList.poll();
            if (collectPerson != null && currentPersonCollect.size() == 0){
                currentPersonCollect = new LinkedList<>(collectPerson);
                System.out.println("currentPersonCollect (следущими едут): " + currentPersonCollect);
            }
        }

    }
}

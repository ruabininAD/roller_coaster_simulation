package org.example;

import java.util.LinkedList;

import static org.example.Main.*;

public class Barrier extends Thread{
    int capacity;
    LinkedList<Passenger> lastCollection = new LinkedList<>();

    public Barrier( int M) {
        this.capacity = M;
    }

    @Override
    public void run() {

        while (true){
            try{
                sleep((60*1000)/barrierSpeed);		//Приостанавливает поток на 1 секунду
            }catch(InterruptedException e){}


            if (lastCollection.size()==capacity){
                //System.out.println("lastCollection size:"+ lastCollection.size());

                collectList.add(new LinkedList<>(lastCollection)); //передача по значению
                lastCollection.clear();
            } else {
                Passenger person = passengersQueue.poll();
                if (person != null){
                    lastCollection.add(person);
                }

            }
        }
    }
}

package org.example;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import static org.example.Main.passengersQueue;
import static org.example.Main.passengersSpeed;


public class PassengersGenerator extends Thread {
    LinkedList<String> namesList = new LinkedList<>(Arrays.asList(
            "Александр", "Сергей", "Дмитрий", "Елена", "Андрей",
            "Анастасия", "Анна", "Екатерина", "Алексей", "Максим",
            "Иван", "Наталья", "Элена", "Владимир", "Евгений",
            "Татьяна", "Виктория", "Ольга", "Михаил", "Ирина",
            "Артем", "Дарина", "Николай", "Юлия", "Владислав",
            "Мария", "Даниил", "Данил", "Роман", "Денис",
            "Светлана", "Ксения", "Игорь", "Александра", "Альина",
            "Алина", "Дима", "Никита", "Антон", "Алёна",
            "Альена", "Альона", "Алена", "Олег", "Павел",
            "Марина", "Юрий", "Василий", "Виктор", "Кирилл",
            "Кирил", "Галина", "Руслан", "Илья", "Виталий",
            "Виталлий", "Валерия", "Елизавета", "Макс", "Дарья",
            "Валентина", "Влад", "Евгения", "Кристина"
    ));

    Random random = new Random(78);

    @Override
    public void run(){

        while (true){
            try{
                sleep((60*1000)/passengersSpeed);		//Приостанавливает поток на 1 секунду
            }catch(InterruptedException e){}

            boolean randomPassengerFlag = random.nextBoolean();

            if (randomPassengerFlag) {
                int randomIndexName = random.nextInt(namesList.size());

                Passenger tmp = new Passenger(namesList.get(randomIndexName));
                passengersQueue.add(tmp);
                //System.out.println("PassengersGenerator arrived: " + namesList.get(randomIndexName));
            }

        }
    }
}

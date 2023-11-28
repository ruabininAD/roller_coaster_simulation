# roller_coaster_simulation

## Задание

Реализовать многопоточное приложение, эмулирующее модель аттракциона "Американские горки". Спроектировать потоки и провести тестирование приложения на наличие deadlocks и race conditions.

## Задача

Американские горки представляют из себя систему управления, которая отправляет тележку только при ее полной загрузке. Пассажиры прибывают на платформу и регистрируются в контроллере через турникеты. Когда количество пассажиров достигает M, контроллер сигнализирует о отправлении тележки. После прибытия пассажиры покидают платформу, а следующие вступают на борт. В задаче необходимо игнорировать синхронизацию между погрузкой пассажиров и отправлением тележки.

Система американских горок состоит из трех процессов: турникет, контроллер и тележка. Турникет и контроллер взаимодействуют через общее действие, обозначающее прибытие пассажира. Контроллер и тележка взаимодействуют через действие отправления тележки.

## Выполнение

В рамках задачи выделены четыре сущности, которые могут быть реализованы в виде потоков:

1. Генератор пассажиров - создает поток пассажиров на входе.
2. Барьер - группирует пассажиров из общей очереди в группы для последующей посадки.
3. Контроллер - перемещает пассажиров из общей очереди групп в вагончик.
4. Вагонетка - получает данные пассажиров на время поездки и ограничивает доступ к ним.

Для передачи данных между потоками используются `ConcurrentLinkedQueue` - неограниченная потокобезопасная очередь, основанная на связанных узлах.

В программе реализованы следующие очереди:
- `passengersQueue` - общая очередь пассажиров.
- `collectList` - очередь групп.
- `currentPersonCollect` - группа, которая сейчас катается.

Также в программе определены следующие параметры системы:
- `passengersSpeed = 160` - количество пассажиров в минуту.
- `barrierSpeed = 1600` - количество пассажиров в минуту на барьере.
- `trolleyDuration = 10000` - продолжительность поездки.
- `trolleyCapacity = 5` - вместимость вагонетки.

## Барьер

Барьер накапливает посетителей во внутреннем списке `lastCollection` и при достижении количества, заданного полем `capacity`, добавляет этот список в очередь групп `collectList`.
```java
@Override
public void run() {
    while (true){
        try{
            sleep((60*1000)/barrierSpeed);		
        } catch(InterruptedException ignored){}
        if (lastCollection.size() == capacity){
            collectList.add(new LinkedList<>(lastCollection));
            lastCollection.clear();
        } else {
            Passenger person = passengersQueue.poll();
            if (person != null){
                lastCollection.add(person);
            }
        }
    }
}
```

## Контроллер

Контроллер перемещает группы пассажиров из collectList в currentPersonCollect при условии, что collectList содержит группу людей, и вагонетка пуста. Используется synchronized для атомарности выполнения проверки условия и выполнения действий.
```java

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


```

## Вагонетка
В процессе "поездки" вагонетки, если условие if (!Main.currentPersonCollect.isEmpty()) выполняется, синхронизируется доступ к currentPersonCollect, чтобы другие потоки не могли его изменить.

```java
@Override
public void run() {
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

```

## Main
Функция main содержит только инициализацию потоков, вызов метода start() у каждого из потоков и вызов GUI.


## Тест
При тестировании успешно запускалось приложение без задержек на генерацию пассажиров и прохождение барьера. Многочисленные запуски не выявили состояний race condition и deadlocks.

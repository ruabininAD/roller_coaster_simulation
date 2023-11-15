package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VariablesGUI extends JFrame {
    private final JLabel label1;
    private final JLabel label2;
    private final JLabel label3;
    private final JLabel label4;

    public VariablesGUI() {
        setTitle("Обновление переменных");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание меток для отображения переменных
        label1 = new JLabel("passengersQueue size:");
        label3 = new JLabel("collectList size:");
        label4 = new JLabel("currentPersonCollect size:");
        label2 = new JLabel("lastCollection in barrier size:");


        // Панель для размещения меток
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Установка BoxLayout для вертикального расположения

        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);

        // Установка панели содержимого
        setContentPane(panel);

        // Запуск таймера для обновления значений переменных каждую секунду
        Timer timer = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                label1.setText("passengersQueue size: " +  Main.passengersQueue.size());
                label2.setText("lastCollection in barrier size: " +  Barrier.lastCollection.size());
                label3.setText("collectList size:" + Main.collectList);
                label4.setText("currentPersonCollect size: "+ Main.currentPersonCollect.size()+":"+ Main.currentPersonCollect);
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VariablesGUI gui = new VariablesGUI();
            gui.setVisible(true);
        });
    }
}

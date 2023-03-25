package swingLearn;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(600, 400));
        frame.setTitle("Test form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

//        frame.setUndecorated(true);
        JButton button = new JButton("First button");
        button.setBackground(new Color(125, 125, 180, 128));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        frame.add(button);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
//        progressBar.setIndeterminate(true);
        progressBar.setMinimum(0);
        progressBar.setMaximum(1200);

        frame.add(progressBar);
        frame.setVisible(true);

        for (int i = progressBar.getMinimum(); i <= progressBar.getMaximum(); i++) {
            try {
                Thread.sleep(5);
            progressBar.setValue(i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }



//        frame.setState(JFrame.ICONIFIED); // свернуть
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // развернет на весь экран
//        frame.setExtendedState(JFrame.NORMAL); // развернуть в нормальном размере


        // компоненты
        // размещение
        // слушатель событий

    }
}

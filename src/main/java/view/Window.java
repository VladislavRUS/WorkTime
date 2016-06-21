package view;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private JButton startButton;
    private JButton pauseButton;
    private JButton stopButton;
    private JTextField textField;

    public Window() {
        startButton = new JButton("Start");
        pauseButton = new JButton("Pause");
        stopButton = new JButton("Stop");
        textField = new JTextField(20);
        textField.setEditable(false);

        compose();
    }

    private void compose() {
        JFrame frame = new JFrame("WorkTime");
        frame.setLayout(new FlowLayout());
        frame.setSize(new Dimension(300, 100));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(startButton);
        frame.add(pauseButton);
        frame.add(stopButton);
        frame.add(textField);

        frame.setVisible(true);
    }

    public void setText(String text) {
        textField.setText(text);
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getPauseButton() {
        return pauseButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }
}

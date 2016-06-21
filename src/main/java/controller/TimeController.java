package controller;

import view.Window;

import java.io.*;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TimeController {
    private Window window;
    private boolean paused;
    private boolean stopped;

    public TimeController(Window window) {
        this.window = window;
        this.stopped = false;
        this.paused = false;

        addListenersToButtons();
    }

    private void addListenersToButtons() {
        window.getStartButton().addActionListener(e -> paused = false);
        window.getPauseButton().addActionListener(e -> paused = true);
        window.getStopButton().addActionListener(e -> stopped = true);
    }

    public void begin() {
        File file = new File("workTime.txt");
        if (!file.exists()) {
            try {
                Files.createFile(file.toPath());
            } catch (IOException e) {
                window.setText(e.toString());
            }
        }

        long time = 0;
        try {
            while (!stopped) {
                if (!paused) {
                    window.setText(getTimeFromMilliseconds(time));
                    time += 1000;
                    Thread.sleep(1000);
                } else {
                    window.setText("Paused: " + getTimeFromMilliseconds(time));
                    Thread.sleep(100);
                }
            }
            Date now = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            List<String> lines = Files.readAllLines(file.toPath());
            String todayDate = format.format(now);

            if(lines.size() > 0) {
                String lastTimeAndDate = lines.get(lines.size() - 1);
                String date = lastTimeAndDate.split(" ")[0];
                if(date.equals(todayDate)){
                    long timeThatWasAlreadyWritten = getMillisFromString(lastTimeAndDate.split(" ")[1]);
                    time += timeThatWasAlreadyWritten;
                    lines.remove(lines.size() - 1);
                }
            }
            lines.add(todayDate + " " + getTimeFromMilliseconds(time));

            PrintWriter writer = new PrintWriter(new FileWriter(file));
            lines.forEach(writer::println);
            writer.flush();
            writer.close();

            System.exit(0);
        } catch (Exception e) {
            window.setText(e.toString());
        }
    }

    public String getTimeFromMilliseconds(long millis) {
        long seconds = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60)) % 60;
        long hours = (millis / (1000 * 60 * 60)) % 24;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public long getMillisFromString(String time) {
        String stringHours = time.split(":")[0];
        String stringMinutes = time.split(":")[1];
        String stringSeconds = time.split(":")[2];

        long hoursMillis = Long.parseLong(stringHours) * 1000 * 60 * 60;
        long minutesMillis = Long.parseLong(stringMinutes) * 1000 * 60;
        long secondsMillis = Long.parseLong(stringSeconds) * 1000;

        return hoursMillis + minutesMillis + secondsMillis;
    }
}

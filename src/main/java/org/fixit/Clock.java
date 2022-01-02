package org.fixit;

import java.util.Date;

public class Clock implements Runnable, IAlgoClock {
    private Date Timer = new Date();
    private Thread clockTread;

    public Clock() {
    }

    public void start() {
        this.clockTread = new Thread(this);
        this.clockTread.start();
    }

    public void run() {
        Thread stopTread = Thread.currentThread();

        while(stopTread == this.clockTread) {
            this.Timer.setSeconds(this.Timer.getSeconds() + 1);

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException var3) {
                var3.printStackTrace();
            }
        }

    }

    public String getTime() {
        if (this.Timer != null) {
            String day = String.valueOf(this.Timer.getDate());
            String month = String.valueOf(this.Timer.getMonth() + 1);
            String year = String.valueOf(this.Timer.getYear() + 1900);
            String hour = String.valueOf(this.Timer.getHours());
            String minute = String.valueOf(this.Timer.getMinutes() + 1);
            String seconds = String.valueOf(this.Timer.getSeconds());
            return year + ":" + month + ":" + day + ":" + hour + ":" + minute + ":" + seconds;
        } else {
            return "there is no Clock";
        }
    }

    public void stop() {
        this.clockTread = null;
    }
}

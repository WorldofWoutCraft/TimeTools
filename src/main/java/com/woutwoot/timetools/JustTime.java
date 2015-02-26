package com.woutwoot.timetools;

/**
 * @author woutwoot
 *         Created by on 26/02/2015 - 18:44.
 */
public class JustTime {

    int hours;
    int minutes;
    int seconds;

    public JustTime(int seconds) {
        this.seconds = seconds;
    }

    public JustTime(int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public JustTime(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public static JustTime parse(String s){
        int hours = Integer.parseInt(s.split(":")[0]);
        int minutes = Integer.parseInt(s.split(":")[1]);
        int seconds = Integer.parseInt(s.split(":")[2]);
        return new JustTime(hours,minutes,seconds);
    }

    public void decrease(){
        if(seconds > 0){
            seconds--;
        }else{
            if(minutes > 0){
                minutes--;
                seconds=59;
            }else{
                if(hours > 0){
                    hours--;
                    minutes=59;
                }
            }
        }
    }

    public String getString(){
        return String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
    }

    public boolean isOver() {
        return (minutes + seconds + hours) == 0;
    }
}

package pl.sda.time;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Paweł Matyaszczyk
 */
public class Time {
    private int hours;
    private int minutes;

    public Time(int hours, int minutes) {
        this.minutes = minutes%60;
        int hoursAdd = minutes/60;
        this.hours = hours+hoursAdd;

    }

    public Time(String value) {
        List<String> valueList = new ArrayList<>(Arrays.asList(value.split(" ")));
        if (valueList.size() == 4){
            this.hours = Integer.parseInt(valueList.get(0)) ;
            this.minutes = Integer.parseInt(valueList.get(2)) ;
        }
        else {
            System.out.println("Błędnie zapisany czas. Wpisz ponownie!");
        }
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

    public Time addTime (Time t){
        return new Time(this.getHours()+t.getHours(),this.getMinutes()+t.getMinutes());
    }
    public Time subtractTime(Time t) {
        int minutes = this.getHours()*60+this.getMinutes()-t.getHours()*60-t.getMinutes();
        return new Time(0,minutes);
    }

    public Time multiplyTime(int multiplyValue){
        int minutes = (this.getHours()*60+this.getMinutes())*multiplyValue;
        return new Time(0,minutes);
    }

    static Time sum(Time[] tab, int n){
        Time value = new Time(0,0);
        for(int a = 0 ; a<n;a++){
            value = value.addTime(tab[a]);
        }
        return value;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(hours).append(" h ");
        sb.append(minutes).append(" min");
        return sb.toString();
    }


}

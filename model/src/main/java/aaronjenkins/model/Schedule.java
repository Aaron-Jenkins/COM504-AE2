package aaronjenkins.model;

public class Schedule {

    public  Schedule() {

    }
    public Schedule(String startTime, Double rate) {
        this.startTime = startTime;
        this.rate = rate;
    }

    private String startTime = null;

    private Double rate = null;

    public String getStartTime() {
        return startTime;
    }

    public Double getRate() {
        return rate;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }



}

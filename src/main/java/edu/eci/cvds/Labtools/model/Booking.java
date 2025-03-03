package edu.eci.cvds.Labtools.model;


public class Booking {

    private String bookingId;
    private int day;
    private int timeLine;
    private User user;
    private Lab lab;

    public Booking(String bookingId, int day, int timeLine, User user, Lab lab) {
        this.bookingId = bookingId;
        this.day = day;
        this.timeLine = timeLine;
        this.user = user;
        this.lab = lab;
    }

    public String getBookingId() {
        return bookingId;
    }

    public int getDay(){
        return day;
    }

    public int getTimeLine(){
        return timeLine;
    }

    public User getUser(){
        return user;
    }

    public Lab getLab(){
        return lab;
    }

}

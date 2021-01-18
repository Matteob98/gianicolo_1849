package com.unitelma.gianicolo1849.classes;

import java.util.Date;

public class Reserve {
    public String guideName;
    public int totalPrice;
    public String myName;
    public String myPhone;
    public String myEmail;
    public String date;
    public String startTime;
    public String endTime;
    public String peopleNumber;

    public Reserve () {}

    public static Reserve copyOf(Reserve reserve) {
        Reserve copy = new Reserve();
        copy.guideName=reserve.guideName;
        copy.totalPrice=reserve.totalPrice;
        copy.myName = reserve.myName;
        copy.myPhone = reserve.myPhone;
        copy.myEmail = reserve.myEmail;
        copy.date = reserve.myEmail;
        copy.startTime = reserve.startTime;
        copy.endTime = reserve.endTime;
        copy.peopleNumber = reserve.peopleNumber;

        return copy;
    }
}

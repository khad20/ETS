package com.example.backend;

import java.math.BigDecimal;

public class Ticket {
    private int TicketID;
    private String TicketName;
    private BigDecimal TicketPrice;

    public Ticket (int TicketID,String TicketName, BigDecimal TicketPrice){
        this.TicketID = TicketID;
        this.TicketName = TicketName;
        this.TicketPrice = TicketPrice;
    }
    public int getTicketID() {
        return TicketID;
    }
    public void setTicketID(int TicketID) {
        this.TicketID = TicketID;
    }
    public String getTicketName() {
        return TicketName;
    }
    public void setTicketName(String TicketName) {
        this.TicketName = TicketName;
    }
    public BigDecimal getTicketPrice() {
        return TicketPrice;
    }
    public void setTicketPrice(BigDecimal TicketPrice) {
        this.TicketPrice = TicketPrice;
    }
    @Override
    public String toString(){
        return "TicketID: " + TicketID + ", TicketName: " + TicketName + ", TicketPrice: " + TicketPrice;

    }
}

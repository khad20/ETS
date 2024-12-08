package com.example.backend;

public class Customer implements Runnable {
    private final Ticketpool Ticketpool;
    private final int CustomerReturnRate;
    private int quantity;

    public Customer(Ticketpool Ticketpool, int CustomerReturnRate, int i) {
        this.Ticketpool = Ticketpool;
        this.CustomerReturnRate = CustomerReturnRate;
        this.quantity = quantity;
    }
    @Override
    public void run(){
        for(int i = 0; i < quantity; i++){
            Ticket ticket = Ticketpool.getTicket();
            System.out.println("Ticket is " + i + ": " + ticket);
            try{
                Thread.sleep(CustomerReturnRate * 1000L);
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }
}

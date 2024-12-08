package com.example.backend;

import java.math.BigDecimal;

public class Vendor implements Runnable {
    private final int totalTickets; // Total tickets to generate
    private final int ticketRetrievalRate; // Time between ticket generation in seconds
    private final Ticketpool ticketpool; // Shared ticket pool

    public Vendor(int totalTickets, int ticketRetrievalRate, Ticketpool ticketpool) {
        this.totalTickets = totalTickets;
        this.ticketRetrievalRate = ticketRetrievalRate;
        this.ticketpool = ticketpool;
    }

    @Override
    public void run() {
        for (int i = 1; i <= totalTickets; i++) {
            Ticket ticket = new Ticket(i, "Event", new BigDecimal(1000));
            try {
                ticketpool.addTicket(ticket); // Add ticket to the pool
                System.out.println(Thread.currentThread().getName() + " added ticket: " + ticket);
                Thread.sleep(ticketRetrievalRate * 1000L); // Wait before generating the next ticket
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted. Exiting.");
                Thread.currentThread().interrupt(); // Restore the interrupt status
                break; // Exit the loop gracefully
            }
        }
    }
}

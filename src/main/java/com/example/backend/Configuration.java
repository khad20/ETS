package com.example.backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Configuration {
    private int totalTickets;
    private int ticketRetrievalRate;
    private int maxTicketCapacity;
    private int CustomerReturnRate;

    public int getTotalTickets() {
        return totalTickets;
    }
    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }
    public int getTicketRetrievalRate() {
        return ticketRetrievalRate;
    }
    public void setTicketRetrievalRate(int ticketRetrievalRate) {
        this.ticketRetrievalRate = ticketRetrievalRate;
    }
    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }
    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }
    public int getCustomerReturnRate() {
        return CustomerReturnRate;
    }
    public void setCustomerReturnRate(int customerReturnRate) {
        CustomerReturnRate = customerReturnRate;
    }

    public void saveToFile(String filename) {
        try(FileWriter writer = new FileWriter(filename)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this, writer);
            System.out.println("Configuration Saved " + filename);
        }catch (IOException e){
            System.out.println("Error saving configuration file"+ e.getMessage());
        }
    }
    public static Configuration loadFromFile(String filename) {
        try(FileReader reader = new FileReader(filename)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Configuration.class);
        }catch (IOException e){
            System.out.println("Error loading configuration file"+ e.getMessage());
            return null;
        }
    }
}

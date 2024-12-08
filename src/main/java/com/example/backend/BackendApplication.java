package com.example.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;


@SpringBootApplication
public class BackendApplication implements CommandLineRunner {
    private static final String CONFIG_FILE = "config.json";

    private final Ticketpool ticketpool = new Ticketpool(10);

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        Configuration configuration = Configuration.loadFromFile(CONFIG_FILE);

        if (configuration == null) {
            System.out.println("no configuration file found.Please configure the system");
            configuration = new Configuration();

            configuration.setTotalTickets(InputValidator.getValidatedInteger("enter total number of tickets",1,100));
            configuration.setTicketRetrievalRate(InputValidator.getValidatedInteger("enter ticket retrieval rate",1,60));
            configuration.setCustomerReturnRate(InputValidator.getValidatedInteger("enter customer return rate",1,60));
            configuration.setMaxTicketCapacity(InputValidator.getValidatedInteger("enter max ticket capacity",1,100));

            configuration.saveToFile(CONFIG_FILE);
        }else {
            System.out.println("existing configuration file");
            System.out.println("Total tickets: " + configuration.getTotalTickets());
            System.out.println("Ticket retrieval rate: " + configuration.getTicketRetrievalRate());
            System.out.println("Customer return rate: " + configuration.getCustomerReturnRate());
            System.out.println("Max ticket capacity: " + configuration.getMaxTicketCapacity());
        }
        System.out.println("Configure the system:");
        System.out.print("Enter total number of tickets: ");
        int totalTickets = scanner.nextInt();
        System.out.print("Enter ticket release rate (seconds between releases): ");
        int ticketReleaseRate = scanner.nextInt();
        System.out.print("Enter customer retrieval rate (seconds between retrievals): ");
        int customerRetrievalRate = scanner.nextInt();
        System.out.print("Enter maximum ticket pool capacity: ");
        int maxTicketCapacity = scanner.nextInt();



        // Configure ticket pool
        ticketpool.setMaxCapacity(maxTicketCapacity);

        Ticketpool ticketpool = new Ticketpool(10);

        Vendor[] vendors = new Vendor[10];
        for (int i = 0; i < vendors.length; i++) {
            vendors[i] = new Vendor(20,5,ticketpool);
            Thread vendorThread = new Thread(vendors[i], "Vendor ID" +  i);
            vendorThread.start();
        }

        Customer[] customers = new Customer[10];
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(ticketpool,6,5);
            Thread customerThread = new Thread(customers[i], "Customer ID" +  i);
            customerThread.start();
        }
    }
}

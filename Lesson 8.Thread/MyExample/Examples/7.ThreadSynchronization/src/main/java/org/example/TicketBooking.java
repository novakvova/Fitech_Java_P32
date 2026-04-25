package org.example;

public class TicketBooking{

    // Shared resource (available tickets)
    private int availableTickets
            = 10;

    // Synchronized method for booking tickets
    public synchronized void bookTicket(int tickets){

        if (availableTickets >= tickets){

            availableTickets -= tickets;
            System.out.println(
                    "Booked " + tickets
                            + " tickets, Remaining tickets: "
                            + availableTickets);
        }
        else{
            System.out.println(
                    "Not enough tickets available to book "
                            + tickets);
        }
    }

    public int getAvailableTickets(){

        return availableTickets;
    }
}
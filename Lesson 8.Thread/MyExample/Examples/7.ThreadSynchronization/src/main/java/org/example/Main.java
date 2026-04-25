package org.example;

public class Main {
    public static void main(String[] args) {
// Shared resource
        TicketBooking booking
                = new TicketBooking();

        // Thread 1 to book tickets
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 2; i++) {

                // Trying to book 2
                // tickets each time
                booking.bookTicket(2);

                try{

                    // Simulate delay
                    Thread.sleep(50);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Thread 2 to book tickets
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 2; i++){

                // Trying to book 3
                // tickets each time
                booking.bookTicket(3);

                try{

                    // Simulate delay
                    Thread.sleep(40);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start both threads
        t1.start();
        t2.start();

        // Wait for threads to finish
        try {
            t1.join();
            t2.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print final remaining tickets
        System.out.println("Final Available Tickets: "
                + booking.getAvailableTickets());
    }
}
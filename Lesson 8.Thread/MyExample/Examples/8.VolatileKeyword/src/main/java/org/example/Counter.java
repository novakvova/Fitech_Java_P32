package org.example;

class Counter {
    private volatile boolean running = true;

    public void stop() {
        running = false;
    }

    public void start() {
        new Thread(() -> {
            while (running) {
                System.out.println("Running...");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Stopped.");
        }).start();
    }
}

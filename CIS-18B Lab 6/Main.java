import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        TicketPool ticketPool = new TicketPool(5); // 5 tickets available

        // Use virtual threads for scalability
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 1; i <= 10; i++) {
                executor.submit(new Customer("Customer-" + i, ticketPool));
            }
        }

        // Optional: add more tickets after some delay to test waiting
        /*
        try {
            Thread.sleep(3000);
            System.out.println("Adding 2 more tickets...");
            ticketPool.addTickets(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        */
    }
}

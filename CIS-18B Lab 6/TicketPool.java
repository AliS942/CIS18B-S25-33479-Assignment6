public class TicketPool {
    private int availableTickets;

    public TicketPool(int totalTickets) {
        this.availableTickets = totalTickets;
    }

    public synchronized Ticket reserveTicket(String customerName) {
        while (availableTickets <= 0) {
            try {
                System.out.println(customerName + " is waiting for a ticket...");
                wait(); // guarded block
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(customerName + " was interrupted.");
                return null;
            }
        }

        availableTickets--;
        Ticket ticket = new Ticket(availableTickets + 1, "Concert");
        System.out.println(customerName + " reserved ticket #" + ticket.getId() + ". Tickets left: " + availableTickets);
        return ticket;
    }

    public synchronized void addTickets(int count) {
        availableTickets += count;
        notifyAll(); // Wake up all waiting threads
    }
}

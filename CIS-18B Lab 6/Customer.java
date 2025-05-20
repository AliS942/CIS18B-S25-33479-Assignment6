public class Customer implements Runnable {
    private final String name;
    private final TicketPool ticketPool;

    public Customer(String name, TicketPool ticketPool) {
        this.name = name;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        Ticket ticket = ticketPool.reserveTicket(name);
        if (ticket != null) {
            System.out.println(name + " successfully reserved ticket #" + ticket.getId());
        } else {
            System.out.println(name + " could not get a ticket.");
        }
    }
}

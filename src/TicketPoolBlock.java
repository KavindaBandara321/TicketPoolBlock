import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TicketPoolBlock implements TicketPool {

    private int noOfTicketsOffered = 0;
    private int noOfTicketsBought = 0;

    private BlockingQueue<Ticket> ticketQueue;

    public TicketPoolBlock(int maxSize) {
        ticketQueue = new LinkedBlockingQueue<Ticket>(maxSize);
    }

    @Override
    public void printTicketPoolStatus() {
        System.out.println("No of tickets added to queue by Vendor: " + noOfTicketsOffered);
        System.out.println("No of tickets bought from queue by Customer: " + noOfTicketsBought);
        System.out.println("No of tickets currently available in the queue: " + ticketQueue.size());
    }


    @Override
    public void addTicket(Ticket ticket) {
        try {
            ticketQueue.put(ticket);
            noOfTicketsOffered++;
            System.out.println(Thread.currentThread().getName() +
                    " added TicketNumber: " + ticket.getTicketId() +
                    ", Vendor: " + ticket.getVendor() +
                    ", Event: " + ticket.getEvent());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Ticket purchaseTicket() {
        try {
            Ticket ticket = ticketQueue.take();
            noOfTicketsBought++;
            System.out.println(Thread.currentThread().getName() +
                    " purchased TicketNumber: " + ticket.getTicketId() +
                    ", Vendor: " + ticket.getVendor() +
                    ", Event: " + ticket.getEvent());
            return ticket;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }

    }
}
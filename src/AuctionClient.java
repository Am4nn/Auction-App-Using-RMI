import java.rmi.*;
import java.util.*;

public class AuctionClient {

    public static void start() {

        AuctionCallback callback = null;
        AuctionItem item = null;

        try {
            item = (AuctionItem) Naming.lookup("rmi://localhost/AuctionItem");
            Scanner scanner = new Scanner(System.in);

            // Enter Client's Name
            System.out.print("Enter your name: ");
            String bidder = scanner.nextLine();

            // Register Callback
            callback = new AuctionCallbackImpl();
            item.registerCallback(callback);

            double bid;
            String nextLine;
            while (true) {
                nextLine = scanner.nextLine();
                if (nextLine.toLowerCase(Locale.ROOT).equals("close")) {
                    break;
                }
                bid = Double.parseDouble(nextLine);
                item.bid(bidder, bid);
            }

        } catch (Exception e) {
            if (!e.getMessage().equals("No line found")) {
                System.err.println("Auction Client Exception: " + e);
                e.printStackTrace();
            }
            try {
                if (item != null && callback != null)
                    item.unregisterCallback(callback);
            } catch (RemoteException ex) {
                System.err.println("Auction Callback Exception: " + ex);
            }
        }
    }

    public static void main(String[] args) {
        AuctionClient.start();
    }
}
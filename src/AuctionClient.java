import java.rmi.*;
import java.util.*;

public class AuctionClient {

    AuctionCallback callback;
    AuctionItemsHandler itemsHandler;
    Scanner scanner;
    String clientName;

    public AuctionClient() {
        scanner = new Scanner(System.in);
    }

    public void start() {

        try {

            itemsHandler = (AuctionItemsHandler) Naming.lookup("rmi://localhost/AuctionItemsHandler");

            System.out.println("New Auction Client Initialized");

            List<AuctionCallback> cbs = itemsHandler.getCallbacks();
            System.out.println("Clients connected : " + cbs.size());

            // Enter Client's Name
            System.out.print("Enter your name: ");
            clientName = scanner.nextLine().trim();

            // Register Client in Item's Callbacks
            callback = new AuctionCallbackImpl();
            itemsHandler.registerCallback(callback);

            System.out.println("\nPress : \n1. List auction items \n2. Bid on an item \n3. Add an item for Auction \nType close To Quit Auction App");

            int choice;
            String nextLine;
            while (true) {
                System.out.print("\nEnter your choice: ");

                nextLine = scanner.nextLine().trim();
                if (nextLine.toLowerCase(Locale.ROOT).equals("close")) break;

                System.out.println();

                choice = Integer.parseInt(nextLine);
                switch (choice) {
                    case 1 -> listAuctionItems();
                    case 2 -> bidOnItem();
                    case 3 -> addAuctionItem();
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }

            // Unregister Client from Item's Callbacks
            itemsHandler.unregisterCallback(callback);

        } catch (Exception e) {
            if (e.getMessage() == null || !e.getMessage().equals("No line found")) {
                System.err.println("Auction Client Exception: " + e);
                e.printStackTrace();
            }
            try {
                if (itemsHandler != null && callback != null)
                    itemsHandler.unregisterCallback(callback);
            } catch (RemoteException ex) {
                System.err.println("Auction Callback Exception: " + ex);
            }
        }
        System.out.println("Auction Client Closed !");
    }

    private void listAuctionItems() throws RemoteException {
        List<AuctionItem> items = itemsHandler.getItems();
        if (items.size() == 0) {
            System.out.println("No Items for Auction Yet !");
            return;
        }
        System.out.println("Auction items available for bidding : ");
        for (AuctionItem item : items) {
            System.out.println("- " + item.getName() + " (current bid: " + item.getCurrentBid() + ")");
        }
    }

    private void bidOnItem() throws RemoteException {
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine().trim();

        AuctionItem item = itemsHandler.getItem(itemName);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }

        System.out.println("Current bid for " + itemName + " is " + item.getCurrentBid());

        System.out.print("Enter your bid: ");
        double bid = Double.parseDouble(scanner.nextLine());

        itemsHandler.bidOnItem(item, this.clientName, bid);
    }

    private void addAuctionItem() throws RemoteException {
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine().trim();

        if (itemsHandler.getItem(itemName) != null) {
            System.out.println("This Item already exists in Auction !");
            return;
        }

        AuctionItem item = new AuctionItemImpl(itemName);
        itemsHandler.addItem(item);
    }

    public static void main(String[] args) {
        (new AuctionClient()).start();
    }
}
import java.rmi.registry.*;

public class AuctionServer {
    public static void start () {
        try {
            AuctionItem item = new AuctionItemImpl("Painting");
            AuctionCallback callback = new AuctionCallbackImpl();
            item.registerCallback(callback);

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("AuctionItem", item);

            System.out.println("Auction server started.");
        } catch (Exception e) {
            System.err.println("Auction server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AuctionServer.start();
    }
}
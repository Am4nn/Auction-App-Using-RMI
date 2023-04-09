import java.rmi.registry.*;

public class AuctionServer {

    public void start () {
        try {
            AuctionItemsHandler itemsHandler = new AuctionItemsHandlerImpl();
            AuctionCallback callback = new AuctionCallbackImpl();
            itemsHandler.registerCallback(callback);

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("AuctionItemsHandler", itemsHandler);

            System.out.println("Auction Server Started !");
        } catch (Exception e) {
            System.err.println("Auction server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        (new AuctionServer()).start();
    }
}
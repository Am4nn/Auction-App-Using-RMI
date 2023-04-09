import java.rmi.*;

public interface AuctionCallback extends Remote {
    void notifyNewBid(String itemName, String highBidder, double currentBid) throws RemoteException;
    void notifyNewItem(String itemName) throws RemoteException;
}

import java.rmi.*;

public interface AuctionCallback extends Remote {
    void notifyNewBid(String itemName, String highBidder, double currentBid) throws RemoteException;
}

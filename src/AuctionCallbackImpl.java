import java.rmi.*;
import java.rmi.server.*;

public class AuctionCallbackImpl extends UnicastRemoteObject implements AuctionCallback {
    public AuctionCallbackImpl() throws RemoteException {
        super();
    }

    @Override
    public void notifyNewBid(String itemName, String highBidder, double currentBid) throws RemoteException {
        System.out.println("\n> " + highBidder + " bid " + currentBid + " on " + itemName);
    }

    @Override
    public void notifyNewItem(String itemName) throws RemoteException {
        System.out.println("\n> A new item has been added to the Auction : " + itemName);
    }
}

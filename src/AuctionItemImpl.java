import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class AuctionItemImpl extends UnicastRemoteObject implements AuctionItem {
    private final String name;
    private double currentBid;
    private String highBidder;
    private final List<AuctionCallback> callbacks;

    public AuctionItemImpl(String name) throws RemoteException {
        super();
        this.name = name;
        this.currentBid = 0.0;
        this.highBidder = "";
        this.callbacks = new ArrayList<>();
    }

    @Override
    public void bid(String bidder, double amount) throws RemoteException {
        if (amount > currentBid) {
            currentBid = amount;
            highBidder = bidder;
            notifyCallbacks();
        }
    }

    @Override
    public void registerCallback(AuctionCallback callback) throws RemoteException {
        callbacks.add(callback);
    }

    @Override
    public void unregisterCallback(AuctionCallback callback) throws RemoteException {
        callbacks.remove(callback);
    }

    private void notifyCallbacks() throws RemoteException {
        for (AuctionCallback callback : callbacks) {
            try {
                callback.notifyNewBid(name, highBidder, currentBid);
            } catch (RemoteException e) {
                // If a remote exception occurs, remove the callback from the list
                callbacks.remove(callback);
            }
        }
    }
}

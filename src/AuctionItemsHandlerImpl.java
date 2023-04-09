import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class AuctionItemsHandlerImpl extends UnicastRemoteObject implements AuctionItemsHandler {
    private final List<AuctionItem> items;
    private final List<AuctionCallback> callbacks;

    public AuctionItemsHandlerImpl() throws RemoteException {
        super();
        items = new ArrayList<>();
        callbacks = new ArrayList<>();
    }

    @Override
    public void registerCallback(AuctionCallback callback) throws RemoteException {
        callbacks.add(callback);
    }

    @Override
    public void unregisterCallback(AuctionCallback callback) throws RemoteException {
        callbacks.remove(callback);
    }

    @Override
    public List<AuctionItem> getItems() throws RemoteException {
        return items;
    }

    @Override
    public AuctionItem getItem(String name) throws RemoteException {
        for (AuctionItem item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public void addItem(AuctionItem item) throws RemoteException {
        items.add(item);
        notifyCallbacks(item, "add");
    }

    @Override
    public void bidOnItem(AuctionItem item, String bidder, double amount) throws RemoteException {
        if (item.getCurrentBid() < amount) {
            item.setCurrentBid(amount);
            item.setHighBidder(bidder);
            notifyCallbacks(item, "bid");
        }
    }

    @Override
    public List<AuctionCallback> getCallbacks() throws RemoteException {
        return callbacks;
    }

    private void notifyCallbacks(AuctionItem item, String action) throws RemoteException {
        for (AuctionCallback callback : callbacks) {
            try {
                if (action.equals("add")) callback.notifyNewItem(item.getName());
                else if (action.equals("bid")) callback.notifyNewBid(item.getName(), item.getHighBidder(), item.getCurrentBid());
            } catch (RemoteException e) {
                // If a remote exception occurs, remove the callback from the list
                callbacks.remove(callback);
            }
        }
    }
}
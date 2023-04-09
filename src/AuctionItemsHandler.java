import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface AuctionItemsHandler extends Remote {
    void registerCallback(AuctionCallback callback) throws RemoteException;
    void unregisterCallback(AuctionCallback callback) throws RemoteException;
    List<AuctionItem> getItems() throws RemoteException;
    AuctionItem getItem(String name) throws RemoteException;
    void addItem(AuctionItem item) throws RemoteException;
    void bidOnItem(AuctionItem item, String bidder, double amount) throws RemoteException;
    List<AuctionCallback> getCallbacks() throws RemoteException;
}

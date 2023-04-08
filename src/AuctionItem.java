import java.rmi.*;

public interface AuctionItem extends Remote {
    void bid(String bidder, double amount) throws RemoteException;
    void registerCallback(AuctionCallback callback) throws RemoteException;
    void unregisterCallback(AuctionCallback callback) throws RemoteException;
}

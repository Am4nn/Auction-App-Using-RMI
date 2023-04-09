import java.rmi.*;

public interface AuctionItem extends Remote {
    String getName() throws RemoteException;
    void setHighBidder(String bidder) throws RemoteException;
    void setCurrentBid(double amount) throws RemoteException;
    double getCurrentBid() throws RemoteException;
    String getHighBidder() throws RemoteException;
}

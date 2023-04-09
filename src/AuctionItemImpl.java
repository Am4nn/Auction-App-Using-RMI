import java.rmi.*;
import java.rmi.server.*;

public class AuctionItemImpl extends UnicastRemoteObject implements AuctionItem {
    private final String name;
    private double currentBid;
    private String highBidder;

    public AuctionItemImpl(String name) throws RemoteException {
        super();
        this.name = name;
        this.currentBid = 0.0;
        this.highBidder = "";
    }

    @Override
    public String getName() throws RemoteException {
        return this.name;
    }

    @Override
    public void setHighBidder(String bidder) throws RemoteException {
        this.highBidder = bidder;
    }

    @Override
    public void setCurrentBid(double amount) throws RemoteException {
        this.currentBid = amount;
    }

    @Override
    public double getCurrentBid() throws RemoteException {
        return this.currentBid;
    }

    @Override
    public String getHighBidder() throws RemoteException {
        return this.highBidder;
    }
}

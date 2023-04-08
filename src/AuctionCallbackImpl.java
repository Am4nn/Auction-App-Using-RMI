import java.rmi.*;
import java.rmi.server.*;

public class AuctionCallbackImpl extends UnicastRemoteObject implements AuctionCallback {
    public AuctionCallbackImpl() throws RemoteException {
        super();
    }

    @Override
    public void notifyNewBid(String itemName, String highBidder, double currentBid) throws RemoteException {
        System.out.println(itemName + ": " + highBidder + " bid " + currentBid);
    }
}

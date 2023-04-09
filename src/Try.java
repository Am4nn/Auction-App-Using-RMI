import java.rmi.*;
import java.util.List;

public class Try {
    public static void main(String[] args) {
        try {
            AuctionItemsHandler itemsHandler = (AuctionItemsHandler) Naming.lookup("rmi://localhost/AuctionItemsHandler");
            itemsHandler.addItem(new AuctionItemImpl("Painting"));
            List<AuctionItem> auctionItems = itemsHandler.getItems();
            for (AuctionItem i : auctionItems) {
                System.out.println(i.getName() + " " + i.getHighBidder() + " " + i.getCurrentBid());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

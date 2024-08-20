import java.util.ArrayList;
import java.util.Scanner;

class AuctionItem {
    String itemName;
    double startingBid;
    double currentBid;
    String highestBidder;
    boolean isActive;

    public AuctionItem(String itemName, double startingBid) {
        this.itemName = itemName;
        this.startingBid = startingBid;
        this.currentBid = startingBid;
        this.highestBidder = "No bids yet";
        this.isActive = true;
    }

    public void placeBid(String bidderName, double bidAmount) {
        if (bidAmount > currentBid && isActive) {
            currentBid = bidAmount;
            highestBidder = bidderName;
            System.out.println("Bid placed successfully by " + bidderName);
        } else {
            System.out.println("Bid too low or auction is closed.");
        }
    }

    public void closeAuction() {
        isActive = false;
        System.out.println("Auction closed. Winning bid: " + currentBid + " by " + highestBidder);
    }

    @Override
    public String toString() {
        return "Item: " + itemName + ", Current Bid: " + currentBid + ", Highest Bidder: " + highestBidder + ", Auction Status: " + (isActive ? "Active" : "Closed");
    }
}

public class OnlineAuctionSystem {
    static ArrayList<AuctionItem> auctionItems = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Online Auction System ---");
            System.out.println("1. Create Auction");
            System.out.println("2. View Auctions");
            System.out.println("3. Place Bid");
            System.out.println("4. Close Auction");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    createAuction();
                    break;
                case 2:
                    viewAuctions();
                    break;
                case 3:
                    placeBid();
                    break;
                case 4:
                    closeAuction();
                    break;
                case 5:
                    System.out.println("Exiting the system.");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public static void createAuction() {
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter starting bid: ");
        double startingBid = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        AuctionItem auctionItem = new AuctionItem(itemName, startingBid);
        auctionItems.add(auctionItem);
        System.out.println("Auction created successfully for " + itemName);
    }

    public static void viewAuctions() {
        System.out.println("\n--- Current Auctions ---");
        for (AuctionItem item : auctionItems) {
            System.out.println(item);
        }
    }

    public static void placeBid() {
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();
        AuctionItem auctionItem = findAuctionItem(itemName);

        if (auctionItem != null && auctionItem.isActive) {
            System.out.print("Enter your name: ");
            String bidderName = scanner.nextLine();
            System.out.print("Enter your bid amount: ");
            double bidAmount = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            auctionItem.placeBid(bidderName, bidAmount);
        } else {
            System.out.println("Auction not found or is closed.");
        }
    }

    public static void closeAuction() {
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();
        AuctionItem auctionItem = findAuctionItem(itemName);

        if (auctionItem != null && auctionItem.isActive) {
            auctionItem.closeAuction();
        } else {
            System.out.println("Auction not found or is already closed.");
        }
    }

    public static AuctionItem findAuctionItem(String itemName) {
        for (AuctionItem item : auctionItems) {
            if (item.itemName.equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }
}

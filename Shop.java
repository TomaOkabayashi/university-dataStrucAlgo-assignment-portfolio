public class Shop {
    private int shopNumber;
    private String shopName;
    private String category;
    private String location;
    private int rating;
    private boolean visited;
    private LinkedList adjacentShops;
    private int previousShop;

    public Shop(int shopNumber, String shopName, String category, String location, int rating) {
        this.shopNumber = shopNumber;
        this.shopName = shopName;
        this.category = category;
        this.location = location;
        this.rating = rating;
        this.visited = false;
        this.adjacentShops = new LinkedList();
        this.previousShop = -1;
    }

    public void setPreviousShop(int previousShop) {
        this.previousShop = previousShop;
    }

    public int getPreviousShop() {
        return previousShop;
    }

    public LinkedList getAdjacentShops() {
        return adjacentShops;
    }

    public void markAsVisited() {
        visited = true;
    }

    public void markAsUnvisited() {
        visited = false;
    }

    public boolean visitStatus() {
        return visited;
    }
    
    // Getter and setter for each attribute

    public void setShopNumber(int shopNumber) {
        this.shopNumber = shopNumber;
    }

    public int getShopNumber() {
        return shopNumber;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return Integer.toString(shopNumber);
    }
    /* By overriding the toString method, it nsures to print a Shop object and show the shop number and not the default object 
    representation and the comparePaths method will display the correct shop numbers in the shortest path. */
}

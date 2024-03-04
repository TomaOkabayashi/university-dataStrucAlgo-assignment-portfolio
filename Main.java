import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph = new Graph(100);
        HashTable table = new HashTable(50, 0.75);

        Shop[] shops = new Shop[] {
            new Shop(1, "ABC Electronics", "Electronics", "Floor 1 - Aisle 3", 4),
            new Shop(2, "XYZ Furniture", "Furniture", "Floor 2 - Aisle 1", 3),
            new Shop(3, "Fashion World", "Clothing", "Floor 1 - Aisle 2", 5),
            new Shop(4, "Gourmet Delights", "Food", "Floor 3 - Aisle 2", 2),
            new Shop(5, "Sports Zone", "Sports", "Floor 2 - Aisle 3", 1),
            new Shop(6, "Apple Store", "Electronics", "Floor 4 - Aisle 2", 2),
            new Shop(7, "Barnes & Noble", "Books", "Floor 1 - Aisle 1", 4),
            new Shop(8, "Tech Haven", "Electronics", "Floor 4 - Aisle 3", 3),
            new Shop(9, "Glamour Boutique", "Clothing", "Floor 1 - Aisle 4", 4),
            new Shop(10, "Sweets Emporium", "Food", "Floor 3 - Aisle 4", 3)
        };
        /* new Shop(11, "Adventure Outfitters", "Sports", "Floor 2 - Aisle 4", 4),
            new Shop(12, "Furniture Elegance", "Furniture", "Floor 2 - Aisle 2", 5),
            new Shop(13, "Treasure Trove", "Jewelry", "Floor 1 - Aisle 5", 5),
            new Shop(14, "Kids Haven", "Clothing", "Floor 1 - Aisle 6", 4),
            new Shop(15, "Digital World", "Electronics", "Floor 4 - Aisle 4", 1),
            new Shop(16, "Culinary Delights", "Food", "Floor 3 - Aisle 5", 3),
            new Shop(17, "Book Nook", "Books", "Floor 1 - Aisle 7", 4),
            new Shop(18, "Jewel Palace", "Jewelry", "Floor 1 - Aisle 8", 5),
            new Shop(19, "Game Zone", "Electronics", "Floor 4 - Aisle 5", 3),
            new Shop(20, "Home Comforts", "Furniture", "Floor 2 - Aisle 5", 5),
            new Shop(21, "Fitness Center", "Sports", "Floor 2 - Aisle 6", 4),
            new Shop(22, "Cafe Central", "Food", "Floor 3 - Aisle 6", 4),
            new Shop(23, "Novelty World", "Books", "Floor 1 - Aisle 9", 3),
            new Shop(24, "Jewelry Elegance", "Jewelry", "Floor 1 - Aisle 10", 5),
            new Shop(25, "Tech Innovations", "Electronics", "Floor 4 - Aisle 6", 4),
            new Shop(26, "Comfort Living", "Furniture", "Floor 2 - Aisle 7", 5),
            new Shop(27, "Outdoor Adventures", "Sports", "Floor 2 - Aisle 8", 1),
            new Shop(28, "Delicious Delights", "Food", "Floor 3 - Aisle 7", 4),
            new Shop(29, "Literary Haven", "Books", "Floor 1 - Aisle 11", 2),
            new Shop(30, "Luxury Jewelry", "Jewelry", "Floor 1 - Aisle 12", 5)
        }; */
            

        // Iterate through the array of shops and add each shop to the graph
        for (Shop shop : shops) {
            graph.addShopVertex(shop.getShopNumber());
        }
        // Add the shops to the HashTable
        for (Shop shop : shops) {
            table.put(Integer.toString(shop.getShopNumber()), shop);
        }


        System.out.println("Welcome to Shop Finding and Navigation System");
        boolean validInput = false;
        int choice;
        while (!validInput) {
            try {
                System.out.println("Menu:");
                System.out.println("1. Delete shop node");
                System.out.println("2. Add edge between shops");
                System.out.println("3. Delete edge between shops");
                System.out.println("4. Adjacency List");
                System.out.println("5. Update shop information");
                System.out.println("6. Depth first search");
                System.out.println("7. Breadth first search");
                System.out.println("8. Compare DFS and BFS paths");
                System.out.println("9. Search by category");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter shop number to delete: ");
                        try {
                            int shopNumberToDelete = scanner.nextInt();
                            graph.deleteShopVertex(shopNumberToDelete);
                            table.remove(Integer.toString(shopNumberToDelete));
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid shop number.");
                            scanner.nextInt();
                        }
                        break;

                    case 2:
                        try {
                            System.out.print("Enter source shop number: ");
                            int sourceShop = scanner.nextInt();
                            System.out.print("Enter destination shop number: ");
                            int destinationShop = scanner.nextInt();
                            graph.addShopEdge(sourceShop, destinationShop);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid shop number.");
                            scanner.nextInt();
                        }
                        break;

                    case 3:
                        try {
                            System.out.print("Enter source shop number: ");
                            int sourceShop = scanner.nextInt();
                            System.out.print("Enter destination shop number: ");
                            int destinationShop = scanner.nextInt();
                            graph.deleteShopEdge(sourceShop, destinationShop);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid shop number.");
                            scanner.nextInt();
                        }
                        break;

                    case 4:
                        graph.adjacencyList();
                        break;

                    case 5:
                        try {
                            System.out.println("Choose the shop attribute to update:");
                            System.out.println("1. Shop Number");
                            System.out.println("2. Shop Name");
                            System.out.println("3. Category");
                            System.out.println("4. Location");
                            System.out.println("5. Rating");
                            System.out.print("Enter your choice: ");
                            int attributeChoice = scanner.nextInt();

                            if (attributeChoice < 1 || attributeChoice > 5) {
                                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                            } else {
                                System.out.print("Enter the shop number to update: ");
                                int shopToUpdate = scanner.nextInt();

                                switch (attributeChoice) {
                                    case 1:
                                        System.out.print("Enter the new shop number: ");
                                        int newShopNumber = scanner.nextInt();
                                        graph.updateShopNumber(shopToUpdate, newShopNumber);
                                        table.updateShopNumber(shopToUpdate, newShopNumber);
                                        break;
                                    case 2:
                                        System.out.print("Enter the new shop name: ");
                                        String newShopName = scanner.next();
                                        table.updateShopName(shopToUpdate, newShopName);
                                        break;
                                    case 3:
                                        System.out.print("Enter the new category: ");
                                        String newCategory = scanner.next();
                                        table.updateShopCategory(shopToUpdate, newCategory);
                                        break;
                                    case 4:
                                        System.out.print("Enter the new location: ");
                                        String newLocation = scanner.next();
                                        table.updateShopLocation(shopToUpdate, newLocation);
                                        break;
                                    case 5:
                                        System.out.print("Enter the new rating: ");
                                        int newRating = scanner.nextInt();
                                        if (newRating < 1 || newRating > 5) {
                                            System.out.println("Invalid choice. Ratings are between 1-5. Please enter a number between 1 and 5.");
                                        } else {
                                            table.updateShopRating(shopToUpdate, newRating);
                                        }
                                        break;
                                    default:
                                        System.out.println("Invalid attribute choice");
                                }
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter an integer.");
                            scanner.nextLine();
                        }
                        
                        break;

                    case 6:
                        try {
                            System.out.print("Enter source shop number for DFS: ");
                            int sourceShop = scanner.nextInt();
                            System.out.print("Enter destination shop number for DFS: ");
                            int destinationShop = scanner.nextInt();
                            graph.depthFirstSearch(sourceShop, destinationShop);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid shop number.");
                            scanner.nextInt();
                        }
                        break;

                    case 7:
                        try {
                            System.out.print("Enter source shop number for BFS: ");
                            int sourceShop = scanner.nextInt();
                            System.out.print("Enter destination shop number for BFS: ");
                            int destinationShop = scanner.nextInt();
                            graph.breadthFirstSearch(sourceShop, destinationShop);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid shop number.");
                            scanner.nextInt();
                        }
                        break;

                    case 8:
                        graph.comparePaths();
                        break;

                    case 9:
                        try {
                            System.out.println("Choose a category to search for:");
                            System.out.println("1. Electronics");
                            System.out.println("2. Furniture");
                            System.out.println("3. Clothing");
                            System.out.println("4. Food");
                            System.out.println("5. Sports");
                            System.out.println("6. Books");
                            System.out.println("7. Jewelry");
                            System.out.print("Enter your choice: ");
                            int categoryChoice = scanner.nextInt();

                            String category;
                            switch (categoryChoice) {
                                case 1:
                                    category = "Electronics";
                                    break;
                                case 2:
                                    category = "Furniture";
                                    break;
                                case 3:
                                    category = "Clothing";
                                    break;
                                case 4:
                                    category = "Food";
                                    break;
                                case 5:
                                    category = "Sports";
                                    break;
                                case 6:
                                    category = "Books";
                                    break;
                                case 7:
                                    category = "Jewelry";
                                    break;
                                default:
                                    System.out.println("Invalid category choice");
                                    continue;

                                
                            }
                            
                            table.searchByCategory(category); // valid category to search with
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid shop number.");
                            scanner.nextInt();
                        }
                        break;

                    case 0:
                        System.out.println("Exiting the Shop Finding and Navigation System.");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                if (choice == 0) {
                    break; // Exit the loop when the user enters 0
                }
            } catch (InputMismatchException e) {
            System.out.println("Please enter an integer.");
            scanner.nextLine();
            }
        }
        scanner.close();
    }
}

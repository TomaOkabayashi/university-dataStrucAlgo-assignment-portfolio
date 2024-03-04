public class TestGraph {
    public static void main(String[] args) {
        // Create a graph without specifying a maximum number of vertices
        Graph graph = new Graph(100);
        HashTable table = new HashTable(50, 0.75);

        Shop[] shops = new Shop[] {
            new Shop(1, "ABC Electronics", "Electronics", "Floor 1 - Aisle 3", 4),
            new Shop(2, "XYZ Furniture", "Furniture", "Floor 2 - Aisle 1", 3),
            new Shop(3, "Fashion World", "Clothing", "Floor 1 - Aisle 2", 5),
            new Shop(4, "Gourmet Delights", "Food", "Floor 3 - Aisle 2", 2),
            new Shop(5, "Sports Zone", "Sports", "Floor 2 - Aisle 3", 1),
            new Shop(6, "Apple Store", "Electronics", "Floor 4 - Aisle 2", 2)
        };


        // Iterate through the array of shops and add each shop to the graph
        for (Shop shop : shops) {
            graph.addShopVertex(shop.getShopNumber());
        }
        // Add the shops to the HashTable
        for (Shop shop : shops) {
            table.put(Integer.toString(shop.getShopNumber()), shop);
        }

        //----ADD SHOP EDGE TEST(works)------
        graph.addShopEdge(1, 2);
        graph.addShopEdge(2, 3);
        graph.addShopEdge(3, 4);
        graph.addShopEdge(4, 5);
        graph.addShopEdge(1, 5);
        System.out.println("No change adjacency list");
        graph.adjacencyList();
        System.out.println();


        //------DELETE SHOP VERTEX-----------
        System.out.println("DELETE SHOP VERTEX");
        System.out.println("Delete shop vertex '5'");
        graph.deleteShopVertex(5);
        graph.adjacencyList();
        System.out.println("Delete non shop vertex: '0'");
        graph.deleteShopVertex(0);
        System.out.println();


        //------DELETE SHOP EDGE-----------
        System.out.println("DELETE SHOP EDGE");
        System.out.println("Delete edge between '1 - 2'");
        graph.deleteShopEdge(1, 2);
        graph.adjacencyList();
        System.out.println("Delete non existent edge between 0 - 1");
        graph.deleteShopEdge(0, 1);
        System.out.println();


        //------ADD SHOP EDGE--------
        System.out.println("ADD SHOP EDGE");
        System.out.println("Add edge between '1 - 2'");
        graph.addShopEdge(1, 2);
        graph.adjacencyList();
        System.out.println("Add non existent edge between 0 - 1");
        graph.addShopEdge(0, 1);
        System.out.println();


        //-----UPDATE SHOP NUMBER------
        System.out.println("UPDATE SHOP NUMBER, REAL TIME");
        System.out.println("Update shop number: Shop1 -> Shop24");
        graph.updateShopNumber(1, 24);
        graph.adjacencyList();
        System.out.println("Update non existent shop's number");
        graph.updateShopNumber(0, 55);
        System.out.println();
        System.out.println("Update shop number to aleady existing shop number. Shop24 change to Shop6");
        graph.updateShopNumber(24, 6);
        System.out.println();

        //------DEPTH FIRST SEARCH------
        System.out.println();
        System.out.println("Starting adjacency list for DFS: ");
        graph.adjacencyList();
        System.out.println();

        System.out.println("Depth_First_Search:");
        System.out.println("DFS with changed Shop number 24");
        
        System.out.println("SOURCE shop does not exist scenario");
        System.out.println("DFS: Source Shop1 -> Destination Shop4");
        graph.depthFirstSearch(1, 4);
        System.out.println();
    
        System.out.println("DESTINATION shop does not exist scenario");
        System.out.println("DFS: Source Shop24 -> Destination Shop5");
        graph.depthFirstSearch(24, 5);
        System.out.println();

        System.out.println("Neither SOURCE AND DESTINATION shop exist scenario");
        System.out.println("DFS: Source Shop1 -> Destination Shop5");
        graph.depthFirstSearch(1, 5);
        System.out.println();

        System.out.println("Working DFS: ");
        System.out.println("DFS: Source Shop24 -> Destination Shop4");
        graph.depthFirstSearch(24, 4);
        System.out.println();

        System.out.println("If cannot reach Destination:");
        System.out.println("DFS: Source Shop24 -> Destination Shop6");
        graph.depthFirstSearch(24, 6);
        System.out.println();

        System.out.println("Delete shop 4. Real time change");
        System.out.println("Shop24 -> Shop3");
        graph.deleteShopVertex(4);
        graph.depthFirstSearch(24, 3);
        System.out.println();




        //-------BREADTH FIRST SEARCH-------
        
        System.out.println("\n");

        System.out.println("Fresh Clean Slate: \n\n");
        System.out.println("Breadth_First_Search:");

        System.out.println("Adding back shop 4 for BFS");
        System.out.println("Adding back Shop3 edge to Shop4");
        graph.addShopVertex(4);
        graph.addShopEdge(3, 4);
        System.out.println("Starting adjacency list for BFS: ");
        graph.adjacencyList();
        System.out.println();


        System.out.println("SOURCE shop does not exist scenario");
        System.out.println("BFS: Source Shop1 -> Destination Shop4");
        graph.breadthFirstSearch(1, 4);
        System.out.println();
        
        System.out.println("DESTINATION shop does not exist scenario");
        System.out.println("BFS: Source Shop24 -> Destination Shop5");
        graph.breadthFirstSearch(24, 5);
        System.out.println();

        System.out.println("Neither SOURCE AND DESTINATION shop exist scenario");
        System.out.println("BFS: Source Shop1 -> Destination Shop5");
        graph.breadthFirstSearch(1, 5);
        System.out.println();

        System.out.println("Working BFS: ");
        System.out.println("BFS: Source Shop24 -> Destination Shop4");
        graph.breadthFirstSearch(24, 4);
        System.out.println();

        System.out.println("If cannot reach Destination:");
        System.out.println("BFS: Source Shop24 -> Destination Shop6");
        graph.breadthFirstSearch(24, 6);
        System.out.println();

        System.out.println("Delete shop 4. Real time change");
        System.out.println("Shop24 -> Shop3");
        graph.deleteShopVertex(4);
        graph.breadthFirstSearch(24, 3);
        System.out.println();
    }
}

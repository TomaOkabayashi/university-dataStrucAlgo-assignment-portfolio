// from Prac 6 (did work together with a couple of friends for graphs. So may look similar)
public class Graph {
    private Shop[] shopVertices;
    private int maxNumberShopVertices;
    private int numberOfShopVertices; // vertex count
    private int numberOfNodesDFSPath;
    private int numberOfNodesBFSPath;
    private Object[] dfsPath;
    private Object[] bfsPath;
    private boolean dfsPathValid;
    private boolean bfsPathValid;

    public Object[] getDFSPath() {
        return dfsPath;
    }

    public Object[] getBFSPath() {
        return bfsPath;
    }

    public int getNumberOfNodesDFSPath() {
        return numberOfNodesDFSPath;
    }

    public int getNumberOfNodesBFSPath() {
        return numberOfNodesBFSPath;
    }


    public Graph(int maxNumberShopVertices) {
        this.maxNumberShopVertices = maxNumberShopVertices;
        shopVertices = new Shop[maxNumberShopVertices];
        numberOfShopVertices = 0;
        dfsPath = new Object[maxNumberShopVertices];
        bfsPath = new Object[maxNumberShopVertices];
        dfsPathValid = false;
        bfsPathValid = false;
    }

    //---------ADD SHOP NODE------------
    public boolean shopNumberExists(int shopNumber) {
        for (int i = 0; i < numberOfShopVertices; i++) {
            if (shopVertices[i] != null && shopVertices[i].getShopNumber() == shopNumber) {
                return true;
            }
        }
        return false;
    }
    public void addShopVertex(int shopNumber) {
        if (numberOfShopVertices >= maxNumberShopVertices) {
            throw new IllegalStateException("Maximum number of shop vertices reached.");
        }
        if (numberOfShopVertices < maxNumberShopVertices && !shopNumberExists(shopNumber)) {
            Shop shopVertex = new Shop(shopNumber, "", "", "", 0); // Create a new Shop instance
            shopVertices[numberOfShopVertices] = shopVertex;
            numberOfShopVertices++;
        }
    }

    //---------ADD SHOP EDGE------------
    public Shop getShopVertex(int shopNumber) {
        for (int i = 0; i < numberOfShopVertices; i++) {
            if (shopVertices[i] != null & shopVertices[i].getShopNumber() == shopNumber) {
                return shopVertices[i];
            }
        }
        return null;
    }
    public void addShopEdge(int shopNumber1, int shopNumber2) {
        Shop shop1 = getShopVertex(shopNumber1);
        Shop shop2 = getShopVertex(shopNumber2);

        if (shop1 == null) {
            System.out.println("Shop " + shopNumber1 + " does not exist in the graph. Cannot add edge.");
            return;
        } else if (shop2 == null) {
            System.out.println("Shop " + shopNumber2 + " does not exist in the graph. Cannot add edge.");
            return;
        } else {
            shop1.getAdjacentShops().insertLast(shop2);
            shop2.getAdjacentShops().insertFirst(shop1);
        }
    }


    //----------DELETE SHOP NODE----------
    private void deleteNullShopVertices() {
        Shop[] newShopVertices = new Shop[maxNumberShopVertices];
        int newIndex = 0;

        for (int i = 0; i < maxNumberShopVertices; i++) {
            if (shopVertices[i] != null) {
                newShopVertices[newIndex] = shopVertices[i];
                newIndex++;
            }
        }

        shopVertices = newShopVertices;
    }
    public void deleteShopVertex(int shopNumber) {
        if (!shopNumberExists(shopNumber)) {
            System.out.println("Shop " + shopNumber + " does not exist in the graph."); // ADD EXCEPTIONS LATER TOMA
            return;
        }

        // Find the shop to delete
        Shop shopToDelete = getShopVertex(shopNumber);

        // Delete edges between the shop to delete and its adjacent shops
        LinkedList adjacentShops = shopToDelete.getAdjacentShops();
        for (Object adjacentShopObj : adjacentShops.convertListToArray()) {
            Shop adjacentShop = (Shop) adjacentShopObj;
            adjacentShop.getAdjacentShops().remove(shopToDelete); // Remove the edge from adjacent shops to the shop to delete
        }

        // this delete shop from shoVertices array
        for (int i = 0; i < numberOfShopVertices; i++) {
            if (shopVertices[i] == shopToDelete) {
                shopVertices[i] = null;
            }
        }

        numberOfShopVertices--;

        deleteNullShopVertices();
    }

    //---------DELETE SHOP EDGE----------
    public void deleteShopEdge(int shopNumber1, int shopNumber2) {
        Shop shop1 = getShopVertex(shopNumber1);
        Shop shop2 = getShopVertex(shopNumber2);

        if (shop1 == null) {
            System.out.println("Shop " + shopNumber1 + " does not exist in the graph. Cannot delete edge.");
            return;
        } else if (shop2 == null) {
            System.out.println("Shop " + shopNumber2 + " does not exist in the graph. Cannot delete edge.");
            return;
        } else {
            shop1.getAdjacentShops().remove(shop2);
            shop2.getAdjacentShops().remove(shop1);
        }
    }


    //---------ADJACENCY LIST------------
    public void adjacencyList() {
        for (int i = 0; i < numberOfShopVertices; i++) {
            Shop currentShopVertex = shopVertices[i]; //gets the i-th vertex from the array of graph shopVertices
            System.out.print(currentShopVertex.getShopNumber() + "| ");

            LinkedList adjacentShops = currentShopVertex.getAdjacentShops();
            
            for (Object adjacentShop : adjacentShops.convertListToArray()) { // 'Object adjacentShop' contains references to adjacent vertices/shops for currentShopVertex
                Shop adjacentShopVertex = (Shop) adjacentShop; // iterate through the adjacent vertices (represented as Shop objects) in the adjacentShops linkedlist
                int adjacentShopNumber = adjacentShopVertex.getShopNumber(); // for each adjacent vertex/shop, get the shop number with getShopNumber
                System.out.print(adjacentShopNumber + " "); // print extracted shop number in the adjacency list
            }
            System.out.println();
        } 
    }



    //---------UPDATING SHOP NUMBER-----------
        public void updateShopNumber(int oldShopNumber, int newShopNumber) {
        if (!shopNumberExists(oldShopNumber)) {
            System.out.println("Shop " + oldShopNumber + " does not exist in the graph. Cannot update shop number.");
            return;
        }

        if (shopNumberExists(newShopNumber)) {
            System.out.println("Shop number " + newShopNumber + " already exists. Cannot update shop number to an existing number.");
            return;
        }

        Shop shopToUpdate = getShopVertex(oldShopNumber);
        if (shopToUpdate != null) {
            shopToUpdate.setShopNumber(newShopNumber);
        } else {
            System.out.println("Shop " + oldShopNumber + " not found.");
        }
    }





    //---------GRAPH TRAVERSALS------------

    private void resetVisitStatus() {
        for (int i = 0; i < numberOfShopVertices; i++) {
            shopVertices[i].markAsUnvisited();
        }
    }

    //---------DFS------------
    public void depthFirstSearch(int sourceShop, int destinationShop) {
        if (!shopNumberExists(sourceShop) && !shopNumberExists(destinationShop)) {
            System.out.println("Both source shop and destination shop do not exist. Cannot perform DFS.");
            return;
        } else if (!shopNumberExists(sourceShop)) {
            System.out.println("Source shop does not exist. Cannot perform DFS.");
            return;
        } else if (!shopNumberExists(destinationShop)) {
            System.out.println("Destination shop does not exist. Cannot perform DFS.");
            return;
        }

        resetVisitStatus(); // Clean slate for repeatability
        numberOfNodesDFSPath = 0; // Reset before each DFS search
        dfsPathValid = false; // reset the validity

        System.out.println("Depth-First Search from Shop " + sourceShop + " to Shop " + destinationShop + ":");

        if (!dfsRecursive(sourceShop, destinationShop)) {
            System.out.println("No valid path for DFS, cannot find destination shop.");
            return;
        }
        System.out.println();
    }


    private boolean dfsRecursive(int currentShop, int destinationShop) {
        numberOfNodesDFSPath++;
        System.out.print(currentShop + " ");
        Shop currentShopVertex = getShopVertex(currentShop);
        currentShopVertex.markAsVisited();

        dfsPath[numberOfNodesDFSPath - 1] = currentShopVertex; // Store current node in path

        //this checks if the current shop is the destination shop
        if (currentShop == destinationShop) {
            dfsPathValid = true; //Set the bool to true as valid path is found
            return true; // Destination shop found
        }

        LinkedList stackAdjacentShops = currentShopVertex.getAdjacentShops();

        for (Object adjacentShop : stackAdjacentShops.convertListToArray()) {
            Shop adjacentVertex = (Shop) adjacentShop; // Cast Object to Shop

            if (!adjacentVertex.visitStatus()) { // Check if not visited
                if (dfsRecursive(adjacentVertex.getShopNumber(), destinationShop)) {
                    dfsPathValid = true; //Set the bool to true as valid path is found
                    return true; // Destination shop found
                }
            }
        }

        numberOfNodesDFSPath--; // Remove current node from path if it does not lead to destination
        return false; // Destination shop not found
    }



    //---------BFS------------
    public void breadthFirstSearch(int sourceShop, int destinationShop) {
        if (!shopNumberExists(sourceShop) && !shopNumberExists(destinationShop)) {
            System.out.println("Both source shop and destination shop do not exist. Cannot perform BFS.");
            return;
        } else if (!shopNumberExists(sourceShop)) {
            System.out.println("Source shop does not exist. Cannot perform BFS.");
            return;
        } else if (!shopNumberExists(destinationShop)) {
            System.out.println("Destination shop does not exist. Cannot perform BFS.");
            return;
        }

        resetVisitStatus(); // Clean slate for repeatability
        numberOfNodesBFSPath = 0; // Reset before each BFS search
        bfsPathValid = false; // Reset the validity of the BFS path
        System.out.println("Breadth-First Search from Shop " + sourceShop + " to Shop " + destinationShop + ":");

        Shop sourceShopVertex = getShopVertex(sourceShop);
        sourceShopVertex.markAsVisited(); // Mark the start vertex as visited before enqueue

        LinkedList queueAdjacentShops = new LinkedList();
        queueAdjacentShops.insertLast(sourceShop);

        if (!bfsRecursive(queueAdjacentShops, destinationShop)) {
            System.out.println("No valid path for BFS, cannot find destination shop.");
            return;
        }
    
        System.out.println();
    }
    
    private boolean bfsRecursive(LinkedList queueAdjacentShops, int destinationShop) {
        numberOfNodesBFSPath++;
        if (queueAdjacentShops.isEmpty()) {
            return false; // Destination shop not found
        }

        int currentShop = (int) queueAdjacentShops.removeFirst();
        Shop currentShopVertex = getShopVertex(currentShop);

        System.out.print(currentShop + " ");

        if (currentShop == destinationShop) {
            bfsPath[numberOfNodesBFSPath - 1] = currentShopVertex;
            bfsPathValid = true; // Set the bool to true as a valid path is found
            return true; // Destination shop found
        }

        LinkedList adjacentShops = currentShopVertex.getAdjacentShops();

        for (Object adjacentShop : adjacentShops.convertListToArray()) {
            Shop adjacentVertex = (Shop) adjacentShop;
            int adjacentShopNumber = adjacentVertex.getShopNumber();

            if (!adjacentVertex.visitStatus()) {
                adjacentVertex.markAsVisited();
                queueAdjacentShops.insertLast(adjacentShopNumber);
            }
        }

        bfsPath[numberOfNodesBFSPath - 1] = currentShopVertex;
        return bfsRecursive(queueAdjacentShops, destinationShop);
    }





    //----------COMPARE PATHS-----------
    public void printPath(Object[] path) { // add proper comments here toma
        if (path == null) {
            System.out.println("Path is null.");
            return;
        }


        boolean previousShopPrinted = false; // To keep track of whether the previous shop was printed.

        for (Object shop : path) {
            if (shop != null) {
                if (previousShopPrinted) {
                    System.out.print(" -> ");
                }
                System.out.print(shop.toString());
                previousShopPrinted = true;
            }
        }
        System.out.println();
    }

    public void comparePaths() { //Add in the exception that both DFS and BFS must be done prior to doing this option to find the shortest path Toma
        int dfsPathNodes = getNumberOfNodesDFSPath();
        int bfsPathNodes = getNumberOfNodesBFSPath();

        int dfsPathEdgeCounter = dfsPathNodes - 1; //not neccessary but shows 'edge counter'
        int bfsPathEdgeCounter = bfsPathNodes - 1;

        Object[] dfsPathArray = getDFSPath();
        Object[] bfsPathArray = getBFSPath();

        //------Output shortest path-------

        if (!dfsPathValid && !bfsPathValid) {
            System.out.println("Both paths are invalid (cannot reach the destination shop). Cannot compare.");
        }
        // Check if DFS path is invalid
        else if (!dfsPathValid) {
            System.out.println("BFS is the shortest path, as DFS path is invalid");
            System.out.print("BFS Path: "); 
            printPath(bfsPathArray);
        }
        // Check if BFS path is invalid
        else if (!bfsPathValid) {
            System.out.println("DFS is the shortest path, as BFS path is invalid");
            System.out.print("DFS Path: ");
            printPath(dfsPathArray);
        }
        // Both paths are valid, compare edge counters
        else {
            if (dfsPathEdgeCounter < bfsPathEdgeCounter) {
                System.out.print("DFS is the shortest path: ");
                printPath(dfsPathArray);
            } else if (dfsPathEdgeCounter > bfsPathEdgeCounter) {
                System.out.print("BFS is the shortest path: ");
                printPath(bfsPathArray);
            } else {
                System.out.println("Both DFS and BFS have the same distance");
                System.out.println("DFS edge count: " + dfsPathEdgeCounter);
                System.out.println("BFS edge count: " + bfsPathEdgeCounter);
            }
        }
            
    }

}


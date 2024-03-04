// From Prac 7
public class HashTable {
    private HashEntry[] hashArray;
    private int maxSize;
    private int currentSize;
    private double loadFactorThreshold; // Threshold for resizing

    public HashEntry[] getHashArray() {
        return hashArray;
    }

    public HashTable(int size, double loadFactorThreshold) {
        this.maxSize = size;
        this.currentSize = 0;
        this.loadFactorThreshold = loadFactorThreshold;
        this.hashArray = new HashEntry[maxSize];

        for (int i = 0; i < maxSize; i++) {
            hashArray[i] = new HashEntry();
        }
    }

    //---------PUT------------
    public void put(String category, Object value) {
        int hashIndex = hash(category);
        int stepSize = stepHash(category, 0); // Initialise step size

        if (currentSize >= maxSize) {
            System.out.println("Hash table is full. Unable to add more entries.");
            return;
        }

        while (hashArray[hashIndex].getState() == HashEntry.USED) {
            hashIndex = (hashIndex + stepSize) % maxSize;
            stepSize = stepHash(category, stepSize); // Update the step size using the secondary hash function
        }

        hashArray[hashIndex].setKey(category);
        hashArray[hashIndex].setValue((Shop) value);
        hashArray[hashIndex].setState(HashEntry.USED);

        currentSize++;

        // Check load factor and resize if necessary after adding an entry
        checkLoadFactor();
    }



    //---------GET------------
    public Object get(String key) {
        int hashIndex = hash(key);
        int stepSize = stepHash(key, 0); // Initialise step size

        for (int i = 0; i < maxSize; i++) {
            int currentIndex = (hashIndex + i * stepSize) % maxSize;
            if (hashArray[currentIndex].getState() == HashEntry.USED &&
                hashArray[currentIndex].getKey().equals(key)) {
                return hashArray[currentIndex].getValue();
            }
        }

        return null; // Key not found
    }


    //---------REMOVE------------
    public boolean remove(String key) {
        int hashIndex = hash(key);

        for (int i = 0; i < maxSize; i++) {
            int currentIndex = (hashIndex + i) % maxSize;
            if (hashArray[currentIndex].getState() == HashEntry.USED &&
                hashArray[currentIndex].getKey().equals(key)) {
                // Found the key, set its state to FREE to mark it as deleted
                hashArray[currentIndex].setState(HashEntry.FREE);
                currentSize--;
                return true;
            }
        }

        return false; // Key not found
    }



    private int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash * 31) + key.charAt(i);
        }
        return Math.abs(hash) % maxSize;
    }


    //---------USING THIS HASH------------
    private int stepHash(String key, int attempt) {
        int prime = 31;
        return prime - (Math.abs(key.hashCode()) % prime);
    }



    //---------LOAD FACTOR------------
    private void checkLoadFactor() {
        double loadFactor = (double) currentSize / maxSize;

        if (loadFactor > 0.75) {
            resize(maxSize * 2); // Double the size when load factor exceeds 75%
        } else if (loadFactor < 0.25 && maxSize > 53) {
            resize(maxSize / 2); // Halve the size when load factor is below 25%
        }
    }


    //---------RESIZE-----------
    private int findNextPrime(int size, int originalSize) {
        while (true) {
            size++;
            if (isPrime(size) && size >= 2 * originalSize) {
                return size;
            }
        }
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }


    //---------RESIZE------------
    private void resize(int newSize) {
        // Ensure newSize is a prime number and greater than or equal to the current size
        newSize = findNextPrime(Math.max(newSize, currentSize), currentSize);

        HashTable newHashTable = new HashTable(newSize, loadFactorThreshold);

        for (int i = 0; i < maxSize; i++) {
            if (hashArray[i].getState() == HashEntry.USED) {
                newHashTable.put(hashArray[i].getKey(), hashArray[i].getValue());
            }
        }

        // Update this hash table's attributes
        this.maxSize = newHashTable.maxSize;
        this.currentSize = newHashTable.currentSize; // Update currentSize based on the new hash table
        this.hashArray = newHashTable.hashArray;
    }




    //---------DISPLAY------------
    public void display() {
        System.out.println("Current Hash Table Contents:");
        for (int i = 0; i < maxSize; i++) {
            System.out.print("Index " + i + ": ");
            if (hashArray[i].getState() == HashEntry.USED) {
                System.out.println(hashArray[i].getKey() + " -> " + hashArray[i].getValue());
            } else {
                System.out.println("Empty");
            }
        }
        System.out.println(); // Add an extra line for better separation
    }




    //-----UPDATE SHOP ATTRIBUTES-------
    public void updateShopNumber(int oldShopNumber, int newShopNumber) {
        for (int i = 0; i < maxSize; i++) {
            Shop shop = (Shop) hashArray[i].getValue();
            if (shop != null && shop.getShopNumber() == oldShopNumber) {
                shop.setShopNumber(newShopNumber);
            }
        }
    }

    public void updateShopName(int shopNumber, String newShopName) {
        boolean shopExists = false;
        for (int i = 0; i < maxSize; i++) {
            Shop shop = (Shop) hashArray[i].getValue();
            if (shop != null && shop.getShopNumber() == shopNumber) {
                shop.setShopName(newShopName);
                shopExists = true;
            }
        }
        if (!shopExists) {
            System.out.println("Shop " + shopNumber + " does not exist in the graph. Cannot update shop name.");
        }
    }

    public void updateShopCategory(int shopNumber, String newCategory) {
        boolean shopExists = false;
        for (int i = 0; i < maxSize; i++) {
            Shop shop = (Shop) hashArray[i].getValue();
            if (shop != null && shop.getShopNumber() == shopNumber) {
                shop.setCategory(newCategory);
                shopExists = true;
            }
        }
        if (!shopExists) {
            System.out.println("Shop " + shopNumber + " does not exist in the graph. Cannot update shop category.");
        }
    }

    public void updateShopLocation(int shopNumber, String newLocation) {
        boolean shopExists = false;
        for (int i = 0; i < maxSize; i++) {
            Shop shop = (Shop) hashArray[i].getValue();
            if (shop != null && shop.getShopNumber() == shopNumber) {
                shop.setLocation(newLocation);
                shopExists = true;
            }
        }
        if (!shopExists) {
            System.out.println("Shop " + shopNumber + " does not exist in the graph. Cannot update shop location.");
        }
    }

    public void updateShopRating(int shopNumber, int newRating) {
        boolean shopExists = false;
        for (int i = 0; i < maxSize; i++) {
            Shop shop = (Shop) hashArray[i].getValue();
            if (shop != null && shop.getShopNumber() == shopNumber) {
                shop.setRating(newRating);
                shopExists = true;
            }
        }
        if (!shopExists) {
            System.out.println("Shop " + shopNumber + " does not exist in the graph. Cannot update shop rating.");
        }
    }


    //---------SEARCH BY CATEGORY------------
    public void searchByCategory(String category) {
        if (category == null) {
            System.out.println("Category cannot be null.");
            return;
        }

        MaxHeap maxHeap = new MaxHeap(currentSize); // Create a max heap

        LinkedList categoryShops = new LinkedList(); 

        for (int i = 0; i < maxSize; i++) {
            Shop shop = (Shop) hashArray[i].getValue(); // Directly get the Shop object

            if (shop != null && shop.getCategory().equals(category)) {
                maxHeap.addShopRating(shop); // Insert the shop into the max heap
                categoryShops.insertLast(shop);
            }
        }

        maxHeap.heapify(); // Heapify the max heap

        if (!categoryShops.isEmpty()) {
            System.out.println("Shops in the '" + category + "' category, sorted by rating:");
            while (!maxHeap.isEmpty()) {
                Shop shop = maxHeap.getTopRatedShop();
                System.out.println("Shop Number: " + shop.getShopNumber() + ", Name: " + shop.getShopName() + ", Location: " + shop.getLocation() + ", Rating: " + shop.getRating());
            }
        } else {
            System.out.println("No shops found in the '" + category + "' category.");
        }
    }
}

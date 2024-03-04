// Prac 8
public class MaxHeap {
    private Shop[] shopRating;
    private int maxSize;
    private int currentSize;

    public MaxHeap(int maxSize) {
        this.maxSize = maxSize;
        this.currentSize = 0;
        this.shopRating = new Shop[maxSize];
    }

    public void addShopRating(Shop shop) {
        if (currentSize == maxSize) {
            System.out.println("Heap is full. Unable to add more shops.");
            return;
        }

        shopRating[currentSize] = shop;
        trickleUp(currentSize);
        currentSize++;
    }

    private void trickleUp(int index) {
        int parentIndex = (index - 1) / 2;
        Shop bottom = shopRating[index];

        while (index > 0 && shopRating[parentIndex].getRating() < bottom.getRating()) {
            shopRating[index] = shopRating[parentIndex];
            index = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }

        shopRating[index] = bottom;
    }

    public Shop getTopRatedShop() {
        if (isEmpty()) {
            System.out.println("Heap is empty. Unable to get the top-rated shop.");
            return null;
        }
        
        Shop topRatedShop = shopRating[0];
        currentSize--;

        shopRating[0] = shopRating[currentSize];
        trickleDown(0);

        return topRatedShop;
    }

    private void trickleDown(int index) {
        int higherRating;
        Shop top = shopRating[index];

        while (index < currentSize / 2) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = leftChildIndex + 1;

            if (rightChildIndex < currentSize && shopRating[leftChildIndex].getRating() < shopRating[rightChildIndex].getRating()) {
                higherRating = rightChildIndex;
            } else {
                higherRating = leftChildIndex;
            }

            if (top.getRating() >= shopRating[higherRating].getRating()) {
                break;
            }

            shopRating[index] = shopRating[higherRating];
            index = higherRating;
        }

        shopRating[index] = top;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void heapify() { //heapify max heap
        for (int i = currentSize / 2 - 1; i >= 0; i--) {
            trickleDown(i);
        }
    }
}

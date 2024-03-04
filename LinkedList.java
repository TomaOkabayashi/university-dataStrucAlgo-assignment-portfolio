// The doubly linked part comes from Practical 4 code. But the countOfNodes, vertexExists, convertListToArray comes from Practical 6
public class LinkedList {
    private class ListNode {
        private Object value;
        private ListNode next;
        private ListNode previous;

        public ListNode(Object nodeValue) {
            value = nodeValue;
            next = null;
            previous = null;
        }

        public Object getValue() {
            return value;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) { //new next
            this.next = next;
        }

        public ListNode getPrevious() {
            return previous;
        }

        public void setPrevious(ListNode previous) { //new previous
            this.previous = previous;
        }
    }


    private ListNode head;
    private ListNode tail;


    public LinkedList() { //constructor, initialise head to null
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }


    //---------INSERT VALUES------------
    public void insertFirst(Object newValue) {
        ListNode newNode = new ListNode(newValue);

        if(isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
    }

    public void insertLast(Object newValue) {
        ListNode newNode = new ListNode(newValue);

        if(isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    
    //---------PEAK VALUES------------
    public Object peekFirst() {
        if(isEmpty()) {
            throw new IllegalStateException("Cannot peek the first element, as list is empty.");
        }
        return head.getValue();
    }

    public Object peekLast() {
        if(isEmpty()) {
            throw new IllegalStateException("Cannot peek the first element, as list is empty.");
        }
        return head.getValue();
    }


    //-----------REMOVE VALUES-------------
    public Object removeFirst() {
        if(isEmpty()) {
            throw new IllegalStateException("Cannot peek the first element, as list is empty.");
        }

        Object nodeValue = head.getValue(); // data of first node stored in nodeValue
        head = head.getNext(); // head reference to point to next node in list, pretty much removes the first node

        if (head == null) {
            tail = null;
        } else {
            head.setPrevious(null);
        }

        return nodeValue; //returns the data that was in the removed node  
    }

    public Object removeLast() {
        if(isEmpty()) {
            throw new IllegalStateException("Cannot peek the first element, as list is empty.");
        }

        Object nodeValue = tail.getValue(); // data of last node stored in nodeValue
        tail = tail.getPrevious(); //reference to point to the previous node in the list

        if (tail == null) {
            head = null;
        } else {
            tail.setNext(null);
        }

        return nodeValue;
    }


    //---------SPECIFIC ELEMENT REMOVAL----------
    public void remove(Object targetValue) {
    if (isEmpty()) {
        return; // List is empty, nothing to remove
    }

    ListNode current = head;

    // Iterate through the list
    while (current != null) {
        if (current.getValue().equals(targetValue)) { 
            if (current == head) { 
                //update the head to point to the next node, basically removing the current node
                head = current.getNext();
                if (head != null) {
                    head.setPrevious(null);
                } else {
                    tail = null; //if hte head was the only element in the list
                }
            } else if (current == tail) {
                tail = current.getPrevious(); //update the tail to point to the previous node
                tail.setNext(null);
            } else {
                //update the next reference of the previous node to bypass the current node
                current.getPrevious().setNext(current.getNext());

                // update the previous reference of the next node to bypass the current node
                current.getNext().setPrevious(current.getPrevious());
            }
            return; // node found and removed
        }
        current = current.getNext();
    }
}


    //------NUMBER OF ELEMENTS/NODES IN LINKEDLIST----------
    public int countOfNodes() { // It iterates through the linked list and counts the nodes until it reaches the end.
        int count = 0;
        ListNode current = head;

        while (current != null) {
            count++;
            current = current.getNext();
        }

        return count;
    }


    //--------LIST -> ARRAY---------
    public Object[] convertListToArray() {
        int count = countOfNodes();
        Object[] array = new Object[count];
        ListNode current = head;

        for (int i = 0; i < count; i++) {
            array[i] = current.getValue();
            current = current.getNext();
        }

        return array;
    }
    
    //--------VERTEX EXISTENCE CHECK----------
    // It checks if the graph contains a vertex with the given shop name and returns true if it does, and false otherwise.
    public boolean vertexExists(String shop) { 
        ListNode current = head;

        while (current != null) {
            if (shop.equals(current.getValue().toString())) {
                return true;
            }
            current = current.getNext();
        }

        return false;
    }

}
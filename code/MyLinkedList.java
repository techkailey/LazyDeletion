/**
* @author Kailey Bergeron 
* @version 11.0.8
* @since September 26th, 2022
* Class for LinkedList Tester

1) The advantages and disadvantages of lazy deletion:

Advantages- 
-----------
Makes deletion faster because there are no shifting of the data 
structures. No shiftting to insert in a deleted objects place either.
Can reinsert the deleted object back in the place it was originally. 

Disadvantages-
------------- 
Have to transverse twice to delete an item. You have to first mark it and then delete it.
In addition, you need more space for lazy deletion, they are just sitting in memory taking up space. 

*/

public class MyLinkedList<AnyType> {

    // instance variables
    private int size;
    private int deleteCount; // keep count of deleted elements
    private boolean deleted; // if true the Node is deleted
    private Node firstNode;
    private Node lastNode;

    // contructor, set initial values
    public MyLinkedList() {
        this.size = 0;
        this.deleteCount = 0;
        this.firstNode = null;
        this.lastNode = null;
    }

    /**
     * Returns the number of items in this collection.
     * @return the number of items in this collection.
     */
    public int size() {
        return this.size;
    }

    /**
     * Adds an item to this collection.
     * @param element any object.
     * void, does not return 
    */
    public void add(AnyType element) {

        Node newNode = new Node(element);

        // walk out along Node linkages until we hit a node
        // that does not have a next node - at that point,
        // currentNode will be the last node
        // while this will work, its HORRIBLY inefficient Theta(N)
        // there's a better way, below, by keeping around a reference
        // to the last node
        /*
        Node currentNode = firstNode;
        while (currentNode.getNext() != null)
            currentNode = currentNode.getNext();
        // tell currentNode that its next node is the one we just built!
        currentNode.setNext(newNode);
        */

        if (size == 0) {
            this.firstNode = newNode;
            this.lastNode = newNode;
        } else {
            this.lastNode.setNext(newNode);
            this.lastNode = newNode;
        }

        // increment the size
        size++;

    }
    /**
     * Adds an item to this collection, at specified position index.
     * Items at or after that position are slid one position higher.
     * @param index add element at index.
     * @param element any object.
     * @throws IndexOutOfBoundsException if idex is out of range.
     */ 
    public void add(AnyType element, int index) {
        if (index > size)
            throw new IndexOutOfBoundsException("index is too big!");
        else if (index < 0) 
            throw new IndexOutOfBoundsException("Have you lost your mind?");

        if (index == size - 1) {
            add(element);
            return;
        }

        Node newNode = new Node(element);

        if (index == 0) {
            newNode.setNext(firstNode);
            firstNode = newNode;
            if (size == 0)
                lastNode = newNode;
            size++;
            return;
        }
    
        Node currentNode = firstNode;
        int currentIndex = 0;   
        while (currentIndex < index-1) {
            currentNode = currentNode.getNext();
            currentIndex++;
        }
        newNode.setNext(currentNode.getNext());
        currentNode.setNext(newNode);
        size++;
    }
    /**
    * Returns the item at position idx.
    * @param idxex the index to search in.
    * @throws IndexOutOfBoundsException if index is out of range.
    * @return Node at position index
    */
    public AnyType get(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("index is too big!");
        else if (index < 0) 
            throw new IndexOutOfBoundsException("Have you lost your mind?");
    
        Node currentNode = firstNode;
        int currentIndex = 0;   
        while (currentIndex < index) {
            currentNode = currentNode.getNext();
            currentIndex++;
        }
        return currentNode.getData();
    }
   

    // remove (and return the data element)
    // helper method for lazyRemove method
    public void remove(){
        int currentIndex = 0;
        // create Node currentNode and initialze to the firstNode
        Node currentNode = firstNode;

        // continue to loop through list while the currentNode is marked as deleted
        while (currentNode.deleted == true) {
            firstNode = currentNode.getNext();
            currentNode.setNext(null);
            currentNode = firstNode;
            // when deleted, decrement the size
            size--;
            // increment deleteCount when marked deleted
            deleteCount++;
        }

        // continue to loop thorugh list while currentIndex is less than size-1
        while(currentIndex < size-1){
            // if the nextNode is marked as deleted 
            if(currentNode.getNext().deleted == true){
                // removal proccess for when marked nodes need to be deleted 
                Node nodeToRemove = currentNode.getNext();
                currentNode.setNext(nodeToRemove.getNext());
                // if the node is deleted set lastNode to currentNode
                if(nodeToRemove.getNext() == null){
                    lastNode = currentNode;
                }
                // set equal to null and remove element, decrement size and deleteCount
                nodeToRemove.setNext(null);
                size--;
                deleteCount--;
            // if not deleted move to next node and increment currentIndex
            }else{
                currentNode = currentNode.getNext();
                currentIndex++;
            }
        }
    }

    /**
    * method that marks element as deleted
    * @param idex the index of the object 
    * @return element that is being marked 
    * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
    */
    public AnyType lazyRemove(int index) {
        if (index > size)
            throw new IndexOutOfBoundsException("index is too big!");
        else if (index < 0) 
            throw new IndexOutOfBoundsException("Have you lost your mind?");

        AnyType thingToReturn = null;

        Node currentNode = firstNode;
        int currentIndex = 0;

        while(currentIndex < index-1){
            currentNode = currentNode.getNext();
            currentIndex++;
        }
        if(index == 0){
            thingToReturn = currentNode.getData();
            // currentNode data is set to null
            currentNode.setData(null);
            // currentNode is marked as "deleted"
            currentNode.deleted = true;
        }else{
            thingToReturn = currentNode.getNext().getData();
            // set next Node's data to null when marked deleted 
            currentNode.getNext().setData(null);
            // marks Node as deleted when true
            currentNode.getNext().deleted = true;
        }
        // Node has been marked, increment deleteCount
        deleteCount++;
        // if there are more Nodes marked than unmarked remove marked elements
        if(this.deleteCount > this.size/2)
            remove();

        return thingToReturn;
    }

    public MyIterator iterator() {

        return new MyIterator();

    }

    public class MyIterator {

        private Node currentNode;

        public MyIterator() {
            currentNode = MyLinkedList.this.firstNode;
        }

        public boolean hasNext() {
            if (currentNode == null)
                return false;
            return true;
        }

        public AnyType next() {
            AnyType valueToReturn = currentNode.getData();
            currentNode = currentNode.getNext();
            return valueToReturn;
        }
    } // end class MyIterator
    
    // add boolean "deleted" to class
    // class to build and initialize Node and data
    private class Node {
        private boolean deleted; //if true Node is "deleted"
        private AnyType element;
        private Node    nextNode;

        public Node(AnyType element) {
            // initially false until deleted
            this.deleted = false; 
            this.element = element;
            this.nextNode = null;
        }

        public AnyType getData() {
            return this.element;
        }

        public Node getNext() {
            return this.nextNode;
        }

        public void setData(AnyType element) {
            this.element = element;
        }

        public void setNext(Node next) {
            this.nextNode = next;
        }
        public void setDeleted(boolean deleted){
            this.deleted = false;
        }

    } // end of class Node


} // end of MyLinkedList

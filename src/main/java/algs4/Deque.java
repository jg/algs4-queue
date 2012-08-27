import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
  private int size;
  private Node first;
  private Node last;

  public Deque() {                    // construct an empty deque
    // sentinels
    first = new Node(null);
    last  = new Node(null);
    first.next    = last;
    last.previous = first;
  }

  public boolean isEmpty() {           // is the deque empty?
    return size == 0;
  }

  public int size() {                 // return the number of items on the deque
    return size;
  }

  public void addFirst(Item item) {    // insert the item at the front
    if (item == null) throw new java.lang.NullPointerException();

    Node n = new Node(item);
    n.previous          = first;
    n.next              = first.next;
    first.next.previous = n;
    first.next          = n;

    size++;
  }

  public void addLast(Item item) {     // insert the item at the end
    if (item == null) throw new java.lang.NullPointerException();

    Node n = new Node(item);
    n.previous         = last.previous;
    n.next             = last;
    last.previous.next = n;
    last.previous      = n;

    size++;
  }

  public Item removeFirst() {          // delete and return the item at the front
    if (isEmpty()) throw new java.util.NoSuchElementException();

    Item item           = first.next.item;
    first.next.previous = null;
    first               = first.next;
    size--;

    return item;
  }

  public Item removeLast() {           // delete and return the item at the end
    if (isEmpty()) throw new java.util.NoSuchElementException();

    Item item          = last.previous.item;
    last.previous.next = null;
    last               = last.previous;
    size--;

    return item;
  }

  public Iterator<Item> iterator() {  // return an iterator over items in order from front to end
    return new DequeIterator();
  }

  private class DequeIterator implements Iterator<Item> {
    Node currentNode;

    DequeIterator() { currentNode = first; }

    public boolean hasNext() {
      return currentNode.next != last; 
    }

    public void remove() {
      throw new java.lang.UnsupportedOperationException();
    }

    public Item next() {
      if (hasNext())
        currentNode = currentNode.next;
      else
        throw new java.util.NoSuchElementException();
      return currentNode.item;
    }
  }

  private class Node {
    Item item;
    Node next, previous;

    Node(Item item) { this.item = item; }
  }
}

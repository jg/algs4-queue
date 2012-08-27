import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item>{
  private Item[] items;
  private int itemCount;

  public RandomizedQueue() {           // construct an empty randomized queue
    items = (Item[])new Object[10];
  }

  public boolean isEmpty() {           // is the queue empty?
    return itemCount == 0;
  }

  public int size() {                  // return the number of items on the queue
    return itemCount;
  }

  public void enqueue(Item item) {     // add the item
    if (item == null) throw new java.lang.NullPointerException();
    if (isFull()) grow();
    items[itemCount++] = item;
  }

  // TODO: shrink array to avoid loitering
  public Item dequeue() {              // delete and return a random item
    if (isEmpty()) throw new java.util.NoSuchElementException();
    // dequeued item index
    int dequeuedIndex = StdRandom.uniform(itemCount);

    // swap element with last element in items array
    Item tmp           = items[dequeuedIndex];
    items[dequeuedIndex]       = items[itemCount-1];

    itemCount--;

    // shrink array if using less than a quarter of capacity
    if (itemCount < items.length/4) shrink();

    return tmp;
  }

  public Item sample() {              // return (but do not delete) a random item
    if (isEmpty()) throw new java.util.NoSuchElementException();
    int index = StdRandom.uniform(itemCount);

    return items[index];
  }

  public Iterator<Item> iterator() {   // return an independent iterator over items in random order
    return new RandomizedQueueIterator();
  }

  private class RandomizedQueueIterator implements Iterator<Item>{
    int[] alreadyVisited;
    int visitedCount;

    // TODO: this will break if the item array grows after the iterator is created
    RandomizedQueueIterator() {
      alreadyVisited = new int[itemCount];
    }

    public boolean hasNext() {
      return visitedCount != itemCount;
    }

    public void remove() {
      throw new java.lang.UnsupportedOperationException();
    }

    public Item next() {
      if (!hasNext()) throw new java.util.NoSuchElementException();

      while (true) {
        int index = StdRandom.uniform(itemCount);
        if (alreadyVisited[index] == 0) {
          alreadyVisited[index] = 1;
          visitedCount++;
          return items[index];
        }
      }
    }
  }

  private void shrink() {
    Item[] oldItems = items;
    items = (Item[])new Object[oldItems.length/2];
    for (int i = 0; i < itemCount; i++) items[i] = oldItems[i];
  }

  private void grow() {
    Item[] oldItems = items;
    items = (Item[])new Object[2*oldItems.length];
    for (int i = 0; i < oldItems.length; i++) items[i] = oldItems[i];
  }

  private boolean isFull() {
    return items.length == itemCount;
  }

  private class Node {
    Item item;
    Node next, previous;

    Node(Item item) { this.item = item; }
  }
}

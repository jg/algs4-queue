import java.util.Iterator;

public class Subset {
  public static void main(String[] args) {
    RandomizedQueue<String> q = new RandomizedQueue<String>();
    Integer k = Integer.parseInt(args[0]);
    String[] lines = StdIn.readStrings();

    for (String s:lines) q.enqueue(s);

    Iterator it = q.iterator();
    for (int i = 0; i < k; i++) System.out.format("%s%n", it.next());
  }
}

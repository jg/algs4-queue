import org.junit.*;
import static org.junit.Assert.*;
import java.util.Iterator;

public class RandomizedQueueTest {
  RandomizedQueue<Integer> q;
    /**
     * Sets up the test fixture. 
     * (Called before every test case method.)
     */
    @Before
    public void setUp() {
      q = new RandomizedQueue<Integer>();
    }

    /**
     * Tears down the test fixture. 
     * (Called after every test case method.)
     */
    @After
    public void tearDown() {
      q = null;
    }

    @Test
    public void testIterator() {
      int values[] = {12, 45, 6, 98, 24, 1, 12, 95, 12, 4, 65, 9, 18, 19, 2};
      for (int val: values) q.enqueue(val);
      assertEquals(q.size(), values.length);

      Iterator it = q.iterator();
      assertTrue(it.hasNext());

      while (it.hasNext()) { 
        int value = (Integer)it.next();
        assertTrue(elementPresent(value, values)); 
      }


    }

    @Test(expected=java.lang.NullPointerException.class)
    public void testExceptionThrownOnNullEnqueue() {
      q.enqueue(null);
    }

    @Test
    public void testEdgeCase() {
      q.enqueue(12);
      assertTrue(q.dequeue() == 12);
      q.enqueue(3);
      assertTrue(q.dequeue() == 3);
    }

    private boolean elementPresent(int val, int[] tab) {
      for (int el: tab) if (el == val) return true;
      return false;
    }

}

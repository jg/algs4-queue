import org.junit.*;
import static org.junit.Assert.*;
import java.util.Iterator;

public class DequeTest {
  Deque<Integer> d;

    @Before
    public void setUp() {
      d = new Deque<Integer>();
    }

    @After
    public void tearDown() {
      d = null;
    }

    @Test
    public void testIsEmpty() {
      assertTrue(d.isEmpty());
      d.addFirst(12);
      assertFalse(d.isEmpty());
      d.removeFirst();
      assertTrue(d.isEmpty());
      d.addLast(12);
        assertFalse(d.isEmpty());
    }

    @Test
    public void testSize() {
      assertEquals(d.size(), 0);
      d.addFirst(12);
      assertEquals(d.size(), 1);
      d.removeFirst();
      assertEquals(d.size(), 0);
      d.addLast(1444);
      assertEquals(d.size(), 1);
    }

    @Test
    public void testRemoveFirst() {
      d.addFirst(1);
      assertEquals(d.size(), 1);
      d.removeFirst();
      assertEquals(d.size(), 0);
      d.addFirst(1);
      d.removeFirst();
    }

    @Test
    public void testAddFirst() {
      d.addFirst(1);
      d.addFirst(2);
      d.addFirst(3);
      assertTrue(d.removeFirst() == 3);
      assertTrue(d.removeFirst() == 2);
      assertTrue(d.removeFirst() == 1);
    }

    @Test
    public void testRandom() {
      for (int i = 0; i < 100; i++) { d.addFirst(i); }
      for (int i = 0; i < 100; i++) { assertTrue(d.removeLast() == i); }
      for (int i = 0; i < 100; i++) { d.addLast(i); }
      for (int i = 0; i < 100; i++) { assertTrue(d.removeFirst() == i); }
    }

    @Test(expected=java.lang.NullPointerException.class)
    public void testExceptionThrownOnNullItemAddFirst() {
      d.addFirst(null);
    }

    @Test(expected=java.lang.NullPointerException.class)
    public void testExceptionThrownOnNullItemAddLast() {
      d.addLast(null);
    }

    @Test(expected=java.lang.UnsupportedOperationException.class)
    public void testExceptionThrownOnIteratorRemove() {
      d.addFirst(12);
      Iterator it = d.iterator();
      it.remove();
    }

    @Test
    public void testRemoveLast() {
      int values[] = {12, 45, 6, 98, 24};
      for (int val: values) d.addLast(val);
      int val = d.removeLast();
      assertEquals(val, 24);
      assertEquals(d.size(), 4);
      d.addLast(25);
      assertEquals(d.size(), 5);
    }

    @Test
    public void testRemoveFirstRemoveLast() {
      int values[] = {12, 45, 6, 98, 24}; for (int val: values) d.addLast(val);

      assertTrue(d.removeLast()  == 24);
      assertTrue(d.removeFirst() == 12);
      assertTrue(d.removeLast()  == 98);
      assertTrue(d.removeFirst() == 45);
      assertTrue(d.removeFirst() == 6);
    }

    @Test
    public void testIterator() {
      int values[] = {12, 45, 6, 98, 24};
      for (int val: values) d.addLast(val);

      Iterator it = d.iterator();
      assertTrue(it.hasNext());

      int i = 0;
      for (int val: values) { assertEquals(values[i++], it.next()); }


      for (int val: values) d.addFirst(val);
      it = d.iterator();
      assertTrue(it.hasNext());
      i = 0;
      for (int val: values) { assertEquals(values[values.length - i++ - 1], it.next()); }
    }

    @Test
    public void testIterator2() {
      for (int i = 0; i < 100; i++) {
        d.addFirst(i); d.addLast(i);
      }

      Iterator it1 = d.iterator();
      Iterator it2 = d.iterator();
      while (it1.hasNext()) {
        assertTrue(it1.next() == it2.next());
      }
    }

    @Test(expected=java.util.NoSuchElementException.class)
    public void testIteratorThrowsNoSuchElementException() {
      Iterator i1 = d.iterator();
      assertTrue(i1.hasNext() == false);
      i1.next();
    }

}

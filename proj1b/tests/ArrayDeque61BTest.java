import deque.ArrayDeque61B;
import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrayDeque61BTest {

    @Test
    public void emptyDequeTest() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();

        assertTrue(ad.isEmpty());
        assertEquals(0, ad.size());
        assertEquals(List.of(), ad.toList());
        assertNull(ad.get(0));
        assertNull(ad.get(-1));
        assertNull(ad.removeFirst());
        assertNull(ad.removeLast());
        assertTrue(ad.isEmpty());
        assertEquals(0, ad.size());
    }

    @Test
    public void addFirstTest() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();

        ad.addFirst(10);
        ad.addFirst(20);
        ad.addFirst(30);

        assertFalse(ad.isEmpty());
        assertEquals(3, ad.size());
        assertEquals(List.of(30, 20, 10), ad.toList());
    }

    @Test
    public void addLastTest() {
        ArrayDeque61B<String> ad = new ArrayDeque61B<>();

        ad.addLast("a");
        ad.addLast("b");
        ad.addLast("c");

        assertFalse(ad.isEmpty());
        assertEquals(3, ad.size());
        assertEquals(List.of("a", "b", "c"), ad.toList());
    }

    @Test
    public void addFirstAndAddLastTest() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();

        ad.addFirst(2);
        ad.addFirst(1);
        ad.addLast(3);
        ad.addLast(4);

        assertEquals(4, ad.size());
        assertEquals(List.of(1, 2, 3, 4), ad.toList());
    }

    @Test
    public void getTest() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();

        ad.addLast(5);
        ad.addLast(6);
        ad.addLast(7);

        assertEquals(5, ad.get(0));
        assertEquals(6, ad.get(1));
        assertEquals(7, ad.get(2));
        assertNull(ad.get(-1));
        assertNull(ad.get(3));
    }

    @Test
    public void removeFirstTest() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();

        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);

        assertEquals(1, ad.removeFirst());
        assertEquals(2, ad.removeFirst());
        assertEquals(1, ad.size());
        assertEquals(List.of(3), ad.toList());
    }

    @Test
    public void removeLastTest() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();

        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);

        assertEquals(3, ad.removeLast());
        assertEquals(2, ad.removeLast());
        assertEquals(1, ad.size());
        assertEquals(List.of(1), ad.toList());
    }

    @Test
    public void removeUntilEmptyTest() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();

        ad.addFirst(2);
        ad.addFirst(1);
        ad.addLast(3);

        assertEquals(1, ad.removeFirst());
        assertEquals(3, ad.removeLast());
        assertEquals(2, ad.removeFirst());
        assertTrue(ad.isEmpty());
        assertEquals(0, ad.size());
        assertNull(ad.removeFirst());
        assertNull(ad.removeLast());
        assertTrue(ad.isEmpty());
        assertEquals(0, ad.size());
    }

    @Test
    public void addMoreThanInitialCapacityTest() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();

        for (int i = 0; i < 12; i++) {
            ad.addLast(i);
        }

        assertEquals(12, ad.size());
        assertEquals(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), ad.toList());
    }

    @Test
    public void circularAddAndRemoveTest() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();

        ad.addLast(0);
        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);
        ad.addLast(4);
        ad.addLast(5);

        assertEquals(0, ad.removeFirst());
        assertEquals(1, ad.removeFirst());

        ad.addLast(6);
        ad.addLast(7);
        ad.addFirst(-1);

        assertEquals(List.of(-1, 2, 3, 4, 5, 6, 7), ad.toList());
    }

    @Test
    public void getRecursiveThrowsTest() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();

        assertThrows(UnsupportedOperationException.class, () -> ad.getRecursive(0));
    }

    @Test
    public void iteratorOrderTest() {
        ArrayDeque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");

        List<String> iterated = new ArrayList<>();
        for (String item : ad) {
            iterated.add(item);
        }

        assertEquals(List.of("front", "middle", "back"), iterated);
    }

    @Test
    public void equalsSameImplSameContentTest() {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        ArrayDeque61B<Integer> b = new ArrayDeque61B<>();

        a.addLast(1);
        a.addLast(2);
        a.addLast(3);

        b.addLast(1);
        b.addLast(2);
        b.addLast(3);

        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
    }

    @Test
    public void equalsDifferentOrderTest() {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        ArrayDeque61B<Integer> b = new ArrayDeque61B<>();

        a.addLast(1);
        a.addLast(2);
        a.addLast(3);

        b.addLast(1);
        b.addLast(3);
        b.addLast(2);

        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
    }

    @Test
    public void equalsNullAndOtherTypeTest() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addLast(1);

        assertFalse(ad.equals(null));
        assertFalse(ad.equals("not a deque"));
    }

    @Test
    public void toStringFormatTest() {
        ArrayDeque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");

        assertEquals("[front, middle, back]", ad.toString());
        assertEquals("[]", new ArrayDeque61B<>().toString());
    }
}

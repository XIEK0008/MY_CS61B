import deque.ArrayDeque61B;
import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LinkedListDeque61BTest {

    @Test
    public void emptyDequeTest() {
        LinkedListDeque61B<Integer> lld = new LinkedListDeque61B<>();

        assertTrue(lld.isEmpty());
        assertEquals(0, lld.size());
        assertEquals(List.of(), lld.toList());
        assertNull(lld.get(0));
        assertNull(lld.get(-1));
        assertNull(lld.removeFirst());
        assertNull(lld.removeLast());
    }

    @Test
    public void addAndRemoveTest() {
        LinkedListDeque61B<Integer> lld = new LinkedListDeque61B<>();

        lld.addFirst(2);
        lld.addFirst(1);
        lld.addLast(3);
        lld.addLast(4);

        assertEquals(List.of(1, 2, 3, 4), lld.toList());
        assertEquals(1, lld.removeFirst());
        assertEquals(4, lld.removeLast());
        assertEquals(List.of(2, 3), lld.toList());
        assertEquals(2, lld.size());
    }

    @Test
    public void getAndGetRecursiveTest() {
        LinkedListDeque61B<String> lld = new LinkedListDeque61B<>();
        lld.addLast("a");
        lld.addLast("b");
        lld.addLast("c");

        assertEquals("a", lld.get(0));
        assertEquals("b", lld.get(1));
        assertEquals("c", lld.get(2));
        assertNull(lld.get(3));
        assertNull(lld.get(-1));

        assertEquals("a", lld.getRecursive(0));
        assertEquals("b", lld.getRecursive(1));
        assertEquals("c", lld.getRecursive(2));
        assertNull(lld.getRecursive(3));
        assertNull(lld.getRecursive(-1));
    }

    @Test
    public void iteratorOrderTest() {
        LinkedListDeque61B<String> lld = new LinkedListDeque61B<>();
        lld.addLast("front");
        lld.addLast("middle");
        lld.addLast("back");

        List<String> iterated = new ArrayList<>();
        for (String s : lld) {
            iterated.add(s);
        }

        assertEquals(List.of("front", "middle", "back"), iterated);
    }

    @Test
    public void equalsSameImplSameContentTest() {
        LinkedListDeque61B<Integer> a = new LinkedListDeque61B<>();
        LinkedListDeque61B<Integer> b = new LinkedListDeque61B<>();

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
        LinkedListDeque61B<Integer> a = new LinkedListDeque61B<>();
        LinkedListDeque61B<Integer> b = new LinkedListDeque61B<>();

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
    public void toStringFormatTest() {
        LinkedListDeque61B<String> lld = new LinkedListDeque61B<>();
        lld.addLast("front");
        lld.addLast("middle");
        lld.addLast("back");

        assertEquals("[front, middle, back]", lld.toString());
        assertEquals("[]", new LinkedListDeque61B<>().toString());
    }
}

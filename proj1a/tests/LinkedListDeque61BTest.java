import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class LinkedListDeque61BTest {



    @Test
    public void toListReturnsDequeItemsInOrderTest() {
        Deque61B<String> deque = new LinkedListDeque61B<>();

        deque.addLast("front");
        deque.addLast("middle");
        deque.addLast("back");

        assertThat(deque.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    public void toListReturnsNewListTest() {
        Deque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(10);
        deque.addLast(20);

        List<Integer> copy = deque.toList();
        copy.add(30);

        assertThat(deque.toList()).containsExactly(10, 20).inOrder();
        assertThat(copy).containsExactly(10, 20, 30).inOrder();
    }

    @Test
    public void emptyDequeTest() {
        Deque61B<Integer> deque = new LinkedListDeque61B<>();

        assertThat(deque.isEmpty()).isTrue();
        assertThat(deque.size()).isEqualTo(0);
        assertThat(deque.toList()).containsExactly().inOrder();
    }

    @Test
    public void removeFirstTest() {
        Deque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        assertThat(deque.removeFirst()).isEqualTo(1);
        assertThat(deque.removeFirst()).isEqualTo(2);

        assertThat(deque.toList()).containsExactly(3).inOrder();
        assertThat(deque.size()).isEqualTo(1);
    }

    @Test
    public void removeLastTest() {
        Deque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        assertThat(deque.removeLast()).isEqualTo(3);
        assertThat(deque.removeLast()).isEqualTo(2);

        assertThat(deque.toList()).containsExactly(1).inOrder();
        assertThat(deque.size()).isEqualTo(1);
    }

    @Test
    public void removeFromEmptyDequeTest() {
        Deque61B<Integer> deque = new LinkedListDeque61B<>();

        assertThat(deque.removeFirst()).isNull();
        assertThat(deque.removeLast()).isNull();
        assertThat(deque.isEmpty()).isTrue();
        assertThat(deque.size()).isEqualTo(0);
    }

    @Test
    public void removeUntilEmptyTest() {
        Deque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addFirst(2);
        deque.addFirst(1);

        assertThat(deque.removeLast()).isEqualTo(2);
        assertThat(deque.removeLast()).isEqualTo(1);
        assertThat(deque.removeLast()).isNull();
        assertThat(deque.isEmpty()).isTrue();
        assertThat(deque.toList()).containsExactly().inOrder();
    }

    @Test
    public void getTest() {
        Deque61B<String> deque = new LinkedListDeque61B<>();
        deque.addLast("zero");
        deque.addLast("one");
        deque.addLast("two");

        assertThat(deque.get(0)).isEqualTo("zero");
        assertThat(deque.get(1)).isEqualTo("one");
        assertThat(deque.get(2)).isEqualTo("two");
        assertThat(deque.get(-1)).isNull();
        assertThat(deque.get(3)).isNull();
    }

    @Test
    public void getRecursiveTest() {
        Deque61B<String> deque = new LinkedListDeque61B<>();
        deque.addLast("zero");
        deque.addLast("one");
        deque.addLast("two");

        assertThat(deque.getRecursive(0)).isEqualTo("zero");
        assertThat(deque.getRecursive(1)).isEqualTo("one");
        assertThat(deque.getRecursive(2)).isEqualTo("two");
        assertThat(deque.getRecursive(-1)).isNull();
        assertThat(deque.getRecursive(3)).isNull();
    }

    @Test
    public void alternatingAddAndRemoveTest() {
        Deque61B<Integer> deque = new LinkedListDeque61B<>();

        deque.addFirst(2);
        deque.addLast(3);
        deque.addFirst(1);
        assertThat(deque.removeLast()).isEqualTo(3);

        deque.addLast(4);
        assertThat(deque.removeFirst()).isEqualTo(1);

        assertThat(deque.toList()).containsExactly(2, 4).inOrder();
        assertThat(deque.size()).isEqualTo(2);
    }


/** Performs some basic linked list tests. */


     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }
}
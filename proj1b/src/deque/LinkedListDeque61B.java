package deque;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    Node sentinel;
    int size;
    public Iterator<T> iterator() {return new LLDequeIterator();};

    private class LLDequeIterator implements Iterator<T>{
        private int i;
        private Node current;

        public LLDequeIterator(){
            i = 0;
            current = sentinel.next;
        }

        public boolean hasNext(){
            return i < size;
        }

        public T next(){
            T returnItem = current.value;
            current = current.next;
            i += 1;
            return returnItem;
        }
    }

    @Override
    public String toString(){
        List<String> listOfItems = new ArrayList<>();
        for (T x: this){
            listOfItems.add(x.toString());
        }
        return "[" + String.join(", ", listOfItems) + "]";
    }

    public boolean contains(T x){
        for (T y: this){
            if (x == y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }

        if (obj instanceof LinkedListDeque61B oas){
            if (oas.size != this.size){
                return false;
            }

            for (T x: this){
                if (!oas.contains(x)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private class Node{
        T value;
        Node next;
        Node prev;
        public Node(T i){
            value = i;
            next = null;
            prev = null;
        }
        public Node(){
            value = null;
            next = null;
            prev = null;
        }
    }

    public LinkedListDeque61B(){
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node newNode = new Node(x);
        newNode.next = sentinel.next;
        newNode.prev = sentinel;
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node newNode = new Node(x);
        newNode.prev = sentinel.prev;
        newNode.next = sentinel;
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> lst = new ArrayList<>();
        Node current = sentinel.next;
        while (current != sentinel){
            lst.add(current.value);
            current = current.next;
        }
        return lst;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0){
            return null;
        }
        T result = sentinel.next.value;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return result;
    }

    @Override
    public T removeLast() {
        if (size == 0){
            return null;
        }
        T result = sentinel.prev.value;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return result;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0){
            return null;
        }
        int count = 0;
        Node current = sentinel.next;
        while (count != index){
            count++;
            current = current.next;
        }
        return current.value;
    }

    @Override
    public T getRecursive(int index) {
        if (index >= size || index < 0){
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }

    public T getRecursiveHelper(int index, Node node){
        if (index == 0){
            return node.value;
        }
        return getRecursiveHelper(index-1, node.next);
    }
}

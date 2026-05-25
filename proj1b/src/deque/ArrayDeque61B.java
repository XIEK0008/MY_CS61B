package deque;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T>{
    int size;
    int nextFirst;
    int nextLast;
    T[] array;
    int arraysize;
    @Override
    public Iterator<T> iterator(){return new ADeque();}


    public boolean contains(T x){
        for (T y: this){
            if (x == y){
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

        if (obj instanceof ArrayDeque61B oas){
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

    @Override
    public String toString(){
        List<String> listOfItems = new ArrayList<>();
        for (T x: this){
            listOfItems.add(x.toString());
        }
        return "[" + String.join(", ", listOfItems) + "]";
    }

    private class ADeque implements Iterator<T>{
        private int i;

        public ADeque(){i = 0;};

        @Override
        public boolean hasNext(){
            return i < size;
        }

        @Override
        public T next(){
            T returnItem = array[Math.floorMod(nextFirst + i + 1, arraysize)];
            i += 1;
            return returnItem;
        }
    }

    public ArrayDeque61B(){
        arraysize = 8;
        array = (T[]) new Object[arraysize];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    @Override
    public void addFirst(T x){
        if (size == arraysize){
            resizeUp();
        }
        size++;
        array[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst - 1, arraysize);
    }

    @Override
    public void addLast(T x){
        if (size == arraysize){
            resizeUp();
        }
        size++;
        array[nextLast] = x;
        nextLast = Math.floorMod(nextLast + 1, arraysize);
    }

    @Override
    public T get(int i){
        if (i < 0 || i >= size){
            return null;
        }
        int index = Math.floorMod(nextFirst + i + 1, arraysize);
        return array[index];
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public List<T> toList(){
        List<T> returnList = new ArrayList<>();
        for (int i = 1; i <= size; i++){
            int index = Math.floorMod(nextFirst + i, arraysize);
            returnList.add(array[index]);
        }
        return returnList;
    }

    @Override
    public T removeFirst(){
        if (size == 0){
            return null;
        }
        if (arraysize > 16 && size < arraysize / 4){
            resizeDown();
        }
        size--;
        nextFirst = Math.floorMod(nextFirst + 1, arraysize);
        T result = array[nextFirst];
        array[nextFirst] = null;
        return result;
    }

    @Override
    public T removeLast(){
        if (size == 0){
            return null;
        }
        if (arraysize > 16 && size < arraysize / 4){
            resizeDown();
        }
        size--;
        nextLast = Math.floorMod(nextLast - 1, arraysize);
        T result = array[nextLast];
        array[nextLast] = null;
        return result;
    }

    @Override
    public T getRecursive(int index){
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    private void resizeUp(){
        if (nextFirst != Math.floorMod(nextLast - 1, arraysize)){
            return;
        }
        T[] new_array = (T[]) new Object[2 * arraysize];
        for (int i = 0; i < size; i++){
            T element = array[Math.floorMod(nextFirst + i + 1, arraysize)];
            new_array[i] = element;
        }
        array = new_array;
        arraysize = 2 * arraysize;
        nextFirst = arraysize - 1;
        nextLast = size;
    }

    private void resizeDown(){
        if (size <= 16 || size >= arraysize / 4){
            return;
        }
        T[] new_array = (T[]) new Object[arraysize / 2];
        for (int i = 0; i < size; i++){
            T element = array[Math.floorMod(nextFirst + i + 1, arraysize)];
            new_array[i] = element;
        }
        array = new_array;
        arraysize = arraysize / 2;
        nextFirst = arraysize - 1;
        nextLast = size;
    }
}

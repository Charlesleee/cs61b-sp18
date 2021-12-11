public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;


    public ArrayDeque() {
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        items = (T[]) new Object[8];
    }

    private void resize(int length) {
        T[] arr = (T[]) new Object[length];
        int x = (nextFirst + 1) % items.length;
        for (int i = 0; i < size; i++) {
            arr[i] = items[x];
            x = (x + 1) % items.length;
        }
        nextFirst = arr.length - 1;
        nextLast = size;
        items = arr;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst + items.length - 1) % items.length;
        size++;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int x = (nextFirst + 1) % items.length;
        for (int i = 0; i < size; i++) {
            System.out.print(items[x] + " ");
            x = (x + 1) % items.length;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = items[(nextFirst + 1) % items.length];
        items[(nextFirst + 1) % items.length] = null;
        nextFirst = (nextFirst + 1) % items.length;
        size--;
        if (items.length >= 16 && size < (items.length / 4)) {
            resize(items.length / 2);
        }
        return item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = items[(nextLast + items.length - 1) % items.length];
        items[(nextLast + items.length - 1) % items.length] = null;
        nextLast = (nextLast + items.length - 1) % items.length;
        size--;
        if (items.length >= 16 && size < (items.length / 4)) {
            resize(items.length / 2);
        }
        return item;
    }

    public T get(int index) {
        return items[(nextFirst + 1 + index) % items.length];
    }
}

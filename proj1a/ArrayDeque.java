public class ArrayDeque<T> {
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

    public ArrayDeque(ArrayDeque other) {
        size = other.size();
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        System.arraycopy(other.items, 0, items, 0, other.items.length);
    }

    public void resizeH() {
        T[] arr = (T[]) new Object[items.length * 2];
        System.arraycopy(items, 0, arr, 0, size);
        items = arr;
    }

    public void resizeL() {
        T[] arr = (T[]) new Object[items.length / 2];
        System.arraycopy(items, 0, arr, 0, size);
        items = arr;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resizeH();
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst + 7) % items.length;
        size++;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resizeH();
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
        for (int x = (nextFirst + 1) % items.length; x != nextLast; x = (x + 1) % items.length) {
            System.out.print(items[x] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        T item = items[(nextFirst + 1) % items.length];
        nextFirst = (nextFirst + 1) % items.length;
        size--;

        if ((float) size / items.length < 0.25) {
            resizeL();
        }

        return item;
    }

    public T removeLast() {
        T item = items[(nextLast + 7) % items.length];
        nextLast = (nextLast + 7) % items.length;
        size--;

        if ((float) size / items.length < 0.25) {
            resizeL();
        }

        return item;
    }

    public T get(int index) {
        return items[index];
    }
}

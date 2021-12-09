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

//    public ArrayDeque(ArrayDeque other) {
//        size = other.size();
//        nextFirst = other.nextFirst;
//        nextLast = other.nextLast;
//        System.arraycopy(other.items, 0, items, 0, other.items.length);
//    }

    private void resizeH() {
        T[] arr = (T[]) new Object[items.length * 2];
        int x = (nextFirst + 1) % items.length;
        for (int i = 1; i <= size; i++) {
            arr[i] = items[x];
            x = (x + 1) % items.length;
        }
        nextFirst = 0;
        nextLast = size + 1;
        items = arr;
    }

    private void resizeL() {
        T[] arr = (T[]) new Object[items.length / 2];
        int x = (nextFirst + 1) % items.length;
        for (int i = 1; i <= size; i++) {
            arr[i] = items[x];
            x = (x + 1) % items.length;
        }
        nextFirst = 0;
        nextLast = size + 1;
        items = arr;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resizeH();
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst + items.length - 1) % items.length;
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
        int x = (nextFirst + 1) % items.length;
        for (int i = 0; i < size; i++) {
            System.out.print(items[x] + " ");
            x = (x + 1) % items.length;
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
        T item = items[(nextLast + items.length - 1) % items.length];
        nextLast = (nextLast + items.length - 1) % items.length;
        size--;
        if ((float) size / items.length < 0.25) {
            resizeL();
        }
        return item;
    }

    public T get(int index) {
        return items[(nextFirst + 1 + index) % items.length];
    }
}

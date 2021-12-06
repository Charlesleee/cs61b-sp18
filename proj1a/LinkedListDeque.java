public class LinkedListDeque<T> {
    private LinkedListNode sentinel;
    private LinkedListNode last;
    private int size;

    private class LinkedListNode {
        LinkedListNode prev;
        T item;
        LinkedListNode next;

        public LinkedListNode() {
            this.prev = null;
            this.item = null;
            this.next = null;
        }

        public LinkedListNode(LinkedListNode prev, T item, LinkedListNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    public LinkedListDeque() {
        sentinel = new LinkedListNode();
        last = new LinkedListNode();
        sentinel.next = last;
        sentinel.prev = last;
        last.prev = sentinel;
        last.next = sentinel;
        size = 0;
    }

//    public LinkedListDeque(LinkedListDeque other) {
//        sentinel = new LinkedListNode();
//        LinkedListNode cursor = other.sentinel.next;
//        while (cursor != null) {
//            addLast(cursor.item);
//            cursor = cursor.next;
//        }
//    }

    public void addFirst(T item) {
        sentinel.next = new LinkedListNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    public void addLast(T item) {
        last.prev = new LinkedListNode(last.prev, item, last);
        last.prev.prev.next = last.prev;
        size++;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        LinkedListNode node = sentinel.next;

        while (node.next != sentinel) {
            System.out.print(node.item + " ");
            node = node.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        LinkedListNode first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        size--;
        return first.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        LinkedListNode end = last.prev;
        last.prev = end.prev;
        end.prev.next = last;
        size--;
        return end.item;
    }

    public T get(int index) {
        if (index + 1 > size) {
            return null;
        }
        int i = 0;
        LinkedListNode node = sentinel.next;
        while (i < index) {
            node = node.next;
            i++;
        }
        return node.item;
    }

    public T getRecursive(int index) {
        if (index + 1 > size) {
            return null;
        } else {
            return getR(index, sentinel.next);
        }
    }

    private T getR(int index, LinkedListNode node) {
        if (index == 0) {
            return node.item;
        } else {
            return getR(index - 1, node.next);
        }
    }

}

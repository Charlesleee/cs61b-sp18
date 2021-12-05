public class SLList {

    private class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }

    private IntNode first;

    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    public void insert(int item, int position) {
        if (position == 0) {
            first = new IntNode(item, first);
        } else {
            IntNode cursor = first;
            int x = 0;
            while (x < position - 1 && cursor.next != null) {
                cursor = cursor.next;
            }
            cursor.next = new IntNode(item, cursor.next);
        }
    }

    public void reverse() {
        IntNode prev = null;
        IntNode succ = first.next;
        while (succ != null) {
            first.next = prev;
            prev = first;
            first = succ;
            succ = succ.next;
        }
        first.next = prev;
    }

    public void reverseR() {
        first = helperReverse(first);
    }

    public IntNode helperReverse(IntNode front) {
        if (front == null || front.next == null) {
            return front;
        } else {
            IntNode reversed = helperReverse(front.next);
            front.next.next = front;
            front.next = null;
            return reversed;
        }
    }

    public static void main(String[] args) {
        SLList slList = new SLList();
        slList.addFirst(1);
        slList.addFirst(2);
        slList.addFirst(3);
        slList.addFirst(4);
        slList.addFirst(5);
        slList.reverseR();
    }
}


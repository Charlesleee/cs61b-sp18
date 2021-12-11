public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    private boolean isPalindrome1(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        int left = 0;
        int right = word.length() - 1;
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left += 1;
            right -= 1;
        }
        return true;
    }

    public boolean isPalindrome(String word) {
        return helper(wordToDeque(word));
    }

    private boolean helper(Deque<Character> rest) {
        if (rest.size() <= 1) {
            return true;
        }
        if (rest.removeFirst() != rest.removeLast()) {
            return false;
        }
        return helper(rest);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return helper(wordToDeque(word), cc);
    }

    private boolean helper(Deque<Character> rest, CharacterComparator cc) {
        if (rest.size() <= 1) {
            return true;
        }
        if (!cc.equalChars(rest.removeFirst(), rest.removeLast())) {
            return false;
        }
        return helper(rest, cc);
    }
}

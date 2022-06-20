import 'dart:ffi';

class PalindromeIndex {
  String _solution(String input) {
    if (isPalindrome(input)) {
      return 'Already a palindrome';
    }
    for (var i = 0; i < input.length; i++) {
      final currentString =
          input.substring(0, i) + input.substring(i + 1, input.length);
      if (isPalindrome(currentString)) {
        return 'Index to be removed: $i';
      }
    }
    return 'No solution';
  }

  bool isPalindrome(String string) {
    for (var i = 0, j = string.length - 1; i < j; i++, j--) {
      if (string[i] != string[j]) return false;
    }
    return true;
  }

  void start() {
    print(_solution('aab'));
  }
}

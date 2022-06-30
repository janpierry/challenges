import 'dart:convert';

class CaesarCipher {
  String _solution(String input, int k) {
    final encodedInput = ascii.encode(input);
    final List<int> encodedResult = [];
    encodedInput.forEach((element) {
      if (element >= 65 && element <= 90) {
        final encrypted = ((element - 65 + k) % 26) + 65;
        encodedResult.add(encrypted);
      } else if (element >= 97 && element <= 122) {
        final encrypted = ((element - 97 + k) % 26) + 97;
        encodedResult.add(encrypted);
      } else {
        encodedResult.add(element);
      }
    });
    final result = ascii.decode(encodedResult);
    return result;
  }

  void start() {
    final result = _solution('Always-Look-on-the-Bright-Side-of-Life', 53);
    print(result);
  }
}

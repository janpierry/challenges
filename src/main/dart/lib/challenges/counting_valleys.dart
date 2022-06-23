class CountingValleys {
  int _solution(String path) {
    int altitude = 0;
    int valleys = 0;
    path.runes.forEach((element) {
      String char = String.fromCharCode(element);
      if (char == 'D') {
        altitude--;
      } else {
        altitude++;
        if (altitude == 0) {
          valleys++;
        }
      }
    });
    return valleys;
  }

  void start() {
    int result = _solution("DDUUDDUDUUUD");
    print(result);
  }
}

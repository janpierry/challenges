class DrawingBook {
  int _solution(int n, int p) {
    int fromStart = (p / 2).floor();
    int fromEnd = (n / 2).floor() - fromStart;
    return fromStart < fromEnd ? fromStart : fromEnd;
  }

  void start() {
    final result = _solution(6, 4);
    print(result);
  }
}

/*
[x,1] [2, 3] [4, 5] [6, 7]
*/
class ElementsCombination {
  void _solution(List<int> elements) {
    final List<List<int>> result = [[]];
    elements.forEach((element) {
      final List<List<int>> currentCombinations = [];
      result.forEach((resultElement) {
        final List<int> currentCombination = [];
        currentCombination.addAll(resultElement);
        currentCombination.add(element);
        currentCombinations.add(currentCombination);
      });
      result.addAll(currentCombinations);
    });
    print(result);
  }

  void start() {
    const elements = [1, 2, 3, 4];
    _solution(elements);
  }
}

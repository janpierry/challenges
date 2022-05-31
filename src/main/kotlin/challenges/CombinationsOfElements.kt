package challenges

class CombinationsOfElements {

    private fun calculate(elements: List<Int>) {
        val result = mutableListOf<List<Int>>()
        result.add(listOf())
        for (element in elements) {
            val currentElementCombinations = mutableListOf<List<Int>>()
            for (resultItem in result) {
                val currentCombination = mutableListOf<Int>()
                currentCombination.addAll(resultItem)
                currentCombination.add(element)
                currentElementCombinations.add(currentCombination)
            }
            result.addAll(currentElementCombinations)
        }
        println(result)
    } // Time complexity: O(2^n) / Space complexity: O(2^n). Very bad complexity. I don't know how to solve it right now

    fun start() {
        val elements = listOf(1, 2, 3, 4)
        calculate(elements)
    }
}
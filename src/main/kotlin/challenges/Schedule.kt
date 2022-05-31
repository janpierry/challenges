package challenges

class Schedule {
    private fun calculate(
        firstSchedules: List<List<String>>,
        secondSchedules: List<List<String>>,
        firstRange: List<String>,
        secondRange: List<String>
    ) {
        // Convert to time
        val firstIntSchedule = toIntTimeSchedules(firstSchedules)
        val secondIntSchedule = toIntTimeSchedules(secondSchedules)
        val firstIntRange = toIntTimeSchedule(firstRange)
        val secondIntRange = toIntTimeSchedule(secondRange)

        // Join schedules
        val joinedSchedules = joinSchedules(firstIntSchedule, secondIntSchedule)

        // Merge ranges
        val mergedRanges = mergeRanges(firstIntRange, secondIntRange)
        if (mergedRanges == null) {
            println("Ranges don't match")
            return
        }

        // Find free time
        val freeTime = findFreeTime(joinedSchedules, mergedRanges)

        // Limit free time by range
        val limitedFreeTime = limitFreeTime(freeTime, mergedRanges)

        // Convert back to String
        val stringLimitedFreeTime = toStringTimeSchedules(limitedFreeTime)
        println(stringLimitedFreeTime)
    } // Time complexity: O(n+m) / Space complexity: O(n+m)

    private fun toIntTimeSchedules(schedules: List<List<String>>): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        for (schedule in schedules) {
            result.add(toIntTimeSchedule(schedule))
        }
        return result
    } // Time complexity: O(n) / Space complexity: O(n)

    private fun toIntTimeSchedule(schedule: List<String>): List<Int> {
        return listOf(toIntTime(schedule[0]), toIntTime(schedule[1]))
    } // Time complexity: O(1) / Space complexity: O(1)

    private fun toIntTime(value: String): Int {
        val splittedValue = value.split(':')
        return splittedValue[0].toInt() * 60 + splittedValue[1].toInt()
    } // Time complexity: O(1) / Space complexity: O(1)

    private fun joinSchedules(
        firstSchedule: List<List<Int>>,
        secondSchedule: List<List<Int>>
    ): List<List<Int>> {
        val result: MutableList<List<Int>> = mutableListOf()
        var firstIndex = 0
        var secondIndex = 0
        while (firstIndex < firstSchedule.size && secondIndex < secondSchedule.size) {
            val firstScdRange = firstSchedule[firstIndex]
            val secondScdRange = secondSchedule[secondIndex]
            if (firstScdRange[0] < secondScdRange[0]) {
                result.add(firstScdRange)
                firstIndex++
            } else {
                result.add(secondScdRange)
                secondIndex++
            }
        }
        // There is some other way to solve this, merging this logic in the while from above, but I believe this way is easier to visualize
        while (firstIndex < firstSchedule.size) {
            result.add(firstSchedule[firstIndex])
            firstIndex++
        }
        while (secondIndex < secondSchedule.size) {
            result.add(secondSchedule[secondIndex])
            secondIndex++
        }
        return result
    } // Time complexity: O(n+m) / Space complexity: O(n+m)

    private fun mergeRanges(firstRange: List<Int>, secondRange: List<Int>): List<Int>? {
        if (firstRange[0] > secondRange[1] || secondRange[0] > firstRange[1]) {
            return null
        }
        val first = if (firstRange[0] > secondRange[0]) firstRange[0] else secondRange[0]
        val second = if (firstRange[1] < secondRange[1]) firstRange[1] else secondRange[1]
        return listOf(first, second)
    } // Time complexity: O(1) / Space complexity: O(1)

    private fun findFreeTime(schedules: List<List<Int>>, availabilityRange: List<Int>): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        var latestEnd = 0
        val earlierStart = schedules[0][0]
        val freeTimeInStart = findFreeTimeInStart(earlierStart, availabilityRange[0])
        if (freeTimeInStart != null) {
            result.add(freeTimeInStart)
        }
        for(i in 0 until schedules.size - 1) {
            if (schedules[i][1] > latestEnd) {
                latestEnd = schedules[i][1]
            }
            val nextStart = schedules[i+1][0]
            if (latestEnd < nextStart && nextStart - latestEnd >= 30) {
                result.add(listOf(latestEnd, nextStart))
            }
        }
        if (schedules.last()[1] > latestEnd) latestEnd = schedules.last()[1]
        val freeTimeInEnd = findFreeTimeInEnd(latestEnd, availabilityRange[1])
        if (freeTimeInEnd != null) {
            result.add(freeTimeInEnd)
        }
        return result
    } // Time complexity: O(n) / Space complexity: O(n)

    private fun findFreeTimeInStart(earlierStart: Int, availabilityStart: Int): List<Int>? {
        if (earlierStart > availabilityStart) {
            return listOf(availabilityStart, earlierStart)
        }
        return null
    } // Time complexity: O(1) / Space complexity: O(1)

    private fun findFreeTimeInEnd(latestEnd: Int, availabilityEnd: Int): List<Int>? {
        if (latestEnd < availabilityEnd) {
            return listOf(latestEnd, availabilityEnd)
        }
        return null
    } // Time complexity: O(1) / Space complexity: O(1)

    private fun limitFreeTime(freeTime: List<List<Int>>, availabilityRange: List<Int>): List<List<Int>> {
        val result: MutableList<List<Int>> = mutableListOf()
        val availabilityStart = availabilityRange[0]
        val availabilityEnd = availabilityRange[1]
        for (range in freeTime) {
            var currentEnd = range[1]
            var currentStart = range[0]
            if (currentEnd > availabilityStart && currentStart < availabilityEnd) {
                if (availabilityStart > currentStart) {
                    currentStart = availabilityStart
                }
                if (availabilityEnd < currentEnd) {
                    currentEnd = availabilityEnd
                }
                if (currentEnd - currentStart >= 30) {
                    result.add(listOf(currentStart, currentEnd))
                }
            }
        }
        return result
    } // Time complexity: O(n) / Space complexity: O(n)

    private fun toStringTimeSchedules(schedules: List<List<Int>>): List<List<String>> {
        val result = mutableListOf<List<String>>()
        for (schedule in schedules) {
            result.add(toStringTimeSchedule(schedule))
        }
        return result
    } // Time complexity: O(n) / Space complexity: O(n)

    private fun toStringTimeSchedule(schedule: List<Int>): List<String> {
        return listOf(toStringTime(schedule[0]), toStringTime(schedule[1]))
    } // Time complexity: O(1) / Space complexity: O(1)

    private fun toStringTime(value: Int): String {
        var hour = (value / 60).toString()
        var minutes = (value % 60).toString()
        if (hour.length < 2) hour = "0${hour}"
        if (minutes.length < 2) minutes = "0${minutes}"
        return "${hour}:${minutes}"
    } // Time complexity: O(1) / Space complexity: O(1)

    fun start() {
        val firstSchedules = listOf(listOf("09:00", "10:30"), listOf("12:00", "13:00"), listOf("16:00", "18:00"))
        val secondSchedules = listOf(
            listOf("10:00", "11:30"),
            listOf("12:30", "14:30"),
            listOf("14:30", "15:00"),
            listOf("16:00", "17:00")
        )
        val firstRange = listOf("09:00", "20:00")
        val secondRange = listOf("10:00", "18:30")
        calculate(firstSchedules, secondSchedules, firstRange, secondRange)
    }
}
/*
[09:00, 10:30] [12:00, 13:00] [16:00, 18:00]
[09:00, 20:00]
[10:00, 11:30] [12:30, 14:30] [14:30, 15:00] [16:00, 17:00]
[10:00, 18:30]
 */
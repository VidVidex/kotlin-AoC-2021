fun main() {
    fun part1(input: List<Int>): Int {
        var oldValue = -1
        var numberOfIncreases = -1
        for (value in input) {
            if (value > oldValue)
                numberOfIncreases++
            oldValue = value;
        }

        return numberOfIncreases
    }

    fun part2(input: List<Int>): Int {
        val WINDOW_SIZE = 3

        var numberOfIncreases = -1

        val window = ArrayList<Int>()
        val prevWindow = ArrayList<Int>()

        for (number in input) {

            window.add(number)

            if (window.size > WINDOW_SIZE)
                window.removeFirst()

            if (window.size == WINDOW_SIZE && window.sum() > prevWindow.sum())
                numberOfIncreases++

            prevWindow.add(number)
            if (prevWindow.size > WINDOW_SIZE)
                prevWindow.removeFirst()
        }

        return numberOfIncreases
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputToIntArr("Day01_test.txt")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInputToIntArr("Day01.txt")
    println(part1(input))
    println(part2(input))
}

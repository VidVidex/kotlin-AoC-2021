fun main() {
    fun part1(input: List<String>, days: Int): Long {

        val fish = readInputToIntArray(input[0].split(",")).toMutableList()

        val population = LongArray(9) { 0 }

        for(f in fish)
            population[f]++

        for (day in 0 until days) {

            val temp = population[0]
            for (i in 0 until 9) {
                when {
                    i != 6 && i != 8 -> population[i] = population[i + 1]
                    i == 6 -> population[i] = population[i + 1] + temp
                    i == 8 -> population[i] = temp
                }
            }
        }

        var sum = 0L
        for (n in population)
            sum += n

        return sum
    }

    // Test if implementation meets criteria from the description
    val testInput = readInputToStringArray("Day06_test.txt")
    check(part1(testInput, 80) == 5934L)
    check(part1(testInput, 256) == 26984457539L)

    val input = readInputToStringArray("Day06_input.txt")
    println("Solution to part 1: " + part1(input, 80))
    println("Solution to part 2: " + part1(input, 256))
}

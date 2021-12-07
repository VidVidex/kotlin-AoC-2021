import kotlin.math.abs

fun main() {
    fun part1(input: List<Int>): Int {

        val min = input.minOrNull()!!
        val max = input.maxOrNull()!!

        var minUsage = -1

        for (pos in min..max) {
            var usage = 0
            for (crab in input)
                usage += abs(crab - pos)

            if(minUsage == -1 || usage < minUsage)
                minUsage = usage
        }

        return minUsage
    }

    fun part2FuelCost(distance:Int): Int{
        var cost = 0

        for(i in 1..distance)
            cost +=i

        return cost
    }

    fun part2(input: List<Int>): Int {

        val min = input.minOrNull()!!
        val max = input.maxOrNull()!!

        var minUsage = -1

        for (pos in min..max) {
            var usage = 0
            for (crab in input)
                usage += part2FuelCost(abs(crab - pos))

            if(minUsage == -1 || usage < minUsage)
                minUsage = usage
        }

        return minUsage
    }

    // Test if implementation meets criteria from the description
    val testInput = stringArrayToIntArray(readInput("Day07_test.txt")[0].split(",")).toMutableList()
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = stringArrayToIntArray(readInput("Day07_input.txt")[0].split(",")).toMutableList()
    println("Solution to part 1: " + part1(input))
    println("Solution to part 2: " + part2(input))
}

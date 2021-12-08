fun main() {
    fun part1(input: List<String>): Int {

        var count = 0
        for (line in input) {
            val entry = Entry(line)

            for (digit in entry.output)
                if (digit.length == 2 || digit.length == 4 || digit.length == 3 || digit.length == 7)
                    count++;
        }
        return count
    }

    fun part2(input: List<String>): Int {

        var count = 0
        for (line in input)
            count += Entry(line).decode()

        return count
    }

    // Test if implementation meets criteria from the description
    val testInput = readInput("Day08_test.txt")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("Day08_input.txt")
    println("Solution to part 1: " + part1(input))
    println("Solution to part 2: " + part2(input))
}

class Entry(line: String) {
    private var patterns = listOf<String>()
    var output = listOf<String>()
    private val segments = Array(7) { "" }

    init {
        this.patterns = line.split(" | ")[0].split(" ")
        this.output = line.split(" | ")[1].split(" ")
    }

    fun decode(): Int {

        assignSymbols()

        determineUniqueMapping()

        return decodeOutput()
    }

    private fun decodeOutput(): Int {
        var number = 0
        for (code in output) {
            when {
                code.length == 2 -> number = number * 10 + 1
                code.length == 3 -> number = number * 10 + 7
                code.length == 4 -> number = number * 10 + 4
                code.length == 7 -> number = number * 10 + 8
                code.length == 6 && !code.contains(segments[6]) -> number = number * 10 + 0
                code.length == 6 && !code.contains(segments[4]) -> number = number * 10 + 9
                code.length == 6 && !code.contains(segments[1]) -> number = number * 10 + 6
                code.length == 5 && code.contains(segments[1]) && code.contains(segments[2]) -> number = number * 10 + 3
                code.length == 5 && code.contains(segments[1]) && code.contains(segments[4]) -> number = number * 10 + 2
                code.length == 5 && code.contains(segments[5]) && code.contains(segments[2]) -> number = number * 10 + 5
            }
        }
        return number
    }

    private fun determineUniqueMapping() {
        val six = patterns.first { it.length == 6 && it.contains(segments[1][0]).xor(it.contains(segments[1][1])) }
        val cell1 = if (six.contains(segments[1][0])) segments[1][1] else segments[1][0]
        val cell2 = if (six.contains(segments[1][0])) segments[1][0] else segments[1][1]
        segments[1] = cell1.toString()
        segments[2] = cell2.toString()

        val nine = patterns.first { it.length == 6 && it.contains(segments[3][0]).xor(it.contains(segments[3][1])) }
        val cell3 = if (nine.contains(segments[3][0])) segments[3][0] else segments[3][1]
        val cell4 = if (nine.contains(segments[3][0])) segments[3][1] else segments[3][0]
        segments[3] = cell3.toString()
        segments[4] = cell4.toString()

        val zero = patterns.first { it.length == 6 && it.contains(segments[5][0]).xor(it.contains(segments[5][1])) }
        val cell5 = if (zero.contains(segments[5][0])) segments[5][0] else segments[5][1]
        val cell6 = if (zero.contains(segments[5][0])) segments[5][1] else segments[5][0]
        segments[5] = cell5.toString()
        segments[6] = cell6.toString()
    }

    private fun assignSymbols() {
        val one = patterns.first { it.length == 2 }
        segments[1] = one
        segments[2] = one

        val seven = patterns.first { it.length == 3 }
        for (char in seven)
            if (char !in one)
                segments[0] = char.toString()

        val four = patterns.first { it.length == 4 }
        for (char in four)
            if (char !in one) {
                segments[5] = segments[5] + char
                segments[6] = segments[6] + char
            }

        for (letter in "abcdefg") {
            if (letter !in one && letter !in seven && letter !in four) {
                segments[3] = segments[3] + letter
                segments[4] = segments[4] + letter
            }
        }
    }
}

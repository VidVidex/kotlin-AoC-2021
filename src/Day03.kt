fun main() {

    fun countOccurrences(list: List<String>, index: Int, char: Char): Int {
        var charCount = 0

        for (value in list)
            if (value[index] == char)
                charCount++

        return charCount
    }

    fun part1(input: List<String>): Int {

        var gamma = 0
        var epsilon = 0

        for (i in 0 until input[0].length) {

            if (countOccurrences(input, i, '1') > input.size / 2) {
                gamma = gamma * 2 + 1
                epsilon *= 2
            } else {
                gamma *= 2
                epsilon = epsilon * 2 + 1
            }
        }

        return gamma * epsilon
    }

    fun part2(input: List<String>): Int {

        var oxygenList = input.toList()
        var co2List = input.toList()

        var oxygen = 0
        var co2 = 0

        for (i in 0 until oxygenList[0].length) {

            var occurrences = countOccurrences(oxygenList, i, '1')
            val oxygenKeep = if (occurrences >= oxygenList.size - occurrences) '1' else '0'
            val oxygenNew = ArrayList<String>()
            for (number in oxygenList)
                if (number[i] == oxygenKeep)
                    oxygenNew.add(number)
            oxygenList = oxygenNew

            if (oxygenList.size == 1)
                oxygen = Integer.parseInt(oxygenList[0], 2)

            occurrences = countOccurrences(co2List, i, '0')
            val co2Keep = if (occurrences > co2List.size - occurrences) '1' else '0'
            val co2New = ArrayList<String>()
            for (number in co2List)
                if (number[i] == co2Keep)
                    co2New.add(number)
            co2List = co2New

            if (co2List.size == 1)
                co2 = Integer.parseInt(co2List[0], 2)

        }

        return oxygen * co2
    }

    // Test if implementation meets criteria from the description
    val testInput = readInput("Day03_test.txt")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03_input.txt")
    println("Solution to part 1: " + part1(input))
    println("Solution to part 2: " + part2(input))
}

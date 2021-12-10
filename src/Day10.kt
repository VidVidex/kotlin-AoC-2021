import java.util.*

fun main() {
    fun part1(input: List<String>): Int {

        var score = 0

        for (line in input) {
            val stack = Stack<Char>()

            for (char in line) {
                when (char) {
                    '(', '[', '{', '<' -> stack.add(char)
                    ')' -> {
                        if (stack.peek() == '(')
                            stack.pop()
                        else {
                            score += 3
                            break
                        }
                    }
                    ']' -> {
                        if (stack.peek() == '[')
                            stack.pop()
                        else {
                            score += 57
                            break
                        }
                    }
                    '}' -> {
                        if (stack.peek() == '{')
                            stack.pop()
                        else {
                            score += 1197
                            break
                        }
                    }
                    '>' -> {
                        if (stack.peek() == '<')
                            stack.pop()
                        else {
                            score += 25137
                            break
                        }
                    }
                }
            }
        }
        return score
    }

    fun part2(input: List<String>): Long {

        val scores = mutableListOf<Long>()

        for (line in input) {
            val stack = Stack<Char>()

            var score = 0L
            var lineCorrupted = false

            for (char in line) {
                when (char) {
                    '(', '[', '{', '<' -> stack.add(char)
                    ')' -> {
                        if (stack.peek() == '(')
                            stack.pop()
                        else {
                            lineCorrupted = true
                            break
                        }
                    }
                    ']' -> {
                        if (stack.peek() == '[')
                            stack.pop()
                        else {
                            lineCorrupted = true
                            break
                        }
                    }
                    '}' -> {
                        if (stack.peek() == '{')
                            stack.pop()
                        else {
                            lineCorrupted = true
                            break
                        }
                    }
                    '>' -> {
                        if (stack.peek() == '<')
                            stack.pop()
                        else {
                            lineCorrupted = true
                            break
                        }
                    }
                }
            }

            if (!lineCorrupted) {
                while (!stack.isEmpty()) {
                    when (stack.pop()) {
                        '(' -> score = score * 5 + 1
                        '[' -> score = score * 5 + 2
                        '{' -> score = score * 5 + 3
                        '<' -> score = score * 5 + 4
                    }
                }
                scores.add(score)
            }
        }
        scores.sort()
        return scores[scores.size / 2]
    }

    // Test if implementation meets criteria from the description
    val testInput = readInput("Day10_test.txt")
    check(part1(testInput) == 26397)
    check(part2(testInput) == 288957L)

    val input = readInput("Day10_input.txt")
    println("Solution to part 1: " + part1(input))
    println("Solution to part 2: " + part2(input))
}

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", name).readLines()

/**
 * Converts list strings to list of ints
 */
fun stringArrayToIntArray(list: List<String>): List<Int> {
    val intInput = ArrayList<Int>()

    for (value in list)
        intInput.add(value.toInt())

    return intInput
}

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

/**
 * Converts a list of strings containing numbers to a grid of numbers
 */
fun createGrid(input: List<String>): Array<IntArray> {

    val grid = Array(input.size) { IntArray(input[0].length) }

    for (i in input.indices)
        for (j in input[i].indices)
            grid[i][j] = input[i][j] - '0'

    return grid
}
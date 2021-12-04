import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInputToStringArray(name: String) = File("src", name).readLines()

fun readInputToIntArray(name: String): List<Int> {
    val intInput = ArrayList<Int>()

    for (value in readInputToStringArray(name))
        intInput.add(value.toInt())

    return intInput
}


/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

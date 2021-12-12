fun main() {

    fun part1(graph: Graph): Int {
        return graph.countPaths(true)
    }

    fun part2(graph: Graph): Int {
        return graph.countPaths(false)
    }

    // Test if implementation meets criteria from the description
    val testInput1 = Graph(readInput("Day12_test1.txt"))
    check(part1(testInput1) == 10)
    check(part2(testInput1) == 36)

    val testInput2 = Graph(readInput("Day12_test2.txt"))
    check(part1(testInput2) == 19)
    check(part2(testInput2) == 103)

    val testInput3 = Graph(readInput("Day12_test3.txt"))
    check(part1(testInput3) == 226)
    check(part2(testInput3) == 3509)

    val input = Graph(readInput("Day12_input.txt"))
    println("Solution to part 1: " + part1(input))
    println("Solution to part 2: " + part2(input))
}

class Graph(edges: List<String>) {
    private val nodes = HashMap<String, Node>()

    init {
        for (edge in edges) {
            val edgeParts = edge.split("-")
            val name1 = edgeParts[0]
            val name2 = edgeParts[1]

            if (!nodes.containsKey(name1))
                nodes[name1] = Node(name1)

            if (!nodes.containsKey(name2))
                nodes[name2] = Node(name2)

            val node1 = nodes[name1]!!
            val node2 = nodes[name2]!!

            node1.addNeighbor(nodes[edgeParts[1]]!!)
            node2.addNeighbor(nodes[edgeParts[0]]!!)
        }
    }

    private fun start(): Node {
        return nodes["start"]!!
    }

    private fun end(): Node {
        return nodes["end"]!!
    }

    fun countPaths(part1: Boolean): Int {
        return countPaths(start(), listOf(), part1)
    }

    private fun countPaths(node: Node, path: List<Node>, part1: Boolean): Int {

        if (node == end())
            return 1

        var newPart1 = part1

        if (!node.big && node in path) {

            if (node == start() || part1)
                return 0

            newPart1 = true
        }

        val newPath = path + node

        var paths = 0
        for (n in node.neighbors)
            paths += countPaths(n, newPath, newPart1)

        return paths
    }
}

class Node(private var name: String) {
    val big: Boolean = name.isUpperCase()
    val neighbors = mutableListOf<Node>()

    fun addNeighbor(node: Node) {
        this.neighbors.add(node)
    }

    override fun toString(): String {
        return name
    }
}

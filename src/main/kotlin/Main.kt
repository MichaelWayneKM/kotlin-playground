fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    //println("Program arguments: ${args.joinToString()}")

    test {
        "This is a sample of a weird function call"
    }
}

fun test(a: () -> Unit) {
    print(a)
}
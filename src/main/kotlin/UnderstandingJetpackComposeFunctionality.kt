fun main() {

    /**
     * How functions work with
     */
    OurTestBaseTheme {
        OurTestComposable()
    }


}


fun OurTestBaseTheme(composable: () -> String) {
    print(composable())
}
fun OurTestComposable(): String {
    return "I am a composable!"
}
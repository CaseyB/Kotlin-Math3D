package productions.moo.kotlin

data class Color (var red: Float = 0f, var green: Float = 0f, var blue: Float = 0f, var alpha: Float = 1f)
{
    companion object
    {
        val RED = Color(1f, 0f, 0f, 1f)
        val GREEN = Color(0f, 1f, 0f, 1f)
        val BLUE = Color(0f, 0f, 1f, 1f)

        val WHITE = Color(1f, 1f, 1f, 1f)
        val BLACK = Color(0f, 0f, 0f, 1f)

        val CORNFLOWER_BLUE = Color(0.392f, 0.5843f, 0.9294f, 1.0f)
    }
}
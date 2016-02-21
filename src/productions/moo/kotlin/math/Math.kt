@file:JvmName("Math")

package productions.moo.kotlin.math

fun Float.clamp (min: Float, max: Float) = Math.min(Math.max(this, min), max)

class Angle(radians: Float? = null, degrees: Float? = null)
{
    var radians: Float = 0f
        get() = field
        set(radians) { field = radians }

    var degrees: Float
        get() = radians * RADIANS_TO_DEGREES
        set(degrees) { radians = DEGREES_TO_RADIANS }

    init {
        when
        {
            radians != null -> this.radians = radians
            degrees != null -> this.radians = degrees * (Math.PI / 180).toFloat()
            else -> this.radians = 0f
        }
    }

    companion object
    {
        val DEGREES_TO_RADIANS = (Math.PI / 180.0).toFloat()
        val RADIANS_TO_DEGREES = (180.0 / Math.PI).toFloat()
    }
}

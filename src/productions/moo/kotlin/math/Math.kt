package productions.moo.kotlin.math

/**
 * Clamps the value between min and max
 *
 * @return Returns a Float whose value is determined as follows:
 *   * if the value is less than min, min is returned
 *   * if the value is greater then max, max is returned
 *   * otherwise the value is unchanged
 */
fun Float.clamp(min: Float, max: Float) = Math.min(Math.max(this, min), max)

/**
 * Represents an angle
 *
 * Handles conversion between radians and degrees
 *
 * @param radians Optional value of the angle in radians
 * @param degrees Optional value of the angle in degrees
 *
 * @constructor Takes the measure of the angle in either radians or degrees. If neither
 * value is supplied the angle will be initialized to 0
 *
 * @property radians Get or set the value of the angle in radians
 * @property degrees Get or set the value of the angle in degrees
 */
class Angle(radians: Float? = null, degrees: Float? = null)
{
	var radians: Float = 0f
		get() = field
		set(radians)
		{
			field = radians
		}

	var degrees: Float
		get() = radians * RADIANS_TO_DEGREES
		set(degrees)
		{
			radians = DEGREES_TO_RADIANS
		}

	init
	{
		when
		{
			radians != null -> this.radians = radians
			degrees != null -> this.radians = degrees * (Math.PI / 180).toFloat()
			else            -> this.radians = 0f
		}
	}

	companion object
	{
		val DEGREES_TO_RADIANS = (Math.PI / 180.0).toFloat()
		val RADIANS_TO_DEGREES = (180.0 / Math.PI).toFloat()
	}
}

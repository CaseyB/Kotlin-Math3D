@file:JvmName("Vector")

package productions.moo.kotlin.math3d

import productions.moo.kotlin.math.Angle
import productions.moo.kotlin.math.clamp
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

// region Float extensions
// Override operators to work with Vector3
operator fun Float.times (vector: Vector3) = Vector3(this * vector.buffer.get(0), this * vector.buffer.get(1), this * vector.buffer.get(2))
operator fun Float.div (vector: Vector3) = Vector3(this / vector.buffer.get(0), this / vector.buffer.get(1), this / vector.buffer.get(2))

// Override operators to work with Vector2
operator fun Float.times (vector: Vector2) = Vector2(this * vector.buffer.get(0), this * vector.buffer.get(1))
operator fun Float.div (vector: Vector2) = Vector2(this / vector.buffer.get(0), this / vector.buffer.get(1))
// endregion

// region Vector3
class Vector3
{
    internal val buffer: FloatBuffer = ByteBuffer.allocateDirect(3 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer()

    constructor()
    {
        buffer.put(floatArrayOf(0f, 0f, 0f))
        buffer.rewind()
    }

    constructor(x: Float, y: Float, z: Float)
    {
        buffer.put(floatArrayOf(x, y, z))
        buffer.rewind()
    }
    
    var x: Float
        get() { return buffer.get(0) }
        set(value) { buffer.put(0, value) }

    var y: Float
        get() { return buffer.get(1) }
        set(value) { buffer.put(1, value) }

    var z: Float
        get() { return buffer.get(2) }
        set(value) { buffer.put(2, value) }

    // Artihmetic operations with other Vector3s
    operator fun plus  (other: Vector3) = Vector3(buffer.get(0) + other.buffer.get(0), buffer.get(1) + other.buffer.get(1), buffer.get(2) + other.buffer.get(2))
    operator fun minus (other: Vector3) = Vector3(buffer.get(0) - other.buffer.get(0), buffer.get(1) - other.buffer.get(1), buffer.get(2) - other.buffer.get(2))
    operator fun times (other: Vector3) = Vector3(buffer.get(0) * other.buffer.get(0), buffer.get(1) * other.buffer.get(1), buffer.get(2) * other.buffer.get(2))
    operator fun div   (other: Vector3) = Vector3(buffer.get(0) / other.buffer.get(0), buffer.get(1) / other.buffer.get(1), buffer.get(2) / other.buffer.get(2))

    // Unary operations
    operator fun unaryPlus  () = this
    operator fun unaryMinus () = Vector3(-buffer.get(0), -buffer.get(1), -buffer.get(2))

    // Artihmetic operations with a float scalar
    operator fun times (scalar: Float) = Vector3(buffer.get(0) * scalar, buffer.get(1) * scalar, buffer.get(2) * scalar)
    operator fun div   (scalar: Float) = Vector3(buffer.get(0) / scalar, buffer.get(1) / scalar, buffer.get(2) / scalar)

    val length: Float
        get() = Math.sqrt((lengthSquared).toDouble()).toFloat()

    val lengthSquared: Float
        get() = buffer.get(0) * buffer.get(0) + buffer.get(1) * buffer.get(1) + buffer.get(2) * buffer.get(2)

    fun distanceFrom (other: Vector3) = (this - other).length
    fun distanceSquaredFrom (other: Vector3) = (this - other).lengthSquared

    fun normalize (): Float
    {
        val oldLength = length

        if (oldLength > 0)
        {
            val lengthInverse = 1.0f / oldLength
            buffer.put(0, buffer.get(0) * lengthInverse)
            buffer.put(1, buffer.get(1) * lengthInverse)
            buffer.put(2, buffer.get(2) * lengthInverse)
        }

        return oldLength
    }

    infix fun dot (other: Vector3) = buffer.get(0) * other.buffer.get(0) + buffer.get(1) * other.buffer.get(1) + buffer.get(2) * other.buffer.get(2)
    infix fun cross (other: Vector3): Vector3
    {
        return Vector3(
                buffer.get(1) * other.buffer.get(2) - buffer.get(2) * other.buffer.get(1),
                buffer.get(2) * other.buffer.get(0) - buffer.get(0) * other.buffer.get(2),
                buffer.get(0) * other.buffer.get(1) - buffer.get(1) * other.buffer.get(0))
    }

    fun angleTo (other: Vector3): Angle
    {
        val lenProduct = length * other.length
        val f = ((this dot other) / lenProduct).clamp(-1f, 1f)
        return Angle(radians = Math.acos(f.toDouble()).toFloat())
    }

    companion object
    {
        fun distanceFrom (vectors: Pair<Vector3, Vector3>) = vectors.first.distanceFrom(vectors.second)
        fun distanceSquaredFrom (vectors: Pair<Vector3, Vector3>) = vectors.first.distanceSquaredFrom(vectors.second)
        fun angleFrom (vectors: Pair<Vector3, Vector3>) = vectors.first.angleTo(vectors.second)
    }
}
// endregion

// region Vector2
class Vector2
{
	internal val buffer: FloatBuffer = ByteBuffer.allocateDirect(2 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer()

	constructor()
	{
		buffer.put(floatArrayOf(0f, 0f))
		buffer.rewind()
	}

	constructor(x: Float, y: Float)
	{
		buffer.put(floatArrayOf(x, y))
		buffer.rewind()
	}

	var x: Float
		get() { return buffer.get(0) }
		set(value) { buffer.put(0, value) }

	var y: Float
		get() { return buffer.get(1) }
		set(value) { buffer.put(1, value) }
	
    // Artihmetic operations with other Vector2s
    operator fun plus  (other: Vector2) = Vector2(buffer.get(0) + other.buffer.get(0), buffer.get(1) + other.buffer.get(1))
    operator fun minus (other: Vector2) = Vector2(buffer.get(0) - other.buffer.get(0), buffer.get(1) - other.buffer.get(1))
    operator fun times (other: Vector2) = Vector2(buffer.get(0) * other.buffer.get(0), buffer.get(1) * other.buffer.get(1))
    operator fun div   (other: Vector2) = Vector2(buffer.get(0) / other.buffer.get(0), buffer.get(1) / other.buffer.get(1))

    // Unary operations
    operator fun unaryPlus  () = this
    operator fun unaryMinus () = Vector2(-buffer.get(0), -buffer.get(1))

    // Artihmetic operations with a float scalar
    operator fun times (scalar: Float) = Vector2(buffer.get(0) * scalar, buffer.get(1) * scalar)
    operator fun div   (scalar: Float) = Vector2(buffer.get(0) / scalar, buffer.get(1) / scalar)

    val length: Float
        get() = Math.sqrt((lengthSquared).toDouble()).toFloat()

    val lengthSquared: Float
        get() = buffer.get(0) * buffer.get(0) + buffer.get(1) * buffer.get(1)

    fun distanceFrom (other: Vector2) = (this - other).length
    fun distanceSquaredFrom (other: Vector2) = (this - other).lengthSquared

    fun normalize (): Float
    {
        val oldLength = length

        if (oldLength > 0)
        {
            val lengthInverse = 1.0f / oldLength
            buffer.put(0, buffer.get(0) * lengthInverse)
            buffer.put(1, buffer.get(1) * lengthInverse)
        }

        return oldLength
    }

    infix fun dot (other: Vector2) = buffer.get(0) * other.buffer.get(0) + buffer.get(1) * other.buffer.get(1)

    companion object
    {
        val ZERO = Vector2(0f, 0f)

        val UNIT_X = Vector2(1f, 0f)
        val UNIT_Y = Vector2(0f, 1f)
        val UNIT_Z = Vector2(0f, 0f)

        val NEGATIVE_UNIT_X = Vector2(-1f, 0f)
        val NEGATIVE_UNIT_Y = Vector2(0f, -1f)
        val NEGATIVE_UNIT_Z = Vector2(0f, 0f)

        val UNIT_SCALE = Vector2(1f, 1f)

        fun distanceFrom (vectors: Pair<Vector2, Vector2>) = (vectors.first - vectors.second).length
        fun distanceSquaredFrom (vectors: Pair<Vector2, Vector2>) = (vectors.first - vectors.second).lengthSquared
    }
}
// endregion

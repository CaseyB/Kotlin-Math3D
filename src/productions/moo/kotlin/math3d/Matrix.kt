package productions.moo.kotlin.math3d

import productions.moo.kotlin.math.Angle
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

/**
 * Row Major 4x4 Matrix
 */
class Matrix4
{
	internal val buffer: FloatBuffer = ByteBuffer.allocateDirect(16 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer()

	constructor()
	{
		buffer.put(floatArrayOf(
				1f, 0f, 0f, 0f,
				0f, 1f, 0f, 0f,
				0f, 0f, 1f, 0f,
				0f, 0f, 0f, 1f))
		buffer.rewind()
	}

	constructor(m00: Float, m01: Float, m02: Float, m03: Float,
				m10: Float, m11: Float, m12: Float, m13: Float,
				m20: Float, m21: Float, m22: Float, m23: Float,
				m30: Float, m31: Float, m32: Float, m33: Float)
	{
		buffer.put(floatArrayOf(
				m00, m01, m02, m03,
				m10, m11, m12, m13,
				m20, m21, m22, m23,
				m30, m31, m32, m33))
		buffer.rewind()
	}

	constructor(buffer: FloatBuffer)
	{
		buffer.put(buffer)
		buffer.rewind()
	}

	operator fun get(index: Int) = buffer.get(index)
	operator fun get(row: Int, column: Int) = buffer.get(row * 4 + column)

	// region Matrix Operators
	operator fun plus(other: Matrix4): Matrix4
	{
		return Matrix4(
				buffer.get(0) + other.buffer.get(0),
				buffer.get(1) + other.buffer.get(1),
				buffer.get(2) + other.buffer.get(2),
				buffer.get(3) + other.buffer.get(3),

				buffer.get(4) + other.buffer.get(4),
				buffer.get(5) + other.buffer.get(5),
				buffer.get(6) + other.buffer.get(6),
				buffer.get(7) + other.buffer.get(7),

				buffer.get(8) + other.buffer.get(8),
				buffer.get(9) + other.buffer.get(9),
				buffer.get(10) + other.buffer.get(10),
				buffer.get(11) + other.buffer.get(11),

				buffer.get(12) + other.buffer.get(12),
				buffer.get(13) + other.buffer.get(13),
				buffer.get(14) + other.buffer.get(14),
				buffer.get(15) + other.buffer.get(15)
		)
	}

	operator fun minus(other: Matrix4): Matrix4
	{
		return Matrix4(
				buffer.get(0) - other.buffer.get(0),
				buffer.get(1) - other.buffer.get(1),
				buffer.get(2) - other.buffer.get(2),
				buffer.get(3) - other.buffer.get(3),

				buffer.get(4) - other.buffer.get(4),
				buffer.get(5) - other.buffer.get(5),
				buffer.get(6) - other.buffer.get(6),
				buffer.get(7) - other.buffer.get(7),

				buffer.get(8) - other.buffer.get(8),
				buffer.get(9) - other.buffer.get(9),
				buffer.get(10) - other.buffer.get(10),
				buffer.get(11) - other.buffer.get(11),

				buffer.get(12) - other.buffer.get(12),
				buffer.get(13) - other.buffer.get(13),
				buffer.get(14) - other.buffer.get(14),
				buffer.get(15) - other.buffer.get(15)
		)
	}

	operator fun times(other: Matrix4): Matrix4
	{
		return Matrix4(
				buffer.get(0) * other.buffer.get(0) + buffer.get(1) * other.buffer.get(4) + buffer.get(2) * other.buffer.get(8) + buffer.get(3) * other.buffer.get(12),
				buffer.get(0) * other.buffer.get(1) + buffer.get(1) * other.buffer.get(5) + buffer.get(2) * other.buffer.get(9) + buffer.get(3) * other.buffer.get(13),
				buffer.get(0) * other.buffer.get(2) + buffer.get(1) * other.buffer.get(6) + buffer.get(2) * other.buffer.get(10) + buffer.get(3) * other.buffer.get(14),
				buffer.get(0) * other.buffer.get(3) + buffer.get(1) * other.buffer.get(7) + buffer.get(2) * other.buffer.get(11) + buffer.get(3) * other.buffer.get(15),

				buffer.get(4) * other.buffer.get(0) + buffer.get(5) * other.buffer.get(4) + buffer.get(6) * other.buffer.get(8) + buffer.get(7) * other.buffer.get(12),
				buffer.get(4) * other.buffer.get(1) + buffer.get(5) * other.buffer.get(5) + buffer.get(6) * other.buffer.get(9) + buffer.get(7) * other.buffer.get(13),
				buffer.get(4) * other.buffer.get(2) + buffer.get(5) * other.buffer.get(6) + buffer.get(6) * other.buffer.get(10) + buffer.get(7) * other.buffer.get(14),
				buffer.get(4) * other.buffer.get(3) + buffer.get(5) * other.buffer.get(7) + buffer.get(6) * other.buffer.get(11) + buffer.get(7) * other.buffer.get(15),

				buffer.get(8) * other.buffer.get(0) + buffer.get(9) * other.buffer.get(4) + buffer.get(10) * other.buffer.get(8) + buffer.get(11) * other.buffer.get(12),
				buffer.get(8) * other.buffer.get(1) + buffer.get(9) * other.buffer.get(5) + buffer.get(10) * other.buffer.get(9) + buffer.get(11) * other.buffer.get(13),
				buffer.get(8) * other.buffer.get(2) + buffer.get(9) * other.buffer.get(6) + buffer.get(10) * other.buffer.get(10) + buffer.get(11) * other.buffer.get(14),
				buffer.get(8) * other.buffer.get(3) + buffer.get(9) * other.buffer.get(7) + buffer.get(10) * other.buffer.get(11) + buffer.get(11) * other.buffer.get(15),

				buffer.get(12) * other.buffer.get(0) + buffer.get(13) * other.buffer.get(4) + buffer.get(14) * other.buffer.get(8) + buffer.get(15) * other.buffer.get(12),
				buffer.get(12) * other.buffer.get(1) + buffer.get(13) * other.buffer.get(5) + buffer.get(14) * other.buffer.get(9) + buffer.get(15) * other.buffer.get(13),
				buffer.get(12) * other.buffer.get(2) + buffer.get(13) * other.buffer.get(6) + buffer.get(14) * other.buffer.get(10) + buffer.get(15) * other.buffer.get(14),
				buffer.get(12) * other.buffer.get(3) + buffer.get(13) * other.buffer.get(7) + buffer.get(14) * other.buffer.get(11) + buffer.get(15) * other.buffer.get(15)
		)
	}
	// endregion

	// region Vector Operators
	operator fun times(vector: Vector3): Vector3
	{
		val fInvW = 1.0f / (buffer.get(12) * vector.x + buffer.get(13) * vector.y + buffer.get(14) * vector.z + buffer.get(15))

		return Vector3(
				( buffer.get(0) * vector.x + buffer.get(1) * vector.y + buffer.get(2) * vector.z + buffer.get(3) ) * fInvW,
				( buffer.get(4) * vector.x + buffer.get(5) * vector.y + buffer.get(6) * vector.z + buffer.get(7) ) * fInvW,
				( buffer.get(8) * vector.x + buffer.get(9) * vector.y + buffer.get(10) * vector.z + buffer.get(11) ) * fInvW
		)
	}
	// endregion

	// region Matrix Manipulations
	fun setTranslation(vector: Vector3)
	{
		buffer.put(12, vector.x)
		buffer.put(13, vector.y)
		buffer.put(14, vector.z)
	}

	fun translate(vector: Vector3)
	{
		buffer.put(12, buffer.get(12) + vector.x)
		buffer.put(13, buffer.get(13) + vector.y)
		buffer.put(14, buffer.get(14) + vector.z)
	}

	fun getTranslation(): Vector3
	{
		return Vector3(buffer.get(12), buffer.get(13), buffer.get(14))
	}

	fun setScale(scale: Vector3)
	{
		buffer.put(0, scale.x)
		buffer.put(5, scale.y)
		buffer.put(10, scale.z)
	}

	fun scale(scale: Vector3)
	{
		buffer.put(0, buffer.get(0) * scale.x)
		buffer.put(5, buffer.get(5) * scale.y)
		buffer.put(10, buffer.get(10) * scale.z)
	}

	fun getScale(): Vector3
	{
		return Vector3(buffer.get(0), buffer.get(5), buffer.get(10))
	}

	fun setRotation(angle: Angle, axis: Vector3)
	{
		val alpha = (angle.radians * axis.x).toDouble()
		val beta = (angle.radians * axis.y).toDouble()
		val gamma = (angle.radians * axis.z).toDouble()

		buffer.put(0, (Math.cos(beta) * Math.cos(gamma)).toFloat())
		buffer.put(1, ((Math.cos(gamma) * Math.sin(alpha) * Math.sin(beta)) - (Math.cos(alpha) * Math.sin(gamma))).toFloat())
		buffer.put(2, ((Math.cos(alpha) * Math.cos(gamma) * Math.sin(beta)) + (Math.sin(alpha) * Math.sin(gamma))).toFloat())

		buffer.put(4, (Math.cos(beta) * Math.sin(gamma)).toFloat())
		buffer.put(5, ((Math.cos(alpha) * Math.cos(gamma)) + (Math.sin(alpha) * Math.sin(beta) * Math.sin(gamma))).toFloat())
		buffer.put(6, -((Math.cos(gamma) * Math.sin(alpha)) + (Math.cos(alpha) * Math.sin(beta) * Math.sin(gamma))).toFloat())

		buffer.put(8, -(Math.sin(beta)).toFloat())
		buffer.put(9, (Math.cos(beta) * Math.sin(alpha)).toFloat())
		buffer.put(10, (Math.cos(alpha) * Math.cos(beta)).toFloat())
	}

	fun getForward(): Vector3
	{
		return Vector3(buffer.get(2), buffer.get(6), buffer.get(10))
	}

	fun getRight(): Vector3
	{
		return Vector3(buffer.get(0), buffer.get(4), buffer.get(8))
	}

	fun inverse(): Matrix4
	{
		val m00 = buffer.get(0)
		val m01 = buffer.get(1)
		val m02 = buffer.get(2)
		val m03 = buffer.get(3)
		val m10 = buffer.get(4)
		val m11 = buffer.get(5)
		val m12 = buffer.get(6)
		val m13 = buffer.get(7)
		val m20 = buffer.get(8)
		val m21 = buffer.get(9)
		val m22 = buffer.get(10)
		val m23 = buffer.get(11)
		val m30 = buffer.get(12)
		val m31 = buffer.get(13)
		val m32 = buffer.get(14)
		val m33 = buffer.get(15)

		var v0 = m20 * m31 - m21 * m30
		var v1 = m20 * m32 - m22 * m30
		var v2 = m20 * m33 - m23 * m30
		var v3 = m21 * m32 - m22 * m31
		var v4 = m21 * m33 - m23 * m31
		var v5 = m22 * m33 - m23 * m32

		val t00 = +(v5 * m11 - v4 * m12 + v3 * m13)
		val t10 = -(v5 * m10 - v2 * m12 + v1 * m13)
		val t20 = +(v4 * m10 - v2 * m11 + v0 * m13)
		val t30 = -(v3 * m10 - v1 * m11 + v0 * m12)

		val invDet = 1 / (t00 * m00 + t10 * m01 + t20 * m02 + t30 * m03)

		val d00 = t00 * invDet
		val d10 = t10 * invDet
		val d20 = t20 * invDet
		val d30 = t30 * invDet

		val d01 = -(v5 * m01 - v4 * m02 + v3 * m03) * invDet
		val d11 = +(v5 * m00 - v2 * m02 + v1 * m03) * invDet
		val d21 = -(v4 * m00 - v2 * m01 + v0 * m03) * invDet
		val d31 = +(v3 * m00 - v1 * m01 + v0 * m02) * invDet

		v0 = m10 * m31 - m11 * m30
		v1 = m10 * m32 - m12 * m30
		v2 = m10 * m33 - m13 * m30
		v3 = m11 * m32 - m12 * m31
		v4 = m11 * m33 - m13 * m31
		v5 = m12 * m33 - m13 * m32

		val d02 = +(v5 * m01 - v4 * m02 + v3 * m03) * invDet
		val d12 = -(v5 * m00 - v2 * m02 + v1 * m03) * invDet
		val d22 = +(v4 * m00 - v2 * m01 + v0 * m03) * invDet
		val d32 = -(v3 * m00 - v1 * m01 + v0 * m02) * invDet

		v0 = m21 * m10 - m20 * m11
		v1 = m22 * m10 - m20 * m12
		v2 = m23 * m10 - m20 * m13
		v3 = m22 * m11 - m21 * m12
		v4 = m23 * m11 - m21 * m13
		v5 = m23 * m12 - m22 * m13

		val d03 = -(v5 * m01 - v4 * m02 + v3 * m03) * invDet
		val d13 = +(v5 * m00 - v2 * m02 + v1 * m03) * invDet
		val d23 = -(v4 * m00 - v2 * m01 + v0 * m03) * invDet
		val d33 = +(v3 * m00 - v1 * m01 + v0 * m02) * invDet

		return Matrix4(
				d00, d01, d02, d03,
				d10, d11, d12, d13,
				d20, d21, d22, d23,
				d30, d31, d32, d33)
	}
	// endregion

	// region Data Functions
	fun equals(other: Matrix4): Boolean
	{
		return buffer.get(0) == other.buffer.get(0) &&
				buffer.get(1) == other.buffer.get(1) &&
				buffer.get(2) == other.buffer.get(2) &&
				buffer.get(3) == other.buffer.get(3) &&

				buffer.get(4) == other.buffer.get(4) &&
				buffer.get(5) == other.buffer.get(5) &&
				buffer.get(6) == other.buffer.get(6) &&
				buffer.get(7) == other.buffer.get(7) &&

				buffer.get(8) == other.buffer.get(8) &&
				buffer.get(9) == other.buffer.get(9) &&
				buffer.get(10) == other.buffer.get(10) &&
				buffer.get(11) == other.buffer.get(11) &&

				buffer.get(12) == other.buffer.get(12) &&
				buffer.get(13) == other.buffer.get(13) &&
				buffer.get(14) == other.buffer.get(14) &&
				buffer.get(15) == other.buffer.get(15)
	}

	override fun toString(): String
	{
		return  "| ${buffer.get(0)} ${buffer.get(1)} ${buffer.get(2)} ${buffer.get(3)} |\n" +
				"| ${buffer.get(4)} ${buffer.get(5)} ${buffer.get(6)} ${buffer.get(7)} |\n" +
				"| ${buffer.get(8)} ${buffer.get(9)} ${buffer.get(10)} ${buffer.get(11)} |\n" +
				"| ${buffer.get(12)} ${buffer.get(13)} ${buffer.get(14)} ${buffer.get(15)} |\n"
	}
	// endregion
}
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
	internal val _buffer: FloatBuffer = ByteBuffer.allocateDirect(16 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer()

	constructor()
	{
		_buffer.put(floatArrayOf(
				1f, 0f, 0f, 0f,
				0f, 1f, 0f, 0f,
				0f, 0f, 1f, 0f,
				0f, 0f, 0f, 1f))
		_buffer.rewind()
	}

	constructor(m00: Float, m01: Float, m02: Float, m03: Float,
				m10: Float, m11: Float, m12: Float, m13: Float,
				m20: Float, m21: Float, m22: Float, m23: Float,
				m30: Float, m31: Float, m32: Float, m33: Float)
	{
		_buffer.put(floatArrayOf(
				m00, m01, m02, m03,
				m10, m11, m12, m13,
				m20, m21, m22, m23,
				m30, m31, m32, m33))
		_buffer.rewind()
	}

	constructor(buffer: FloatBuffer)
	{
		_buffer.put(buffer)
		_buffer.rewind()
	}

	operator fun get(index: Int) = _buffer.get(index)
	operator fun get(row: Int, column: Int) = _buffer.get(row * 4 + column)

	// region Matrix Operators
	operator fun plus(other: Matrix4): Matrix4
	{
		return Matrix4(
				_buffer.get(0) + other._buffer.get(0),
				_buffer.get(1) + other._buffer.get(1),
				_buffer.get(2) + other._buffer.get(2),
				_buffer.get(3) + other._buffer.get(3),

				_buffer.get(4) + other._buffer.get(4),
				_buffer.get(5) + other._buffer.get(5),
				_buffer.get(6) + other._buffer.get(6),
				_buffer.get(7) + other._buffer.get(7),

				_buffer.get(8) + other._buffer.get(8),
				_buffer.get(9) + other._buffer.get(9),
				_buffer.get(10) + other._buffer.get(10),
				_buffer.get(11) + other._buffer.get(11),

				_buffer.get(12) + other._buffer.get(12),
				_buffer.get(13) + other._buffer.get(13),
				_buffer.get(14) + other._buffer.get(14),
				_buffer.get(15) + other._buffer.get(15)
		)
	}

	operator fun minus(other: Matrix4): Matrix4
	{
		return Matrix4(
				_buffer.get(0) - other._buffer.get(0),
				_buffer.get(1) - other._buffer.get(1),
				_buffer.get(2) - other._buffer.get(2),
				_buffer.get(3) - other._buffer.get(3),

				_buffer.get(4) - other._buffer.get(4),
				_buffer.get(5) - other._buffer.get(5),
				_buffer.get(6) - other._buffer.get(6),
				_buffer.get(7) - other._buffer.get(7),

				_buffer.get(8) - other._buffer.get(8),
				_buffer.get(9) - other._buffer.get(9),
				_buffer.get(10) - other._buffer.get(10),
				_buffer.get(11) - other._buffer.get(11),

				_buffer.get(12) - other._buffer.get(12),
				_buffer.get(13) - other._buffer.get(13),
				_buffer.get(14) - other._buffer.get(14),
				_buffer.get(15) - other._buffer.get(15)
		)
	}

	operator fun times(other: Matrix4): Matrix4
	{
		return Matrix4(
				_buffer.get(0) * other._buffer.get(0) + _buffer.get(1) * other._buffer.get(4) + _buffer.get(2) * other._buffer.get(8) + _buffer.get(3) * other._buffer.get(12),
				_buffer.get(0) * other._buffer.get(1) + _buffer.get(1) * other._buffer.get(5) + _buffer.get(2) * other._buffer.get(9) + _buffer.get(3) * other._buffer.get(13),
				_buffer.get(0) * other._buffer.get(2) + _buffer.get(1) * other._buffer.get(6) + _buffer.get(2) * other._buffer.get(10) + _buffer.get(3) * other._buffer.get(14),
				_buffer.get(0) * other._buffer.get(3) + _buffer.get(1) * other._buffer.get(7) + _buffer.get(2) * other._buffer.get(11) + _buffer.get(3) * other._buffer.get(15),

				_buffer.get(4) * other._buffer.get(0) + _buffer.get(5) * other._buffer.get(4) + _buffer.get(6) * other._buffer.get(8) + _buffer.get(7) * other._buffer.get(12),
				_buffer.get(4) * other._buffer.get(1) + _buffer.get(5) * other._buffer.get(5) + _buffer.get(6) * other._buffer.get(9) + _buffer.get(7) * other._buffer.get(13),
				_buffer.get(4) * other._buffer.get(2) + _buffer.get(5) * other._buffer.get(6) + _buffer.get(6) * other._buffer.get(10) + _buffer.get(7) * other._buffer.get(14),
				_buffer.get(4) * other._buffer.get(3) + _buffer.get(5) * other._buffer.get(7) + _buffer.get(6) * other._buffer.get(11) + _buffer.get(7) * other._buffer.get(15),

				_buffer.get(8) * other._buffer.get(0) + _buffer.get(9) * other._buffer.get(4) + _buffer.get(10) * other._buffer.get(8) + _buffer.get(11) * other._buffer.get(12),
				_buffer.get(8) * other._buffer.get(1) + _buffer.get(9) * other._buffer.get(5) + _buffer.get(10) * other._buffer.get(9) + _buffer.get(11) * other._buffer.get(13),
				_buffer.get(8) * other._buffer.get(2) + _buffer.get(9) * other._buffer.get(6) + _buffer.get(10) * other._buffer.get(10) + _buffer.get(11) * other._buffer.get(14),
				_buffer.get(8) * other._buffer.get(3) + _buffer.get(9) * other._buffer.get(7) + _buffer.get(10) * other._buffer.get(11) + _buffer.get(11) * other._buffer.get(15),

				_buffer.get(12) * other._buffer.get(0) + _buffer.get(13) * other._buffer.get(4) + _buffer.get(14) * other._buffer.get(8) + _buffer.get(15) * other._buffer.get(12),
				_buffer.get(12) * other._buffer.get(1) + _buffer.get(13) * other._buffer.get(5) + _buffer.get(14) * other._buffer.get(9) + _buffer.get(15) * other._buffer.get(13),
				_buffer.get(12) * other._buffer.get(2) + _buffer.get(13) * other._buffer.get(6) + _buffer.get(14) * other._buffer.get(10) + _buffer.get(15) * other._buffer.get(14),
				_buffer.get(12) * other._buffer.get(3) + _buffer.get(13) * other._buffer.get(7) + _buffer.get(14) * other._buffer.get(11) + _buffer.get(15) * other._buffer.get(15)
		)
	}
	// endregion

	// region Vector Operators
	operator fun times(vector: Vector3): Vector3
	{
		val fInvW = 1.0f / (_buffer.get(12) * vector.x + _buffer.get(13) * vector.y + _buffer.get(14) * vector.z + _buffer.get(15))

		return Vector3(
				( _buffer.get(0) * vector.x + _buffer.get(1) * vector.y + _buffer.get(2) * vector.z + _buffer.get(3) ) * fInvW,
				( _buffer.get(4) * vector.x + _buffer.get(5) * vector.y + _buffer.get(6) * vector.z + _buffer.get(7) ) * fInvW,
				( _buffer.get(8) * vector.x + _buffer.get(9) * vector.y + _buffer.get(10) * vector.z + _buffer.get(11) ) * fInvW
		)
	}
	// endregion

	// region Matrix Manipulations
	fun setTranslation(vector: Vector3)
	{
		_buffer.put(12, vector.x)
		_buffer.put(13, vector.y)
		_buffer.put(14, vector.z)
	}

	fun translate(vector: Vector3)
	{
		_buffer.put(12, _buffer.get(12) + vector.x)
		_buffer.put(13, _buffer.get(13) + vector.y)
		_buffer.put(14, _buffer.get(14) + vector.z)
	}

	fun getTranslation(): Vector3
	{
		return Vector3(_buffer.get(12), _buffer.get(13), _buffer.get(14))
	}

	fun setScale(scale: Vector3)
	{
		_buffer.put(0, scale.x)
		_buffer.put(5, scale.y)
		_buffer.put(10, scale.z)
	}

	fun scale(scale: Vector3)
	{
		_buffer.put(0, _buffer.get(0) * scale.x)
		_buffer.put(5, _buffer.get(5) * scale.y)
		_buffer.put(10, _buffer.get(10) * scale.z)
	}

	fun getScale(): Vector3
	{
		return Vector3(_buffer.get(0), _buffer.get(5), _buffer.get(10))
	}

	fun setRotation(angle: Angle, axis: Vector3)
	{
		val alpha = (angle.radians * axis.x).toDouble()
		val beta = (angle.radians * axis.y).toDouble()
		val gamma = (angle.radians * axis.z).toDouble()

		_buffer.put(0, (Math.cos(beta) * Math.cos(gamma)).toFloat())
		_buffer.put(1, ((Math.cos(gamma) * Math.sin(alpha) * Math.sin(beta)) - (Math.cos(alpha) * Math.sin(gamma))).toFloat())
		_buffer.put(2, ((Math.cos(alpha) * Math.cos(gamma) * Math.sin(beta)) + (Math.sin(alpha) * Math.sin(gamma))).toFloat())

		_buffer.put(4, (Math.cos(beta) * Math.sin(gamma)).toFloat())
		_buffer.put(5, ((Math.cos(alpha) * Math.cos(gamma)) + (Math.sin(alpha) * Math.sin(beta) * Math.sin(gamma))).toFloat())
		_buffer.put(6, -((Math.cos(gamma) * Math.sin(alpha)) + (Math.cos(alpha) * Math.sin(beta) * Math.sin(gamma))).toFloat())

		_buffer.put(8, -(Math.sin(beta)).toFloat())
		_buffer.put(9, (Math.cos(beta) * Math.sin(alpha)).toFloat())
		_buffer.put(10, (Math.cos(alpha) * Math.cos(beta)).toFloat())
	}

	fun getForward(): Vector3
	{
		return Vector3(_buffer.get(2), _buffer.get(6), _buffer.get(10))
	}

	fun getRight(): Vector3
	{
		return Vector3(_buffer.get(0), _buffer.get(4), _buffer.get(8))
	}

	fun inverse(): Matrix4
	{
		val m00 = _buffer.get(0)
		val m01 = _buffer.get(1)
		val m02 = _buffer.get(2)
		val m03 = _buffer.get(3)
		val m10 = _buffer.get(4)
		val m11 = _buffer.get(5)
		val m12 = _buffer.get(6)
		val m13 = _buffer.get(7)
		val m20 = _buffer.get(8)
		val m21 = _buffer.get(9)
		val m22 = _buffer.get(10)
		val m23 = _buffer.get(11)
		val m30 = _buffer.get(12)
		val m31 = _buffer.get(13)
		val m32 = _buffer.get(14)
		val m33 = _buffer.get(15)

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
		return _buffer.get(0) == other._buffer.get(0) &&
				_buffer.get(1) == other._buffer.get(1) &&
				_buffer.get(2) == other._buffer.get(2) &&
				_buffer.get(3) == other._buffer.get(3) &&

				_buffer.get(4) == other._buffer.get(4) &&
				_buffer.get(5) == other._buffer.get(5) &&
				_buffer.get(6) == other._buffer.get(6) &&
				_buffer.get(7) == other._buffer.get(7) &&

				_buffer.get(8) == other._buffer.get(8) &&
				_buffer.get(9) == other._buffer.get(9) &&
				_buffer.get(10) == other._buffer.get(10) &&
				_buffer.get(11) == other._buffer.get(11) &&

				_buffer.get(12) == other._buffer.get(12) &&
				_buffer.get(13) == other._buffer.get(13) &&
				_buffer.get(14) == other._buffer.get(14) &&
				_buffer.get(15) == other._buffer.get(15)
	}

	override fun toString(): String
	{
		return  "| ${_buffer.get(0)} ${_buffer.get(1)} ${_buffer.get(2)} ${_buffer.get(3)} |\n" +
				"| ${_buffer.get(4)} ${_buffer.get(5)} ${_buffer.get(6)} ${_buffer.get(7)} |\n" +
				"| ${_buffer.get(8)} ${_buffer.get(9)} ${_buffer.get(10)} ${_buffer.get(11)} |\n" +
				"| ${_buffer.get(12)} ${_buffer.get(13)} ${_buffer.get(14)} ${_buffer.get(15)} |\n"
	}
	// endregion
}
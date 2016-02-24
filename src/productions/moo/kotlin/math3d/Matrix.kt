package productions.moo.kotlin.math3d

class Matrix4
{
    private val _values = FloatArray(16)

    constructor(m00: Float, m01: Float, m02: Float, m03: Float,
                m10: Float, m11: Float, m12: Float, m13: Float,
                m20: Float, m21: Float, m22: Float, m23: Float,
                m30: Float, m31: Float, m32: Float, m33: Float)
    {
        _values[ 0] = m00
        _values[ 1] = m01
        _values[ 2] = m02
        _values[ 3] = m03

        _values[ 4] = m10
        _values[ 5] = m11
        _values[ 6] = m12
        _values[ 7] = m13

        _values[ 8] = m20
        _values[ 9] = m21
        _values[10] = m22
        _values[11] = m23

        _values[12] = m30
        _values[13] = m31
        _values[14] = m32
        _values[15] = m33
    }

    operator fun get (index: Int) = _values[index]
    operator fun get (row: Int, column: Int) = _values[row * 4 + column]

    // region Matrix Operators
    operator fun plus (other: Matrix4): Matrix4
    {
        return Matrix4(
            _values[ 0] + other[ 0],
            _values[ 1] + other[ 1],
            _values[ 2] + other[ 2],
            _values[ 3] + other[ 3],

            _values[ 4] + other[ 4],
            _values[ 5] + other[ 5],
            _values[ 6] + other[ 6],
            _values[ 7] + other[ 7],

            _values[ 8] + other[ 8],
            _values[ 9] + other[ 9],
            _values[10] + other[10],
            _values[11] + other[11],

            _values[12] + other[12],
            _values[13] + other[13],
            _values[14] + other[14],
            _values[15] + other[15]
        )
    }

    operator fun minus (other: Matrix4): Matrix4
    {
        return Matrix4(
            _values[ 0] - other[ 0],
            _values[ 1] - other[ 1],
            _values[ 2] - other[ 2],
            _values[ 3] - other[ 3],

            _values[ 4] - other[ 4],
            _values[ 5] - other[ 5],
            _values[ 6] - other[ 6],
            _values[ 7] - other[ 7],

            _values[ 8] - other[ 8],
            _values[ 9] - other[ 9],
            _values[10] - other[10],
            _values[11] - other[11],

            _values[12] - other[12],
            _values[13] - other[13],
            _values[14] - other[14],
            _values[15] - other[15]
        )
    }

    operator fun times (other: Matrix4): Matrix4
    {
        return Matrix4(
            _values[ 0] * other[ 0] + _values[ 1] * other[ 4] + _values[ 2] * other[ 8] + _values[ 3] * other[12],
            _values[ 0] * other[ 1] + _values[ 1] * other[ 5] + _values[ 2] * other[ 9] + _values[ 3] * other[13],
            _values[ 0] * other[ 2] + _values[ 1] * other[ 6] + _values[ 2] * other[10] + _values[ 3] * other[14],
            _values[ 0] * other[ 3] + _values[ 1] * other[ 7] + _values[ 2] * other[11] + _values[ 3] * other[15],

            _values[ 4] * other[ 0] + _values[ 5] * other[ 4] + _values[ 6] * other[ 8] + _values[ 7] * other[12],
            _values[ 4] * other[ 1] + _values[ 5] * other[ 5] + _values[ 6] * other[ 9] + _values[ 7] * other[13],
            _values[ 4] * other[ 2] + _values[ 5] * other[ 6] + _values[ 6] * other[10] + _values[ 7] * other[14],
            _values[ 4] * other[ 3] + _values[ 5] * other[ 7] + _values[ 6] * other[11] + _values[ 7] * other[15],

            _values[ 8] * other[ 0] + _values[ 9] * other[ 4] + _values[10] * other[ 8] + _values[11] * other[12],
            _values[ 8] * other[ 1] + _values[ 9] * other[ 5] + _values[10] * other[ 9] + _values[11] * other[13],
            _values[ 8] * other[ 2] + _values[ 9] * other[ 6] + _values[10] * other[10] + _values[11] * other[14],
            _values[ 8] * other[ 3] + _values[ 9] * other[ 7] + _values[10] * other[11] + _values[11] * other[15],

            _values[12] * other[ 0] + _values[13] * other[ 4] + _values[14] * other[ 8] + _values[15] * other[12],
            _values[12] * other[ 1] + _values[13] * other[ 5] + _values[14] * other[ 9] + _values[15] * other[13],
            _values[12] * other[ 2] + _values[13] * other[ 6] + _values[14] * other[10] + _values[15] * other[14],
            _values[12] * other[ 3] + _values[13] * other[ 7] + _values[14] * other[11] + _values[15] * other[15]
        )
    }
    // endregion

    // region Vector Operators
    operator fun times (vector: Vector3): Vector3
    {
        val fInvW = 1.0f / (_values[12] * vector.x + _values[13] * vector.y + _values[14] * vector.z + _values[15])

        return Vector3(
            ( _values[ 0] * vector.x + _values[ 1] * vector.y + _values[ 2] * vector.z + _values[ 3] ) * fInvW,
            ( _values[ 4] * vector.x + _values[ 5] * vector.y + _values[ 6] * vector.z + _values[ 7] ) * fInvW,
            ( _values[ 8] * vector.x + _values[ 9] * vector.y + _values[10] * vector.z + _values[11] ) * fInvW
        )
    }
    // endregion

    // region Matrix Manipulations
    fun setTranslation (vector: Vector3)
    {
        _values[ 3] = vector.x
        _values[ 7] = vector.y
        _values[11] = vector.z
    }

    /** Extracts the translation transformation part of the matrix.
     */
    fun getTranslation(): Vector3
    {
        return Vector3(_values[ 3], _values[ 7], _values[11])
    }

    fun setScale(scale: Vector3)
    {
        _values[ 0] = scale.x
        _values[ 5] = scale.y
        _values[10] = scale.z
    }

    fun getScale(): Vector3
    {
        return Vector3(_values[ 0], _values[ 5], _values[10])
    }

    fun inverse(): Matrix4
    {
        val m00 = _values[ 0]
        val m01 = _values[ 1]
        val m02 = _values[ 2]
        val m03 = _values[ 3]
        val m10 = _values[ 4]
        val m11 = _values[ 5]
        val m12 = _values[ 6]
        val m13 = _values[ 7]
        val m20 = _values[ 8]
        val m21 = _values[ 9]
        val m22 = _values[10]
        val m23 = _values[11]
        val m30 = _values[12]
        val m31 = _values[13]
        val m32 = _values[14]
        val m33 = _values[15]
    
        var v0 = m20 * m31 - m21 * m30
        var v1 = m20 * m32 - m22 * m30
        var v2 = m20 * m33 - m23 * m30
        var v3 = m21 * m32 - m22 * m31
        var v4 = m21 * m33 - m23 * m31
        var v5 = m22 * m33 - m23 * m32
    
        val t00 = + (v5 * m11 - v4 * m12 + v3 * m13)
        val t10 = - (v5 * m10 - v2 * m12 + v1 * m13)
        val t20 = + (v4 * m10 - v2 * m11 + v0 * m13)
        val t30 = - (v3 * m10 - v1 * m11 + v0 * m12)
    
        val invDet = 1 / (t00 * m00 + t10 * m01 + t20 * m02 + t30 * m03)
    
        val d00 = t00 * invDet
        val d10 = t10 * invDet
        val d20 = t20 * invDet
        val d30 = t30 * invDet
    
        val d01 = - (v5 * m01 - v4 * m02 + v3 * m03) * invDet
        val d11 = + (v5 * m00 - v2 * m02 + v1 * m03) * invDet
        val d21 = - (v4 * m00 - v2 * m01 + v0 * m03) * invDet
        val d31 = + (v3 * m00 - v1 * m01 + v0 * m02) * invDet
    
        v0 = m10 * m31 - m11 * m30
        v1 = m10 * m32 - m12 * m30
        v2 = m10 * m33 - m13 * m30
        v3 = m11 * m32 - m12 * m31
        v4 = m11 * m33 - m13 * m31
        v5 = m12 * m33 - m13 * m32
    
        val d02 = + (v5 * m01 - v4 * m02 + v3 * m03) * invDet
        val d12 = - (v5 * m00 - v2 * m02 + v1 * m03) * invDet
        val d22 = + (v4 * m00 - v2 * m01 + v0 * m03) * invDet
        val d32 = - (v3 * m00 - v1 * m01 + v0 * m02) * invDet
    
        v0 = m21 * m10 - m20 * m11
        v1 = m22 * m10 - m20 * m12
        v2 = m23 * m10 - m20 * m13
        v3 = m22 * m11 - m21 * m12
        v4 = m23 * m11 - m21 * m13
        v5 = m23 * m12 - m22 * m13
    
        val d03 = - (v5 * m01 - v4 * m02 + v3 * m03) * invDet
        val d13 = + (v5 * m00 - v2 * m02 + v1 * m03) * invDet
        val d23 = - (v4 * m00 - v2 * m01 + v0 * m03) * invDet
        val d33 = + (v3 * m00 - v1 * m01 + v0 * m02) * invDet
    
        return Matrix4(
                d00, d01, d02, d03,
                d10, d11, d12, d13,
                d20, d21, d22, d23,
                d30, d31, d32, d33)
    }
    // endregion

    // region Data Functions
    fun equals (other: Matrix4): Boolean
    {
        return  _values[ 0] == other[ 0] &&
                _values[ 1] == other[ 1] &&
                _values[ 2] == other[ 2] &&
                _values[ 3] == other[ 3] &&

                _values[ 4] == other[ 4] &&
                _values[ 5] == other[ 5] &&
                _values[ 6] == other[ 6] &&
                _values[ 7] == other[ 7] &&

                _values[ 8] == other[ 8] &&
                _values[ 9] == other[ 9] &&
                _values[10] == other[10] &&
                _values[11] == other[11] &&

                _values[12] == other[12] &&
                _values[13] == other[13] &&
                _values[14] == other[14] &&
                _values[15] == other[15]
    }
    // endregion

    companion object
    {
        val ZERO = Matrix4 (
        0f, 0f, 0f, 0f,
        0f, 0f, 0f, 0f,
        0f, 0f, 0f, 0f,
        0f, 0f, 0f, 0f)

        val ZERO_AFFINE = Matrix4 (
        0f, 0f, 0f, 0f,
        0f, 0f, 0f, 0f,
        0f, 0f, 0f, 0f,
        0f, 0f, 0f, 1f)

        val IDENTITY = Matrix4 (
        1f, 0f, 0f, 0f,
        0f, 1f, 0f, 0f,
        0f, 0f, 1f, 0f,
        0f, 0f, 0f, 1f)
    }
}
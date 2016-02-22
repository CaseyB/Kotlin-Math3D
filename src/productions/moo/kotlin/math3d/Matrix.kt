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
        0f, 0f, 0f, 1f);

        val IDENTITY = Matrix4 (
        1f, 0f, 0f, 0f,
        0f, 1f, 0f, 0f,
        0f, 0f, 1f, 0f,
        0f, 0f, 0f, 1f);
    }
}
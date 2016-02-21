// Override operators to work with Vector3
operator fun Float.times (vector: Vector3) = Vector3(this * vector.x, this * vector.y, this * vector.z)
operator fun Float.div (vector: Vector3) = Vector3(this / vector.x, this / vector.y, this / vector.z)

data class Vector3 (var x: Float, var y: Float, var z: Float)
{
    // Artihmetic operations with other Vector3s
    operator fun plus  (other: Vector3) = Vector3(x + other.x, y + other.y, z + other.z)
    operator fun minus (other: Vector3) = Vector3(x - other.x, y - other.y, z - other.z)
    operator fun times (other: Vector3) = Vector3(x * other.x, y * other.y, z * other.z)
    operator fun div   (other: Vector3) = Vector3(x / other.x, y / other.y, z / other.z)

    // Unary operations
    operator fun unaryPlus  () = this
    operator fun unaryMinus () = Vector3(-x, -y, -z)

    // Artihmetic operations with a float scalar
    operator fun times (scalar: Float) = Vector3(x * scalar, y * scalar, z * scalar)
    operator fun div   (scalar: Float) = Vector3(x / scalar, y / scalar, z / scalar)

    val length: Float
        get() = Math.sqrt((lengthSquared).toDouble()).toFloat()

    val lengthSquared: Float
        get() = x * x + y * y + z * z
    
    fun distanceFrom (other: Vector3) = (this - other).length
    fun distanceSquaredFrom (other: Vector3) = (this - other).lengthSquared
    
    fun normalize (): Float
    {
        val oldLength = length
        
        if (oldLength > 0)
        {
            val lengthInverse = 1.0f / oldLength
            x *= lengthInverse
            y *= lengthInverse
            z *= lengthInverse
        }
        
        return oldLength
    }
    
    infix fun dot (other: Vector3) = x * other.x + y * other.y + z * other.z
    infix fun cross (other: Vector3): Vector3
    {
        return Vector3(
                y * other.z - z * other.y,
                z * other.x - x * other.z,
                x * other.y - y * other.x)
    }

    companion object
    {
        val ZERO = Vector3(0f, 0f, 0f)
        
        val UNIT_X = Vector3(1f, 0f, 0f)
        val UNIT_Y = Vector3(0f, 1f, 0f)
        val UNIT_Z = Vector3(0f, 0f, 1f)
        
        val NEGATIVE_UNIT_X = Vector3(-1f, 0f, 0f)
        val NEGATIVE_UNIT_Y = Vector3(0f, -1f, 0f)
        val NEGATIVE_UNIT_Z = Vector3(0f, 0f, -1f)
        
        val UNIT_SCALE = Vector3(1f, 1f, 1f)

        fun distanceFrom (vectors: Pair<Vector3, Vector3>) = (vectors.first - vectors.second).length
        fun distanceSquaredFrom (vectors: Pair<Vector3, Vector3>) = (vectors.first - vectors.second).lengthSquared
    }
}
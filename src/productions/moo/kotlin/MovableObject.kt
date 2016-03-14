package productions.moo.kotlin

import productions.moo.kotlin.math.Angle
import productions.moo.kotlin.math3d.Matrix4
import productions.moo.kotlin.math3d.Vector3

open class MovableObject
{
	internal var parent: Node? = null
	internal val matrix = Matrix4()

	var position: Vector3
		get()
		{
			return matrix.getTranslation()
		}
		set(value)
		{
			matrix.setTranslation(value)
		}

	val worldPosition: Matrix4
		get()
		{
			parent?.let {
				return matrix * it.matrix
			}

			return matrix
		}

	fun setRotation(rotation: Angle, x: Float, y: Float, z: Float)
	{
		matrix.setRotation(rotation, Vector3(x, y, z))
	}
}
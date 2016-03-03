package productions.moo.kotlin

import productions.moo.kotlin.math.Angle
import productions.moo.kotlin.math3d.Matrix4
import productions.moo.kotlin.math3d.Vector3
import productions.moo.kotlin.models.Mesh

class Node()
{
	private var parent: Node? = null
	internal val matrix = Matrix4()

	internal val children: MutableSet<Node> = mutableSetOf()
	internal val meshes: MutableSet<Mesh> = mutableSetOf()

	fun getPosition(): Vector3
	{
		return matrix.getTranslation()
	}

	fun setPosition(position: Vector3)
	{
		matrix.setTranslation(position)
	}

	fun setRotation(rotation: Angle, x: Float, y: Float, z: Float)
	{
		matrix.setRotation(rotation, Vector3(x, y, z))
	}

	fun addChild(child: Node)
	{
		children.add(child)
		child.parent = this
	}

	fun addMesh(mob: Mesh)
	{
		meshes.add(mob)
	}
}
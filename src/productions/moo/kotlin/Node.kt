package productions.moo.kotlin

import productions.moo.kotlin.math.Angle
import productions.moo.kotlin.math.clamp
import productions.moo.kotlin.math3d.Vector3
import productions.moo.kotlin.models.Mesh

data class Node(var position: Vector3 = Vector3.ZERO)
{
	private var parent: Node? = null

	// TODO: This is shit
	internal var rotAmount = Angle()
	internal var rotAxis = Vector3.ZERO
	fun setRotation(rotation: Angle, x: Float, y: Float, z: Float)
	{
		rotAmount = rotation
		rotAxis = Vector3(x.clamp(-1f, 1f), y.clamp(-1f, 1f), z.clamp(-1f, 1f))
	}

	internal val children: MutableSet<Node> = mutableSetOf()
	internal val meshes: MutableSet<Mesh> = mutableSetOf()

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
package productions.moo.kotlin

import productions.moo.kotlin.math3d.Vector3
import productions.moo.kotlin.models.Mesh

data class Node(var position: Vector3 = Vector3.ZERO)
{
	private var parent: Node? = null

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
package productions.moo.kotlin

import productions.moo.kotlin.models.Mesh

class Node() : MovableObject()
{
	internal val children: MutableSet<Node> = mutableSetOf()
	internal val meshes: MutableSet<Mesh> = mutableSetOf()

	fun addChild(child: Node)
	{
		children.add(child)
		child.parent = this
	}

	fun addMesh(mob: Mesh)
	{
		mob.parent = this
		meshes.add(mob)
	}

	fun attachCamera(camera: Camera)
	{
		camera.parent = this
	}
}
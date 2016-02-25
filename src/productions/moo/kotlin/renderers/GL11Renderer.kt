package productions.moo.kotlin.renderers

import org.lwjgl.opengl.GL11
import productions.moo.kotlin.Color
import productions.moo.kotlin.models.Mesh

class GL11Renderer : GLRenderer()
{
	private var meshes: Array<Mesh>? = null
	private var rot: Float = 0f

	override fun setClearColor(color: Color)
	{
		GL11.glClearColor(color.red, color.green, color.blue, color.alpha)
	}

	override fun addMesh(mesh: Mesh)
	{
		meshes = arrayOf(mesh)
	}

	override fun render ()
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
		GL11.glLoadIdentity()

		GL11.glRotatef(rot, 1f, 1f, 1f)

		GL11.glBegin(GL11.GL_TRIANGLE_FAN)

		for (mesh in meshes!!)
		{
			for (index in mesh.indicies!!)
			{
				val vec = mesh.verticies!![index]
				val color = mesh.vertexColors!![index]

				GL11.glColor3f(color.red, color.green, color.blue)
				GL11.glVertex3f(vec.x, vec.y, vec.z)
			}
		}

		GL11.glEnd()

		rot += 0.25f
	}
}
package productions.moo.kotlin.renderers.GL11

import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GLCapabilities
import productions.moo.kotlin.models.Mesh
import productions.moo.kotlin.renderers.GLModelUtils

internal class GL11ModelUtils(private val capabilities: GLCapabilities) : GLModelUtils
{
	override fun loadMesh(mesh: Mesh)
	{
		var listID: Int? = null

		mesh.indicies?.let {
			// TODO: For now we just make one list per model
			listID = GL11.glGenLists(1)

			GL11.glNewList(listID!!, GL11.GL_COMPILE)
			GL11.glBegin(GL11.GL_TRIANGLES)

			for (index in it)
			{
				mesh.vertexColors?.let {
					val color = it[index]
					GL11.glColor3f(color.red, color.green, color.blue)
				}

				mesh.verticies?.let {
					val vec = it[index]
					GL11.glVertex3f(vec.x, vec.y, vec.z)
				}
			}

			GL11.glEnd()
			GL11.glEndList()
		}

		mesh.rendererID = listID
	}
}

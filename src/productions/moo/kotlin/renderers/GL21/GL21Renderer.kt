package productions.moo.kotlin.renderers.GL21

import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER
import org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER
import org.lwjgl.opengl.GL15.glBindBuffer
import productions.moo.kotlin.Node
import productions.moo.kotlin.math3d.Vector2
import productions.moo.kotlin.renderers.GLModelUtils
import productions.moo.kotlin.renderers.GLRenderer

class GL21Renderer() : GLRenderer()
{
	override val modelUtils: GLModelUtils = GL21ModelUtils()

	override fun initialize(frameBufferSize: Vector2)
	{
		resize(frameBufferSize.x.toInt(), frameBufferSize.y.toInt())

		glShadeModel(GL_SMOOTH);
		glClearDepth(1.0);
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LEQUAL);
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);

		glEnable(GL_LIGHT0);
		glEnable(GL_LIGHTING);
		glEnable(GL_COLOR_MATERIAL);
	}

	override fun renderNode(node: Node)
	{
		glPushMatrix()
		glMultMatrixf(node.matrix._buffer)

		// Render meshes
		for (mesh in node.meshes)
		{
			mesh.vertexBuffer?.let {
				glEnableClientState(GL_VERTEX_ARRAY)
				glBindBuffer(GL_ARRAY_BUFFER, it)
				glVertexPointer(3, GL_FLOAT, 0, 0)
			}
			mesh.colorBuffer?.let {
				glEnableClientState(GL_COLOR_ARRAY)
				glBindBuffer(GL_ARRAY_BUFFER, it)
				glColorPointer(4, GL_FLOAT, 0, 0)
			}
			mesh.indexBuffer?.let {
				glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, it)
				glDrawElements(GL_TRIANGLES, mesh.indicies!!.size, GL_UNSIGNED_INT, 0);
			}
		}

		// Render children
		for (child in node.children)
		{
			renderNode(child)
		}

		glPopMatrix()
	}
}
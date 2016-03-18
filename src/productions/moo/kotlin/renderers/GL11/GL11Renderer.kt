package productions.moo.kotlin.renderers.GL11

import org.lwjgl.opengl.GL11.*
import productions.moo.kotlin.Node
import productions.moo.kotlin.math3d.Vector2
import productions.moo.kotlin.renderers.GLModelUtils
import productions.moo.kotlin.renderers.GLRenderer

class GL11Renderer : GLRenderer()
{
	override val modelUtils: GLModelUtils = GL11ModelUtils()

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
		glMultMatrixf(node.matrix.buffer)

		// Render meshes
		for (mesh in node.meshes)
		{
			mesh.rendererID?.let {
				glCallList(mesh.rendererID!!)
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
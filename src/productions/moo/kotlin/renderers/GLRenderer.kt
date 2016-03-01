package productions.moo.kotlin.renderers

import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GLCapabilities
import productions.moo.kotlin.Color
import productions.moo.kotlin.Node
import productions.moo.kotlin.Renderable
import productions.moo.kotlin.math3d.Vector2
import productions.moo.kotlin.models.Mesh
import productions.moo.kotlin.renderers.GL11.GL11Renderer

abstract class GLRenderer : Renderable
{
	companion object
	{
		fun getInstance(): GLRenderer?
		{
			if (GL.createCapabilities().OpenGL11)
			{
				return GL11Renderer()
			}
			else
			{
				return null
			}
		}
	}

	abstract val modelUtils: GLModelUtils
	val rootNode = Node()

	abstract fun initialize(frameBufferSize: Vector2)
	abstract fun resize(width: Int, height: Int)
	abstract fun setClearColor(color: Color)
}

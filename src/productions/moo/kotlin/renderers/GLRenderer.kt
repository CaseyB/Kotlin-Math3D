package productions.moo.kotlin.renderers

import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import productions.moo.kotlin.Color
import productions.moo.kotlin.Node
import productions.moo.kotlin.Renderable
import productions.moo.kotlin.math.Angle
import productions.moo.kotlin.math3d.Vector2
import productions.moo.kotlin.renderers.GL11.GL11Renderer
import productions.moo.kotlin.renderers.GL21.GL21Renderer

abstract class GLRenderer : Renderable
{
	companion object
	{
		fun getInstance(): GLRenderer?
		{
			val capabilities = GL.createCapabilities()

			if (capabilities.OpenGL21)
			{
				return GL21Renderer()
			}
			else if (capabilities.OpenGL11)
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

	fun resize(width: Int, height: Int)
	{
		val near = 0.1
		val far = 100.0

		GL11.glViewport(0, 0, width, height);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();

		val aspect = width / height.toDouble()
		val fov = Angle(degrees = 45f)

		val fH = Math.tan(fov.radians.toDouble()) * near
		val fW = fH * aspect
		GL11.glFrustum(-fW, fW, -fH, fH, near, far);


		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
	}

	fun setClearColor(color: Color)
	{
		GL11.glClearColor(color.red, color.green, color.blue, color.alpha)
	}
}

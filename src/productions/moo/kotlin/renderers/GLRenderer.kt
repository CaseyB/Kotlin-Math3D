package productions.moo.kotlin.renderers

import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import productions.moo.kotlin.Camera
import productions.moo.kotlin.Color
import productions.moo.kotlin.Node
import productions.moo.kotlin.Renderable
import productions.moo.kotlin.math3d.Vector2
import productions.moo.kotlin.renderers.GL11.GL11Renderer
import productions.moo.kotlin.renderers.GL21.GL21Renderer

abstract class GLRenderer : Renderable
{
	internal var cameras:MutableList<Camera> = mutableListOf()
	protected var width: Int = 0
	protected var height: Int = 0

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

	internal fun updateCamera()
	{
		resize(width, height)
	}

	fun resize(width: Int, height: Int)
	{
		this.width = width
		this.height = height

		if(cameras.size == 0)
		{
			return
		}

		val camera = cameras.first()

		GL11.glViewport(0, 0, width, height);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();

		val aspect = width / height.toDouble()

		val fH = Math.tan(camera.fov.radians.toDouble()) * camera.nearPlane
		val fW = fH * aspect
		GL11.glFrustum(-fW, fW, -fH, fH, camera.nearPlane.toDouble(), camera.farPlane.toDouble());


		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
	}

	fun setClearColor(color: Color)
	{
		GL11.glClearColor(color.red, color.green, color.blue, color.alpha)
	}

	fun getCamera(): Camera
	{
		if (cameras.size == 0)
		{
			cameras.add(Camera(this))
		}

		updateCamera()

		return cameras.first()
	}
}

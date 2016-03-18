package productions.moo.kotlin.renderers

import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import productions.moo.kotlin.Camera
import productions.moo.kotlin.Color
import productions.moo.kotlin.Light
import productions.moo.kotlin.Node
import productions.moo.kotlin.Renderable
import productions.moo.kotlin.math3d.Vector2
import productions.moo.kotlin.renderers.GL11.GL11Renderer
import productions.moo.kotlin.renderers.GL21.GL21Renderer

abstract class GLRenderer : Renderable
{
	internal var camera: Camera? = null
	internal var lights: MutableList<Light> = mutableListOf()

	protected var width: Int = 0
	protected var height: Int = 0
	
	protected val maxLights: Int

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
	
	protected constructor()
	{
		maxLights = glGetInteger(GL_MAX_LIGHTS)
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

		camera?.let { camera ->
 			glViewport(0, 0, width, height);

			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();

			val aspect = width / height.toDouble()

			val fH = Math.tan(camera.fov.radians.toDouble()) * camera.nearPlane
			val fW = fH * aspect
			glFrustum(-fW, fW, -fH, fH, camera.nearPlane.toDouble(), camera.farPlane.toDouble());

			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();
		}
	}

	fun setClearColor(color: Color)
	{
		glClearColor(color.red, color.green, color.blue, color.alpha)
	}

	fun getCamera(): Camera
	{
		if (camera == null)
		{
			camera = Camera(this)
		}

		updateCamera()

		return camera!!
	}

	/**
	 * Returns a light if it can or null if there aren't enough lights
	 */
	fun createLight(): Light?
	{
		if(lights.size < maxLights)
		{
			val light = Light(this)
			lights.add(light)
			return light
		}
		else
		{
			return null
		}
	}

	override fun render()
	{
		glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)

		glMatrixMode(GL_MODELVIEW)
		glLoadIdentity()

		camera?.let { camera ->
			// Set light position
			if (lights.size > 0)
			{
				val light = lights.first()
				glLightfv(GL_LIGHT0, GL_POSITION, light.lightPosition)
			}

			glMultMatrixf(camera.worldPosition.inverse().buffer)

			// Recursively render root node
			renderNode(rootNode)
		}
	}

	abstract protected fun renderNode(node: Node)
}

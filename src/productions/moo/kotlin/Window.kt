package productions.moo.kotlin

import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.glfw.GLFWKeyCallback

interface KeyDelegate
{
	fun keyEvent(key: Int, scanCode: Int, action: Int, mod: Int)
}

class Window(var title: String? = null, var width: Int = 800, var height: Int = 600) : Renderable
{
	var keyDelegate: KeyDelegate? = null

	private val _errorCallback: GLFWErrorCallback
	private val _keyCallback: GLFWKeyCallback

	private val _window: Long

	init
	{
		_errorCallback = GLFWErrorCallback.createPrint(System.err)
		GLFW.glfwSetErrorCallback(_errorCallback)

		if (GLFW.glfwInit() != GLFW.GLFW_TRUE)
		{
			throw IllegalStateException("Unable to initialize GLFW")
		}

		GLFW.glfwDefaultWindowHints()
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE)
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE)

		_window = GLFW.glfwCreateWindow(width, height, title ?: "", 0, 0)
		if (_window == 0L)
		{
			throw RuntimeException("Failed to create window")
		}

		_keyCallback = object : GLFWKeyCallback()
		{
			override fun invoke(window: kotlin.Long, key: kotlin.Int, scancode: kotlin.Int, action: kotlin.Int, mod: kotlin.Int)
			{
				keyDelegate?.keyEvent(key, scancode, action, mod)
			}
		}
		GLFW.glfwSetKeyCallback(_window, _keyCallback)

		// Get the resolution of the primary monitor
		val vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor())
		// Center our window
		GLFW.glfwSetWindowPos(_window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2)

		// Make the OpenGL context current
		GLFW.glfwMakeContextCurrent(_window)
		// Enable v-sync
		GLFW.glfwSwapInterval(1)

		// Make the window visible
		GLFW.glfwShowWindow(_window)
	}

	fun destroy()
	{
		GLFW.glfwDestroyWindow(_window)
		_keyCallback.release()

		GLFW.glfwTerminate()
		_errorCallback.release()
	}

	fun shouldClose() = GLFW.glfwWindowShouldClose(_window) == GLFW.GLFW_TRUE

	override fun preRender()
	{
		GLFW.glfwPollEvents()
	}

	override fun postRender()
	{
		GLFW.glfwSwapBuffers(_window)
	}
}

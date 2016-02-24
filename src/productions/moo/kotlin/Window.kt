package productions.moo.kotlin

import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWCursorEnterCallback
import org.lwjgl.glfw.GLFWCursorPosCallback
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.glfw.GLFWKeyCallback
import org.lwjgl.glfw.GLFWMouseButtonCallback
import org.lwjgl.glfw.GLFWWindowSizeCallback

interface KeyDelegate
{
	fun keyEvent(key: Int, scanCode: Int, action: Int, mods: Int)
}

interface MouseDelegate
{
	fun positionEvent(x: Float, y: Float)
	fun buttonEvent(button: Int, action: Int, mods: Int)
}

interface WindowDelegate
{
	fun resize(width: Int, height: Int)
}

class Window(var title: String? = null, var width: Int = 800, var height: Int = 600) : Renderable
{
	var keyDelegate: KeyDelegate? = null
	var windowDelegate: WindowDelegate? = null
	var mouseDelegate: MouseDelegate? = null

	val shouldClose: Boolean
		get() = GLFW.glfwWindowShouldClose(_window) == GLFW.GLFW_TRUE

	private val _errorCallback: GLFWErrorCallback

	private val _keyCallback: GLFWKeyCallback
	private val _cursorEnterCallback: GLFWCursorEnterCallback
	private val _cursorPosCallback: GLFWCursorPosCallback
	private val _mouseButtonCallback: GLFWMouseButtonCallback

	private var _mouseInWindow = false
	
	private val _windowSizeCallback: GLFWWindowSizeCallback

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
			override fun invoke(window: kotlin.Long, key: kotlin.Int, scancode: kotlin.Int, action: kotlin.Int, mods: kotlin.Int)
			{
				keyDelegate?.keyEvent(key, scancode, action, mods)
			}
		}
		GLFW.glfwSetKeyCallback(_window, _keyCallback)

		_windowSizeCallback = object : GLFWWindowSizeCallback()
		{
			override fun invoke(window: kotlin.Long, newWidth: kotlin.Int, newHeight: kotlin.Int)
			{
				width = newWidth
				height = newHeight

				windowDelegate?.resize(width, height)
			}
		}
		GLFW.glfwSetWindowSizeCallback(_window, _windowSizeCallback)

		_cursorEnterCallback = object : GLFWCursorEnterCallback()
		{
			override fun invoke(window: kotlin.Long, entered: kotlin.Int)
			{
				_mouseInWindow = (entered == GLFW.GLFW_TRUE)
			}
		}
		GLFW.glfwSetCursorEnterCallback(_window, _cursorEnterCallback)

		_cursorPosCallback = object : GLFWCursorPosCallback()
		{
			override fun invoke(window: kotlin.Long, x: kotlin.Double, y: kotlin.Double)
			{
				if (_mouseInWindow)
				{
					mouseDelegate?.positionEvent(x.toFloat(), y.toFloat())
				}
			}
		}
		GLFW.glfwSetCursorPosCallback(_window, _cursorPosCallback)

		_mouseButtonCallback = object : GLFWMouseButtonCallback()
		{
			override fun invoke(window: kotlin.Long, button: kotlin.Int, action: kotlin.Int, mods: kotlin.Int)
			{
				if (_mouseInWindow)
				{
					mouseDelegate?.buttonEvent(button, action, mods)
				}
			}
		}
		GLFW.glfwSetMouseButtonCallback(_window, _mouseButtonCallback)

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

	override fun preRender()
	{
		GLFW.glfwPollEvents()
	}

	override fun postRender()
	{
		GLFW.glfwSwapBuffers(_window)
	}
}

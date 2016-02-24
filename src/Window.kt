import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.glfw.GLFWKeyCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11

class Window
{
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

        _window = GLFW.glfwCreateWindow(300, 300, "Some Game", 0, 0)
        if (_window == 0L)
        {
            throw RuntimeException("Failed to create window")
        }

        _keyCallback = object: GLFWKeyCallback()
        {
            override fun invoke(window: kotlin.Long, key: kotlin.Int, scancode: kotlin.Int, action: kotlin.Int, mod: kotlin.Int)
            {
                if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE)
                {
                    GLFW.glfwSetWindowShouldClose(window, GLFW.GLFW_TRUE)
                }
            }
        }
        GLFW.glfwSetKeyCallback(_window, _keyCallback)

        // Get the resolution of the primary monitor
        val vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor())
        // Center our window
        GLFW.glfwSetWindowPos(_window, (vidmode.width() - 300) / 2, (vidmode.height() - 300) / 2)

        // Make the OpenGL context current
        GLFW.glfwMakeContextCurrent(_window)
        // Enable v-sync
        GLFW.glfwSwapInterval(1)

        // Make the window visible
        GLFW.glfwShowWindow(_window)
    }

    fun run()
    {
        loop()

        GLFW.glfwDestroyWindow(_window)
        _keyCallback.release()

        GLFW.glfwTerminate()
        _errorCallback.release()
    }

    private fun loop()
    {
        GL.createCapabilities()

        GL11.glClearColor(0.392f, 0.5843f, 0.9294f, 1.0f)

        while (GLFW.glfwWindowShouldClose(_window) == GLFW.GLFW_FALSE)
        {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)

            GLFW.glfwSwapBuffers(_window)
            GLFW.glfwPollEvents()
        }
    }
}
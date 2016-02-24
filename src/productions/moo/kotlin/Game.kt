package productions.moo.kotlin

import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11

class Game
{
	lateinit var window: Window
	var running = true

	inner class KeyHandler : KeyDelegate
	{
		override fun keyEvent(key: Int, scanCode: Int, action: Int, mod: Int)
		{
			if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE)
			{
				running = false
			}
		}

	}

	init
	{
		window = Window("Woot")
		window.keyDelegate = KeyHandler()

		GL.createCapabilities()

		val color = Color.CORNFLOWER_BLUE
		GL11.glClearColor(color.red, color.green, color.blue, color.alpha)

		while (running and !window.shouldClose())
		{
			window.preRender()

			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)

			window.postRender()
		}
	}
}
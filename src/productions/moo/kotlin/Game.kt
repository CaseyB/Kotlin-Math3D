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
		override fun keyEvent(key: Int, scanCode: Int, action: Int, mods: Int)
		{
			if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE)
			{
				running = false
			}
		}
	}

	inner class WindowHandler : WindowDelegate
	{
		override fun resize(width: Int, height: Int)
		{
			println("Window Resize ($width, $height)")
		}
	}

	inner class MouseHandler : MouseDelegate
	{
		override fun positionEvent(x: Float, y: Float)
		{
			println("Mouse Move ($x, $y)")
		}

		override fun buttonEvent(button: Int, action: Int, mods: Int)
		{
			println("Mouse Button: $button, $action, $mods")
		}
	}

	init
	{
		window = Window("Woot")
		window.keyDelegate = KeyHandler()
		window.windowDelegate = WindowHandler()
		window.mouseDelegate = MouseHandler()

		GL.createCapabilities()

		val color = Color.CORNFLOWER_BLUE
		GL11.glClearColor(color.red, color.green, color.blue, color.alpha)

		while (running and !window.shouldClose)
		{
			window.preRender()

			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)

			window.postRender()
		}

		window.destroy()
	}
}
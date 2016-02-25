package productions.moo.kotlin

import org.lwjgl.glfw.GLFW
import productions.moo.kotlin.renderers.GLRenderer

class Game
{
	val window: Window
	val renderer: GLRenderer

	var running = true

	val colors = listOf(Color.CORNFLOWER_BLUE, Color.RED, Color.GREEN, Color.BLUE)
	var currentColor = 0

	inner class KeyHandler : KeyDelegate
	{
		override fun keyEvent(key: Int, scanCode: Int, state: ButtonState, mods: Int)
		{
			if (key == GLFW.GLFW_KEY_ESCAPE && state == ButtonState.RELEASE)
			{
				running = false
			}
			else if ((key == GLFW.GLFW_KEY_LEFT || key == GLFW.GLFW_KEY_RIGHT) && state == ButtonState.PRESS)
			{
				if (key == GLFW.GLFW_KEY_LEFT)
				{
					currentColor--
				}
				else
				{
					currentColor++
				}

				currentColor = (currentColor % colors.size)
				if (currentColor < 0)
				{
					currentColor = 3
				}
				val color = colors[currentColor]

				renderer.setClearColor(color)
			}
		}
	}

	inner class WindowHandler : WindowDelegate
	{
		override fun resize(width: Int, height: Int)
		{
			//println("Window Resize ($width, $height)")
		}
	}

	inner class MouseHandler : MouseDelegate
	{
		override fun positionEvent(x: Float, y: Float)
		{
			//println("Mouse Move ($x, $y)")
		}

		override fun buttonEvent(button: Int, state: ButtonState, mods: Int)
		{
			//println("Mouse Button: $button, $state, $mods")
		}
	}

	init
	{
		window = Window("Woot")
		window.keyDelegate = KeyHandler()
		window.windowDelegate = WindowHandler()
		window.mouseDelegate = MouseHandler()

		renderer = GLRenderer.getInstance() ?: throw RuntimeException ("Failed to create OpenGL Renderer")
		renderer.setClearColor(Color.CORNFLOWER_BLUE)

		while (running and !window.shouldClose)
		{
			window.preRender()

			renderer.render()

			window.postRender()
		}

		window.destroy()
	}
}

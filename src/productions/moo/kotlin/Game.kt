package productions.moo.kotlin

import org.lwjgl.glfw.GLFW
import productions.moo.kotlin.math3d.Vector3
import productions.moo.kotlin.models.UNIT_PYRAMID
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
			// TODO: Abstract out key codes
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
			renderer.resize(width, height)
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
		window = Window("Woot", 800, 800)
		window.windowDelegate = WindowHandler()
		window.keyDelegate = KeyHandler()
		window.mouseDelegate = MouseHandler()

		renderer = GLRenderer.getInstance() ?: throw RuntimeException ("Failed to create OpenGL Renderer")
		renderer.initialize(800, 800)
		renderer.setClearColor(Color.CORNFLOWER_BLUE)

		val pyramid = UNIT_PYRAMID
		pyramid.position = Vector3(0f, 0f, -5f)

		// TODO: Meshes shouldn't be added directly to the renderer, they should be attached to the scenegraph
		renderer.addMesh(pyramid)

		while (running and !window.shouldClose)
		{
			// TODO: Have a list of renderables and just loop through and call their render functions
			window.preRender()

			renderer.render()

			window.postRender()
		}

		window.destroy()
	}
}

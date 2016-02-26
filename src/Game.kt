import org.lwjgl.glfw.GLFW
import productions.moo.kotlin.ButtonState
import productions.moo.kotlin.Color
import productions.moo.kotlin.KeyDelegate
import productions.moo.kotlin.MouseDelegate
import productions.moo.kotlin.Node
import productions.moo.kotlin.Window
import productions.moo.kotlin.WindowDelegate
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
		override fun windowSize(width: Int, height: Int)
		{
//			println("Window Size: ($width, $height)")
		}

		override fun frameBufferSize(width: Int, height: Int)
		{
			println("FrameBuffer Size: ($width, $height)")
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
		window = Window("Woot", 300, 300)
		window.windowDelegate = WindowHandler()
		window.keyDelegate = KeyHandler()
		window.mouseDelegate = MouseHandler()

		renderer = GLRenderer.getInstance() ?: throw RuntimeException ("Failed to create OpenGL Renderer")
		renderer.initialize(window.frameBufferSize)
		renderer.setClearColor(Color.CORNFLOWER_BLUE)

		val middle = Node(Vector3(0f, 0f, -5f))
		middle.addMesh(UNIT_PYRAMID)

		val right = Node(Vector3(2f, 0f, 0f))
		right.addMesh(UNIT_PYRAMID)
		middle.addChild(right)

		val left = Node(Vector3(-2f, 0f, 0f))
		left.addMesh(UNIT_PYRAMID)
		middle.addChild(left)

		renderer.rootNode.addChild(middle)

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

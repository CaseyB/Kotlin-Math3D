import productions.moo.kotlin.ButtonState
import productions.moo.kotlin.Color
import productions.moo.kotlin.KeyCode
import productions.moo.kotlin.KeyDelegate
import productions.moo.kotlin.ModiferKey
import productions.moo.kotlin.MouseDelegate
import productions.moo.kotlin.Node
import productions.moo.kotlin.Window
import productions.moo.kotlin.WindowDelegate
import productions.moo.kotlin.math.Angle
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

	val middle: Node

	inner class KeyHandler : KeyDelegate
	{
		override fun keyEvent(key: KeyCode, scanCode: Int, state: ButtonState, mods: Int)
		{
			if((mods and ModiferKey.SHIFT) != 0)
			{
				if (key == KeyCode.ESCAPE && state == ButtonState.RELEASE)
				{
					running = false
				}
				else if ((key == KeyCode.LEFT || key == KeyCode.RIGHT) && state == ButtonState.PRESS)
				{
					if (key == KeyCode.LEFT)
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
		window = Window("Woot", 800, 800)
		window.windowDelegate = WindowHandler()
		window.keyDelegate = KeyHandler()
		window.mouseDelegate = MouseHandler()

		renderer = GLRenderer.getInstance() ?: throw RuntimeException ("Failed to create OpenGL Renderer")
		renderer.initialize(window.frameBufferSize)
		renderer.setClearColor(Color.CORNFLOWER_BLUE)

		middle = Node()
		middle.setPosition(Vector3(0f, 0f, -3f))
		middle.addMesh(UNIT_PYRAMID)

		val right = Node()
		right.setPosition(Vector3(2f, 0f, 0f))
		right.addMesh(UNIT_PYRAMID)
		middle.addChild(right)

		val left = Node()
		left.setPosition(Vector3(-2f, 0f, 0f))
		left.addMesh(UNIT_PYRAMID)
		middle.addChild(left)

		renderer.rootNode.addChild(middle)

		var rot = Angle()

		// TODO: This loop should be in the renderer and we can have a callback that updates our stuff
		while (running and !window.shouldClose)
		{
			// TODO: Have a list of renderables and just loop through and call their render functions
			window.preRender()

			rot = Angle(radians = rot.radians + 0.01f)
			middle.setRotation(rot, 0f, 1f, 0f)
			left.setRotation(rot, 1f, 0f, 0f)
			right.setRotation(rot, -1f, 0f, 0f)

			renderer.render()

			window.postRender()
		}

		window.destroy()
	}
}

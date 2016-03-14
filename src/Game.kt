import productions.moo.kotlin.ButtonState
import productions.moo.kotlin.Color
import productions.moo.kotlin.KeyDelegate
import productions.moo.kotlin.KeyEvent
import productions.moo.kotlin.ModiferKey
import productions.moo.kotlin.MouseButtonEvent
import productions.moo.kotlin.MouseDelegate
import productions.moo.kotlin.Node
import productions.moo.kotlin.Window
import productions.moo.kotlin.WindowDelegate
import productions.moo.kotlin.math.Angle
import productions.moo.kotlin.math3d.Vector2
import productions.moo.kotlin.math3d.Vector3
import productions.moo.kotlin.models.UNIT_PYRAMID
import productions.moo.kotlin.renderers.GLModelUtils
import productions.moo.kotlin.renderers.GLRenderer

class Game
{
	val window: Window
	val renderer: GLRenderer
	val modelUtils: GLModelUtils

	var running = true

	val colors = listOf(Color.CORNFLOWER_BLUE, Color.RED, Color.GREEN, Color.BLUE)
	var currentColor = 0

	val middle: Node
	val cameraNode: Node
	val cameraRotX: Angle
	val cameraRotY: Angle
	val previousPosition = Vector2(0f, 0f)

	inner class KeyHandler : KeyDelegate
	{
		override fun keyEvent(event: KeyEvent, scanCode: Int, mods: Int)
		{
			if ((mods and ModiferKey.SHIFT) != 0)
			{
				if (event == KeyEvent.ESCAPE && event.state == ButtonState.RELEASE)
				{
					running = false
				}
				else if ((event == KeyEvent.LEFT || event == KeyEvent.RIGHT) && event.state == ButtonState.PRESS)
				{
					if (event == KeyEvent.LEFT)
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
				else if ((event == KeyEvent.UP || event == KeyEvent.DOWN))
				{
					if (event == KeyEvent.UP)
					{
						renderer.getCamera().nearPlane += 0.1f
					}
					else
					{
						renderer.getCamera().nearPlane -= 0.1f
					}
				}
			}
			else if (event.state == ButtonState.PRESS)
			{
				var position = cameraNode.position

				if (event == KeyEvent.LEFT)
				{
					position.x--
				}
				if (event == KeyEvent.RIGHT)
				{
					position.x++
				}
				if (event == KeyEvent.UP)
				{
					position.z--
				}
				if (event == KeyEvent.DOWN)
				{
					position.z++
				}
				cameraNode.position = position
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
			if (previousPosition != Vector2.ZERO)
			{
				val xOffset = previousPosition.x - x;
				val yOffset = previousPosition.y - y;

				cameraRotX.degrees += (xOffset * 0.1f)
				cameraRotY.degrees += (yOffset * 0.1f)
			}

			previousPosition.x = x
			previousPosition.y = y
		}

		override fun buttonEvent(event: MouseButtonEvent, mods: Int)
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

		val camera = renderer.getCamera()
		camera.nearPlane = 0.1f
		camera.farPlane = 100.0f
		camera.fov = Angle(degrees = 45.0f)

		modelUtils = renderer.modelUtils
		val pyramid = UNIT_PYRAMID
		modelUtils.loadMesh(pyramid)

		middle = Node()
		middle.position = Vector3(0f, 0f, -3f)
		middle.addMesh(pyramid)

		val right = Node()
		right.position = Vector3(2f, 0f, 0f)
		right.addMesh(pyramid)
		middle.addChild(right)

		val left = Node()
		left.position = Vector3(-2f, 0f, 0f)
		left.addMesh(pyramid)
		middle.addChild(left)

		renderer.rootNode.addChild(middle)

		cameraRotX = Angle()
		cameraRotY = Angle()
		cameraNode = Node()
		cameraNode.position = Vector3(0f, 0f, 5f)
		cameraNode.addCamera(camera)
		renderer.rootNode.addChild(cameraNode)

		var rot = Angle()

		// TODO: This loop should be in the renderer and we can have a callback that updates our stuff
		while (running and !window.shouldClose)
		{
			// TODO: Have a list of renderables and just loop through and call their render functions
			window.preRender()

			cameraNode.setRotation(cameraRotX, 0f, 1f, 0f)

//			rot = Angle(radians = rot.radians + 0.01f)
//			middle.setRotation(rot, 0f, 1f, 0f)
//			left.setRotation(rot, 1f, 0f, 0f)
//			right.setRotation(rot, -1f, 0f, 0f)

			renderer.render()

			window.postRender()
		}

		window.destroy()
	}
}

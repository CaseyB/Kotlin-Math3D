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

	val middle: Node
	val moveNode: Node
	val cameraRotX: Angle
	val cameraRotY: Angle
	val previousPosition = Vector2(0f, 0f)

	inner class KeyHandler : KeyDelegate
	{
		override fun keyEvent(event: KeyEvent, scanCode: Int, mods: Int)
		{
			if (event.state == ButtonState.PRESS)
			{
				var position = moveNode.position

				if (event == KeyEvent.LEFT || event == KeyEvent.A)
				{
					position.x--
				}
				if (event == KeyEvent.RIGHT || event == KeyEvent.D)
				{
					position.x++
				}
				if (event == KeyEvent.UP || event == KeyEvent.W)
				{
					position.z--
				}
				if (event == KeyEvent.DOWN || event == KeyEvent.S)
				{
					position.z++
				}

				moveNode.position = position
			}
			else
			{
				if(event == KeyEvent.ESCAPE)
				{
					window.captureMouse = !window.captureMouse
				}
			}
		}
	}

	inner class WindowHandler : WindowDelegate
	{
		override fun windowSize(width: Int, height: Int)
		{
			// Nothing to do here
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
			// Right now we don't care about mouse buttons
		}
	}

	init
	{
		window = Window("Woot", 800, 800)
		window.windowDelegate = WindowHandler()
		window.keyDelegate = KeyHandler()
		window.mouseDelegate = MouseHandler()
		window.captureMouse = true

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

		moveNode = Node()
		moveNode.position = Vector3(0f, 0f, 5f)

		val pitchNode = Node()
		pitchNode.addCamera(camera)
		moveNode.addChild(pitchNode)

		renderer.rootNode.addChild(moveNode)

		val light = renderer.createLight()
		val lightNode = Node()
		lightNode.position = Vector3(0f, 0f, 0f)
		lightNode.addLight(light!!)
		lightNode.addMesh(UNIT_PYRAMID)
		renderer.rootNode.addChild(lightNode)

		var rot = Angle()

		// TODO: This loop should be in the renderer and we can have a callback that updates our stuff
		while (running and !window.shouldClose)
		{
			// TODO: Have a list of renderables and just loop through and call their render functions
			window.preRender()

			moveNode.setRotation(cameraRotX, 0f, 1f, 0f)
			pitchNode.setRotation(cameraRotY, 1f, 0f, 0f)

			rot = Angle(radians = rot.radians + 0.01f)
			middle.setRotation(rot, 0f, 1f, 0f)
//			left.setRotation(rot, 1f, 0f, 0f)
//			right.setRotation(rot, -1f, 0f, 0f)

			renderer.render()

			window.postRender()
		}

		window.destroy()
	}
}

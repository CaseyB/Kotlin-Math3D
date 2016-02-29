package productions.moo.kotlin

import org.lwjgl.BufferUtils
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWCursorEnterCallback
import org.lwjgl.glfw.GLFWCursorPosCallback
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.glfw.GLFWFramebufferSizeCallback
import org.lwjgl.glfw.GLFWKeyCallback
import org.lwjgl.glfw.GLFWMouseButtonCallback
import org.lwjgl.glfw.GLFWWindowSizeCallback
import productions.moo.kotlin.math3d.Vector2

enum class ButtonState
{
	PRESS,
	RELEASE,
	REPEAT,
	UNSET;

	companion object
	{
		internal fun fromAction(action: Int): ButtonState
		{
			when (action)
			{
				GLFW.GLFW_PRESS   -> return PRESS
				GLFW.GLFW_RELEASE -> return RELEASE
				GLFW.GLFW_REPEAT  -> return REPEAT
				else              -> return UNSET
			}
		}
	}
}

enum class MouseButtonEvent(val windowCode: Int)
{
	UNKNOWN(-1),
	BUTTON_1(GLFW.GLFW_MOUSE_BUTTON_1),
	BUTTON_2(GLFW.GLFW_MOUSE_BUTTON_2),
	BUTTON_3(GLFW.GLFW_MOUSE_BUTTON_3),
	BUTTON_4(GLFW.GLFW_MOUSE_BUTTON_4),
	BUTTON_5(GLFW.GLFW_MOUSE_BUTTON_5),
	BUTTON_6(GLFW.GLFW_MOUSE_BUTTON_6),
	BUTTON_7(GLFW.GLFW_MOUSE_BUTTON_7),
	BUTTON_8(GLFW.GLFW_MOUSE_BUTTON_8),
	LEFT(GLFW.GLFW_MOUSE_BUTTON_LEFT),
	RIGHT(GLFW.GLFW_MOUSE_BUTTON_RIGHT),
	MIDDLE(GLFW.GLFW_MOUSE_BUTTON_MIDDLE);

	var state = ButtonState.UNSET

	companion object
	{
		internal fun fromWindowCode(buttonCode: Int, action: Int): MouseButtonEvent
		{
			var event: MouseButtonEvent
			when (buttonCode)
			{
				LEFT.windowCode     -> event = LEFT
				RIGHT.windowCode    -> event = RIGHT
				MIDDLE.windowCode   -> event = MIDDLE
				BUTTON_1.windowCode -> event = BUTTON_1
				BUTTON_2.windowCode -> event = BUTTON_2
				BUTTON_3.windowCode -> event = BUTTON_3
				BUTTON_4.windowCode -> event = BUTTON_4
				BUTTON_5.windowCode -> event = BUTTON_5
				BUTTON_6.windowCode -> event = BUTTON_6
				BUTTON_7.windowCode -> event = BUTTON_7
				BUTTON_8.windowCode -> event = BUTTON_8
				else                -> event = UNKNOWN
			}

			event.state = ButtonState.fromAction(action)
			return event
		}
	}
}

enum class KeyEvent(val windowCode: Int)
{
	UNKNOWN(GLFW.GLFW_KEY_UNKNOWN),
	SPACE(GLFW.GLFW_KEY_SPACE),
	APOSTROPHE(GLFW.GLFW_KEY_APOSTROPHE),
	COMMA(GLFW.GLFW_KEY_COMMA),
	MINUS(GLFW.GLFW_KEY_MINUS),
	PERIOD(GLFW.GLFW_KEY_PERIOD),
	SLASH(GLFW.GLFW_KEY_SLASH),
	KEY_0(GLFW.GLFW_KEY_0),
	KEY_1(GLFW.GLFW_KEY_1),
	KEY_2(GLFW.GLFW_KEY_2),
	KEY_3(GLFW.GLFW_KEY_3),
	KEY_4(GLFW.GLFW_KEY_4),
	KEY_5(GLFW.GLFW_KEY_5),
	KEY_6(GLFW.GLFW_KEY_6),
	KEY_7(GLFW.GLFW_KEY_7),
	KEY_8(GLFW.GLFW_KEY_8),
	KEY_9(GLFW.GLFW_KEY_9),
	SEMICOLON(GLFW.GLFW_KEY_SEMICOLON),
	EQUAL(GLFW.GLFW_KEY_EQUAL),
	A(GLFW.GLFW_KEY_A),
	B(GLFW.GLFW_KEY_B),
	C(GLFW.GLFW_KEY_C),
	D(GLFW.GLFW_KEY_D),
	E(GLFW.GLFW_KEY_E),
	F(GLFW.GLFW_KEY_F),
	G(GLFW.GLFW_KEY_G),
	H(GLFW.GLFW_KEY_H),
	I(GLFW.GLFW_KEY_I),
	J(GLFW.GLFW_KEY_J),
	K(GLFW.GLFW_KEY_K),
	L(GLFW.GLFW_KEY_L),
	M(GLFW.GLFW_KEY_M),
	N(GLFW.GLFW_KEY_N),
	O(GLFW.GLFW_KEY_O),
	P(GLFW.GLFW_KEY_P),
	Q(GLFW.GLFW_KEY_Q),
	R(GLFW.GLFW_KEY_R),
	S(GLFW.GLFW_KEY_S),
	T(GLFW.GLFW_KEY_T),
	U(GLFW.GLFW_KEY_U),
	V(GLFW.GLFW_KEY_V),
	W(GLFW.GLFW_KEY_W),
	X(GLFW.GLFW_KEY_X),
	Y(GLFW.GLFW_KEY_Y),
	Z(GLFW.GLFW_KEY_Z),
	LEFT_BRACKET(GLFW.GLFW_KEY_LEFT_BRACKET),
	BACKSLASH(GLFW.GLFW_KEY_BACKSLASH),
	RIGHT_BRACKET(GLFW.GLFW_KEY_RIGHT_BRACKET),
	GRAVE_ACCENT(GLFW.GLFW_KEY_GRAVE_ACCENT),
	WORLD_1(GLFW.GLFW_KEY_WORLD_1),
	WORLD_2(GLFW.GLFW_KEY_WORLD_2),
	ESCAPE(GLFW.GLFW_KEY_ESCAPE),
	ENTER(GLFW.GLFW_KEY_ENTER),
	TAB(GLFW.GLFW_KEY_TAB),
	BACKSPACE(GLFW.GLFW_KEY_BACKSPACE),
	INSERT(GLFW.GLFW_KEY_INSERT),
	DELETE(GLFW.GLFW_KEY_DELETE),
	RIGHT(GLFW.GLFW_KEY_RIGHT),
	LEFT(GLFW.GLFW_KEY_LEFT),
	DOWN(GLFW.GLFW_KEY_DOWN),
	UP(GLFW.GLFW_KEY_UP),
	PAGE_UP(GLFW.GLFW_KEY_PAGE_UP),
	PAGE_DOWN(GLFW.GLFW_KEY_PAGE_DOWN),
	HOME(GLFW.GLFW_KEY_HOME),
	END(GLFW.GLFW_KEY_END),
	CAPS_LOCK(GLFW.GLFW_KEY_CAPS_LOCK),
	SCROLL_LOCK(GLFW.GLFW_KEY_SCROLL_LOCK),
	NUM_LOCK(GLFW.GLFW_KEY_NUM_LOCK),
	PRINT_SCREEN(GLFW.GLFW_KEY_PRINT_SCREEN),
	PAUSE(GLFW.GLFW_KEY_PAUSE),
	F1(GLFW.GLFW_KEY_F1),
	F2(GLFW.GLFW_KEY_F2),
	F3(GLFW.GLFW_KEY_F3),
	F4(GLFW.GLFW_KEY_F4),
	F5(GLFW.GLFW_KEY_F5),
	F6(GLFW.GLFW_KEY_F6),
	F7(GLFW.GLFW_KEY_F7),
	F8(GLFW.GLFW_KEY_F8),
	F9(GLFW.GLFW_KEY_F9),
	F10(GLFW.GLFW_KEY_F10),
	F11(GLFW.GLFW_KEY_F11),
	F12(GLFW.GLFW_KEY_F12),
	F13(GLFW.GLFW_KEY_F13),
	F14(GLFW.GLFW_KEY_F14),
	F15(GLFW.GLFW_KEY_F15),
	F16(GLFW.GLFW_KEY_F16),
	F17(GLFW.GLFW_KEY_F17),
	F18(GLFW.GLFW_KEY_F18),
	F19(GLFW.GLFW_KEY_F19),
	F20(GLFW.GLFW_KEY_F20),
	F21(GLFW.GLFW_KEY_F21),
	F22(GLFW.GLFW_KEY_F22),
	F23(GLFW.GLFW_KEY_F23),
	F24(GLFW.GLFW_KEY_F24),
	F25(GLFW.GLFW_KEY_F25),
	NUMPAD_0(GLFW.GLFW_KEY_KP_0),
	NUMPAD_1(GLFW.GLFW_KEY_KP_1),
	NUMPAD_2(GLFW.GLFW_KEY_KP_2),
	NUMPAD_3(GLFW.GLFW_KEY_KP_3),
	NUMPAD_4(GLFW.GLFW_KEY_KP_4),
	NUMPAD_5(GLFW.GLFW_KEY_KP_5),
	NUMPAD_6(GLFW.GLFW_KEY_KP_6),
	NUMPAD_7(GLFW.GLFW_KEY_KP_7),
	NUMPAD_8(GLFW.GLFW_KEY_KP_8),
	NUMPAD_9(GLFW.GLFW_KEY_KP_9),
	NUMPAD_DECIMAL(GLFW.GLFW_KEY_KP_DECIMAL),
	NUMPAD_DIVIDE(GLFW.GLFW_KEY_KP_DIVIDE),
	NUMPAD_MULTIPLY(GLFW.GLFW_KEY_KP_MULTIPLY),
	NUMPAD_SUBTRACT(GLFW.GLFW_KEY_KP_SUBTRACT),
	NUMPAD_ADD(GLFW.GLFW_KEY_KP_ADD),
	NUMPAD_ENTER(GLFW.GLFW_KEY_KP_ENTER),
	NUMPAD_EQUAL(GLFW.GLFW_KEY_KP_EQUAL),
	LEFT_SHIFT(GLFW.GLFW_KEY_LEFT_SHIFT),
	LEFT_CONTROL(GLFW.GLFW_KEY_LEFT_CONTROL),
	LEFT_ALT(GLFW.GLFW_KEY_LEFT_ALT),
	LEFT_SUPER(GLFW.GLFW_KEY_LEFT_SUPER),
	RIGHT_SHIFT(GLFW.GLFW_KEY_RIGHT_SHIFT),
	RIGHT_CONTROL(GLFW.GLFW_KEY_RIGHT_CONTROL),
	RIGHT_ALT(GLFW.GLFW_KEY_RIGHT_ALT),
	RIGHT_SUPER(GLFW.GLFW_KEY_RIGHT_SUPER),
	MENU(GLFW.GLFW_KEY_MENU),
	LAST(GLFW.GLFW_KEY_LAST);

	var state: ButtonState = ButtonState.UNSET

	companion object
	{
		internal fun fromWindowCode(keyCode: Int, action: Int): KeyEvent
		{
			var event: KeyEvent
			when(keyCode)
			{
				SPACE.windowCode           -> event = SPACE
				APOSTROPHE.windowCode      -> event = APOSTROPHE
				COMMA.windowCode           -> event = COMMA
				MINUS.windowCode           -> event = MINUS
				PERIOD.windowCode          -> event = PERIOD
				SLASH.windowCode           -> event = SLASH
				KEY_0.windowCode           -> event = KEY_0
				KEY_1.windowCode           -> event = KEY_1
				KEY_2.windowCode           -> event = KEY_2
				KEY_3.windowCode           -> event = KEY_3
				KEY_4.windowCode           -> event = KEY_4
				KEY_5.windowCode           -> event = KEY_5
				KEY_6.windowCode           -> event = KEY_6
				KEY_7.windowCode           -> event = KEY_7
				KEY_8.windowCode           -> event = KEY_8
				KEY_9.windowCode           -> event = KEY_9
				SEMICOLON.windowCode       -> event = SEMICOLON
				EQUAL.windowCode           -> event = EQUAL
				A.windowCode               -> event = A
				B.windowCode               -> event = B
				C.windowCode               -> event = C
				D.windowCode               -> event = D
				E.windowCode               -> event = E
				F.windowCode               -> event = F
				G.windowCode               -> event = G
				H.windowCode               -> event = H
				I.windowCode               -> event = I
				J.windowCode               -> event = J
				K.windowCode               -> event = K
				L.windowCode               -> event = L
				M.windowCode               -> event = M
				N.windowCode               -> event = N
				O.windowCode               -> event = O
				P.windowCode               -> event = P
				Q.windowCode               -> event = Q
				R.windowCode               -> event = R
				S.windowCode               -> event = S
				T.windowCode               -> event = T
				U.windowCode               -> event = U
				V.windowCode               -> event = V
				W.windowCode               -> event = W
				X.windowCode               -> event = X
				Y.windowCode               -> event = Y
				Z.windowCode               -> event = Z
				LEFT_BRACKET.windowCode    -> event = LEFT_BRACKET
				BACKSLASH.windowCode       -> event = BACKSLASH
				RIGHT_BRACKET.windowCode   -> event = RIGHT_BRACKET
				GRAVE_ACCENT.windowCode    -> event = GRAVE_ACCENT
				WORLD_1.windowCode         -> event = WORLD_1
				WORLD_2.windowCode         -> event = WORLD_2
				ESCAPE.windowCode          -> event = ESCAPE
				ENTER.windowCode           -> event = ENTER
				TAB.windowCode             -> event = TAB
				BACKSPACE.windowCode       -> event = BACKSPACE
				INSERT.windowCode          -> event = INSERT
				DELETE.windowCode          -> event = DELETE
				RIGHT.windowCode           -> event = RIGHT
				LEFT.windowCode            -> event = LEFT
				DOWN.windowCode            -> event = DOWN
				UP.windowCode              -> event = UP
				PAGE_UP.windowCode         -> event = PAGE_UP
				PAGE_DOWN.windowCode       -> event = PAGE_DOWN
				HOME.windowCode            -> event = HOME
				END.windowCode             -> event = END
				CAPS_LOCK.windowCode       -> event = CAPS_LOCK
				SCROLL_LOCK.windowCode     -> event = SCROLL_LOCK
				NUM_LOCK.windowCode        -> event = NUM_LOCK
				PRINT_SCREEN.windowCode    -> event = PRINT_SCREEN
				PAUSE.windowCode           -> event = PAUSE
				F1.windowCode              -> event = F1
				F2.windowCode              -> event = F2
				F3.windowCode              -> event = F3
				F4.windowCode              -> event = F4
				F5.windowCode              -> event = F5
				F6.windowCode              -> event = F6
				F7.windowCode              -> event = F7
				F8.windowCode              -> event = F8
				F9.windowCode              -> event = F9
				F10.windowCode             -> event = F10
				F11.windowCode             -> event = F11
				F12.windowCode             -> event = F12
				F13.windowCode             -> event = F13
				F14.windowCode             -> event = F14
				F15.windowCode             -> event = F15
				F16.windowCode             -> event = F16
				F17.windowCode             -> event = F17
				F18.windowCode             -> event = F18
				F19.windowCode             -> event = F19
				F20.windowCode             -> event = F20
				F21.windowCode             -> event = F21
				F22.windowCode             -> event = F22
				F23.windowCode             -> event = F23
				F24.windowCode             -> event = F24
				F25.windowCode             -> event = F25
				NUMPAD_0.windowCode        -> event = NUMPAD_0
				NUMPAD_1.windowCode        -> event = NUMPAD_1
				NUMPAD_2.windowCode        -> event = NUMPAD_2
				NUMPAD_3.windowCode        -> event = NUMPAD_3
				NUMPAD_4.windowCode        -> event = NUMPAD_4
				NUMPAD_5.windowCode        -> event = NUMPAD_5
				NUMPAD_6.windowCode        -> event = NUMPAD_6
				NUMPAD_7.windowCode        -> event = NUMPAD_7
				NUMPAD_8.windowCode        -> event = NUMPAD_8
				NUMPAD_9.windowCode        -> event = NUMPAD_9
				NUMPAD_DECIMAL.windowCode  -> event = NUMPAD_DECIMAL
				NUMPAD_DIVIDE.windowCode   -> event = NUMPAD_DIVIDE
				NUMPAD_MULTIPLY.windowCode -> event = NUMPAD_MULTIPLY
				NUMPAD_SUBTRACT.windowCode -> event = NUMPAD_SUBTRACT
				NUMPAD_ADD.windowCode      -> event = NUMPAD_ADD
				NUMPAD_ENTER.windowCode    -> event = NUMPAD_ENTER
				NUMPAD_EQUAL.windowCode    -> event = NUMPAD_EQUAL
				LEFT_SHIFT.windowCode      -> event = LEFT_SHIFT
				LEFT_CONTROL.windowCode    -> event = LEFT_CONTROL
				LEFT_ALT.windowCode        -> event = LEFT_ALT
				LEFT_SUPER.windowCode      -> event = LEFT_SUPER
				RIGHT_SHIFT.windowCode     -> event = RIGHT_SHIFT
				RIGHT_CONTROL.windowCode   -> event = RIGHT_CONTROL
				RIGHT_ALT.windowCode       -> event = RIGHT_ALT
				RIGHT_SUPER.windowCode     -> event = RIGHT_SUPER
				MENU.windowCode            -> event = MENU
				LAST.windowCode            -> event = LAST
				else                       -> event = UNKNOWN
			}

			event.state = ButtonState.fromAction(action)
			return event
		}
	}
}

class ModiferKey()
{
	companion object
	{
		val SHIFT = 1
		val CONTROL = 2
		val ALT = 4
		val SUPER = 8
	}
}

interface KeyDelegate
{
	fun keyEvent(event: KeyEvent, scanCode: Int, mods: Int)
}

interface MouseDelegate
{
	fun positionEvent(x: Float, y: Float)
	fun buttonEvent(event: MouseButtonEvent, mods: Int)
}

interface WindowDelegate
{
	fun windowSize(width: Int, height: Int)
	fun frameBufferSize(width: Int, height: Int)
}

class Window(var title: String? = null, var width: Int = 800, var height: Int = 600) : Renderable
{
	var keyDelegate: KeyDelegate? = null
	var windowDelegate: WindowDelegate? = null
	var mouseDelegate: MouseDelegate? = null

	val shouldClose: Boolean
		get() = GLFW.glfwWindowShouldClose(_window) == GLFW.GLFW_TRUE

	val frameBufferSize: Vector2
		get()
		{
			var bufferWidth = BufferUtils.createIntBuffer(4)
			var bufferHeight = BufferUtils.createIntBuffer(4)

			GLFW.glfwGetFramebufferSize(_window, bufferWidth, bufferHeight)

			return Vector2(bufferWidth.get(0).toFloat(), bufferHeight.get(0).toFloat())
		}

	private val _errorCallback: GLFWErrorCallback

	private val _keyCallback: GLFWKeyCallback
	private val _cursorEnterCallback: GLFWCursorEnterCallback
	private val _cursorPosCallback: GLFWCursorPosCallback
	private val _mouseButtonCallback: GLFWMouseButtonCallback

	private var _mouseInWindow = false

	private val _frameBufferSizeCallback: GLFWFramebufferSizeCallback
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
				keyDelegate?.keyEvent(KeyEvent.fromWindowCode(key, action), scancode, mods)
			}
		}
		GLFW.glfwSetKeyCallback(_window, _keyCallback)

		_windowSizeCallback = object : GLFWWindowSizeCallback()
		{
			override fun invoke(window: kotlin.Long, newWidth: kotlin.Int, newHeight: kotlin.Int)
			{
				width = newWidth
				height = newHeight

				windowDelegate?.windowSize(width, height)
			}
		}
		GLFW.glfwSetWindowSizeCallback(_window, _windowSizeCallback)

		_frameBufferSizeCallback = object : GLFWFramebufferSizeCallback()
		{
			override fun invoke(window: kotlin.Long, width: kotlin.Int, height: kotlin.Int)
			{
				windowDelegate?.frameBufferSize(width, height)
			}
		}
		GLFW.glfwSetFramebufferSizeCallback(_window, _frameBufferSizeCallback)

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
					mouseDelegate?.buttonEvent(MouseButtonEvent.fromWindowCode(button, action), mods)
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

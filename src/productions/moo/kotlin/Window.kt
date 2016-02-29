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
	REPEAT;

	companion object
	{
		internal fun fromAction(action: Int): ButtonState
		{
			var state: ButtonState = PRESS
			when (action)
			{
				GLFW.GLFW_PRESS   -> state = PRESS
				GLFW.GLFW_RELEASE -> state = RELEASE
				GLFW.GLFW_REPEAT  -> state = REPEAT
			}

			return state
		}
	}
}

enum class KeyCode(val windowCode: Int)
{
	UNKNOWN(-1),
	SPACE(32),
	APOSTROPHE(39),
	COMMA(44),
	MINUS(45),
	PERIOD(46),
	SLASH(47),
	ZERO(48),
	ONE(49),
	TWO(50),
	THREE(51),
	FOUR(52),
	FIVE(53),
	SIX(54),
	SEVEN(55),
	EIGHT(56),
	NINE(57),
	SEMICOLON(59),
	EQUAL(61),
	A(65),
	B(66),
	C(67),
	D(68),
	E(69),
	F(70),
	G(71),
	H(72),
	I(73),
	J(74),
	K(75),
	L(76),
	M(77),
	N(78),
	O(79),
	P(80),
	Q(81),
	R(82),
	S(83),
	T(84),
	U(85),
	V(86),
	W(87),
	X(88),
	Y(89),
	Z(90),
	LEFT_BRACKET(91),
	BACKSLASH(92),
	RIGHT_BRACKET(93),
	GRAVE_ACCENT(96),
	WORLD_1(161),
	WORLD_2(162),
	ESCAPE(256),
	ENTER(257),
	TAB(258),
	BACKSPACE(259),
	INSERT(260),
	DELETE(261),
	RIGHT(262),
	LEFT(263),
	DOWN(264),
	UP(265),
	PAGE_UP(266),
	PAGE_DOWN(267),
	HOME(268),
	END(269),
	CAPS_LOCK(280),
	SCROLL_LOCK(281),
	NUM_LOCK(282),
	PRINT_SCREEN(283),
	PAUSE(284),
	F1(290),
	F2(291),
	F3(292),
	F4(293),
	F5(294),
	F6(295),
	F7(296),
	F8(297),
	F9(298),
	F10(299),
	F11(300),
	F12(301),
	F13(302),
	F14(303),
	F15(304),
	F16(305),
	F17(306),
	F18(307),
	F19(308),
	F20(309),
	F21(310),
	F22(311),
	F23(312),
	F24(313),
	F25(314),
	KP_0(320),
	KP_1(321),
	KP_2(322),
	KP_3(323),
	KP_4(324),
	KP_5(325),
	KP_6(326),
	KP_7(327),
	KP_8(328),
	KP_9(329),
	KP_DECIMAL(330),
	KP_DIVIDE(331),
	KP_MULTIPLY(332),
	KP_SUBTRACT(333),
	KP_ADD(334),
	KP_ENTER(335),
	KP_EQUAL(336),
	LEFT_SHIFT(340),
	LEFT_CONTROL(341),
	LEFT_ALT(342),
	LEFT_SUPER(343),
	RIGHT_SHIFT(344),
	RIGHT_CONTROL(345),
	RIGHT_ALT(346),
	RIGHT_SUPER(347),
	MENU(348),
	LAST(348);

	companion object
	{
		internal fun fromWindowCode(keyCode: Int): KeyCode
		{
			when(keyCode)
			{
				SPACE.windowCode -> return SPACE
				APOSTROPHE.windowCode -> return APOSTROPHE
				COMMA.windowCode -> return COMMA
				MINUS.windowCode -> return MINUS
				PERIOD.windowCode -> return PERIOD
				SLASH.windowCode -> return SLASH
				ZERO.windowCode -> return ZERO
				ONE.windowCode -> return ONE
				TWO.windowCode -> return TWO
				THREE.windowCode -> return THREE
				FOUR.windowCode -> return FOUR
				FIVE.windowCode -> return FIVE
				SIX.windowCode -> return SIX
				SEVEN.windowCode -> return SEVEN
				EIGHT.windowCode -> return EIGHT
				NINE.windowCode -> return NINE
				SEMICOLON.windowCode -> return SEMICOLON
				EQUAL.windowCode -> return EQUAL
				A.windowCode -> return A
				B.windowCode -> return B
				C.windowCode -> return C
				D.windowCode -> return D
				E.windowCode -> return E
				F.windowCode -> return F
				G.windowCode -> return G
				H.windowCode -> return H
				I.windowCode -> return I
				J.windowCode -> return J
				K.windowCode -> return K
				L.windowCode -> return L
				M.windowCode -> return M
				N.windowCode -> return N
				O.windowCode -> return O
				P.windowCode -> return P
				Q.windowCode -> return Q
				R.windowCode -> return R
				S.windowCode -> return S
				T.windowCode -> return T
				U.windowCode -> return U
				V.windowCode -> return V
				W.windowCode -> return W
				X.windowCode -> return X
				Y.windowCode -> return Y
				Z.windowCode -> return Z
				LEFT_BRACKET.windowCode -> return LEFT_BRACKET
				BACKSLASH.windowCode -> return BACKSLASH
				RIGHT_BRACKET.windowCode -> return RIGHT_BRACKET
				GRAVE_ACCENT.windowCode -> return GRAVE_ACCENT
				WORLD_1.windowCode -> return WORLD_1
				WORLD_2.windowCode -> return WORLD_2
				ESCAPE.windowCode -> return ESCAPE
				ENTER.windowCode -> return ENTER
				TAB.windowCode -> return TAB
				BACKSPACE.windowCode -> return BACKSPACE
				INSERT.windowCode -> return INSERT
				DELETE.windowCode -> return DELETE
				RIGHT.windowCode -> return RIGHT
				LEFT.windowCode -> return LEFT
				DOWN.windowCode -> return DOWN
				UP.windowCode -> return UP
				PAGE_UP.windowCode -> return PAGE_UP
				PAGE_DOWN.windowCode -> return PAGE_DOWN
				HOME.windowCode -> return HOME
				END.windowCode -> return END
				CAPS_LOCK.windowCode -> return CAPS_LOCK
				SCROLL_LOCK.windowCode -> return SCROLL_LOCK
				NUM_LOCK.windowCode -> return NUM_LOCK
				PRINT_SCREEN.windowCode -> return PRINT_SCREEN
				PAUSE.windowCode -> return PAUSE
				F1.windowCode -> return F1
				F2.windowCode -> return F2
				F3.windowCode -> return F3
				F4.windowCode -> return F4
				F5.windowCode -> return F5
				F6.windowCode -> return F6
				F7.windowCode -> return F7
				F8.windowCode -> return F8
				F9.windowCode -> return F9
				F10.windowCode -> return F10
				F11.windowCode -> return F11
				F12.windowCode -> return F12
				F13.windowCode -> return F13
				F14.windowCode -> return F14
				F15.windowCode -> return F15
				F16.windowCode -> return F16
				F17.windowCode -> return F17
				F18.windowCode -> return F18
				F19.windowCode -> return F19
				F20.windowCode -> return F20
				F21.windowCode -> return F21
				F22.windowCode -> return F22
				F23.windowCode -> return F23
				F24.windowCode -> return F24
				F25.windowCode -> return F25
				KP_0.windowCode -> return KP_0
				KP_1.windowCode -> return KP_1
				KP_2.windowCode -> return KP_2
				KP_3.windowCode -> return KP_3
				KP_4.windowCode -> return KP_4
				KP_5.windowCode -> return KP_5
				KP_6.windowCode -> return KP_6
				KP_7.windowCode -> return KP_7
				KP_8.windowCode -> return KP_8
				KP_9.windowCode -> return KP_9
				KP_DECIMAL.windowCode -> return KP_DECIMAL
				KP_DIVIDE.windowCode -> return KP_DIVIDE
				KP_MULTIPLY.windowCode -> return KP_MULTIPLY
				KP_SUBTRACT.windowCode -> return KP_SUBTRACT
				KP_ADD.windowCode -> return KP_ADD
				KP_ENTER.windowCode -> return KP_ENTER
				KP_EQUAL.windowCode -> return KP_EQUAL
				LEFT_SHIFT.windowCode -> return LEFT_SHIFT
				LEFT_CONTROL.windowCode -> return LEFT_CONTROL
				LEFT_ALT.windowCode -> return LEFT_ALT
				LEFT_SUPER.windowCode -> return LEFT_SUPER
				RIGHT_SHIFT.windowCode -> return RIGHT_SHIFT
				RIGHT_CONTROL.windowCode -> return RIGHT_CONTROL
				RIGHT_ALT.windowCode -> return RIGHT_ALT
				RIGHT_SUPER.windowCode -> return RIGHT_SUPER
				MENU.windowCode -> return MENU
				LAST.windowCode -> return LAST
				else -> return UNKNOWN
			}
		}
	}
}

class ModiferKey(val windowCode: Int)
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
	fun keyEvent(key: KeyCode, scanCode: Int, state: ButtonState, mods: Int)
}

interface MouseDelegate
{
	fun positionEvent(x: Float, y: Float)
	fun buttonEvent(button: Int, state: ButtonState, mods: Int)
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
				keyDelegate?.keyEvent(KeyCode.fromWindowCode(key), scancode, ButtonState.fromAction(action), mods)
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
					mouseDelegate?.buttonEvent(button, ButtonState.fromAction(action), mods)
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

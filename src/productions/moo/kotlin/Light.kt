package productions.moo.kotlin

import productions.moo.kotlin.renderers.GLRenderer
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

class Light : MovableObject
{
	internal val positionBuffer: FloatBuffer = ByteBuffer.allocateDirect(4 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer()

	enum class Type
	{
		POINT,
		DIRECTIONAL,
		SPOT
	}

	var _renderer: GLRenderer

	// Hide this away from prying eyes
	internal constructor(renderer: GLRenderer)
	{
		_renderer = renderer
	}

	var ambiantColor = Color.WHITE
	var diffuseColor = Color.WHITE
	var specularColor = Color.WHITE

	internal val lightPosition: FloatBuffer
		get()
		{
			val pos = worldPosition.getTranslation()
			positionBuffer.put(0, pos.x)
			positionBuffer.put(1, pos.y)
			positionBuffer.put(2, pos.z)
			positionBuffer.put(3, 1f)
			return positionBuffer
		}
}
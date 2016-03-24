package productions.moo.kotlin

import productions.moo.kotlin.math.Angle
import productions.moo.kotlin.renderers.GLRenderer

class Camera : MovableObject
{
	var _renderer: GLRenderer

	internal constructor(renderer: GLRenderer)
	{
		_renderer = renderer
	}

	var nearPlane: Float = 0.1f
		get() = field
		set(value)
		{
			field = if (value < 0 ) 0f else value
			_renderer.updateCamera()
		}


	var farPlane  = 100.0f
		get() = field
		set(value)
		{
			field = if (value < 0 ) 0f else value
			_renderer.updateCamera()
		}

	var fov = Angle(degrees = 45.0f)
		get() = field
		set(value)
		{
			field = value
			_renderer.updateCamera()
		}
}

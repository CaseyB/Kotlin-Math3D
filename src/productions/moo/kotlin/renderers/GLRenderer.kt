package productions.moo.kotlin.renderers

import org.lwjgl.opengl.GL
import productions.moo.kotlin.Color
import productions.moo.kotlin.Renderable

abstract class GLRenderer: Renderable
{
	companion object
	{
		fun getInstance(): GLRenderer?
		{
			val capabilities = GL.createCapabilities()
			if(capabilities.OpenGL11)
			{
				return GL11Renderer()
			}
			else
			{
				return null
			}
		}
	}

	abstract fun setClearColor(color: Color)
}

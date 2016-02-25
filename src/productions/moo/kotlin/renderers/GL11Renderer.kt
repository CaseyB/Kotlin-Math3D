package productions.moo.kotlin.renderers

import org.lwjgl.opengl.GL11
import productions.moo.kotlin.Color

class GL11Renderer : GLRenderer()
{
	override fun setClearColor(color: Color)
	{
		GL11.glClearColor(color.red, color.green, color.blue, color.alpha)
	}

	override fun render ()
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
	}
}
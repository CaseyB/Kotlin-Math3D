package productions.moo.kotlin.renderers

import org.lwjgl.opengl.GL11
import productions.moo.kotlin.Color
import productions.moo.kotlin.Node
import productions.moo.kotlin.math.Angle
import productions.moo.kotlin.math3d.Vector2
import productions.moo.kotlin.models.Mesh

class GL11Renderer : GLRenderer()
{
	override fun initialize(frameBufferSize: Vector2)
	{
		resize(frameBufferSize.x.toInt(), frameBufferSize.y.toInt())

		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glClearDepth(1.0);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
	}

	override fun resize(width: Int, height: Int)
	{
		val near = 0.1
		val far = 100.0

		GL11.glViewport(0, 0, width, height);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();

		val aspect = width / height.toDouble()
		val fov = Angle(degrees = 45f)

		val fH = Math.tan(fov.radians.toDouble()) * near
		val fW = fH * aspect
		GL11.glFrustum(-fW, fW, -fH, fH, near, far);


		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
	}

	private fun perspective(fovY: Double, aspect: Double, near: Double, far: Double)
	{

	}

	override fun setClearColor(color: Color)
	{
		GL11.glClearColor(color.red, color.green, color.blue, color.alpha)
	}

	override fun render()
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)

		GL11.glMatrixMode(GL11.GL_MODELVIEW)
		GL11.glLoadIdentity()

		// Recursively render root node
		renderNode(rootNode)
	}

	private fun renderNode(node: Node)
	{
		GL11.glPushMatrix()

		GL11.glMultMatrixf(node.matrix._buffer)

		// Render meshes
		for (mesh in node.meshes)
		{
			mesh.indicies?.let {
				GL11.glBegin(GL11.GL_TRIANGLES)

				for (index in it)
				{
					mesh.vertexColors?.let {
						val color = it[index]
						GL11.glColor3f(color.red, color.green, color.blue)
					}

					mesh.verticies?.let {
						val vec = it[index]
						GL11.glVertex3f(vec.x, vec.y, vec.z)
					}
				}

				GL11.glEnd()
			}
		}

		// Render children
		for (child in node.children)
		{
			renderNode(child)
		}

		GL11.glPopMatrix()
	}
}
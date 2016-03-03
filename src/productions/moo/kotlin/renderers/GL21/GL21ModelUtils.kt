package productions.moo.kotlin.renderers.GL21

import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL15.*
import org.lwjgl.opengl.GL20.*
import org.lwjgl.opengl.GL21.*
import productions.moo.kotlin.models.Mesh
import productions.moo.kotlin.renderers.GLModelUtils
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.IntBuffer

class GL21ModelUtils : GLModelUtils
{
	override fun loadMesh(mesh: Mesh)
	{
		// TODO: Only 1 array per model for now
		val buffersNeeded = getBufferCount(mesh)
		if (buffersNeeded > 0)
		{
			if (mesh.verticies != null)
			{
				val buffer = glGenBuffers()

				// Create data buffers
				val vertBuffer: FloatBuffer = ByteBuffer.allocateDirect(mesh.verticies!!.size * 3 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer()
				vertBuffer.rewind()

				// Fill data buffers
				for ((index, vert) in mesh.verticies!!.withIndex())
				{
					vertBuffer.put(index * 3, vert.x)
					vertBuffer.put((index * 3) + 1, vert.y)
					vertBuffer.put((index * 3) + 2, vert.z)
				}
				vertBuffer.rewind()

				// Bind data buffers
				glBindBuffer(GL_ARRAY_BUFFER, buffer)
				glBufferData(GL_ARRAY_BUFFER, vertBuffer, GL_STATIC_DRAW)
				vertBuffer.rewind()

				mesh.vertexBuffer = buffer
			}

			if (mesh.indicies != null)
			{
				val buffer = glGenBuffers()

				// Create data buffers
				val indexBuffer: IntBuffer = ByteBuffer.allocateDirect(mesh.indicies!!.size * 4).order(ByteOrder.nativeOrder()).asIntBuffer()
				indexBuffer.rewind()

				// Fill data buffers
				for ((index, ind) in mesh.indicies!!.withIndex())
				{
					indexBuffer.put(index, ind)
				}
				indexBuffer.rewind()

				// Bind data buffers
				glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, buffer)
				glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW)
				indexBuffer.rewind()

				mesh.indexBuffer = buffer
			}

			if (mesh.vertexColors != null)
			{
				val buffer = glGenBuffers()

				// Create data buffers
				val colorBuffer: FloatBuffer = ByteBuffer.allocateDirect(mesh.vertexColors!!.size * 4 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer()

				// Fill data buffers
				for ((index, color) in mesh.vertexColors!!.withIndex())
				{
					colorBuffer.put( index * 4, color.red)
					colorBuffer.put((index * 4) + 1, color.green)
					colorBuffer.put((index * 4) + 2, color.blue)
					colorBuffer.put((index * 4) + 3, color.alpha)
				}

				// Bind data buffers
				glBindBuffer(GL_ARRAY_BUFFER, buffer)
				glBufferData(GL_ARRAY_BUFFER, colorBuffer, GL_STATIC_DRAW)

				mesh.colorBuffer = buffer
			}
		}
	}

	private fun getBufferCount(mesh: Mesh): Int
	{
		var count = 0
		if (mesh.verticies != null) count++
		if (mesh.indicies != null) count++
		if (mesh.vertexColors != null) count++
		if (mesh.textureCoords != null) count++
		return count
	}
}

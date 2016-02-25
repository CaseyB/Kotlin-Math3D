package productions.moo.kotlin.models

import productions.moo.kotlin.Color
import productions.moo.kotlin.math3d.Vector2
import productions.moo.kotlin.math3d.Vector3

class Mesh(var verticies: Array<Vector3>? = null, var indicies: Array<Int>? = null, var vertexColors: Array<Color>? = null, var textureCoords: Array<Vector2>? = null)
{
}

val UNIT_PLANE = Mesh(
		arrayOf(
				Vector3(-0.5f, -0.5f, 0.0f),
				Vector3(-0.5f, 0.5f, 0.0f),
				Vector3(0.5f, 0.5f, 0.0f),
				Vector3(0.5f, -0.5f, 0.0f)
		),
		arrayOf(0, 1, 2, 3))
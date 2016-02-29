package productions.moo.kotlin.models

import productions.moo.kotlin.Color
import productions.moo.kotlin.math3d.Vector2
import productions.moo.kotlin.math3d.Vector3

// TODO: This constructor is UGLY!
class Mesh(var verticies: Array<Vector3>? = null, var indicies: Array<Int>? = null, var vertexColors: Array<Color>? = null, var textureCoords: Array<Vector2>? = null)
{
}

val UNIT_TRIANLGE = Mesh(
		arrayOf(
				Vector3(-0.5f, -0.5f, 0.0f),
				Vector3(0.0f, 0.5f, 0.0f),
				Vector3(0.5f, -0.5f, 0.0f)
		),
		arrayOf(0, 1, 2)
)

val UNIT_PLANE = Mesh(
		arrayOf(
				Vector3(-0.5f, -0.5f, 0.0f),
				Vector3(-0.5f, 0.5f, 0.0f),
				Vector3(0.5f, 0.5f, 0.0f),
				Vector3(0.5f, -0.5f, 0.0f)
		),
		arrayOf(0, 1, 2, 2, 3, 0)
)

val UNIT_PYRAMID = Mesh(
		arrayOf(
				Vector3(0.0f, 0.5f, 0.0f),
				Vector3(-0.5f, -0.5f, 0.5f),
				Vector3(-0.5f, -0.5f, -0.5f),
				Vector3(0.5f, -0.5f, -0.5f),
				Vector3(0.5f, -0.5f, 0.5f)
		),
		arrayOf(1, 0, 4, 2, 0, 1, 3, 0, 2, 4, 0, 3, 1, 4, 2, 2, 4, 3),
		arrayOf(
				Color.RED,
				Color.GREEN,
				Color.BLUE,
				Color.WHITE,
				Color.BLACK
		)
)

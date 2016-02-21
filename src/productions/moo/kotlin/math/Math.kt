@file:JvmName("Math")

package productions.moo.kotlin.math

fun Float.clamp (min: Float, max: Float) = Math.min(Math.max(this, min), max)
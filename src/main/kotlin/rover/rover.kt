package rover

import rover.Direction.*
import rover.Move.*

data class Position(val x: Int, val y: Int, val direction: Direction) {

    fun moveByMany(moves: String): Position =
        moves.toCharArray().fold(this) { accumulator, moveChar ->
            val move = Move.parse(moveChar.toString())
            accumulator.moveByOne(move)
        }

    fun moveByOne(move: Move): Position {
        val (newX, newY) = changeCoordinates(move)
        return Position(newX, newY, changeDirection(move))
    }

    private fun changeCoordinates(move: Move) = when (move) {
        LEFT, RIGHT -> x to y
        FORWARD -> when (direction) {
            NORTH -> x to (y + 1)
            WEST -> (x - 1) to y
            SOUTH -> x to (y - 1)
            EAST -> (x + 1) to y
        }
    }

    private fun changeDirection(move: Move) = when (move) {
        FORWARD -> direction
        LEFT -> when (direction) {
            NORTH -> WEST
            WEST -> SOUTH
            SOUTH -> EAST
            EAST -> NORTH
        }
        RIGHT -> when (direction) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
        }
    }
}

enum class Direction {
    NORTH, EAST, SOUTH, WEST
}

enum class Move {
    LEFT, RIGHT, FORWARD;

    companion object {
        fun parse(s: String): Move = when (s) {
            "L" -> LEFT
            "R" -> RIGHT
            "F" -> FORWARD
            else -> throw IllegalArgumentException(s)
        }
    }
}


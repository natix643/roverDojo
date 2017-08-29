package rover

import spock.lang.Specification
import spock.lang.Unroll

import static rover.Direction.*
import static rover.Move.*

class roverTest extends Specification {

    @Unroll
    "should turn left from #oldDirection"() {
        given:
        def position = new Position(0, 0, oldDirection)

        expect:
        position.moveByOne(LEFT) == new Position(0, 0, newDirection)

        where:
        oldDirection | newDirection
        NORTH        | WEST
        EAST         | NORTH
        WEST         | SOUTH
        SOUTH        | EAST
    }

    @Unroll
    "should turn right from #oldDirection"() {
        given:
        def position = new Position(0, 0, oldDirection)

        expect:
        position.moveByOne(RIGHT) == new Position(0, 0, newDirection)

        where:
        oldDirection | newDirection
        NORTH        | EAST
        EAST         | SOUTH
        WEST         | NORTH
        SOUTH        | WEST
    }

    @Unroll
    "should not turn from #oldDirection"() {
        given:
        def position = new Position(0, 0, oldDirection)

        expect:
        position.moveByOne(FORWARD).direction == newDirection

        where:
        oldDirection | newDirection
        NORTH        | NORTH
        EAST         | EAST
        WEST         | WEST
        SOUTH        | SOUTH
    }

    @Unroll
    "should move by one to #direction"() {
        given:
        def position = new Position(0, 0, direction)

        expect:
        position.moveByOne(FORWARD) == new Position(newX, newY, direction)

        where:
        direction | newX | newY
        NORTH     | 0    | 1
        EAST      | 1    | 0
        SOUTH     | 0    | -1
        WEST      | -1   | 0
    }

    @Unroll
    def "should move by many '#movements'"() {
        given:
        def position = new Position(0, 0, NORTH)

        expect:
        position.moveByMany(movements) == new Position(x, y, direction)

        where:
        movements  | x | y  | direction
        "LFLFLFLF" | 0 | 0  | NORTH
        "FFF"      | 0 | 3  | NORTH
        "RFF"      | 2 | 0  | EAST
        "RR"       | 0 | 0  | SOUTH
        "RFRFRF"   | 0 | -1 | WEST
    }
}

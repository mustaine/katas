package com.jspcore.rover;

import com.jspcore.entity.Rover;
import com.jspcore.values.Command;
import com.jspcore.values.Coordinate;
import com.jspcore.values.Direction;
import com.jspcore.values.Point;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class RoverShould {

    private Rover rover;
    final Point x = Point.create(3);
    final Point y = Point.create(4);


    @Before public void
    setUp() {
        final Coordinate startingPoint = Coordinate.create(x, y);
        rover = new Rover(startingPoint, Direction.NORTH);
    }

    @Test public void
    init_rover_with_coordinates_and_direction() {
        assertThat(rover.startPoint().x().location(), is(3));
        assertThat(rover.startPoint().y().location(), is(4));
        assertThat(rover.facing(), is(Direction.NORTH));
    }

    @Test public void
    increase_y_when_move_forward_and_north() {
        rover.command(Command.FORWARD.command());
        assertThat(rover.position(), equalTo(Coordinate.create(
                Point.create(rover.startPoint().x().location()), Point.create(rover.startPoint().y().location() + 1))));
    }

    @Test public void
    turn_left_when_command_left() {
        rover.command(Command.LEFT.command());
        assertThat(rover.facing(), equalTo(Direction.WEST));
    }

    @Test public void
    turn_right_when_command_right() {
        rover.command(Command.RIGHT.command());
        assertThat(rover.facing(), equalTo(Direction.EAST));
    }

    @Test public void
    move_up_when_command_forward_and_facing_north() {
        rover.command(Command.FORWARD.command());
        assertThat(rover.position(), is(Coordinate.create(x, y.increase())));
    }

    @Test public void
    move_down_when_command_backward_and_facing_north() {
        rover.command(Command.BACKWARD.command());
        assertThat(rover.position(), is(Coordinate.create(x, y.decrease())));
    }
}
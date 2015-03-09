package com.jspcore.entity;

import com.jspcore.values.Command;
import com.jspcore.values.Coordinate;
import com.jspcore.values.Direction;
import org.apache.commons.lang.StringUtils;

public class Rover {
    private Coordinate startPoint;
    private Coordinate position;
    private Direction direction;

    public Rover(Coordinate coordinate, Direction direction) {
        this.startPoint = coordinate;
        this.direction = direction;
        this.position = coordinate;
    }

    public Direction facing() {
        return this.direction;
    }

    public Coordinate startPoint() {
        return this.startPoint;
    }

    public Coordinate position() {
        return this.position;
    }

    private void rotate(Command command) {
        direction = direction.rotateWith(command);
    }

    public void commands(String commands) {
        commands.chars()
                .mapToObj(i -> (char) i)
                .forEach(command -> command(command));
    }

    public void command(char command) {
        if (Command.LEFT.command() == command) rotate(Command.LEFT);
        else if (Command.RIGHT.command() == command) rotate(Command.RIGHT);
        else if (Command.FORWARD.command() == command) moveForward();
        else if (Command.BACKWARD.command() == command) moveBackward();
        else {
            throw new IllegalArgumentException("wrong command");
        }
    }

    public void moveForward() {
        position = direction.move(position);
    }

    public void moveBackward() {
        position = direction.invested().move(position);
    }

    public String displayPosition() {
        StringBuilder builder = new StringBuilder();
        builder.append(position.display());
        builder.append(" ");
        builder.append(direction.value);
        return builder.toString();
    }

}

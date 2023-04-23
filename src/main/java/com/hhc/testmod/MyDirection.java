package com.hhc.testmod;

import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Rotation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.hhc.testmod.blocks.RotateBlock.MYDIRECTION;


public enum MyDirection implements StringRepresentable {
    NORTH("north", Direction.NORTH),
    EAST("east", Direction.EAST),
    SOUTH("south", Direction.SOUTH),
    WEST("west", Direction.WEST),
    UP("up", Direction.UP),
    DOWN("down",Direction.DOWN);
    private static final Map<Direction, MyDirection> DIRECTION_TO_DIRECTION = Arrays.stream(values())
            .collect(Collectors.toMap(MyDirection::getDirection, Function.identity()));

    private final String name;
    private final Direction direction;

    MyDirection(String name, Direction direction) {
        this.name = name;
        this.direction = direction;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public static MyDirection fromDirection(Direction direction) {
        return DIRECTION_TO_DIRECTION.get(direction);
    }
    public MyDirection rotateClockwise() {
        return values()[(ordinal() + 1) % values().length];
    }

    public static MyDirection getOpposite(BlockPlaceContext context){
      switch (context.getClickedFace())
      {
          case DOWN :
              return UP;
          case UP:
              return DOWN;
          case WEST:
              return WEST;
          case NORTH:
              return NORTH;
          case EAST:
              return EAST;
          default:
              return SOUTH;
      }
    }


}
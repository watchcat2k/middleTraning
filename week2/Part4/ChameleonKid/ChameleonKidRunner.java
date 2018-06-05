/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains chameleon critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class ChameleonKidRunner
{
    public static void main(String[] args)
    {
        int num1 = 1;
        int num2 = 2;
        int num3 = 3;
        int num4 = 4;
        int num5 = 5;
        int num6 = 6;
        int num7 = 7;
        int num8 = 8;
        ActorWorld world = new ActorWorld();
        world.add(new Location(num7, num8), new Rock());
        world.add(new Location(num3, num3), new Rock());
        world.add(new Location(num2, num8), new Rock(Color.BLUE));
        world.add(new Location(num5, num5), new Rock(Color.PINK));
        world.add(new Location(num1, num5), new Rock(Color.RED));
        world.add(new Location(num7, num2), new Rock(Color.YELLOW));
        world.add(new Location(num4, num4), new ChameleonKid());
        world.add(new Location(num5, num8), new ChameleonKid());
        world.show();
    }
}
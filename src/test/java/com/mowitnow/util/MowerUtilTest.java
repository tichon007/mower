package com.mowitnow.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.mowitnow.entity.generated.Direction;
import com.mowitnow.entity.generated.Mower;
import com.mowitnow.entity.generated.Orientation;
import com.mowitnow.entity.generated.Root;

public class MowerUtilTest
{

    @Test
    public void moveItParseXmlTest()
    {
        try
        {
            Root root = MowerUtil.parseXml("src\\test\\resources\\mower.xml");
            assertEquals(root.getMowers().size(), 2);
            assertEquals(root.getMowers().get(0).getDirections().get(0), Direction.G);
            assertEquals(root.getMowers().get(0).getPositionX(), 1);

        }
        catch (Throwable e)
        {
            assertTrue(false);
        }

    }

    @Test
    public void moveItParseFileTest()
    {
        try
        {
            Root root = MowerUtil.parseTextFile("src\\test\\resources\\mower.txt");
            assertEquals(root.getMowers().size(), 2);
            assertEquals(root.getMowers().get(0).getDirections().get(0), Direction.G);
            assertEquals(root.getMowers().get(0).getPositionX(), 1);

        }
        catch (Throwable e)
        {
            assertTrue(false);
        }

    }

    @Test
    public void moveItTurnMoverTest()
    {
        Mower mower = new Mower();

        mower.setOrientation(Orientation.N);
        MowerUtil.turnMower(Direction.D, mower);
        assertEquals(mower.getOrientation(), Orientation.E);

        mower.setOrientation(Orientation.N);
        MowerUtil.turnMower(Direction.G, mower);
        assertEquals(mower.getOrientation(), Orientation.O);

        mower.setOrientation(Orientation.S);
        MowerUtil.turnMower(Direction.D, mower);
        assertEquals(mower.getOrientation(), Orientation.O);

        mower.setOrientation(Orientation.S);
        MowerUtil.turnMower(Direction.G, mower);
        assertEquals(mower.getOrientation(), Orientation.E);

        mower.setOrientation(Orientation.E);
        MowerUtil.turnMower(Direction.D, mower);
        assertEquals(mower.getOrientation(), Orientation.S);

        mower.setOrientation(Orientation.E);
        MowerUtil.turnMower(Direction.G, mower);
        assertEquals(mower.getOrientation(), Orientation.N);

        mower.setOrientation(Orientation.O);
        MowerUtil.turnMower(Direction.D, mower);
        assertEquals(mower.getOrientation(), Orientation.N);

        mower.setOrientation(Orientation.O);
        MowerUtil.turnMower(Direction.G, mower);
        assertEquals(mower.getOrientation(), Orientation.S);

    }

}

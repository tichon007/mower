package com.mowitnow.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.mowitnow.entity.generated.Mower;
import com.mowitnow.entity.generated.Orientation;
import com.mowitnow.entity.generated.Root;
import com.mowitnow.util.MowerUtil;

public class DefaultMowerServiceTest
{

    @Test
    public void moveItParseXmlTest()
    {
        try
        {
            MowerUtil.parseXml("src\\test\\resources\\mower.xml");
        }
        catch (Throwable e)
        {
            assertTrue(false);
        }

    }

    /**
     * Test the first mower move
     */
    @Test
    public void moveItCase1Test()
    {
        try
        {
            Root root = MowerUtil.parseXml("src\\test\\resources\\mower_case_1.xml");
            Mower mower = new DefaultMowerService().mowerNavigation(root.getMowers().get(0), root.getGrid());

            assertEquals(mower.getPositionX(), 1);
            assertEquals(mower.getPositionY(), 3);
            assertEquals(mower.getOrientation(), Orientation.N);

        }
        catch (Throwable e)
        {
            assertTrue(false);
        }

    }

    /**
     * Test the second mower move
     */
    @Test
    public void moveItCase2Test()
    {
        try
        {
            Root root = MowerUtil.parseXml("src\\test\\resources\\mower_case_2.xml");
            Mower mower = new DefaultMowerService().mowerNavigation(root.getMowers().get(0), root.getGrid());

            assertEquals(mower.getPositionX(), 5);
            assertEquals(mower.getPositionY(), 1);
            assertEquals(mower.getOrientation(), Orientation.E);

        }
        catch (Throwable e)
        {
            assertTrue(false);
        }

    }

    /**
     * Test the first mower move
     */
    @Test
    public void moveItCase1TxtTest()
    {
        try
        {
            Root root = MowerUtil.parseTextFile("src\\test\\resources\\mower_case_1.txt");
            Mower mower = new DefaultMowerService().mowerNavigation(root.getMowers().get(0), root.getGrid());

            assertEquals(mower.getPositionX(), 1);
            assertEquals(mower.getPositionY(), 3);
            assertEquals(mower.getOrientation(), Orientation.N);

        }
        catch (Throwable e)
        {
            assertTrue(false);
        }

    }

    /**
     * Test the second mower move
     */
    @Test
    public void moveItCase2TxtTest()
    {
        try
        {
            Root root = MowerUtil.parseTextFile("src\\test\\resources\\mower_case_2.txt");
            Mower mower = new DefaultMowerService().mowerNavigation(root.getMowers().get(0), root.getGrid());

            assertEquals(mower.getPositionX(), 5);
            assertEquals(mower.getPositionY(), 1);
            assertEquals(mower.getOrientation(), Orientation.E);

        }
        catch (Throwable e)
        {
            assertTrue(false);
        }

    }

    /**
     * Test the third extrem mower move xml
     */
    @Test
    public void moveItCase3Test()
    {
        try
        {
            Root root = MowerUtil.parseXml("src\\test\\resources\\mower_case_3.xml");
            Mower mower = new DefaultMowerService().mowerNavigation(root.getMowers().get(0), root.getGrid());

            assertEquals(mower.getPositionX(), 43);
            assertEquals(mower.getPositionY(), 6);
            assertEquals(mower.getOrientation(), Orientation.E);

        }
        catch (Throwable e)
        {
            assertTrue(false);
        }

    }
}

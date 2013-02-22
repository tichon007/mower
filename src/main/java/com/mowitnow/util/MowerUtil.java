package com.mowitnow.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mowitnow.entity.OrientationKey;
import com.mowitnow.entity.generated.Direction;
import com.mowitnow.entity.generated.Grid;
import com.mowitnow.entity.generated.Mower;
import com.mowitnow.entity.generated.Orientation;
import com.mowitnow.entity.generated.Root;

public class MowerUtil
{

    /**
     * 
     */
    private static final Logger logger = LoggerFactory.getLogger(MowerUtil.class);

    /**
     * Orientation map
     */
    private static HashMap<OrientationKey, Orientation> orientationMap;

    static
    {
        // Hash map determining orientation after direction change
        orientationMap = new HashMap<OrientationKey, Orientation>();
        orientationMap.put(new OrientationKey(Orientation.N, Direction.D), Orientation.E);
        orientationMap.put(new OrientationKey(Orientation.N, Direction.G), Orientation.O);
        orientationMap.put(new OrientationKey(Orientation.O, Direction.D), Orientation.N);
        orientationMap.put(new OrientationKey(Orientation.O, Direction.G), Orientation.S);
        orientationMap.put(new OrientationKey(Orientation.S, Direction.D), Orientation.O);
        orientationMap.put(new OrientationKey(Orientation.S, Direction.G), Orientation.E);
        orientationMap.put(new OrientationKey(Orientation.E, Direction.D), Orientation.S);
        orientationMap.put(new OrientationKey(Orientation.E, Direction.G), Orientation.N);
    }

    /**
     * Parse xml file into
     * @param pXmlFilePath
     * @return the root object containing mower(s) & grid information
     */
    public static Root parseXml(String pXmlFilePath)
    {

        File file = new File(pXmlFilePath);
        isfileExist(file);
        try
        {
            return (Root) JAXBContext.newInstance(Root.class).createUnmarshaller().unmarshal(file);
        }
        catch (JAXBException e)
        {
            logger.error("Can't parse " + pXmlFilePath + " file", e);
            throw new RuntimeException(e);
        }

    }

    /**
     * @param pTxtFilePath
     * @return the root object containing mower(s) & grid information
     */
    public static Root parseTextFile(String pTxtFilePath)
    {

        Root root = new Root();
        Grid grid = new Grid();
        Scanner scanner = null;

        File file = new File(pTxtFilePath);
        isfileExist(file);
        try
        {

            scanner = new Scanner(file);

            String[] gridString = scanner.nextLine().split(" ");

            grid.setWidth(Integer.parseInt(gridString[0]));
            grid.setHeight(Integer.parseInt(gridString[1]));

            root.setGrid(grid);

            while (scanner.hasNext())
            {
                Mower mower = new Mower();

                String[] mowerPosition = scanner.nextLine().split(" ");
                mower.setPositionX(Integer.parseInt(mowerPosition[0]));
                mower.setPositionY(Integer.parseInt(mowerPosition[1]));
                mower.setOrientation(Orientation.fromValue(mowerPosition[2]));

                String[] mowerDirection = scanner.nextLine().split(" ");
                for (String direction : mowerDirection)
                {
                    mower.getDirections().add(Direction.fromValue(direction));
                }
                root.getMowers().add(mower);
            }
            return root;
        }
        catch (FileNotFoundException e)
        {
            logger.error("File " + pTxtFilePath + " not found", e);
            throw new RuntimeException(e);
        }
        catch (Throwable e1)
        {
            logger.error("File " + pTxtFilePath + " file can't be parsed", e1);
            throw new RuntimeException(e1);
        }

    }

    /**
     * Turn the mower using it's orientation and the provided directon
     * @param pDirection the targeted direction
     * @param pMowerType the mower
     */
    public static void turnMower(Direction pDirection, Mower pMower)
    {

        pMower.setOrientation(orientationMap.get(new OrientationKey(pMower.getOrientation(), pDirection)));

    }

    /**
     * Move the mower
     * @param pMower
     * @param pGrid
     */
    public static void moveMower(Mower pMower, Grid pGrid)
    {
        // If the mower is oriented north
        if (pMower.getOrientation().equals(Orientation.N))
        {
            int newPositionY = pMower.getPositionY() + 1;
            if (newPositionY <= pGrid.getHeight())
                pMower.setPositionY(newPositionY);
        }
        // If the mower is oriented south
        else if (pMower.getOrientation().equals(Orientation.S))
        {
            int newPositionY = pMower.getPositionY() - 1;
            if (newPositionY >= 0)
                pMower.setPositionY(newPositionY);
        }
        // If the mower is oriented east
        else if (pMower.getOrientation().equals(Orientation.E))
        {
            int newPositionX = pMower.getPositionX() + 1;
            if (newPositionX <= pGrid.getWidth())
                pMower.setPositionX(newPositionX);
        }
        // If the mower is oriented west
        else if (pMower.getOrientation().equals(Orientation.O))
        {
            int newPositionX = pMower.getPositionX() - 1;
            if (newPositionX >= 0)
                pMower.setPositionX(newPositionX);
        }

    }

    /**
     * Check if the file exit, if not exist
     * @param pFile The file
     */
    private static void isfileExist(File pFile)
    {
        if (!pFile.exists())
        {
            logger.info("The file  {} not exists ", pFile);
            System.exit(1);
        }
    }

}

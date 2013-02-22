package com.mowitnow.service;

import com.mowitnow.entity.generated.Direction;
import com.mowitnow.entity.generated.Grid;
import com.mowitnow.entity.generated.Mower;
import com.mowitnow.util.MowerUtil;

public class DefaultMowerService
    implements IMowerService
{

    /**
     * {@inheritDoc}
     */
    public Mower mowerNavigation(Mower pMower, Grid pGrid)
    {
        // Iteration on each direction
        for (Direction direction : pMower.getDirections())
        {
            // If mower turn
            if (!direction.equals(Direction.A))
            {
                MowerUtil.turnMower(direction, pMower);
            }
            // If mower go straight
            else
                MowerUtil.moveMower(pMower, pGrid);
        }
        return pMower;
    }

}

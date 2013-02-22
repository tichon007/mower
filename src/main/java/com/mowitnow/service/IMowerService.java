package com.mowitnow.service;

import com.mowitnow.entity.generated.Grid;
import com.mowitnow.entity.generated.Mower;

public interface IMowerService
{
    /**
     * Move the mower into the grid
     * @param pMower The mower to move
     * @param pGrid The grid where the mover navigate
     * @return Last state mower
     */
    public Mower mowerNavigation(Mower pMower, Grid pGrid);
}

package com.mowitnow.entity;

import com.mowitnow.entity.generated.Direction;
import com.mowitnow.entity.generated.Orientation;

public class OrientationKey
{

    private Orientation orientation;

    private Direction direction;

    /**
     * Constructor.
     * @param pOrientation
     * @param pDirection
     */
    public OrientationKey(Orientation pOrientation, Direction pDirection)
    {
        super();
        orientation = pOrientation;
        direction = pDirection;
    }

    /**
     * Get orientation.
     * @return Value of orientation
     */
    public Orientation getOrientation()
    {
        return orientation;
    }

    /**
     * Set orientation.
     * @param pOrientation Value to set.
     */
    public void setOrientation(Orientation pOrientation)
    {
        orientation = pOrientation;
    }

    /**
     * Get direction.
     * @return Value of direction
     */
    public Direction getDirection()
    {
        return direction;
    }

    /**
     * Set direction.
     * @param pDirection Value to set.
     */
    public void setDirection(Direction pDirection)
    {
        direction = pDirection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object pObj)
    {
        if (pObj instanceof OrientationKey)
        {
            OrientationKey toCompare = (OrientationKey) pObj;
            return orientation.equals(toCompare.getOrientation()) && direction.equals(toCompare.getDirection());
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode()
    {
        return orientation.value().charAt(0) * 10 + direction.value().charAt(0);
    }

}

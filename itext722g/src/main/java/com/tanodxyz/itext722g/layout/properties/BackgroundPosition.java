/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2022 iText Group NV
    Authors: iText Software.

    This program is offered under a commercial and under the AGPL license.
    For commercial licensing, contact us at https://itextpdf.com/sales.  For AGPL licensing, see below.

    AGPL licensing:
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.tanodxyz.itext722g.layout.properties;

import java.util.Objects;

/**
 * Class to hold background-position property.
 */
public class BackgroundPosition {

    private static final double EPS = 1e-4F;
    private static final int FULL_VALUE = 100;
    private static final int HALF_VALUE = 50;

    private PositionX positionX;
    private PositionY positionY;
    private com.itextpdf.layout.properties.UnitValue xShift;
    private com.itextpdf.layout.properties.UnitValue yShift;

    /**
     * Creates a new {@link BackgroundPosition} instance. Fills it with default values.
     */
    public BackgroundPosition() {
        xShift = new com.itextpdf.layout.properties.UnitValue(com.itextpdf.layout.properties.UnitValue.POINT, 0);
        yShift = new com.itextpdf.layout.properties.UnitValue(com.itextpdf.layout.properties.UnitValue.POINT, 0);
        positionX = PositionX.LEFT;
        positionY = PositionY.TOP;
    }

    /**
     * Converts all percentage and enum values to point equivalent.
     *
     * @param fullWidth  container width to calculate percentage.
     * @param fullHeight container height to calculate percentage.
     * @param outXValue  {@link UnitValue} to store processed xPosition.
     * @param outYValue  {@link UnitValue} to store processed yPosition.
     */
    public void calculatePositionValues(float fullWidth, float fullHeight, com.itextpdf.layout.properties.UnitValue outXValue, com.itextpdf.layout.properties.UnitValue outYValue) {
        int posMultiplier = parsePositionXToUnitValueAndReturnMultiplier(outXValue);
        if (posMultiplier == 0 && xShift != null && Math.abs(xShift.getValue()) > EPS) {
            outXValue.setValue(0);
        } else {
            outXValue.setValue(
                    calculateValue(outXValue, fullWidth) + calculateValue(xShift, fullWidth) * posMultiplier);
        }
        outXValue.setUnitType(com.itextpdf.layout.properties.UnitValue.POINT);

        posMultiplier = parsePositionYToUnitValueAndReturnMultiplier(outYValue);
        if (posMultiplier == 0 && yShift != null && Math.abs(yShift.getValue()) > EPS) {
            outYValue.setValue(0);
        } else {
            outYValue.setValue(
                    calculateValue(outYValue, fullHeight) + calculateValue(yShift, fullHeight) * posMultiplier);
        }
        outYValue.setUnitType(com.itextpdf.layout.properties.UnitValue.POINT);
    }

    /**
     * Gets horizontal position.
     *
     * @return position in x-dimension
     */
    public PositionX getPositionX() {
        return positionX;
    }

    /**
     * Sets horizontal position.
     *
     * @param xPosition position in x-dimension
     * @return {@link BackgroundPosition}
     */
    public BackgroundPosition setPositionX(final PositionX xPosition) {
        this.positionX = xPosition;
        return this;
    }

    /**
     * Gets vertical position.
     *
     * @return position in y-dimension
     */
    public PositionY getPositionY() {
        return positionY;
    }

    /**
     * Sets vertical position.
     *
     * @param yPosition position in y-dimension
     * @return {@link BackgroundPosition}
     */
    public BackgroundPosition setPositionY(final PositionY yPosition) {
        this.positionY = yPosition;
        return this;
    }

    /**
     * Gets horizontal shift.
     *
     * @return shift in x-dimension from left
     */
    public com.itextpdf.layout.properties.UnitValue getXShift() {
        return xShift;
    }

    /**
     * Sets horizontal shift.
     *
     * @param xShift shift in x-dimension from left
     * @return {@link BackgroundPosition}
     */
    public BackgroundPosition setXShift(final com.itextpdf.layout.properties.UnitValue xShift) {
        this.xShift = xShift;
        return this;
    }

    /**
     * Gets vertical shift.
     *
     * @return shift in y-dimension from top
     */
    public com.itextpdf.layout.properties.UnitValue getYShift() {
        return yShift;
    }

    /**
     * Sets vertical shift.
     *
     * @param yShift shift in y-dimension
     * @return {@link BackgroundPosition}
     */
    public BackgroundPosition setYShift(final com.itextpdf.layout.properties.UnitValue yShift) {
        this.yShift = yShift;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @return true if every field equals. False otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BackgroundPosition position = (BackgroundPosition) o;
        return Objects.equals(positionX, position.positionX) &&
                Objects.equals(positionY, position.positionY) &&
                Objects.equals(xShift, position.xShift) &&
                Objects.equals(yShift, position.yShift);
    }

    /**
     * {@inheritDoc}
     *
     * @return object's hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(positionX.ordinal(), positionY.ordinal(), xShift, (Object) yShift);
    }

    /**
     * Parses positionX to {@link UnitValue}.
     *
     * @param outValue {@link UnitValue} in which positionX will be parsed
     * @return multiplier by which the xShift will be multiplied
     */
    private int parsePositionXToUnitValueAndReturnMultiplier(com.itextpdf.layout.properties.UnitValue outValue) {
        outValue.setUnitType(com.itextpdf.layout.properties.UnitValue.PERCENT);
        switch (positionX) {
            case LEFT:
                outValue.setValue(0);
                return 1;
            case RIGHT:
                outValue.setValue(FULL_VALUE);
                return -1;
            case CENTER:
                outValue.setValue(HALF_VALUE);
                return 0;
            default:
                return 0;
        }
    }

    /**
     * Parses positionY to {@link UnitValue}.
     *
     * @param outValue {@link UnitValue} in which positionY will be parsed
     * @return multiplier by which the yShift will be multiplied
     */
    private int parsePositionYToUnitValueAndReturnMultiplier(com.itextpdf.layout.properties.UnitValue outValue) {
        outValue.setUnitType(com.itextpdf.layout.properties.UnitValue.PERCENT);
        switch (positionY) {
            case TOP:
                outValue.setValue(0);
                return 1;
            case BOTTOM:
                outValue.setValue(FULL_VALUE);
                return -1;
            case CENTER:
                outValue.setValue(HALF_VALUE);
                return 0;
            default:
                return 0;
        }
    }

    private static float calculateValue(com.itextpdf.layout.properties.UnitValue value, float fullValue) {
        if (value == null) {
            return 0;
        }
        return value.isPercentValue() ? (value.getValue() / 100 * fullValue) : value.getValue();
    }

    public static enum PositionX {
        LEFT,
        RIGHT,
        CENTER
    }

    public static enum PositionY {
        TOP,
        BOTTOM,
        CENTER
    }
}

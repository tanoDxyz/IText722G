/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2022 iText Group NV
    Authors: iText Software.

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License version 3
    as published by the Free Software Foundation with the addition of the
    following permission added to Section 15 as permitted in Section 7(a):
    FOR ANY PART OF THE COVERED WORK IN WHICH THE COPYRIGHT IS OWNED BY
    ITEXT GROUP. ITEXT GROUP DISCLAIMS THE WARRANTY OF NON INFRINGEMENT
    OF THIRD PARTY RIGHTS

    This program is distributed in the hope that it will be useful, but
    WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
    or FITNESS FOR A PARTICULAR PURPOSE.
    See the GNU Affero General Public License for more details.
    You should have received a copy of the GNU Affero General Public License
    along with this program; if not, see http://www.gnu.org/licenses or write to
    the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
    Boston, MA, 02110-1301 USA, or download the license from the following URL:
    http://itextpdf.com/terms-of-use/

    The interactive user interfaces in modified source and object code versions
    of this program must display Appropriate Legal Notices, as required under
    Section 5 of the GNU Affero General Public License.

    In accordance with Section 7(b) of the GNU Affero General Public License,
    a covered work must retain the producer line in every PDF that is created
    or manipulated using iText.

    You can be released from the requirements of the license by purchasing
    a commercial license. Buying such a license is mandatory as soon as you
    develop commercial activities involving the iText software without
    disclosing the source code of your own applications.
    These activities include: offering paid services to customers as an ASP,
    serving PDFs on the fly in a web application, shipping iText with a closed
    source product.

    For more information, please contact iText Software Corp. at this
    address: sales@itextpdf.com
 */
package com.tanodxyz.itext722g.svg.renderers.path.impl;

import com.itextpdf.styledxmlparser.css.util.CssDimensionParsingUtils;
import com.itextpdf.styledxmlparser.css.util.CssUtils;
import com.itextpdf.svg.renderers.path.IPathShape;
import com.tanodxyz.itext722g.kernel.geom.Point;
import com.tanodxyz.itext722g.kernel.geom.Rectangle;

import java.util.Map;

/**
 * This class handles common behaviour in IPathShape implementations
 */
public abstract class AbstractPathShape implements IPathShape {

    /**
     * The properties of this shape.
     */
    protected Map<String, String> properties;

    /**
     * Whether this is a relative operator or not.
     */
    protected boolean relative;
    protected final com.itextpdf.svg.renderers.path.impl.IOperatorConverter copier;
    // Original coordinates from path instruction, according to the (x1 y1 x2 y2 x y)+ spec
    protected String[] coordinates;

    public AbstractPathShape() {
        this(false);
    }

    public AbstractPathShape(boolean relative) {
        this(relative, new com.itextpdf.svg.renderers.path.impl.DefaultOperatorConverter());
    }

    public AbstractPathShape(boolean relative, com.itextpdf.svg.renderers.path.impl.IOperatorConverter copier) {
        this.relative = relative;
        this.copier = copier;
    }

    @Override
    public boolean isRelative() {
        return this.relative;
    }

    protected Point createPoint(String coordX, String coordY) {
        return new Point((double) CssDimensionParsingUtils.parseDouble(coordX), (double) CssDimensionParsingUtils.parseDouble(coordY));
    }

    @Override
    public Point getEndingPoint() {
        return createPoint(coordinates[coordinates.length - 2], coordinates[coordinates.length - 1]);
    }

    /**
     * Get bounding rectangle of the current path shape.
     *
     * @param lastPoint start point for this shape
     * @return calculated rectangle
     */
    @Override
    public Rectangle getPathShapeRectangle(Point lastPoint) {
        return new Rectangle((float) CssUtils.convertPxToPts(getEndingPoint().getX()),
                (float) CssUtils.convertPxToPts(getEndingPoint().getY()), 0,
                0);
    }
}
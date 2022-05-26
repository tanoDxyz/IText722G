package com.tanodxyz.itext722g.styledXmlParser;

import java.util.Comparator;

/**
 * Comparator class used to sort CSS rule set objects.
 */
public class CssRuleSetComparator implements Comparator<com.itextpdf.styledxmlparser.css.CssRuleSet> {

    /** The selector comparator. */
    private com.itextpdf.styledxmlparser.css.selector.CssSelectorComparator selectorComparator = new com.itextpdf.styledxmlparser.css.selector.CssSelectorComparator();

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(com.itextpdf.styledxmlparser.css.CssRuleSet o1, com.itextpdf.styledxmlparser.css.CssRuleSet o2) {
        return selectorComparator.compare(o1.getSelector(), o2.getSelector());
    }
}

package ru.aceinet.rcml;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ParserTest
        extends TestCase {
    public ParserTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(ParserTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        Parser parser = new Parser("[color: 0,255,17]Colored text");
        parser.parse();
        NodeTag tag = (NodeTag) parser.root.children.get(0);
        NodeText text = (NodeText) parser.root.children.get(1);
        assertTrue(tag.name.equals("color"));
        assertTrue(tag.value.equals("0,255,17"));
        assertTrue(text.text.equals("Colored text"));
    }
}
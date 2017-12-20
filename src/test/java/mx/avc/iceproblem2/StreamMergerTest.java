/*
 * Copyright (C) 2017 Alejandro Vazquez

 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 */
package mx.avc.iceproblem2;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

/**
 *
 * @author alexv
 */
public class StreamMergerTest {
    private static final Logger LOGGER = getLogger(StreamMergerTest.class);

    private static final String NL = System.getProperty("line.separator");

    private static final String TEST_VALUE1 =
            "assort" + NL + "catalogue" + NL + "choose" + NL + "comb" + NL +
            "distribute" + NL + "file" + NL + "order" + NL + "pick" + NL +
            "pigeonhole" + NL + "put down as" + NL + "put down for" + NL +
            "put in order" + NL + "put in shape" + NL + "screen" + NL +
            "select" + NL + "separate" + NL + "size up" + NL +
            "systematize" + NL +  "tab" + NL + "winnow";

    private static final String TEST_VALUE2 =
            "arrange" + NL + "button down" + NL + "categorize" + NL +
            "class" + NL + "classify" + NL + "cull" + NL + "divide" + NL +
            "grade" + NL + "group" + NL + "peg" + NL + "put to rights" + NL +
            "rank" + NL + "riddle" + NL + "sift" + NL + "typecast";

    private static final String EXPECTED_VALUE =
            "arrange" + NL + "assort" + NL + "button down" + NL +
            "catalogue" + NL + "categorize" + NL + "choose" + NL +
            "class" + NL + "classify" + NL + "comb" + NL + "cull" + NL +
            "distribute" + NL + "divide" + NL + "file" + NL + "grade" + NL +
            "group" + NL + "order" + NL + "peg" + NL + "pick" + NL +
            "pigeonhole" + NL + "put down as" + NL + "put down for" + NL +
            "put in order" + NL + "put in shape" + NL + "put to rights" + NL +
            "rank" + NL + "riddle" + NL + "screen" + NL + "select" + NL +
            "separate" + NL + "sift" + NL + "size up" + NL +
            "systematize" + NL + "tab" + NL + "typecast" + NL + "winnow" + NL;

    @Test
    public void testStreamMerger() {
        LOGGER.info("Testing streamMerger()");

        Reader in1 = new StringReader(TEST_VALUE1);
        Reader in2 = new StringReader(TEST_VALUE2);

        StringWriter out = new StringWriter();

        StreamMerger.streamMerger(in1, in2, out);

        assertEquals(EXPECTED_VALUE, out.toString());
    }

}

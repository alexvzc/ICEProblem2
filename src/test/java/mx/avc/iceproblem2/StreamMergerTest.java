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
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

/**
 *
 * @author alexv
 */
public class StreamMergerTest {
    private static final Logger LOGGER = getLogger(StreamMergerTest.class);

    private static final String TEST_VALUE1 =
            "assort\ncatalogue\nchoose\ncomb\ndistribute\nfile\norder\npick\n" +
            "pigeonhole\nput down as\nput down for\nput in order\n" +
            "put in shape\nscreen\nselect\nseparate\nsize up\nsystematize\n" +
            "tab\nwinnow";

    private static final String TEST_VALUE2 =
            "arrange\nbutton down\ncategorize\nclass\nclassify\ncull\n" +
            "divide\ngrade\ngroup\npeg\nput to rights\nrank\nriddle\nsift\n" +
            "typecast";

    private static final String EXPECTED_VALUE =
            "arrange\nassort\nbutton down\ncatalogue\ncategorize\nchoose\n" +
            "class\nclassify\ncomb\ncull\ndistribute\ndivide\nfile\ngrade\n" +
            "group\norder\npeg\npick\npigeonhole\nput down as\nput down for\n" +
            "put in order\nput in shape\nput to rights\nrank\nriddle\n" +
            "screen\nselect\nseparate\nsift\nsize up\nsystematize\ntab\n" +
            "typecast\nwinnow\n";

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

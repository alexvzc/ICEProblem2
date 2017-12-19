/*
 * Copyright (C) 2017 Alejandro Vazquez

 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 */
package mx.avc.iceproblem2;

import com.google.devtools.common.options.OptionsParser;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

/**
 *
 * @author alexv
 */
public class App {
    private static final Logger LOGGER = getLogger(App.class);

    private static void processFiles(String input1, String input2,
            String output) throws IOException {
        Path in1 = Paths.get(input1);
        Path in2 = Paths.get(input2);

        Path out = Paths.get(output);

        if(!Files.isReadable(in1)) {
            System.out.println("Cannot read from " + input1);
            return;
        }

        if(!Files.isReadable(in2)) {
            System.out.println("Cannot read from " + input2);
            return;
        }

        try(Reader is1 = Files.newBufferedReader(in1);
            Reader is2 = Files.newBufferedReader(in2);
            Writer os = Files.newBufferedWriter(out, WRITE, CREATE,
                    TRUNCATE_EXISTING)) {
            StreamMerger.streamMerger(is1, is2, os);
        }
    }

    public static void main(String[] args) {
        OptionsParser parser = OptionsParser.newOptionsParser(AppOptions.class);
        parser.parseAndExitUponError(args);
        AppOptions options = parser.getOptions(AppOptions.class);
        String outputfilename = options.getOutputFilename();
        List<String> inputfilenames = options.getInputFilenames();

        if(outputfilename.isEmpty() || inputfilenames.size() != 2) {
            printUsage(parser);
            return;
        }

        try {
            processFiles(inputfilenames.get(0), inputfilenames.get(1),
                    outputfilename);
        } catch (IOException ex) {
            LOGGER.error("IOException found", ex);
            printUsage(parser);
        }
    }

    private static void printUsage(OptionsParser parser) {
        System.out.println("Usage: java -jar iceproblem2.jar -i inputfile1 "
                + "-i inputfile 2 -o outputfile");
        System.out.println(parser.describeOptions(Collections.emptyMap(),
                OptionsParser.HelpVerbosity.LONG));
    }

}

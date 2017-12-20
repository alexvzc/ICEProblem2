/*
 * Copyright (C) 2017 Alejandro Vazquez

 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 */
package mx.avc.iceproblem2;

import com.google.devtools.common.options.OptionsParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
import static java.util.stream.Collectors.toList;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

/**
 *
 * @author alexv
 */
public class App {
    private static final Logger LOGGER = getLogger(App.class);

    private static void processFiles(List<String> inputs, String output)
            throws IOException {

        List<String> unreadable = inputs.stream()
                .filter(i -> !Files.isReadable(Paths.get(i)))
                .collect(toList());

        if(!unreadable.isEmpty()) {
            unreadable.forEach(s ->
                    System.out.println("Cannot read from " + s));
            return;
        }

        List<BufferedReader> readers = inputs.stream()
                .map(i -> {
                    Path p = Paths.get(i);
                    try {
                        return Files.newBufferedReader(p);
                    } catch(IOException ioe) {
                        throw new RuntimeException(ioe);
                    }
                })
                .collect(toList());

        try {
            if(output.isEmpty()) {
                Writer os = System.console() != null
                        ? System.console().writer()
                        : new OutputStreamWriter(System.out);
                StreamMerger.streamMerger(os,
                        readers.toArray(new Reader[readers.size()]));
            } else {
                try(Writer os = Files.newBufferedWriter(Paths.get(output),
                        WRITE, CREATE, TRUNCATE_EXISTING)) {
                    StreamMerger.streamMerger(os,
                            readers.toArray(new Reader[readers.size()]));
                }
            }
        } finally {
            readers.forEach(r -> {
                try {
                    r.close();
                } catch(IOException ioe) {
                }
            });
        }
    }

    public static void main(String[] args) {
        OptionsParser parser = OptionsParser.newOptionsParser(AppOptions.class);
        parser.parseAndExitUponError(args);
        AppOptions options = parser.getOptions(AppOptions.class);
        String outputfilename = options.getOutputFilename();
        List<String> inputfilenames = options.getInputFilenames();

        if(inputfilenames.size() < 1) {
            printUsage(parser);
            return;
        }

        try {
            processFiles(inputfilenames, outputfilename);
        } catch (IOException ex) {
            LOGGER.error("IOException found", ex);
            printUsage(parser);
        }
    }

    private static void printUsage(OptionsParser parser) {
        PrintWriter out = System.console() != null ?
                System.console().writer() : new PrintWriter(System.out);

        out.println("Usage: java -jar iceproblem2.jar -i inputfile1 " +
                        "-i inputfile 2 ... [-o outputfile]");
        out.println(parser.describeOptions(Collections.emptyMap(),
                        OptionsParser.HelpVerbosity.LONG));
    }

}

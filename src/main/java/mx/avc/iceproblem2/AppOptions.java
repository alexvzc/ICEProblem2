/*
 * Copyright (C) 2017 Alejandro Vazquez

 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 */
package mx.avc.iceproblem2;

import com.google.devtools.common.options.Option;
import com.google.devtools.common.options.OptionsBase;
import java.util.List;

/**
 *
 * @author alexv
 */
public class AppOptions extends OptionsBase {

    @Option(
            name = "output",
            abbrev = 'o',
            help = "The output filename",
            defaultValue = ""
    )
    public String outputFilename;

    @Option(
            name = "input",
            abbrev = 'i',
            help = "An input filename",
            allowMultiple = true,
            defaultValue = ""
    )
    public List<String> inputFilenames;

    public List<String> getInputFilenames() {
        return inputFilenames;
    }

    public String getOutputFilename() {
        return outputFilename;
    }

    public void setInputFilenames(List<String> inputFilenames) {
        this.inputFilenames = inputFilenames;
    }

    public void setOutputFilename(String outputFilename) {
        this.outputFilename = outputFilename;
    }
}

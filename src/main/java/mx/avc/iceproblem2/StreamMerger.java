/*
 * Copyright (C) 2017 Alejandro Vazquez

 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 */
package mx.avc.iceproblem2;

import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Optional;
import java.util.Scanner;

/**
 *
 * @author alexv
 */
public class StreamMerger {

    public static void streamMerger(Reader in1, Reader in2, Writer out) {

        Scanner scanner1 = new Scanner(in1);
        Scanner scanner2 = new Scanner(in2);
        PrintWriter print = new PrintWriter(out);

        Optional<String> prev_line1 = Optional.empty();
        Optional<String> prev_line2 = Optional.empty();

        while((prev_line1.isPresent() || scanner1.hasNextLine())
                && (prev_line2.isPresent() || scanner2.hasNextLine())) {

            String line1 = prev_line1.orElseGet(scanner1::nextLine);
            String line2 = prev_line2.orElseGet(scanner2::nextLine);

            if(line1.compareTo(line2) <= 0) {
                print.println(line1);
                prev_line1 = Optional.empty();
                prev_line2 = Optional.of(line2);
            } else {
                print.println(line2);
                prev_line1 = Optional.of(line1);
                prev_line2 = Optional.empty();
            }
        }

        prev_line1.ifPresent(print::println);
        while(scanner1.hasNextLine()) {
            print.println(scanner1.nextLine());
        }

        prev_line2.ifPresent(print::println);
        while(scanner2.hasNextLine()) {
            print.println(scanner2.nextLine());
        }
    }
}

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
import java.util.function.Supplier;

/**
 *
 * @author alexv
 */
public class StreamMerger {

    private static <T> Optional<T> orElseOptional(Optional<T> o,
            Supplier<T> s) {
        return o.isPresent() ? o : Optional.of(s.get());
    }

    public static void streamMerger(Reader in1, Reader in2, Writer out) {

        Scanner scanner1 = new Scanner(in1);
        Scanner scanner2 = new Scanner(in2);
        PrintWriter print = new PrintWriter(out);

        Optional<String> prev_line1 = Optional.empty();
        Optional<String> prev_line2 = Optional.empty();

        while((prev_line1.isPresent() || scanner1.hasNextLine())
                && (prev_line2.isPresent() || scanner2.hasNextLine())) {

            Optional<String> line1 = orElseOptional(prev_line1,
                    () -> scanner1.nextLine());
            Optional<String> line2 = orElseOptional(prev_line2,
                    () -> scanner2.nextLine());

            int comparison = line1.flatMap(l1 -> line2.map(
                    l2 -> l1.compareTo(l2))).get();

            if(comparison <= 0) {
                print.println(line1.get());
                prev_line1 = Optional.empty();
                prev_line2 = line2;
            } else {
                print.println(line2.get());
                prev_line1 = line1;
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

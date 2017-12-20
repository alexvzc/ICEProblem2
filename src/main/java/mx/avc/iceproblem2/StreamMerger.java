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
import static java.util.Comparator.comparing;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import static mx.avc.iceproblem2.Utils.pair;

/**
 *
 * @author alexv
 */
public class StreamMerger {

    public static void streamMerger(Writer out, Reader... in) {
        PrintWriter print = new PrintWriter(out, true);

        List<Entry<Scanner, String>> scanners = Stream.of(in)
                .map(i -> new Scanner(i))
                .filter(Scanner::hasNextLine)
                .map(s -> pair(s, s.nextLine()))
                .collect(toList());

        PriorityQueue<Entry<Scanner, String>> sources = new PriorityQueue<>(
                comparing(Entry::getValue, String::compareTo));
        sources.addAll(scanners);

        while(!sources.isEmpty()) {
            Entry<Scanner, String> top = sources.poll();
            print.println(top.getValue());

            Scanner s = top.getKey();
            if(s.hasNextLine()) {
                sources.offer(pair(s, s.nextLine()));
            }
        }
    }
}

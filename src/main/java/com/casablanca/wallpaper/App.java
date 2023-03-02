package com.casablanca.wallpaper;

import com.casablanca.wallpaper.model.Command;
import com.casablanca.wallpaper.processor.RoomProcessor;

import java.nio.file.Path;
import java.util.Scanner;

import static java.lang.System.err;
import static java.lang.System.out;

public class App {
    public static void main(String[] args) {
        out.println("Select a source!");
        out.println(
                Command.READ_FILE.getCommand() + " - file, " + Command.READ_LINE.getCommand() + " - string:");
        out.println("Calculate number of total square feet, press: " + Command.TOTAL.getCommand());
        out.println("All rooms from input that have a cubic shape: " + Command.CUBIC.getCommand());
        out.println(
                "All rooms from input that are appearing more than once: " + Command.DUPLICATION.getCommand());
        out.println("To end the program, enter: " + Command.QUIT.getCommand());

        var sc = new Scanner(System.in);
        var roomProcessor = new RoomProcessor();
        while (sc.hasNextLine()) {
            try {
                var command = Command.getCommand(sc.nextLine().toUpperCase());
                switch (command) {
                    case READ_FILE -> {
                        out.println("Enter filename:");
                        roomProcessor.process(Path.of(sc.nextLine()));
                    }
                    case READ_LINE -> {
                        out.println("Enter room:");
                        roomProcessor.process(sc.nextLine());
                    }
                    case TOTAL -> out.println("Total: " + roomProcessor.getTotal());
                    case CUBIC -> out.println("Cubic: " + roomProcessor.getCubicShapes());
                    case DUPLICATION -> out.println("Duplicate: " + roomProcessor.getDuplicate());
                    case QUIT -> System.exit(0);
                }
            } catch (Exception e) {
                err.println(e);
            }
        }
        sc.close();
    }
}

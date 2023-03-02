package com.casablanca.wallpaper.processor;

import com.casablanca.wallpaper.context.AppContext;
import com.casablanca.wallpaper.model.Room;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Room processor
 * <p>
 * Processor reads rooms from file, line, etc
 */
public class RoomProcessor {

    /**
     * Process file
     *
     * @param path path to file
     * @throws IOException if an I/O error occurs reading from the file or a malformed or unmappable byte sequence is
     *                     read
     */
    public void process(Path path) throws IOException {
        Files.readAllLines(path).forEach(this::process);
    }

    /**
     * Process line
     *
     * @param room single line
     */
    public void process(String room) {
        AppContext.addRoom(new Room(room));
    }

    /**
     * @return number of total square feet of wallpaper
     */
    public int getTotal() {
        return AppContext.getRooms().stream()
                .map(Room::calculateSquare)
                .reduce(0, Integer::sum);
    }

    /**
     * List all rooms from input that have a cubic shape (order by total needed wallpaper descending)
     *
     * @return list of rooms
     */
    public List<String> getCubicShapes() {
        return AppContext.getRooms()
                .stream()
                .filter(Room::isCubic)
                .sorted(Comparator.comparingInt(Room::calculateSquare)
                        .reversed())
                .map(Room::toString)
                .toList();
    }

    /**
     * List all rooms from input that are appearing more than once (order is irrelevant)
     *
     * @return set of rooms
     */
    public Set<String> getDuplicate() {
        var items = new HashSet<>();
        return AppContext.getRooms().stream()
                .map(Room::toString)
                .filter(room -> !items.add(room))
                .collect(Collectors.toSet());
    }
}

package com.casablanca.wallpaper.context;

import com.casablanca.wallpaper.model.Room;

import java.util.ArrayList;
import java.util.List;

public class AppContext {

    private AppContext() {
    }

    private static final List<Room> rooms = new ArrayList<>();

    public static void addRoom(Room room) {
        rooms.add(room);
    }

    public static List<Room> getRooms() {
        return List.copyOf(rooms);
    }

    public static void clear() {
        rooms.clear();
    }
}

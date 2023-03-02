package com.casablanca.wallpaper.processor;

import com.casablanca.wallpaper.context.AppContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

class RoomProcessorTest {

    private RoomProcessor underTest;

    @BeforeEach
    void setUp() {
        underTest = new RoomProcessor();
        AppContext.clear();
    }

    @Test
    void testProcessFile() throws URISyntaxException, IOException {
        processFile();
        Assertions.assertEquals(18, AppContext.getRooms().size());
    }

    @Test
    void testProcessLine() {
        underTest.process("1x1x1");
        Assertions.assertEquals(1, AppContext.getRooms().size());
    }

    @Test
    void getTotal() throws URISyntaxException, IOException {
        processFile();
        int total = underTest.getTotal();
        Assertions.assertEquals(26156, total);
    }

    @Test
    void getCubicShapes() throws IOException, URISyntaxException {
        processFile();
        List<String> cubicShapes = underTest.getCubicShapes();
        Assertions.assertEquals(1, cubicShapes.size());
    }

    @Test
    void getDuplicate() throws IOException, URISyntaxException {
        processFile();
        Set<String> duplicate = underTest.getDuplicate();
        Assertions.assertEquals(1, duplicate.size());
    }

    @Test
    void testSpecificCase1() {
        underTest.process("1x2x3");
        int total = underTest.getTotal();
        Assertions.assertEquals(24, total);
    }

    @Test
    void testSpecificCase2() {
        underTest.process("1x1x5");
        int total = underTest.getTotal();
        Assertions.assertEquals(23, total);
    }


    private void processFile() throws IOException, URISyntaxException {
        URI uri = ClassLoader.getSystemResource("sample-input.txt").toURI();
        String mainPath = Paths.get(uri).toString();
        Path path = Paths.get(mainPath);
        underTest.process(path);
    }
}
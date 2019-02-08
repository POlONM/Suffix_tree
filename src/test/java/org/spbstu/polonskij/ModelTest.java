package org.spbstu.polonskij;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ModelTest {

    @Test
    public void fileNotFound() {
        try {
            String path = "src/test/resources/input/nonexistent_file";
            new SuffixTree(path);
            assertTrue(false);
        } catch (Exception ex){}
        assertTrue(true);
    }

    @Test
    public void sufTreeEmpty() {
        try {
            String path = "src/test/resources/input/file_1";
            new SuffixTree(path);
            assertTrue(false);
        } catch (Exception ex){}
        assertTrue(true);
    }

    @Test
    public void searchSub() {
            String path = "src/test/resources/input/file_2";
            String result = "Result of search: [o, ve you, u]";
            SuffixTree suffixTree = new SuffixTree(path);
            assertEquals(result, suffixTree.searchSubstring("src/test/resources/input/sub_for_file_2"));
    }

    @Test
    public void longRepSub() {
        String path = "src/test/resources/input/file_2";
        String result = "Longest repeated substring: o";
        assertEquals(result, new SuffixTree(path).longRepSubstring());
    }
}

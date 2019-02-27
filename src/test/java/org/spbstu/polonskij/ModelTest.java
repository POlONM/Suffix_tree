package org.spbstu.polonskij;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ModelTest {

    @Test
    public void fileNotFound() {
        try {
            String path = "src/test/resources/input/nonexistent_file";
            new SuffixTree(path);
            assertTrue(false);
        } catch (Exception ex) {
        }
        assertTrue(true);
    }

    @Test
    public void sufTreeEmpty() {
        try {
            String path = "src/test/resources/input/file_1";
            new SuffixTree(path);
            assertTrue(false);
        } catch (Exception ex) {
        }
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

    private static boolean equalFiles(String expectedFileName,
                                      String resultFileName) {
        boolean equal;
        BufferedReader bExp;
        BufferedReader bRes;
        String expLine;
        String resLine;

        equal = false;
        bExp = null;
        bRes = null;

        try {
            bExp = new BufferedReader(new FileReader(expectedFileName));
            bRes = new BufferedReader(new FileReader(resultFileName));

            if ((bExp != null) && (bRes != null)) {
                expLine = bExp.readLine();
                resLine = bRes.readLine();

                equal = ((expLine == null) && (resLine == null)) || ((expLine != null) && expLine.equals(resLine));

                while (equal && expLine != null) {
                    expLine = bExp.readLine();
                    resLine = bRes.readLine();
                    equal = expLine.equals(resLine);
                }
            }
        } catch (Exception e) {

        } finally {
            try {
                if (bExp != null) {
                    bExp.close();
                }
                if (bRes != null) {
                    bRes.close();
                }
            } catch (Exception e) {
            }

        }

        return equal;

    }

    private boolean equalsFile(List<File> expected, List<File> actual) throws IOException {
        boolean answ = true;
        int counter = 0;
        if (expected.size() != actual.size()) answ = false;
        for (File files : expected) {
            Scanner scannerExpected = new Scanner(files);
            Scanner scannerActual = new Scanner(actual.get(counter));
            while (scannerActual.hasNextLine()) {
                if (!scannerExpected.hasNextLine()) {
                    answ = false;
                    break;
                }
                String strExpected = scannerExpected.nextLine();
                String strActual = scannerActual.nextLine();
                if (!strExpected.equals(strActual)) {
                    answ = false;
                }
            }
            if (!answ) {
                break;
            } else {
                counter++;
            }
        }
        return answ;
    }


    @Test
    public void buildTree() throws IOException {
        String act = "src/test/resources/input/file_2";
        File tr = new SuffixTree(act).visualize();
        Path out = Paths.get("C:\\Users\\Asus\\IdeaProjects\\example\\result.txt");
        List<File> expected = new ArrayList<File>();
        List<File> actual = new ArrayList<File>();
        actual.add(new File(String.valueOf(out)));
        expected.add(new File("src\\test\\resources\\input\\tree"));
        assertTrue(equalsFile(expected, actual));
    }
}

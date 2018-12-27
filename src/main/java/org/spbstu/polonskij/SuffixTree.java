package org.spbstu.polonskij;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SuffixTree {
    private List<Node> nodes = new ArrayList<>();

    SuffixTree(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
        String str = reader.readLine();
        reader.close();
        nodes.add(new Node());
        for (int i = 0; i < str.length(); ++i) {
            addSuffix(str.substring(i));
        }
    }

    /**
     * @param suf current substring
     * main function for building suffix tree
     * function is void
     */
    private void addSuffix(String suf) {
        int n = 0;
        int i = 0;
        while (i < suf.length()) {
            char b = suf.charAt(i);
            List<Integer> children = nodes.get(n).ch;
            int x2 = 0;
            int n2;
            while (true) {
                if (x2 == children.size()) {
                    n2 = nodes.size();
                    Node temp = new Node();
                    temp.sub = suf.substring(i);
                    nodes.add(temp);
                    children.add(n2);
                    return;
                }
                n2 = children.get(x2);
                if (nodes.get(n2).sub.charAt(0) == b) break;
                x2++;
            }
            String sub2 = nodes.get(n2).sub;
            int j = 0;
            while (j < sub2.length()) {
                if (suf.charAt(i + j) != sub2.charAt(j)) {
                    int n3 = n2;
                    n2 = nodes.size();
                    Node temp = new Node();
                    temp.sub = sub2.substring(0, j);
                    temp.ch.add(n3);
                    nodes.add(temp);
                    nodes.get(n3).sub = sub2.substring(j);
                    nodes.get(n).ch.set(x2, n2);
                    break;
                }
                j++;
            }
            i += j;
            n = n2;
        }
    }

    /**
     * @param path input file path
     * @return the desired substring with children or, if he has no children, just a substring
     */
    public String searchSubstring(String path) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
        String str = reader.readLine();
        reader.close();
        List<String> result = new ArrayList<>();
        try {
            if (nodes.isEmpty()) {
                return "Suffix tree is empty";
            }
            for (Node node : nodes) {
                if (str.equals(node.sub)) {
                    result.add(str);
                    for (int i = 0; i < node.ch.size(); i++) {
                        result.add(String.valueOf(nodes.get(node.ch.get(i)).sub));
                    }
                    break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (result.isEmpty()) throw  new Exception("Substring is not found :(");
        else return "Result of search: " + String.valueOf(result);
    }

    /**
     * @return a substring that has at least two different occurrences in a given string
     */
    public String longRepSubstring() {
        String ans = "";
        if (nodes.isEmpty()) {
            return "Suffix tree is empty";
        }
        for (Node node : nodes) {
            if (node.ch.isEmpty()) continue;
            if (node.sub.length() >= ans.length()) ans = node.sub;
        }
        return "Longest repeated substring: " + ans;
    }

    /**
     * @return a file with a rendered suffix tree
     */
    public File visualize() throws IOException {
        if (nodes.isEmpty()) {
            System.out.println("<empty>");
            System.out.println("Suffix tree is empty");
        }
        String path = new File("").getAbsolutePath();
        File newFile = new File(path + "\\result.txt");
        FileOutputStream file = new FileOutputStream(newFile);
        System.setOut(new PrintStream(file));
        print_tree(0, "");
        return newFile;
    }

    /**
     * @param n num for passage through the nodes
     * @param pre carry to another line
     * recursive function for print suffix tree to file
     * function is void
     */
    private void print_tree(int n, String pre) {
        List<Integer> children = nodes.get(n).ch;
        if (children.isEmpty()) {
            System.out.println("- " + nodes.get(n).sub);
            return;
        }
        System.out.println("┐ " + nodes.get(n).sub);
        for (int i = 0; i < children.size() - 1; i++) {
            Integer c = children.get(i);
            System.out.print(pre + "├─");
            print_tree(c, pre + "│ ");
        }
        System.out.print(pre + "└─");
        print_tree(children.get(children.size() - 1), pre + "  ");
    }
}




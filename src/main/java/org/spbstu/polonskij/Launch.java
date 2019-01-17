package org.spbstu.polonskij;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class Launch {
    @Option(name = "-s", required = true)
    private boolean s = false;

    @Argument(required = true)
    private String path = null;

    @Option(name = "-u")
    private boolean u = false;

    @Argument(index = 1)
    private String string = null;

    @Option(name = "-l")
    private boolean l = false;

    public static void main(String[] args) {
        try {
            new Launch().launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("Error 1: Expected arguments: -s folder\\filename1.txt -u folder\\filename2.txt -l" +
                    "\n" + " -s - main option for building tree" +
                    "\n" + " folder\\filename1.txt - file with inputs for building tree" +
                    "\n" + " -u - other option for search substring in tree" +
                    "\n" + " folder\\filename2.txt - file with inputs for search substring" +
                    "\n" + " -l - other option for search a long repeated substring in tree");
            parser.printUsage(System.err);
        }

        try {
            if (s == true) {
                new SuffixTree(path).visualize();
                if (u == true) System.out.println(new SuffixTree(path).searchSubstring(string));
                if (l == true) System.out.println(new SuffixTree(path).longRepSubstring());
            }
        } catch (Exception e) {
            System.err.println("Error 2: Suffix tree is empty");
            System.exit(2);
        }
    }
}

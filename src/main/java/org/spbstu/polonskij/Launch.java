package org.spbstu.polonskij;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class Launch {
    @Option(name = "-s")
    private String s = "inputs\\input_1";

    @Option(name = "-u")
    private boolean u = false;

    @Argument()
    private String path = null;

    @Option(name = "-l")
    private boolean l = false;

    public static void main(String[] args) throws Exception {
        new Launch().launch(args);
    }

    public void launch(String[] args) throws Exception {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("Expected arguments: [-s folder\\filename1.txt] -u folder\\filename2.txt -l");
            parser.printUsage(System.err);
        }


        new SuffixTree(s).visualize();
        if (u == true) System.out.println(new SuffixTree(s).searchSubstring(path));
        if (l == true) System.out.println(new SuffixTree(s).longRepSubstring());
        if ((u && l) == true){
            System.out.println(new SuffixTree(s).searchSubstring(path));
            System.out.println(new SuffixTree(s).longRepSubstring());
        }
    }
}

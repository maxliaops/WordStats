import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.text.DecimalFormat;

public class WordStats {
    public static void main(String[] args) throws IOException {
        //System.out.println("Hello World!");
        //System.out.println(args.length);
        int argc = args.length;
        String inputFilename;
        String outputFilename;
        if (argc == 2) {
            inputFilename = args[0];
            outputFilename = args[1];
            //System.out.println(args[0]);
            //System.out.println(args[1]);
         } else {
            System.out.println("usage: java WordStats <inputfile> <outputfile>");
            return;
         }

        BufferedReader in = new BufferedReader(new FileReader(inputFilename));
        File fout = new File(outputFilename);
        PrintWriter out = new PrintWriter(new FileWriter(fout));

        Map<String, Integer> stats = new HashMap<String, Integer>();
        String str;
        Integer count;
        while ((str = in.readLine()) != null) {
            //System.out.println(str);
            if (stats.containsKey(str)) {
                count = (Integer)stats.get(str);
                count++;
                stats.put(str, count);
            } else {
                count = 1;
                stats.put(str, count);
            }
        }

        ArrayList<Entry<String,Integer>> l = new ArrayList<Entry<String,Integer>>(stats.entrySet());
        Collections.sort(l, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });

        for (Entry<String, Integer> e : l) {
            //System.out.println(new DecimalFormat("000").format(e.getValue()) + " " + e.getKey());
            out.println(new DecimalFormat("000").format(e.getValue()) + " " + e.getKey());
        }

        in.close();
        out.close();
    }
}
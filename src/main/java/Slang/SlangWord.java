package Slang;

import java.io.*;
import java.util.*;

public class SlangWord {
    private  TreeMap<String, List<String>> sw = new TreeMap<>();
    private static  SlangWord obj;

    static {
        try {
            obj = new SlangWord();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int size;
    private String FILE_SLANGWORD = "slang.txt";
    private String FILE_HISTORY = "history.txt";

    SlangWord () throws IOException {
        String path = new File(".").getCanonicalPath();
        FILE_SLANGWORD = path + "\\" + FILE_SLANGWORD;
        FILE_HISTORY = path + "\\" + FILE_HISTORY;
    }

    void readFile(String file) throws Exception {
        sw.clear();
        String slag = null;
        Scanner scanner = new Scanner(new File(file));
        scanner.useDelimiter("\n");
        scanner.next();
        size = 0;
        while (scanner.hasNext()) {
            List<String> meaning = new ArrayList<String>();
            String [] part = scanner.next().split("`");
            if (part.length == 1)
                    continue;
            slag = part[0].trim();

            if (sw.containsKey(slag)) {
                meaning = sw.get(slag);
            }
            if (part[1].contains("|")) {
                String[] d = (part[1]).split("\\|");
                for (int index = 0; index < d.length; index++)
                Collections.addAll(meaning, d);
                size += d.length - 1;
            } else {
                meaning.add(part[1]);
            }
            sw.put(slag, meaning);
            size++;
        }
        scanner.close();
    }

    public void reset() {
        System.out.println("");
    }
}

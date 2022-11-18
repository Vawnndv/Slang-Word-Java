package Slang;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class SlangWord {
    private  TreeMap<String, List<String>> sw = new TreeMap<>();
    private static  SlangWord obj;

    static {
        try {
            obj = new SlangWord();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int size;
    private String FILE_SLANGWORD = "slang.txt";
    private String FILE_HISTORY = "history.txt";
    private String FILE_UPDATE_SWANGWORD = "newSlang.txt";

    SlangWord () throws Exception {
        String path = new File(".").getCanonicalPath();
        FILE_SLANGWORD = path + "\\" + FILE_SLANGWORD;
        FILE_HISTORY = path + "\\" + FILE_HISTORY;
        FILE_UPDATE_SWANGWORD = path + "\\" + FILE_UPDATE_SWANGWORD;
        readFile(FILE_SLANGWORD);
    }

    public static Object getInstance() throws Exception {
        if (obj == null) {
            synchronized (SlangWord.class) {
                if (obj == null) {
                    obj = new SlangWord();// instance will be created at request time
                }
            }
        }
        return obj;
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
        System.out.println("reset");
    }

    public String[][] getMeaning(String key) {
        List<String> meaning = sw.get(key);
        if (meaning == null)
            return null;
        String temp[][] = new String[meaning.size()][3];
        for (int i = 0; i < meaning.size(); i++) {
            temp[i][0] = String.valueOf(i);
            temp[i][1] = key;
            temp[i][2] = meaning.get(i);
        }
        return temp;
    }

    public String[][] getKey(String meaningInput) {
        List<String> keyList = new ArrayList<>();
        List<String> meaningList = new ArrayList<>();
        for (Entry<String, List<String>> entry : sw.entrySet()) {
            List<String> meaning = entry.getValue();
            for (int i = 0; i < meaning.size(); i++) {
                if (meaning.get(i).toLowerCase().contains(meaningInput.toLowerCase())) {
                    keyList.add(entry.getKey());
                    meaningList.add(meaning.get(i));
                }
            }
        }
        int size = keyList.size();
        String temp[][] = new String[size][3];

        for (int i = 0; i < size; i++) {
            temp[i][0] = String.valueOf(i);
            temp[i][1] = keyList.get(i);
            temp[i][2] = meaningList.get(i);
        }
        return temp;
    }

    public void saveHistory(String key, String meaning) throws IOException {
        FileWriter fw = new FileWriter(FILE_HISTORY, true);
        BufferedWriter bw = new BufferedWriter(fw);
        String str = key + "`" + meaning;
        bw.write(str);
        bw.newLine();
        bw.close();
    }

    public void setMeaning(String slag, String oldValue, String newValue) {
        System.out.println(oldValue + "\t" + newValue);
        List<String> meaning = sw.get(slag);
        int index = meaning.indexOf(oldValue);
        meaning.set(index, newValue);
        saveFile(FILE_UPDATE_SWANGWORD);
    }
    void saveFile (String fileName) {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            Set<String> keySet = sw.keySet();
            Object[] keyArray = keySet.toArray();
            myWriter.write("Slag`Meaning\n");
            for (int i = 0; i < sw.size() - 1; i++) {
                String str = (String) keyArray[i] + "`";
                List<String> meaning = sw.get(keyArray[i]);
                for (int j = 0; j < meaning.size(); j++)
                    if (j != meaning.size() - 1)
                        str += meaning.get(j) + "|";
                    else
                        str += meaning.get(j) + "\n";
                myWriter.write(str);
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

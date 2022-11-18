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

    Boolean readFile(String file) throws Exception {
        sw.clear();
        String slag = null;
        Scanner scanner = new Scanner(new File(file));
        if (!scanner.hasNext())
            return false;
        scanner.useDelimiter("\\n");
        scanner.next();
        while (scanner.hasNext()) {
            String str = scanner.next();
            List<String> meaning = new ArrayList<String>();
            String [] part = str.split("`");
            if (part.length == 1)
                    continue;
            slag = part[0].trim();

            if (sw.containsKey(slag)) {
                meaning = sw.get(slag);
            }
            if (part[1].contains("|")) {
                String[] d = (part[1]).split("\\|");
                for (int index = 0; index < d.length; index++) {
                    meaning.add(d[index]);
                }

            } else {
                meaning.add(part[1]);
            }
            sw.put(slag, meaning);
        }
        scanner.close();
        return true;
    }

    public Boolean reset() throws Exception {
        sw.clear();
        if (readFile(FILE_SLANGWORD)) {
            return true;
        };
        return false;
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

    public void saveHistory(String key, String meaning, String date) throws IOException {
        FileWriter fw = new FileWriter(FILE_HISTORY, true);
        BufferedWriter bw = new BufferedWriter(fw);
        String str = key + "`" + meaning + "`" + date;
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

    public String[][] getHistory() throws FileNotFoundException {
        String[][] temp = null;
        List<String> keyList = new ArrayList<>();
        List<String> meaningList = new ArrayList<>();
        List<String> dateList = new ArrayList<>();
        Scanner scanner = new Scanner(new File(FILE_HISTORY));
        scanner.useDelimiter("\n");
        while (scanner.hasNext()) {
            String[] part = scanner.next().split("`");
            if (part.length != 3)
                continue;
            keyList.add(part[0]);
            meaningList.add(part[1]);
            dateList.add(part[2]);
        }
        scanner.close();
        temp = new String[keyList.size()][4];
        for (int i = 0; i < keyList.size(); i++) {
            temp[i][0] = String.valueOf(i);
            temp[i][1] = keyList.get(i);
            temp[i][2] = meaningList.get(i);
            temp[i][3] = dateList.get(i);
        }
        return temp;
    }

    public Boolean isSameSlang(String key) {
        List<String> meaning = sw.get(key);
        if (meaning != null)
            return true;
        return false;
    }

    public void addOverWrite(String key, String meaning) {
        List<String> meaningList = sw.get(key);
        meaningList.set(0, meaning);
        sw.put(key, meaningList);
        this.saveFile(FILE_UPDATE_SWANGWORD);
    }

    public void addDupilicate(String key, String meaning) {
        List<String> meaningList = sw.get(key);
        meaningList.add(meaning);
        sw.put(key, meaningList);
        this.saveFile(FILE_UPDATE_SWANGWORD);
    }

    public void addSlang(String key, String meaning) {
        List<String> meaningList = new ArrayList<>();
        meaningList.add(meaning);
        sw.put(key, meaningList);
        this.saveFile(FILE_UPDATE_SWANGWORD);
    }

    public void delete(String key) {
        sw.remove(key);
        this.saveFile(FILE_UPDATE_SWANGWORD);
    }

    public String[][] randomSlangWord() {
        Random generator = new Random();
        int value = generator.nextInt(sw.size());
        String key = (String) sw.keySet().toArray()[value];
        List<String> meaning = sw.get(key);
        String str = "";
        for (int i = 0; i < meaning.size(); i++) {
            if (i != meaning.size() - 1)
                str += meaning.get(i) + ", ";
            else
                str += meaning.get(i) + ".";
        }
        String temp[][] = {{key,str}};
        return temp;
    }

    public String[] questionSlang() {
        String []temp = new String[6];
        Random generator = new Random();

        int value = generator.nextInt(sw.size());
        String key = (String) sw.keySet().toArray()[value];
        temp[0] = key;
        List<String> meaning = sw.get(key);
        int result = generator.nextInt(meaning.size());
        String str_result = meaning.get(result);
        result = generator.nextInt(4) + 1;
        temp[result] = str_result;
        temp[5] = String.valueOf(result);
        int ran = 0;
        for (int i = 1; i < 5; i++) {
            if (i == result)
                continue;
            else {
                do {
                    ran = generator.nextInt(sw.size());
                } while (value == ran);
                key = (String) sw.keySet().toArray()[ran];
                meaning = sw.get(key);
                int r = generator.nextInt(meaning.size());
                String str_r = meaning.get(r);
                temp[i] = str_r;
            }
        }
        return temp;
    }

    public String[] questionDefinition() {
        String []temp = new String[6];
        Random generator = new Random();

        int value = generator.nextInt(sw.size());
        String key = (String) sw.keySet().toArray()[value];
        List<String> meaning = sw.get(key);
        int result = generator.nextInt(meaning.size());
        String str_result = meaning.get(result);
        temp[0] = str_result;
        result = generator.nextInt(4) + 1;
        temp[result] = key;
        temp[5] = String.valueOf(result);
        int ran = 0;
        for (int i = 1; i < 5; i++) {
            if (i == result)
                continue;
            else {
                do {
                    ran = generator.nextInt(sw.size());
                } while (value == ran);
                key = (String) sw.keySet().toArray()[ran];
                temp[i] = key;
            }
        }
        return temp;
    }
}

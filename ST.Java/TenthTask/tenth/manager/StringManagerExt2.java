package tenth.manager;


import ninth.manager.StringManagerExt;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;



public class StringManagerExt2 extends StringManagerExt {

    public static final String PARAGRAPH_TAG_REGEX = "<\\s*p[^>]*>";
    public static final String DEFAULT_IN_FILE;
    public static final String DEFAULT_OUT_FILE;



    static {

        DEFAULT_IN_FILE = Paths.get("", "TenthTask", "example.txt")
                               .toAbsolutePath()
                               .toString();

        DEFAULT_OUT_FILE = Paths.get("", "TenthTask", "out.txt")
                                .toAbsolutePath()
                                .toString();
    }



    public static String findTagsAndSimplify(String src, String regex) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(src);

        return matcher.replaceAll("<p>");
    }



    /** @noinspection ResultOfMethodCallIgnored */
    public static List<String> readFileAndFormatTags(String filepath, String regex) {

        try {
            File file = new File(filepath);
            file.createNewFile();

            List<String> formattedStrings;
            try (BufferedReader bReader = new BufferedReader(new FileReader(file))) {
                formattedStrings = bReader.lines()
                                          .map(match -> findTagsAndSimplify(match, regex))
                                          .collect(Collectors.toList());
            }

            return formattedStrings;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



    /** @noinspection ResultOfMethodCallIgnored */
    public static int writeToFile(String filepath, List<String> strings) {

        if (strings == null) {
            return -2;
        }

        try {
            File file = new File(filepath);
            file.createNewFile();

            try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(file,
                                                                            StandardCharsets.UTF_8,
                                                                            false))) {

                for (String str : strings) {
                    bWriter.write(str);
                    bWriter.newLine();
                }
            }

            return 0;
        }
        catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

}

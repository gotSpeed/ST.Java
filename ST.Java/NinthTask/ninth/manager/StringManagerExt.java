package ninth.manager;


import eighth.manager.StringManager;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class StringManagerExt extends StringManager {

    private static final String HEX_PATTERN = "(?i)0x[1-9a-f]+";



    private static Stream<String> findHexMatches(String src) {

        Pattern pattern = Pattern.compile(HEX_PATTERN);
        Matcher matcher = pattern.matcher(src);

        return matcher.results()
                      .map(MatchResult::group)
                      .map(match -> match.substring(2));
    }



    public static List<Integer> getHexValuesFromString(String src) {

        Stream<String> matches = findHexMatches(src);

        return matches.map(match -> Integer.parseInt(match, 16))
                      .collect(Collectors.toList());
    }

}

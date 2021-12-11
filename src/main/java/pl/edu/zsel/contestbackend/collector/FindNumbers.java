package pl.edu.zsel.contestbackend.collector;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindNumbers {

    static List<Integer> findIntegers(String stringToSearch) {
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(stringToSearch);

        List<Integer> numbers = new ArrayList<>();
        while (matcher.find())
            numbers.add(Integer.parseInt(matcher.group()));

        return numbers;
    }
}

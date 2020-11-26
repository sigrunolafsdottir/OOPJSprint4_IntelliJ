package RegexpDemo;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexTestHarness {

    public static void main(String[] args) throws IOException {

        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Enter your regex: ");
            Pattern pattern =
                    Pattern.compile(buf.readLine().trim());

            System.out.println("Enter input string to search: ");
            Matcher matcher =
                    pattern.matcher(buf.readLine());

            boolean found = false;
            while (matcher.find()) {
                String temp = String.format("I found the text" +
                                " \"%s\" starting at " +
                                "index %d and ending at index %d.%n",
                        matcher.group(),
                        matcher.start(),
                        matcher.end());
                System.out.println(temp);
                found = true;
            }
            if(!found){
                System.out.println("No match found.%n");
            }
        }
    }
}
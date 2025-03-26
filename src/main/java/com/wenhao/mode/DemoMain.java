package com.wenhao.mode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DemoMain {

    public static void main(String[] args) {
        String str = """
                    <div class="container"><span id='10010'>中国联通</span></div>
                    <div class="container"><span id='10086'>中国移动</span></div>
                """;
        String regex = "<span id='(?<id>\\d+)'>(?<name>.*?)</span>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group("id"));
        }
    }
}


package com.example.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class K8sTest {
    public static final String NAME = "ccccc";
    //    private static final String master = "https://localhost:6443";

    public static void main(String[] args) {
//       test();
        String pattern = "^\\[INFO\\].*(\\d+).+(\\d+).+(\\d+).+(\\d+)$";
        String str = "[INFO] Tests run: 2, Failures: 8, Errors: 9, Skipped: 1";
//        String str = "[INFO] Tests run: 2, Failures: 8, Errors: 9, Skipped: 1";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        if (!m.matches()) {
            return;
        }
        System.out.println(m.group(1));
        System.out.println(m.group(2));
        System.out.println(m.group(3));
        System.out.println(m.group(4));
//        while (m.matches()){
//            System.out.println(m.group());
//
//        }
    }

    public static void test() {
    }
}

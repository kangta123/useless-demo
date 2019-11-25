package com.example.demo;

import io.fabric8.kubernetes.client.AutoAdaptableKubernetesClient;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.ExecWatch;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class K8sTest {
    public static final String NAME = "ccccc";
    //    private static final String master = "https://localhost:6443";


    private static KubernetesClient getClient() {
        Config config = new ConfigBuilder()
            .withOauthToken("qur4ih.hro86xc1u0qox3sy")
            .withMasterUrl("https://47.94.59.17:6443")
            .withNamespace("default")
            .withDisableHostnameVerification(true)
            .build();
        return new AutoAdaptableKubernetesClient(config);
    }

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
        System.out.println(getClient().namespaces().list());
    }
}

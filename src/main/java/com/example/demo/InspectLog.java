package com.example.demo;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.Strings.strcat;
import static com.sun.btrace.BTraceUtils.jstack;
import static com.sun.btrace.BTraceUtils.println;
import static com.sun.btrace.BTraceUtils.str;

@BTrace
public class InspectLog {
    private static long s_1 = 1000 * 1000 * 1000;

    @OnMethod(
            clazz = "org.apache.ibatis.executor.SimpleExecutor",
            method = "doQuery",
            location = @Location(Kind.RETURN)
    )
    public static void doQuery(@ProbeMethodName String probeMethod, @Duration long duration) {
        if (duration > s_1) {
            println(strcat(probeMethod + "：", str(duration / 1000000)));
        }
    }


    @OnMethod(
            clazz = "com.alibaba.druid.pool.DruidPooledPreparedStatement",
            method = "execute",
            location = @Location(Kind.RETURN)
    )
    public static void execTime(@ProbeMethodName String probeMethod, @Duration long duration) {
        if (duration > s_1/2) {
            println(strcat(probeMethod + "：", str(duration / 1000000)));
        }
    }


}

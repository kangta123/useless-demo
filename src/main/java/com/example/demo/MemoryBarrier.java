package com.example.demo;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MemoryBarrier {

    public static void main(String[] args) throws Exception {
        new MemoryBarrier().start();
    }

    public void start() {
        Executor executor = new Executor();

        new Thread(executor::write).start();
        new Thread(executor::read).start();
    }


    static class Executor {
        private int maxTimes = 1000;
        private boolean hasValue = false;

        void write() {
            for (int i = 0; i < maxTimes; i++) {
                while (hasValue) {
                }
                hasValue = true;
                System.out.println("write " + i);
            }

        }

        void read() {
            for (int i = 0; i < maxTimes; i++) {
                while (!hasValue) {
                }
                hasValue = false;
                System.out.println("read " + i);
            }
        }
    }
}

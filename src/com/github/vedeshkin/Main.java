package com.github.vedeshkin;

import java.util.Arrays;

public class Main {

    private static final int size = 10000000;
    private static final int h = size / 2;
    private float data[] = new float[size];
    private float defaultValue = 1.0f;

    public static void main(String[] args) {
        new Main().calculate();
        new Main().concurrentCalculate();
    }

    private void calculate() {

        Arrays.fill(data, defaultValue);
        long start = System.currentTimeMillis();
        for (int i = 0; i < data.length; i++) //However.there is should be away to do it in more moder style using streams
        {
            data[i] = (float) (data[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long end = System.currentTimeMillis();
        System.out.println("Time taken : " + (end - start));
    }

    private void concurrentCalculate() {

        float partData1[] = new float[h];
        float partData2[] = new float[h];
        Arrays.fill(data, defaultValue);
        long start = System.currentTimeMillis();
        //First thread
        Thread t1 = new Thread(() -> {
            System.arraycopy(data, 0, partData1, 0, h);

            for (int i = 0; i <= partData1.length; i++) {
                partData1[i] = (float) (partData1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.arraycopy(partData1, 0, data, 0, h);

        });
        t1.start();
        //Second thread
        Thread t2 = new Thread(() -> {
            System.arraycopy(data, 0, partData2, 0, h);

            for (int i = 0; i <= partData2.length; i++) {
                partData2[i] = (float) (partData2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.arraycopy(partData2, 0, data, h, h);
        });
        t2.start();

        try {
            t1.join();
            t2.join();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time taken: " + (end - start));

    }
}

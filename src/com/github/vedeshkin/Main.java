package com.github.vedeshkin;

import java.util.Arrays;

public class Main {

    static final int size = 10000000;
    static final int h = size / 2;
    float data[] = new float[size];
    float defaultValue = 1.0f;

    public static void main(String[] args) {
        new Main().calculate();
        new Main().concurrentCalculate();
    }

    private void calculate() {

        Arrays.fill(data,defaultValue);
        long start  = System.currentTimeMillis();
        for (int i = 0; i< data.length;i++)
        {
            data[i] = (float)(data[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long end = System.currentTimeMillis();
        System.out.println("Time taken : " + (end - start));
    }

    private void concurrentCalculate() {



    }
}

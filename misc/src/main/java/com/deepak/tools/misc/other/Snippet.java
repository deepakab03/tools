package com.deepak.tools.misc.other;

public class Snippet {

    public static void main(String[] args) {
        int a =0, b = 1, temp = 0;
        while (b < 10) {
            System.out.println(a);
            temp = a + b;
            a = b;
            temp = b;
        }
    }
}

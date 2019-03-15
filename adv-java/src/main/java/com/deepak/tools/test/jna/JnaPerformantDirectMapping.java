package com.deepak.tools.test.jna;

import com.sun.jna.Native;
import com.sun.jna.Platform;

public class JnaPerformantDirectMapping {

    static {
      Native.register(Platform.C_LIBRARY_NAME);
    }

    //varArgs are not supported in Direct Mapping
//    public static native int printf(String format, Object... args);
    public static native int printf(String format);
    public static native double cos(double x);
    public static native double sin(double x);

    public static void main(String[] args) {
        System.out.println("cos(0)=" + cos(0));
        System.out.println("sin(0)=" + sin(0));

        JnaPerformantDirectMapping.printf("Hello, World \n");
//        for (int i = 0; i < args.length; i++) {
        //gives an IllegalArgumentException for windows
//            JnaPerformantDirectMapping.printf("Argument %d: %s\n", i, args[i]);
//        }
    }

}

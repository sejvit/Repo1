package sk.juko.main;

import sk.juko.common.ClazzUtils;

import java.util.Arrays;

/**
 * book
 * 11.10.2016
 */
public class Main {

    public static void main(String[] args) throws Throwable {
        ClassX classX = (ClassX) ClazzUtils.newClass(ClassX.class, "xx90x", "yyy");
        System.out.println(classX);
        System.out.println(Arrays.toString(ClazzUtils.getValues(classX)));
        System.out.println(Arrays.toString(ClazzUtils.getVariables(classX.getClass())));

        ClassX classy = (ClassX) ClazzUtils.newClass(ClassY.class, "xx90x", "yyy", "zzz");
        System.out.println(classy);
        System.out.println(Arrays.toString(ClazzUtils.getValues(classy)));
        System.out.println(Arrays.toString(ClazzUtils.getVariables(classy.getClass())));
    }
}


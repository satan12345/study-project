package com.able.studyproject.mytest;

import java.util.Date;

/**
 * @param
 * @author jipeng
 * @date 2020-04-29 16:06
 */
public class ForTest {
    public static void main(String[] args){
        String a="Ja";
        String b="va";
        String c=new String("Ja").intern();
        System.out.println(a==c);

        System.out.println("new Date(1588223904974L) = " + new Date(1588223904974L));
    }
}


package com.waa.lab2.service;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        System.out.println(checkPattern("010","amazing"));
    }
    public static int checkPattern(String pattern, String source){
        int count=0;
        for(int i=0;i<source.length()-pattern.length();i++){
            boolean found=true;
            for(int j=0;j<pattern.length();j++){
                if(!fallsInSameCat(source.charAt(i+j),pattern.charAt(j))){
                    found=false;
                    break;
                }
            }
            if(found){
                count++;
            }
        }
        return count;
    }

    private static boolean fallsInSameCat(char charAt, char charAt1) {
        if(charAt1=='0'){
            return contains(charAt);
        }else{
            return !contains(charAt);
        }
    }

    private static boolean contains(char c){
        Character[] vowels={'a','e','i','o','u','y'};
        return Arrays.asList(vowels).indexOf(c)!=-1;
    }
}

package com.company;


import org.junit.Test;

import static com.company.Main.*;
import static junit.framework.Assert.assertEquals;


public class MainTest {

    @Test
    public void isPalindromicTest(){
        assertEquals(true, isPalindromic(545, 10));
        assertEquals(true, isPalindromic(585, 2));
        assertEquals(true, isPalindromic(173, 3));
        assertEquals(false, isPalindromic(123, 10));
    }

    @Test
    public void getArnTest(){
        assertEquals(1, getArn(5, 10));
        assertEquals(3, getArn(5, 2));
        assertEquals(2, getArn(55, 10));
        assertEquals(6, getArn(55, 2));
        assertEquals(5, getArn(69875, 10));
    }

    @Test
    public void getDigitTest(){
        assertEquals(6, getDigit(965659, 2, 10));
        assertEquals(6, getDigit(965659, 4, 10));
        assertEquals(9, getDigit(965659, 1, 10));
        assertEquals(9, getDigit(965659, 6, 10));
        assertEquals(0, getDigit(16, 4, 2));
    }

    @Test
    public void genNebPalindromeTest(){
        assertEquals(5, genNextPalindrome(4));
        assertEquals(33, genNextPalindrome(11));
        assertEquals(121, genNextPalindrome(111));
        assertEquals(1001, genNextPalindrome(999));
        assertEquals(101, genNextPalindrome(99));
        assertEquals(1, genNextPalindrome(0));
        assertEquals(900009, genNextPalindrome(889988));
        assertEquals(1000001, genNextPalindrome(999999));
    }

    @Test
    public void getReflectedTest(){
        assertEquals(1111, genReflected(11));
        assertEquals(123321, genReflected(123));
        assertEquals(55, genReflected(5));
        assertEquals(333333, genReflected(333));
        assertEquals(11, genReflected(1));
    }

    @Test
    public void genReflectedAndCenterTest(){
        assertEquals(12321, genReflectedAndCenter(12, 3));
        assertEquals(12021, genReflectedAndCenter(12, 0));
    }

    @Test
    public void getHalfTest(){
        assertEquals(12, getHalf(1221));
        assertEquals(22, getHalf(2222));
        assertEquals(36, getHalf(36563));
        assertEquals(12, getHalf(12321));
        assertEquals(123, getHalf(123321));
        assertEquals(112, getHalf(112211));
    }

    @Test(timeout = 10000)
    public void getArnLoadTest(){
        long l = 0;
        for (int i = 0; i < 10000000; i++) {
            l+= getArn(i, 10);
        }
    }

    @Test
    public void powLoadTest(){
        long l = 0;
        for (long i = 0; i < 1000000000L; i++) {
            l+= pow(8);
        }
        System.out.println(l);
    }
}
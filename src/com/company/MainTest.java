package com.company;


import org.junit.Test;

import static com.company.Main.*;
import static junit.framework.Assert.assertEquals;


public class MainTest {

    @Test
    public void isPalindromicTest(){
        assertEquals(true, isPalindromic(585));
        assertEquals(true, isPalindromic(7284717174827L));
        assertEquals(true, isPalindromic(1413899983141L));
        assertEquals(true, isPalindromic(32479297423L));
        assertEquals(true, isPalindromic(939474939));
        assertEquals(true, isPalindromic(5259525));
        assertEquals(true, isPalindromic(1934391));
        assertEquals(true, isPalindromic(1758571));
        assertEquals(true, isPalindromic(717));
    }

    @Test
    public void getArnTest(){
        assertEquals(1, get10arn(5));
        assertEquals(3, get10arn(585));
        assertEquals(3, get2Arn(5));
        assertEquals(2, get10arn(55));
        assertEquals(6, get2Arn(55));
        assertEquals(5, get10arn(69875));
    }

    @Test
    public void getDigitTest(){
        assertEquals(6, get10Digit(965659, 2));
        assertEquals(6, get10Digit(965659, 4));
        assertEquals(9, get10Digit(965659, 1));
        assertEquals(9, get10Digit(965659, 6));
    }

    @Test
    public void genNebPalindromeTest(){
        assertEquals(33, genNextPalindrome(11));
        assertEquals(121, genNextPalindrome(111));
        assertEquals(1001, genNextPalindrome(999));
        assertEquals(101, genNextPalindrome(99));
        assertEquals(300003, genNextPalindrome(199991));
        assertEquals(1000001, genNextPalindrome(999999));

        assertEquals(3000003, genNextPalindrome(1999991));
        assertEquals(1111111, genNextPalindrome(1110111));
        assertEquals(1120211, genNextPalindrome(1119111));
        assertEquals(1200021, genNextPalindrome(1199911));
        assertEquals(3000003, genNextPalindrome(1999991));
        assertEquals(10000001, genNextPalindrome(9999999));

        assertEquals(34141388314143L, genNextPalindrome(34141377314143L));
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
        for (int i = 11; i < 10000000; i++) {
            l+= get10arn(i);
        }
    }

    @Test(timeout = 10000)
    public void getDigitLoadTest(){
        long l = 0;
        for (int i = 11; i < 10000000; i++) {
            l+= get10Digit(i, 1);
        }
        System.out.println(l);
    }

    @Test
    public void powLoadTest(){
        long l = 0;
        for (long i = 0; i < 1000000000L; i++) {
            l+= pow(8);
        }
        System.out.println(l);
    }

    @Test
    public void get2HalfTest(){
        assertEquals(4, get2Half(33));
        assertEquals(6, get2Half(99));
    }

    @Test
    public void getInvHalfTest(){
        assertEquals(4, getInvSecondHalf(33));
        assertEquals(6, getInvSecondHalf(99));
    }

    @Test
    public void getHalfLoadTest(){
        long l = 0;
        for (int i = 11; i < 10000000; i++) {
            l+= getHalf(i);
        }
        System.out.println(l);
    }

    @Test
    public void where9test(){
        assertEquals(1, where9(1001));
        assertEquals(0, where9(1991));
        assertEquals(2, where9(101101));
        assertEquals(1, where9(109901));
        assertEquals(0, where9(199991));
    }

    @Test
    public void where9nptest(){
        assertEquals(-1, where9np(1000001));
        assertEquals(0, where9np(19991));
        assertEquals(2, where9np(1019101));
        assertEquals(1, where9np(1099901));
        assertEquals(0, where9np(191));
    }
}
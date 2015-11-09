package com.company;

public class Main {

    static public boolean isPalindromic(long i, long base){
        long n = getArn(i, base);
        for (long j = 1; j <= n / 2; j++) {
            if(getDigit(i, j ,base) != getDigit(i, n-(j-1), base)) return false;
        }
        return true;
    }

    static long getDigit(long i, long n, long base){
        long d = getArn(i, base)-n;
        for (long j = 0; j < d; j++) {
            i = i/base;
        }
        return i%base;
    }

    static long getArn(long i, long base){
        long d = i/base;
        if(d > 0){
            return getArn(d, base) + 1;
        }else {
            return 1;
        }
    }

    static long genNextPalindrome(long x){
        if(x == 9) return 11;
        long d = getArn(x, 10);
        if(d == 1) return x+1;
        if(d%2 == 0) {
            long lp = getHalf(x)+1;
            if(getArn(lp,10) != getArn(getHalf(x),10)){
                return genReflectedAndCenter(lp / 10, 0);
            }else {
                return genReflected(lp);
            }
        }
        if(d%2 == 1) {
            long cd = getDigit(x, d / 2 + 1, 10);
            if(cd == 9){
                long lp = getHalf(x)+1;
                if(getArn(lp,10) != getArn(getHalf(x),10)){
                    return genReflected(lp);
                }else {
                    return genReflectedAndCenter(lp, 0);
                }
            }
            return genReflectedAndCenter(getHalf(x), cd + 1);
        }
        return 0;
    }

    static long getHalf(long i){
        long n = getArn(i, 10);
        long res = 0;
        for (long j = 1; j <= n/2; j++) {
            res += getDigit(i, j, 10)*Math.pow(10, n/2-j);
        }
        return res;
    }

    static long genReflected(long i){
        long res = i*(long)Math.pow(10, getArn(i, 10));
        for(long j = 1; j <= getArn(i, 10); j++){
            res += getDigit(i, j, 10)*Math.pow(10, j - 1);
        }
        return res;
    }

    static long genReflectedAndCenter(long i, long c){
        long res = i*(long)Math.pow(10, getArn(i, 10)+1) + c*(long)Math.pow(10, getArn(i, 10));
        for(long j = 1; j <= getArn(i, 10); j++){
            res += getDigit(i, j, 10)*Math.pow(10, j - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        long palindrome = 0;
        long n = 0;
        long t1 = System.currentTimeMillis();
        for(; n < 39; ){
            palindrome = genNextPalindrome(palindrome);
            if(palindrome%2 != 0 && isPalindromic(palindrome, 2)){
                System.out.println(++n);
                System.out.println("10x" + palindrome);
                System.out.println("2x" + Long.toBinaryString(palindrome));
            }
        }
        System.out.println(t1-System.currentTimeMillis());
    }
}

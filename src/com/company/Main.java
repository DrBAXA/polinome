package com.company;

public class Main {

    static public boolean isPalindromic(long i, int base){
        int n = getArn(i, base);
        for (int j = 1; j <= n / 2; j++) {
            if(getDigit(i, j ,base) != getDigit(i, n-(j-1), base)) return false;
        }
        return true;
    }

    static int getDigit(long i, int n, int base){
        int d = getArn(i, base)-n;
        for (int j = 0; j < d; j++) {
            i = i/base;
        }
        return (int)(i%base);
    }

    static int getArn(long i, int base){
        int n = 1;
        i /= base;
        while (i > 0){
            n++;
            i /= base;
        }
        return n;
    }

    static long genNextPalindrome(long x){
        if(x == 9) return 11;
        int d = getArn(x, 10);
        if(d == 1) return x+1;
        if(d%2 == 0) {
            long lp = getHalf(x)+1;
            int fd = getDigit(lp, 1, 10);
            if(fd%2 == 0){
                lp = (fd+1)*pow(getArn(lp, 10)-1);
            }

            if(getArn(lp,10) != getArn(getHalf(x),10)){
                return genReflectedAndCenter(lp / 10, 0);
            }else {
                return genReflected(lp);
            }
        }
        if(d%2 == 1) {
            int cd = getDigit(x, d / 2 + 1, 10);
            if(cd == 9){
                long lp = getHalf(x)+1;
                int fd = getDigit(lp, 1, 10);
                if(fd%2 == 0){
                    lp = (fd+1)*pow(getArn(lp, 10)-1);
                }

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

    static int getHalf(long i){
        int n = getArn(i, 10);
        int res = 0;
        for (int j = 1; j <= n/2; j++) {
            res += getDigit(i, j, 10)*pow(n/2-j);
        }
        return res;
    }

    static long genReflected(long i){
        long k = 1;
        for(int j = 0; j < getArn(i, 10); j++){
            k *= 10;
        }
        long res = i*pow(getArn(i, 10));
        for(int j = 1; j <= getArn(i, 10); j++){
            res += getDigit(i, j, 10)*pow(j - 1);
        }
        return res;
    }

    static long pow(int n){
        long x = 1;
        for (int i = 0; i < n; i++) {
            x*= 10;
        }
        return x;
    }

    static long genReflectedAndCenter(long i, long c){
        long res = i*pow(getArn(i, 10)+1) + c*pow(getArn(i, 10));
        for(int j = 1; j <= getArn(i, 10); j++){
            res += getDigit(i, j, 10)*pow(j - 1);
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

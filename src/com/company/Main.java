package com.company;

public class Main {

    static public boolean isPalindromic(long i){
        int n = get2Arn(i);
        for (int j = 1; j <= n / 2; j++) {
            if(get2Digit(i, j) != get2Digit(i, n - (j - 1))) return false;
        }
        return true;
    }

    static int get2Digit(long i, int n){
            int d = get2Arn(i)-n;
            i = i >> d;
            return (int)(i&1);
    }

    static int get10Digit(long i, int n){
        int d = get10arn(i)-n;
        i = i/pow(d);
        return (int)(i%10);
    }

    static int get2Arn(long i){
            int n = 1;
            i >>= 1;
            while (i > 0){
                n++;
                i >>= 1;
            }
            return n;
    }
    static int get10arn(long i){
        return getTenarn(i, 0, 16);
    }

    static int getTenarn(long i, int min, int max){
        if(i< 10) return 1;
        if(i< 100) return 2;
        if(i< 1000) return 3;
        int m = (max+min) >> 1;
        long d = i/pow(m);
        if(d< 10 && d > 0) return m+1;
        if(d >= 10) return getTenarn(i, m+1, max);
        return getTenarn(i, min, m-1);
    }

    static long genNextPalindrome(long x){
        if(x == 9) return 11;
        int d = get10arn(x);
        if(d == 1) return x+1;
        if(d%2 == 0) {
            long lp = getHalf(x)+1;
            int fd = get10Digit(lp, 1);
            if(fd%2 == 0){
                lp = (fd+1)*pow(get10arn(lp)-1);
            }

            if(get10arn(lp) != get10arn(getHalf(x))){
                return genReflectedAndCenter(lp / 10, 0);
            }else {
                return genReflected(lp);
            }
        }
        if(d%2 == 1) {
            int cd = get10Digit(x, d / 2 + 1);
            if(cd == 9){
                long lp = getHalf(x)+1;
                int fd = get10Digit(lp, 1);
                if(fd%2 == 0){
                    lp = (fd+1)*pow(get10arn(lp)-1);
                }

                if(get10arn(lp) != get10arn(getHalf(x))){
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
        int n = get10arn(i);
        int res = 0;
        for (int j = 1; j <= n/2; j++) {
            res += get10Digit(i, j)*pow(n/2-j);
        }
        return res;
    }

    static long genReflected(long i){
        long res = i*pow(get10arn(i));
        for(int j = 1; j <= get10arn(i); j++){
            res += get10Digit(i, j)*pow(j - 1);
        }
        return res;
    }

    static long pow(int n){
        switch (n){
            case 0: return 1;
            case 1: return 10;
            case 2: return 100;
            case 3: return 1000;
            case 4: return 10000;
            case 5: return 100000;
            case 6: return 1000000;
            case 7: return 10000000;
            case 8: return 100000000;
            case 9: return 1000000000;
            case 10: return 10000000000L;
            case 11: return 100000000000L;
            case 12: return 1000000000000L;
            case 13: return 10000000000000L;
            case 14: return 100000000000000L;
            case 15: return 1000000000000000L;
            case 16: return 10000000000000000L;
            case 17: return 100000000000000000L;
            case 18: return 1000000000000000000L;
            default:return 0;
        }
    }

    static long genReflectedAndCenter(long i, long c){
        long res = i*pow(get10arn(i)+1) + c*pow(get10arn(i));
        for(int j = 1; j <= get10arn(i); j++){
            res += get10Digit(i, j)*pow(j - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        long palindrome = 0;
        long n = 0;
        long t1 = System.currentTimeMillis();
        for(; n < 50; ){
            palindrome = genNextPalindrome(palindrome);
            if(palindrome%2 != 0 && isPalindromic(palindrome)){
                System.out.println(++n);
                System.out.println("10x" + palindrome);
                System.out.println("2x" + Long.toBinaryString(palindrome));
            }
        }
        System.out.println(t1-System.currentTimeMillis());
    }
}

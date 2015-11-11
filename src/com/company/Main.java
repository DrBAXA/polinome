package com.company;

public class Main {
    private static int lastLength = 2;

    private static long powLL2 = 10;
    private static long powLL1 = 10;
    private static long powLL22 = 10;

    private static int counter = 0;

    static public boolean isPalindromic(long i){
        int n = get2Arn(i);
        return (get2Half(i, n)^getInvSecondHalf(i, n)) == 0;
    }

    static public int get2Half(long i,int n){
        n = (n >> 1) + (n&1);
        return (int)(i >> n);
    }

    static public int getInvSecondHalf(long i, int n){
        n = (n >> 1);
        int res = 0;
        for (int j = 0; j < n; j++) {
            int c = 1 << j;
            res = (int)((res << 1) + ((i&c) >> j));
        }
        return res;
    }

    static int get10Digit(long i, int n){
        int d = lastLength-n;
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

    static int getTenarn(long i){
        if(i < 1000000000){
            if(i < 100000){
                return i >= 10000 ? 5 : i >= 1000 ? 4 : i >= 100 ? 3 : i >= 10 ? 2 : 1;
            }else{
                return i >= 100000000 ? 9 : i >= 10000000 ? 8 : i >= 1000000 ? 7 : 6;
            }
        }else{
            if(i < 10000000000000L){
                return i >= 1000000000000L ? 13 : i >= 100000000000L ? 12 : i >= 10000000000L ? 11 : 10;
            }else {
                return i >= 10000000000000000L ? 17 : i >= 1000000000000000L ? 16 : i >= 100000000000000L ? 15 : 14;
            }
        }
    }

    static long genNextPalindrome(long x){
        if(all9(x)){
            lastLength++;
            powLL2 = pow(lastLength >> 1);
            powLL22 = pow((lastLength >> 1) + (lastLength&1));
            powLL1 *= 10;
            counter = 0;
            return x+2;
        }
        long res;
        int nine;
        if((lastLength&1) == 0){
            nine = where9(x);
            res = x+ 11*pow(nine);
        } else {
            nine = where9np(x);
            if(nine<0) {
                res = x + powLL2;
            }else {
                res = x + 11 * pow(nine);
            }
        }
        if(nine == 0) {
            int d = get10Digit(res, 1);
            if ((d & 1) == 0) return (d + 1) * powLL1 + d + 1;
        }

        return res;
    }

    static int where9(long x){
        int d = lastLength >> 1;
        int half = getHalf(x);
        while (half%10 == 9){
            d--;
            half /= 10;
        }
        return d-1;
    }

    static int where9np(long x){
        int d = lastLength >> 1;
        int half = getHalf(x);
        if(counter != 9){
            counter++;
            return -1;
        }
        counter = 0;
        while (half%10 == 9){
            d--;
            half /= 10;
        }
        return d-1;
    }

    static boolean all9(long i){
        return lastLength != getTenarn(i+1);
    }

    static int getHalf(long i){
        return (int)(i/powLL22);
    }

    static long pow(int n){
        if(n < 10){
            if(n <= 5){
                if(n > 2){
                    return n == 3 ? 1000 : n == 4 ? 10000 : 100000;
                }else{
                    return n == 0 ? 1 : n == 1 ? 10 : 100;
                }
            }else{
                if(n > 7){
                    return n == 8 ? 100000000 : 1000000000;
                }else{
                    return n == 6 ? 1000000 : 10000000;
                }
            }
        }else {
            if(n <= 15){
                if(n > 12){
                    return n == 13 ? 10000000000000L : n == 14 ? 100000000000000L : 1000000000000000L;
                }else{
                    return n == 10 ? 10000000000L : n == 11 ? 100000000000L : 1000000000000L;
                }
            }else{
                if(n > 17){
                    return n == 18 ? 1000000000000000000L : 0;
                }else{
                    return n == 16 ? 10000000000000000L : 100000000000000000L;
                }
            }
        }
    }

    public static void main(String[] args) {
        long palindrome = 11;
        long n = 5;
        long t1 = System.currentTimeMillis();
        for(; n < 60; ){
            palindrome = genNextPalindrome(palindrome);
            if(isPalindromic(palindrome)){
                System.out.println(++n);
                System.out.println("10x  " + palindrome);
                System.out.println("2x   " + Long.toBinaryString(palindrome));
                System.out.println(-(t1-System.currentTimeMillis()));
                System.out.println("====================================================================================");
            }
        }

    }
}

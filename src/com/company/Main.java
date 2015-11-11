package com.company;

public class Main {

    private static long lastArg = 0;
    private static int lastRes = 0;

    static public boolean isPalindromic(long i){
        return (get2Half(i)^getInvSecondHalf(i)) == 0;
    }

    static public int get2Half(long i){
        int n = get2Arn(i);
        n = (n >> 1) + (n&1);
        return (int)(i >> n);
    }

    static public int getInvSecondHalf(long i){
        int n = get2Arn(i);
        n = (n >> 1);
        int res = 0;
        for (int j = 0; j < n; j++) {
            int c = 1 << j;
            res = (int)((res << 1) + ((i&c) >> j));
        }
        return res;
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
        if(lastArg != i){
            lastRes =  getTenarn(i, 0, 18);
            lastArg = i;
        }
        return lastRes;
    }

    static int getTenarn(long i, int min, int max){
        int m = (max+min) >> 1;
        long d = i/pow(m);
        if(i == 0) return 1;
        if(d == 10) return m+2;
        if(d > 0 && d < 10) return m+1;
        if(d >= 10) return getTenarn(i, m+1, max);
        return getTenarn(i, min, m-1);
    }

    static long genNextPalindrome(long x){
        if(all9(x)) return x+2;
        int l = get10arn(x);
        long res;
        if(l%2 == 0){
            res = x+ 11*pow(where9(x));
        } else {
            int p = where9np(x);
            if(p<0) {
                res = x + pow(l/2);
            }else {
                res = x + 11 * pow(p);
            }
        }
        int d = get10Digit(res, 1);
        if(d%2 == 0) return (d+1)*pow(l-1)+d+1;

        return res;
    }

    static int where9(long x){
        int d = get10arn(x)/2;
        int half = getHalf(x);
        while (half%10 == 9){
            d--;
            half /= 10;
        }
        return d-1;
    }

    static int where9np(long x){
        int d = get10arn(x)/2;
        int half = getHalf(x);
        if(get10Digit(x, d+1) != 9) return -1;
        while (half%10 == 9){
            d--;
            half /= 10;
        }
        return d-1;
    }

    static boolean all9(long i){
        return get10arn(i) != get10arn(i+1);
    }

    static int getHalf(long i){
        int n = get10arn(i);
        return (int)(i/pow((n/2) + (n%2)));
    }

    static long genReflected(long i){
        int r = get10arn(i);
        long res = i*pow(r);
        for(int j = 1; j <= r; j++){
            res += get10Digit(i, j)*pow(j - 1);
        }
        return res;
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

    static long genReflectedAndCenter(long i, long c){
        int r = get10arn(i);
        long p = pow(r);
        long res = i*p*10 + c*p;
        for(int j = 1; j <= r; j++){
            res += get10Digit(i, j)*pow(j - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        long palindrome = 11;
        long n = 5;
        long t1 = System.currentTimeMillis();
        for(; n < 55; ){
            palindrome = genNextPalindrome(palindrome);
            if(isPalindromic(palindrome)){
                System.out.println(++n);
                System.out.println("10x" + palindrome);
                System.out.println("2x" + Long.toBinaryString(palindrome));
                System.out.println(-(t1-System.currentTimeMillis()));
                System.out.println("====================================================================================");
            }
        }

    }
}

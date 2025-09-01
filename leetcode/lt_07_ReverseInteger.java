
class lt_07_ReverseInteger {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int digit = x % 10;  
            x /= 10;             
            if (rev > Integer.MAX_VALUE / 10 || 
               (rev == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }

            if (rev < Integer.MIN_VALUE / 10 || 
               (rev == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }

            rev = rev * 10 + digit;
        }
        return rev;
    }

    public static void main(String[] args) {
        lt_07_ReverseInteger solver = new lt_07_ReverseInteger();
        System.out.println(solver.reverse(123));   
        System.out.println(solver.reverse(-123));  
        System.out.println(solver.reverse(120));   
        System.out.println(solver.reverse(1534236469)); 
    }
}
                                                                    
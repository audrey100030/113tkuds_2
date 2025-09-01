
class lt_08_StringToInteger {
    public int myAtoi(String s) {
        if (s == null || s.isEmpty()) return 0;

        int i = 0, n = s.length();
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }
        if (i >= n) return 0;

        int sign = 1;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        int result = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            if (result > Integer.MAX_VALUE / 10 ||
               (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }

    public static void main(String[] args) {
        lt_08_StringToInteger solver = new lt_08_StringToInteger();
        System.out.println(solver.myAtoi("42"));           
        System.out.println(solver.myAtoi("   -42"));       
        System.out.println(solver.myAtoi("4193 with words")); 
        System.out.println(solver.myAtoi("words and 987"));   
        System.out.println(solver.myAtoi("-91283472332")); 
        System.out.println(solver.myAtoi("2147483648"));  
    }
}

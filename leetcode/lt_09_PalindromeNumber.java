
class lt_09_PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedHalf = 0;
        while (x > reversedHalf) {
            int digit = x % 10;
            reversedHalf = reversedHalf * 10 + digit;
            x /= 10;
        }

        return (x == reversedHalf || x == reversedHalf / 10);
    }

    public static void main(String[] args) {
        lt_09_PalindromeNumber solver = new lt_09_PalindromeNumber();
        System.out.println(solver.isPalindrome(121));  
        System.out.println(solver.isPalindrome(-121));  
        System.out.println(solver.isPalindrome(10));    
        System.out.println(solver.isPalindrome(0));   
        
    }
}

import java.util.HashSet;

public class lt_03_LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int left = 0, right = 0, maxLen = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            if (!set.contains(c)) {
                set.add(c);
                right++;
                maxLen = Math.max(maxLen, right - left);
            } else {
                // 如果重複，從左邊移除字元
                set.remove(s.charAt(left));
                left++;
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        lt_03_LongestSubstringWithoutRepeatingCharacters solver = new lt_03_LongestSubstringWithoutRepeatingCharacters();
        String s = "abcabcbb";
        System.out.println("最長子字串長度: " + solver.lengthOfLongestSubstring(s)); // 輸出 3
    }
}

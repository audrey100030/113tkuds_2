//題目：Substring with Concatenation of All Words
//要找出所有子字串，剛好由 words 的所有字組合而成（順序可變）

import java.util.*;

class lt_30_SubstringWithConcatenation {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();    // 每個單字的長度
        int wordCount = words.length;       // 單字總數
        int totalLen = wordLen * wordCount; // 所有單字拼接後的總長度

        // 預先計算 words 的詞頻
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        // 從不同的起始點開始滑動 (0 到 wordLen-1)
        for (int i = 0; i < wordLen; i++) {
            int left = i; // 視窗左邊界
            int count = 0;
            Map<String, Integer> windowMap = new HashMap<>();

            for (int j = i; j + wordLen <= s.length(); j += wordLen) {
                String word = s.substring(j, j + wordLen);

                if (wordMap.containsKey(word)) {
                    windowMap.put(word, windowMap.getOrDefault(word, 0) + 1);
                    count++;

                    // 如果某個單字出現次數超過需求，縮小視窗
                    while (windowMap.get(word) > wordMap.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        windowMap.put(leftWord, windowMap.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }

                    // 如果剛好匹配所有單字
                    if (count == wordCount) {
                        result.add(left);
                    }
                } else {
                    // 重置視窗
                    windowMap.clear();
                    count = 0;
                    left = j + wordLen;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        lt_30_SubstringWithConcatenation solver = new lt_30_SubstringWithConcatenation();
        System.out.println(solver.findSubstring("barfoothefoobarman", new String[]{"foo","bar"})); 
        // [0, 9]
        System.out.println(solver.findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","word"})); 
        // []
        System.out.println(solver.findSubstring("barfoofoobarthefoobarman", new String[]{"bar","foo","the"})); 
        // [6, 9, 12]
    }
}
/*
解題思路：
1.建立一個 wordMap 記錄 words 的詞頻。
2.由於每個單字長度固定，從 0 到 wordLen-1 作為不同的偏移起點。
3.使用滑動視窗，每次擷取一個長度為 wordLen 的子字串：
    如果在 wordMap 裡，就放進 windowMap，並維護出現次數。
    如果某個單字出現次數超標，縮小視窗直到符合條件。
    如果字數剛好等於 words.length，就找到一個起始索引。
4.如果遇到不是 wordMap 的單字，則清空視窗，重新開始。
*/
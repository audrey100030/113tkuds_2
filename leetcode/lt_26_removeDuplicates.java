//題目：Remove Duplicates from Sorted Array
//陣列已經排序，所以重複元素一定是連續的。要「原地修改」，不能額外開陣列。

class lt_26_removeDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int slow = 0; // 慢指針：指向最後一個不重複元素的位置
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                slow++;               // 移動慢指針
                nums[slow] = nums[fast]; // 更新下一個唯一元素
            }
        }

        return slow + 1; // 不重複元素的數量
    }

    public static void main(String[] args) {
        lt_26_removeDuplicates solver = new lt_26_removeDuplicates();
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int k = solver.removeDuplicates(nums);

        System.out.println("Unique count: " + k);
        System.out.print("Array after removing duplicates: ");
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
/*
解題思路：
1.使用兩個指針：
    slow：指向最後一個不重複元素。
    fast：遍歷整個陣列。
2.當 nums[fast] != nums[slow] → 找到新的不重複元素：
    slow++，然後把 nums[fast] 放到 nums[slow]。
3.遍歷結束後，唯一元素數量為 slow + 1。
*/
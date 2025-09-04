//題目：Search in Rotated Sorted Array
//陣列原本升序，但被旋轉過

class lt_33_SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // 判斷哪一半是有序的
            if (nums[left] <= nums[mid]) {
                // 左半邊有序
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // target 在左半邊
                } else {
                    left = mid + 1;  // target 在右半邊
                }
            } else {
                // 右半邊有序
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;  // target 在右半邊
                } else {
                    right = mid - 1; // target 在左半邊
                }
            }
        }

        return -1; // 沒找到
    }

    public static void main(String[] args) {
        lt_33_SearchInRotatedSortedArray solver = new lt_33_SearchInRotatedSortedArray();
        System.out.println(solver.search(new int[]{4,5,6,7,0,1,2}, 0)); // 4
        System.out.println(solver.search(new int[]{4,5,6,7,0,1,2}, 3)); // -1
        System.out.println(solver.search(new int[]{1}, 0));             // -1
    }
}
/*
解題思路：
1.每次二分找到 mid，判斷哪一半是有序的：
    如果 nums[left] <= nums[mid] → 左半邊有序。
    否則 → 右半邊有序。
2.再檢查 target 是否在有序區間內，決定往左或右繼續搜尋。
3.重複直到找到 target 或範圍縮小為空。
*/
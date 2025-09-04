//題目：Search Insert Position
//升序陣列中找到 target 的插入位置

class lt_35_SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid; // 找到目標
            } else if (nums[mid] < target) {
                left = mid + 1; // 往右找
            } else {
                right = mid - 1; // 往左找
            }
        }

        // 若沒找到，left 會是 target 應插入的位置
        return left;
    }

    // 測試
    public static void main(String[] args) {
        lt_35_SearchInsertPosition solver = new lt_35_SearchInsertPosition();
        System.out.println(solver.searchInsert(new int[]{1,3,5,6}, 5)); // 2
        System.out.println(solver.searchInsert(new int[]{1,3,5,6}, 2)); // 1
        System.out.println(solver.searchInsert(new int[]{1,3,5,6}, 7)); // 4
        System.out.println(solver.searchInsert(new int[]{1,3,5,6}, 0)); // 0
    }
}
/*
解題思路：
1.設定左右指針 left = 0, right = n-1。
2.不斷二分：
    若 nums[mid] == target → 回傳 mid。
    若 nums[mid] < target → 往右半邊找。
    若 nums[mid] > target → 往左半邊找。
3.當迴圈結束，left 會停在應插入的位置。
*/
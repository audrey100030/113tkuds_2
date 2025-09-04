//題目：Remove Element
//移除陣列中所有等於 val 的元素

class lt_27_RemoveElement {
    public int removeElement(int[] nums, int val) {
        int k = 0; // 指向存放「非 val 元素」的位置

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i]; // 覆蓋掉 val
                k++;
            }
        }

        return k; // 回傳不等於 val 的元素數量
    }

    public static void main(String[] args) {
        lt_27_RemoveElement solver = new lt_27_RemoveElement();
        int[] nums = {3,2,2,3};
        int val = 3;

        int k = solver.removeElement(nums, val);

        System.out.println("Remaining count: " + k);
        System.out.print("Array after removal: ");
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + " ");
        }

    }
}
/*
解題思路：
1.設一個 k 指針，指向存放「非 val 元素」的位置。
2.遍歷陣列：
    如果 nums[i] != val，就把它放到 nums[k]，然後 k++。
3.遍歷結束後，k 就是新陣列的長度
*/

class It_01_twoSum {
    public int[] twoSum(int[] nums, int target) {
        return helper(nums, target, 0, 1);
    }
    private int[] helper(int[] nums, int target, int i, int j) {
        if (i >= nums.length) {
            return new int[]{};  
        }
        if (j >= nums.length) {
            return helper(nums, target, i + 1, i + 2);
        }
        if (nums[i] + nums[j] == target) {
            return new int[]{i, j}; 
        }
        return helper(nums, target, i, j + 1);
    }
    public static void main(String[] args) {
        It_01_twoSum solver = new It_01_twoSum();  
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] result = solver.twoSum(nums, target); 
        System.out.println("索引: " + result[0] + ", " + result[1]);
    }
}

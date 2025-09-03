//題目：Container With Most Water
//要找兩根柱子形成最大「盛水容器」

class lt_11_ContainerWithMostWater {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int width = right - left;
            maxArea = Math.max(maxArea, h * width);

            // 移動較小的那根柱子
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    // 測試
    public static void main(String[] args) {
        lt_11_ContainerWithMostWater solver = new lt_11_ContainerWithMostWater();
        System.out.println(solver.maxArea(new int[]{1,8,6,2,5,4,8,3,7})); // 49
        System.out.println(solver.maxArea(new int[]{1,1}));               // 1
    }
}
/*
解題思路：
1.用兩個指針 left、right 從陣列兩端開始。
2.每次計算當前容器容量，更新最大值。
3.移動高度較小的一側（因為瓶頸在較矮的柱子）。
4.直到兩指針相遇。
*/
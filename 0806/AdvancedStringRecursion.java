/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
public class AdvancedStringRecursion {
    public static void main(String[] args) {
        //所有排列組合
        char[] arr = {'a', 'b', 'c'};
        System.out.println("所有排列組合：");
        permute(arr, 0);
        
        //字串匹配
        char[] one = {'a', 'b', 'c', 'd', 'a', 'b', 'e'};
        char[] two = {'a', 'b', 'e'};
        boolean matchFound = match(one, 0, two, 0);
        System.out.println("字串匹配結果：" + (matchFound ? "有找到" : "沒找到"));
        
        //移除重複字符
        char[] three = {'a', 'b', 'a', 'c', 'b'};
        char[] newthree = removeDuplicates(three, 0, new char[three.length], 0);
        System.out.print("移除重複後：");
        printCharArray(newthree);
        
        //所有子字串組合
        char[] four = {'a', 'b', 'c'};
        System.out.println("所有子字串組合：");
        substrings(four, 0, "");
    }
    
    public static void permute(char[] arr, int index){
        if(index == arr.length){
            printCharArray(arr);
            return;
        }
        for(int i = index; i < arr.length; i++){
            swap(arr, i, index);
            permute(arr, index + 1);
            swap(arr, i, index); 
        }
    }
    
    public static void swap(char[] arr, int i, int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static void printCharArray(char[] arr){
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i]);
        }
        System.out.println();
    }
    
    public static boolean match(char[] one, int index1, char[] two, int index2){
        if(index2 == two.length) return true;
        if(index1 == one.length) return false;
        if(one[index1] == two[index2]){
            return match(one, index1 + 1, two, index2 + 1);
        }else{
           return match(one, index1 + 1, two, 0); 
        }
    }
    
    public static char[] removeDuplicates(char[] arr, int index, char[] result, int resIndex){
        if(index == arr.length){
            char[] ans = new char[resIndex];
            for(int i=0; i < resIndex; i++){
                ans[i] = result[i];
            }
            return ans;
        }
        boolean find = false;
        for(int i=0; i < resIndex; i++){
            if(arr[index] == result[i]){
                find = true;
                break;
            }
        }
        if(!find){
            result[resIndex] = arr[index];
            return removeDuplicates(arr, index + 1, result, resIndex + 1);
        }else{
            return removeDuplicates(arr, index + 1, result, resIndex);
        }
    }
    
    public static void substrings(char[] arr, int index, String current){
        if(index == arr.length){
            if(!current.isEmpty()){
                System.out.println(current);
            }
            return;
        }
        substrings(arr, index + 1, current + arr[index]);
        substrings(arr, index + 1, current);
    }
}

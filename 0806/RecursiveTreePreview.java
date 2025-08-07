/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author audre
 */
public class RecursiveTreePreview {
    public static void main(String[] args) {
        //模擬資料夾結構
        Object[] fileSystem = {
            "file1.txt",
            new Object[]{
                "file2.txt", "file3.txt",
                new Object[]{"file4.txt"}
            }
        };
        System.out.println("總檔案數：" + countFiles(fileSystem));
        
        //多層選單列印
        Object[] menu = {
            "主選單",
            new Object[]{
                new Object[]{"檔案", new Object[]{"開啟", "儲存"}},
                new Object[]{"編輯", new Object[]{"剪下", "貼上"}},
                "幫助"
            }
        };
        System.out.println("多層選單：");
        printMenu(menu, 0);
        
        //巢狀陣列展平
        Object[] nested = {
            1, new Object[]{2, 3}, 4, new Object[]{5, new Object[]{6, 7}}, 8
        };
        System.out.print("展平後：");
        flatten(nested);
        System.out.println();
        
        //巢狀清單最大深度
        Object[] deep = {
            1, new Object[]{2, new Object[]{3, new Object[]{4}}}
        };
        System.out.println("最大深度：" + maxDepth(deep));
    }
    
    public static int countFiles(Object[] node){
        int count = 0;
        for(int i = 0; i < node.length; i++) {
            if(node[i] instanceof Object[]) {
                count += countFiles((Object[]) node[i]);
            }else{
                count++;
            }
        }
        return count;
    }
    
    public static void printMenu(Object[] node, int level){
        for(int i = 0; i < node.length; i++) {
            if(node[i] instanceof Object[]) {
                printMenu((Object[]) node[i], level + 1);
            }else{
                for (int j = 0; j < level; j++) System.out.print("  ");
                System.out.println("- " + node[i]);
            }
        }
    }
    
    public static void flatten(Object[] arr){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] instanceof Object[]){
                flatten((Object[]) arr[i]);
            }else{
                System.out.print(arr[i] + " ");
            }
        }
    }
    
    public static int maxDepth(Object[] arr){
        int max = 1;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] instanceof Object[]){
                int d = 1 + maxDepth((Object[]) arr[i]);
                if(d > max) max = d;
            }
        }
        return max;
    }

}

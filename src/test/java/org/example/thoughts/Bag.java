package org.example.thoughts;

/**
 * @author anyuan
 * @since 2021-03-08 16:26
 */
public class Bag {

    public int maxW = Integer.MIN_VALUE; // 存储背包中物品总重量的最大值
    public int count = 0;

    // cw 表示当前已经装进去的物品的重量和；i 表示考察到哪个物品了；
// w 背包重量；items 表示每个物品的重量；n 表示物品个数
// 假设背包可承受重量 100，物品个数 10，物品重量存储在数组 a 中，那可以这样调用函数：
// f(0, 0, a, 10, 100)
    public void f(int i, int cw, int[] items, int n, int w) {
        count++;
        if (cw == w || i == n) { // cw==w 表示装满了 ;i==n 表示已经考察完所有的物品
            if (cw > maxW) maxW = cw;
            return;
        }
        f(i + 1, cw, items, n, w);
        if (cw + items[i] <= w) {// 已经超过可以背包承受的重量的时候，就不要再装了
            f(i + 1, cw + items[i], items, n, w);
        }
    }

    public static void main(String[] args) {
        final Bag bag = new Bag();
        bag.f(0, 0, new int[]{1, 10, 20, 30, 40, 50, 60, 70, 80, 90}, 10, 100);
        System.out.println(bag.count);
    }
}

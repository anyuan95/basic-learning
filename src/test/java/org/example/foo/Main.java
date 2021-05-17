package org.example.foo;

public class Main {

    static int[] queens;
    static int count = 0;
    static int n;

    static void dfs(int row) {
        if (row == n) {
            count++;
            System.out.println("方案" + count + ":"); //打印方案
            print();
            return;
        }
        for (int column = 0; column < n; column++) {
            System.out.print("dfs: row=[" + row + "], column=[" + column + "]");
            queens[row] = column;
            if (conflict(row) == false) {
                System.out.println("----- no conflict");
                dfs(row + 1);
            } else {
                System.out.println("----- conflicted");
            }
        }
    }

    static boolean conflict(int row) {
        for (int x = 0; x < row; x++) {
            if (queens[x] == queens[row])
                return true;
            if (Math.abs(queens[x] - queens[row]) == (row - x))
                return true;
        }
        return false;
    }

    static void print() { //打印方案的函数
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (queens[i] == j)
                    System.out.print("1 "); //表示皇后位置
                else
                    System.out.print("0 ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        n = 4;
        queens = new int[n];
        dfs(0);
        System.out.println("总方案数：" + count);

    }

}
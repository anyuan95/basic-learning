package org.example.ai.springless.mcp.client;

import java.io.*;

public class ProcessExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 启动进程（示例：启动一个交互式 Python 脚本）
        Process process = Runtime.getRuntime().exec("python interactive_script.py");

        // 创建线程异步读取标准输出流
        Thread outputThread = new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("输出: " + line);
                    // 根据输出内容动态响应输入（示例：检测到输入提示时写入内容）
                    if (line.contains("请输入姓名:")) {
                        writeToProcess(process, "张三\n");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // 创建线程异步读取错误流
        Thread errorThread = new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.err.println("错误: " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // 启动线程
        outputThread.start();
        errorThread.start();

        // 等待进程结束
        int exitCode = process.waitFor();
        System.out.println("进程退出码: " + exitCode);

        // 等待线程结束
        outputThread.join();
        errorThread.join();
    }

    // 向进程写入输入内容
    private static void writeToProcess(Process process, String input) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(process.getOutputStream()))) {
            writer.write(input);
            writer.flush(); // 必须刷新缓冲区
            System.out.println("已输入: " + input.trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
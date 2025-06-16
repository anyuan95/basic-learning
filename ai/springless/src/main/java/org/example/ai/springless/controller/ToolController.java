package org.example.ai.springless.controller;

import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

@RestController
public class ToolController {

    @PostMapping("/tools")
    public String anything() {
        // 启动交互式脚本
        final Process process = RuntimeUtil.exec("uvx mcp-server-time --local-timezone=America/New_York");

        try (
            // 获取标准输入流和输出流
            final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))
        ) {
            // 写入内容到标准输入流
            writer.write("input content\n");
            writer.flush();

            // 读取标准输出流的内容
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // 返回读取到的内容
            return output.toString();
        } catch (Exception e) {
            // 捕获异常并返回错误信息
            return "Error: " + e.getMessage();
        }
    }
}
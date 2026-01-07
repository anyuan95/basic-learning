package org.example.ai.spring.mcp.server.stdio;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.modelcontextprotocol.spec.McpSchema;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author anyuan
 * @date 2025-04-29 09:54
 */
public class UseBasicIoStreamTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void test() {
        try {
            // 1. 创建并启动外部进程
            final ProcessBuilder builder = new ProcessBuilder();
            // uvx mcp-server-time --local-timezone=Asia/Shanghai
//            builder.command("uvx", "mcp-server-time", "--local-timezone=Asia/Shanghai");
            builder.command("npx", "-y", "@modelcontextprotocol/server-github");
            final Process process = builder.start();

            final String input = objectMapper.writeValueAsString(buildListToolRequest());
            final OutputStream outputStream = process.getOutputStream();
            IoUtil.write(outputStream, false, input.getBytes(StandardCharsets.UTF_8));
            IoUtil.write(outputStream, false, StrUtil.LF.getBytes(StandardCharsets.UTF_8));
            final String output = IoUtil.read(process.getInputStream(), StandardCharsets.UTF_8);
            System.out.println(output);

//            // 2. 获取进程的输入输出流
//            OutputStream stdin = process.getOutputStream();  // 向进程写入数据
//            InputStream stdout = process.getInputStream();   // 读取进程输出
//
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
//            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
//
//            // 3. 向进程发送数据
//            String input = objectMapper.writeValueAsString(buildListToolRequest());
//            writer.write(input);
//            writer.newLine();  // 很多程序需要换行才会处理输入
//            writer.flush();    // 确保数据被发送
//
//            // 4. 读取进程输出
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println("程序输出: " + line);
//            }

            // 5. 等待进程结束
            int exitCode = process.waitFor();
            System.out.println("进程退出码: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static final AtomicInteger MCP_REQUEST_ID_COUNTER = new AtomicInteger(1);

    private static McpSchema.JSONRPCRequest buildListToolRequest() {
        return new McpSchema.JSONRPCRequest(
                McpSchema.JSONRPC_VERSION,
                McpSchema.METHOD_TOOLS_LIST,
                // 不知道id的作用，先自增
                MCP_REQUEST_ID_COUNTER.getAndIncrement(),
                Collections.emptyList());
    }

}

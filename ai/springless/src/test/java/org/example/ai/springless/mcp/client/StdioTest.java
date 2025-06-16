package org.example.ai.springless.mcp.client;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RuntimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.*;

@Slf4j
public class StdioTest {

    public static final String LIST_TOOLS_JSON = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"tools/list\",\"params\":{}}";

    @Test
    public void testStdio() throws IOException {
        // 启动交互式脚本
//        final Process process = RuntimeUtil.exec("uvx mcp-server-time --local-timezone=America/New_York");
//        final Process process = RuntimeUtil.exec("cmd /c npx -y @modelcontextprotocol/server-filesystem D:\\Documents");
//        final Process process = RuntimeUtil.exec("ipconfig");
//        final Process process = Runtime.getRuntime().exec("cmd /c npx -y @modelcontextprotocol/server-filesystem D:\\Documents");

        final ProcessBuilder builder = new ProcessBuilder();
//        builder.command("cmd", "/c", "npx -y @modelcontextprotocol/server-filesystem D:\\Documents");
        builder.command("cmd", "/c", "ipconfig");

        final Process process = builder.start();


        try (
                // 获取标准输入流和输出流
//                final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
//                final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))
                final OutputStream outputStream = process.getOutputStream();
                final InputStream inputStream = process.getInputStream();

        ) {
            // 写入内容到标准输入流
            IoUtil.writeUtf8(outputStream, false, LIST_TOOLS_JSON);
//            writer.write(LIST_TOOLS_JSON);
//            writer.flush();

//            // 读取标准输出流的内容
//            StringBuilder output = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                output.append(line).append("\n");
//            }
            final String output = IoUtil.readUtf8(inputStream);

            // 返回读取到的内容
            log.info("输出内容：{}", output);
        } catch (Exception e) {
            // 捕获异常并返回错误信息
            log.error("出现异常", e);
        }
    }

    @Test
    public void testStdio2() throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("bash", "-c", "echo Hello; read x; echo $x");

        try {
            Process process = builder.start();

            // 获取输出流和输入流
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            PrintWriter writer = new PrintWriter(process.getOutputStream());

            // 读取进程的输出
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.equals("Hello")) {
                    // 发送输入到进程
                    writer.println("World");
                    writer.flush(); // 确保输入被发送
                }
            }

            // 等待进程结束并获取退出值
            int exitCode = process.waitFor();
            System.out.println("Exited with code : " + exitCode);

            reader.close();
            writer.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

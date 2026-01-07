package org.example.ai.spring.mcp.mixed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * MCP混合应用，同时作为MCP服务器和MCP客户端
 * - 作为客户端连接配置的MCP服务器（stdio或SSE形式）
 * - 作为服务器提供SSE形式的API端点，将请求转发到内部MCP客户端
 */
@EnableAsync
@SpringBootApplication
@ComponentScan(basePackages = {
    "org.example.ai.spring.mcp.mixed",
    "org.springframework.ai.mcp"
})
public class McpMixedMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpMixedMainApplication.class, args);
    }
}

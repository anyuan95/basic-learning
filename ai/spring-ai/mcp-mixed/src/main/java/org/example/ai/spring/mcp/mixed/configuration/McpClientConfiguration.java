package org.example.ai.spring.mcp.mixed.configuration;

import io.modelcontextprotocol.client.McpSyncClient;
import jakarta.annotation.Resource;
import org.springframework.boot.SpringBootConfiguration;

import java.util.List;

/**
 * 作为client，要连接配置文件中的所有server
 *
 * @author anyuan
 * @date 2025-04-28 21:31
 */
@SpringBootConfiguration
public class McpClientConfiguration {

    @Resource
    private List<McpSyncClient> mcpSyncClients;

}

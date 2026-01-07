package org.example.ai.spring.mcp.mixed.configuration;

import jakarta.annotation.Resource;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.boot.SpringBootConfiguration;

/**
 * 作为server，要能暴露tool等组件能力
 *
 * @author anyuan
 * @date 2025-04-28 21:29
 */
@SpringBootConfiguration
public class McpServerConfiguration {

    @Resource
    private ToolCallbackProvider toolCallbackProvider;

}

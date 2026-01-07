package org.example.ai.spring.mcp.client.controller;

import io.modelcontextprotocol.client.McpSyncClient;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ChatController {

    @Resource
    private ChatClient.Builder chatClientBuilder;
    @Resource
    private ToolCallbackProvider toolCallbackProvider;
    @Resource
    private List<McpSyncClient> mcpSyncClients;

    @GetMapping("/chat")
    public String chat(@RequestParam("content") String content) {
        final String result = chatClientBuilder
//                .defaultTools(new SyncMcpToolCallbackProvider(mcpSyncClients))
                .defaultToolCallbacks(toolCallbackProvider)
                .build()
                .prompt()
                .user(content)
                .call()
                .content();
        log.info("chat返回值：{}", result);
        return result;
    }
}

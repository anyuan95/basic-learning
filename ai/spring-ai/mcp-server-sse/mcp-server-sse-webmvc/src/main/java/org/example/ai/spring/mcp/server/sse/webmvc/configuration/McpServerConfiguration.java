package org.example.ai.spring.mcp.server.sse.webmvc.configuration;

import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.server.McpSyncServerExchange;
import io.modelcontextprotocol.spec.McpSchema;
import lombok.extern.slf4j.Slf4j;
import org.example.ai.spring.mcp.server.sse.webmvc.tools.Tools;
import org.example.ai.spring.mcp.server.sse.webmvc.tools.WeatherTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.function.BiConsumer;

@Slf4j
@SpringBootConfiguration
public class McpServerConfiguration {

    @Bean
    public ToolCallbackProvider myTools(List<Tools> tools) {
        return MethodToolCallbackProvider.builder()
                // TODO 这里toArray不太优雅，但是目前没想到更好的方式
                .toolObjects(tools.toArray(new Tools[0]))
                .build();
    }

//    @Bean
//    public List<McpServerFeatures.SyncResourceSpecification> myResources(...) {
//        var systemInfoResource = new McpSchema.Resource(...);
//        var resourceSpecification = new McpServerFeatures.SyncResourceSpecification(systemInfoResource, (exchange, request) -> {
//            try {
//                var systemInfo = Map.of(...);
//                String jsonContent = new ObjectMapper().writeValueAsString(systemInfo);
//                return new McpSchema.ReadResourceResult(
//                        List.of(new McpSchema.TextResourceContents(request.uri(), "application/json", jsonContent)));
//            }
//            catch (Exception e) {
//                throw new RuntimeException("Failed to generate system info", e);
//            }
//        });
//
//        return List.of(resourceSpecification);
//    }

//    @Bean
//    public List<McpServerFeatures.SyncPromptSpecification> myPrompts() {
//        var prompt = new McpSchema.Prompt("greeting", "A friendly greeting prompt",
//                List.of(new McpSchema.PromptArgument("name", "The name to greet", true)));
//
//        var promptSpecification = new McpServerFeatures.SyncPromptSpecification(prompt, (exchange, getPromptRequest) -> {
//            String nameArgument = (String) getPromptRequest.arguments().get("name");
//            if (nameArgument == null) {
//                nameArgument = "friend";
//            }
//            var userMessage = new McpSchema.PromptMessage(McpSchema.Role.USER, new McpSchema.TextContent("Hello " + nameArgument + "! How can I assist you today?"));
//            return new McpSchema.GetPromptResult("A personalized greeting message", List.of(userMessage));
//        });
//
//        return List.of(promptSpecification);
//    }
//
//    @Bean
//    public BiConsumer<McpSyncServerExchange, List<McpSchema.Root>> rootsChangeHandler() {
//        return (exchange, roots) -> {
//            log.info("Registering root resources: {}", roots);
//        };
//    }
}

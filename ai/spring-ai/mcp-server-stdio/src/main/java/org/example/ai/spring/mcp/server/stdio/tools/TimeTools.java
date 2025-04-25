package org.example.ai.spring.mcp.server.stdio.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TimeTools {

    @Tool(description = "获取当前时间戳")
    public Long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }
}

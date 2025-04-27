package org.example.ai.spring.mcp.server.stdio.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class TimeTools implements Tools{

    @Tool(description = "获取当前时间戳")
    public Long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    @Tool(description = "获取特定日期时间的时间戳")
    public Long getTimestamp(@ToolParam(description = "日期时间") Date date) {
        return date.getTime();
    }
}

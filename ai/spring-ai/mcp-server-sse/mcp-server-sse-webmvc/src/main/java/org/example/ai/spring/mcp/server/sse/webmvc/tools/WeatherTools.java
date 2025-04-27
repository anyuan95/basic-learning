package org.example.ai.spring.mcp.server.sse.webmvc.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WeatherTools implements Tools {

    @Tool(description = "获取今日天气信息")
    public String getTodayWeather(@ToolParam(description = "城市名称，使用英文格式，如Beijing、New York等") String city) {
        log.info("假装计算一下城市[{}]今天的天气信息", city);
        return "天气信息：" + city;
    }

}

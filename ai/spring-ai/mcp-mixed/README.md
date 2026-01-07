# MCP混合模块

该模块同时作为MCP客户端和MCP服务器：
- 作为客户端：连接配置中指定的MCP服务器（支持stdio和SSE两种形式）
- 作为服务器：对外提供统一的SSE形式的API端点

## 工作原理

1. 模块加载配置的MCP客户端（通过配置文件指定）
2. 提供SSE形式的API端点接收工具调用请求
3. 将工具调用请求路由到合适的内部MCP客户端
4. 返回处理结果给调用者

## 主要功能

- 将多个MCP服务器统一封装为一个SSE接口
- 支持同时连接多种类型的MCP服务器（stdio、SSE等）
- 提供统一的工具注册和发现机制
- 灵活的路由策略，可以根据工具名称前缀等因素选择合适的处理服务器

## 配置说明

在`application.yml`中可以配置：

```yaml
spring:
  ai:
    mcp:
      server:
        enabled: true
        name: mixed-mcp-server
        version: 1.0.0
        type: SYNC
        sse-message-endpoint: /api/mcp/messages
      client:
        enabled: true
        stdio:
          enabled: true
          servers-configuration: classpath:/unix/mcp-servers.json
        sse:
          enabled: false  # 设置为true可连接其他SSE形式的MCP服务器
```

## API端点

- `/api/mcp/messages` - 主要的SSE消息端点，用于接收工具调用请求
- `/api/mcp/tools` - 查询所有可用工具
- `/api/mcp/info` - 查询服务器信息
package org.example.spring.web.sse;

import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.Resource;

/**
 * 实现sse通信
 * 参考：https://www.jb51.net/program/3188562lv.htm
 *
 * @author anyuan
 * @date 2025-03-13 10:47
 */
@Controller
@RequestMapping("/sse")
public class SseController {

    @Resource
    private SseClient sseClient;

    @GetMapping("/createSse")
    public SseEmitter createSse(String uid) {
        return sseClient.createSse(uid);
    }

    @CrossOrigin
    @GetMapping("/sendMsg")
    @ResponseBody
    public String sseChat(String uid) {
        for (int i = 0; i < 10; i++) {
            sseClient.sendMessage(uid, "no" + i, IdUtil.fastUUID());
        }
        return "ok";
    }

    /**
     * 关闭连接
     */
    @CrossOrigin
    @GetMapping("/closeSse")
    public void closeConnect(String uid) {
        sseClient.closeSse(uid);
    }
}

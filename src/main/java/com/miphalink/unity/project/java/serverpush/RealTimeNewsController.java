package com.miphalink.unity.project.java.serverpush;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ajax 长连接获取实时新闻推送
 * 实时推送新闻
 */
@Controller
@RequestMapping(value = "serverPush",produces = "text/html;charset=UTF-8")
public class RealTimeNewsController {
    String[] NEWS = new String[]{
            "重磅!上海发布科改“25条”," +
            "激发创新将有这些措施 ",
            "李克强记者会上释放哪些“重磅”民生利好? ",
            "我的科研我做主 上海科改“25条”重磅落地 ",
            "重磅!中央最新的选人用人标准来了",
            "重磅!决战美联储决议机构前瞻观点大汇总 "};
    String[] NEWSOFREN = new String[]{
            "重磅!任雯娟升任院长!" +
            "感人，任院长亲临灾区慰问受灾群众 ",
            "感人，张校长亲临灾区慰问受灾群众 ",
            "惊?北京市民张珺玮中奖1个亿！ ",
            "惊?太原市民任雯娟中奖1个亿！"};

    //创建线程池
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    /**
     * servlet3.0 异步请求处理比较麻烦
     * 这里我们使用Spring的DeferredResult
     * web.xml 设置
     * <async-supported>true</async-supported>
     * <dispatcher>ASYNC</dispatcher>
     * @return
     */
    @RequestMapping("/realTimeNews")
    @ResponseBody
    public DeferredResult<String> realTimeNews() {
        System.out.println("---->realTimeNews");
        //线程不安全，不能做成员变量
        final DeferredResult<String> deferredResult = new DeferredResult<String>();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //随机获取新闻
                int index = new Random().nextInt(NEWSOFREN.length);
                System.out.println(index);
                deferredResult.setResult(NEWSOFREN[index]);

            }
        });

        return deferredResult;
    }
}

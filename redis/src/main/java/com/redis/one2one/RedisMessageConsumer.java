package com.redis.one2one;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redis.pojo.User;
import com.redis.redisutil.JedisPoolUtils;

import redis.clients.jedis.Jedis;

/**
 * 消息消费者
 * @author hp
 *
 */
public class RedisMessageConsumer {
	 /**
     * 参数是初始化线程池子的大小
     */
    private static final ScheduledExecutorService batchTaskPool = Executors.newScheduledThreadPool(2);
    private static final Logger log = LoggerFactory.getLogger(RedisMessageConsumer.class);
    /**
     * 消费消息
     *
     * @param consumerUser 接收消息的用户
     */
    public static void consumerMessage(final User consumerUser) {
        final Jedis jedis = JedisPoolUtils.getJedis();

        //新建一个线程，线程池获取消息
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true){
                    List<String> messages = jedis.brpop(0, consumerUser.getUserName() + ":msg");//0是timeout,返回的是一个集合，第一个是消息的key，第二个是消息的内容
                    String key = messages.get(0);//第一个是key
                    String message = messages.get(1);//第二个是消息
                    String sendUserName = message.substring(0, message.indexOf("*-*"));//获取消息的发送者
                    message = message.substring(message.indexOf("*-*")+3);//获取消息内容
                    log.debug("ThreadName is {},user {} consumer message {} ,sended by {}", Thread.currentThread().getName(),consumerUser.getUserName(), message, sendUserName);
                }
            }
        };
        //线程池中获取消息
        //第一个参数是需要执行的任务，第二个参数是第一次的延迟时间，第三个参数是两次执行的时间间隔，第四个参数是时间的单位
        batchTaskPool.scheduleWithFixedDelay(runnable, 3,5, TimeUnit.SECONDS);
    }

}

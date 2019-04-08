package com.redis.one2one;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redis.pojo.User;
import com.redis.redisutil.JedisPoolUtils;

import redis.clients.jedis.Jedis;
/**
 * 消息生产者
 * @author hp
 *
 */
public class RedisMessageProducer {
	 private static final Logger log = LoggerFactory.getLogger(RedisMessageProducer.class);
	 /**
	     * 发送消息的方法
	     *
	     * @param sendUser   发送消息的用户
	     * @param sendToUser 接收消息的用户
	     * @param messages   可变参数返送多条消息
	     * @return
	     */
	    public static boolean sendMessage(User sendUser, User sendToUser, String... messages) {
	        Jedis jedis = JedisPoolUtils.getJedis();
	        try {
	            String key = sendToUser.getUserName() + ":msg";
	            //将消息的内容加上消息的发送人以 *-* 分割，不能用增强for循环
	            for (int i = 0, length_1 = messages.length; i < length_1; i++) {
	                messages[i] = sendUser.getUserName() + "*-*" + messages[i];
	            }
	            Long lpush = jedis.lpush(key, messages);//返回值是还有多少消息未消费
	            log.debug("user {} send message [{}] to {}", sendUser.getUserName(), messages, sendToUser.getUserName());
	            log.debug("user {} has {} messages ", sendToUser.getUserName(), lpush);
	        } catch (Exception e) {
	            log.error("sendMessage error", e);
	        } finally {
	            jedis.close();
	        }
	        return true;
	    }

}

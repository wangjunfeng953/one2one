package redis;

import com.redis.one2one.RedisMessageProducer;
import com.redis.pojo.User;

public class TestProducer {
	public static void main(String[] args) {
        User sndUser = new User();
        sndUser.setUserName("zhangsan");

        User sndToUser = new User();
        sndToUser.setUserName("lisi");

        User sndToUser2 = new User();
        sndToUser2.setUserName("wangwu");

        RedisMessageProducer.sendMessage(sndUser, sndToUser, "给李四的消息一", "给李四的消息二");
        RedisMessageProducer.sendMessage(sndUser, sndToUser2, "给王五的消息一1", "给王五的消息二");
    }

}

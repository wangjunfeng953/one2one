package redis;

import com.redis.one2one.RedisMessageConsumer;
import com.redis.pojo.User;

public class TestConsumer {

	    public static void main(String[] args) {
	        User sndToUser = new User();
	        sndToUser.setUserName("lisi");

	        User sndToUser2 = new User();
	        sndToUser2.setUserName("wangwu");

	        RedisMessageConsumer.consumerMessage(sndToUser);
	        RedisMessageConsumer.consumerMessage(sndToUser2);
	    }
}

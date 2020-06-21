package demo.redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class RedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("name", "wanglihong222");
        jedis.lpush("listKey","1","2");
        jedis.sadd("setKey","3","4","4");
        jedis.zadd("sortKey",1,"sort1");
        jedis.zadd("sortKey",2,"sort2");
        Map map = new HashMap<String, String>();
        map.put("name","zhangsan");
        map.put("age","16");
        jedis.hmset("hmkey",map);
        jedis.close();
    }
}

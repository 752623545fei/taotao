import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.nio.channels.Pipe;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by lyf on 2016/12/8.
 */
public class JedisTest {

    @Test
    public void testJedis(){
        Jedis jedis = new Jedis("192.168.56.103",6379);
        jedis.set("a","1");
        String a = jedis.get("a");
        System.out.println(a);
        jedis.close();
    }

    @Test
    public void testJedisCluster(){
        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.0.195",7001));
        nodes.add(new HostAndPort("192.168.0.195",7002));
        nodes.add(new HostAndPort("192.168.0.195",7003));
        nodes.add(new HostAndPort("192.168.0.195",7004));
        nodes.add(new HostAndPort("192.168.0.195",7005));
        nodes.add(new HostAndPort("192.168.0.195",7006));

        JedisCluster cluster = new JedisCluster(nodes);
        long start = new Date().getTime();
        cluster.set("bb","111");
        String b = cluster.get("bb");
        long end = new Date().getTime();
        System.out.println(end-start);
        System.out.println(b);
        cluster.close();

    }
    @Test
    public void testSpringJedis(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-jedis.xml");
        JedisPool client = (JedisPool) context.getBean("redisClient");
        Jedis jedis = client.getResource();
        jedis.set("zxc","123");
        String zxc = jedis.get("zxc");
        System.out.println(zxc);
        jedis.close();
    }

    @Test
    public void testSpringJedisCluster(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-jedis.xml");
        JedisCluster cluster = (JedisCluster) context.getBean("redisClient");
        cluster.set("zxc","123");
        String zxc = cluster.get("zxc");


        System.out.println(zxc);
        cluster.close();
    }
}

package DB;

import SiteConfig.SiteConfig;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by wangjj on 2018/11/5.
 * 服务端：使用指定redis.conf启动：./src/redis-server ./redis.conf
 * 客户端：连接指定host及port的server：./src/redis-cli -h 10.110.180.85 -p 6379
 */
public class RedisUtils {
    private static final Logger log = Logger.getLogger(RedisUtils.class);
    private static JedisPool jedisPool;
    private static JedisCluster jedisCluster;

    public void basic_test(String host, int port) {
        Jedis jedis = new Jedis(host, port);
        log.info("连接成功");
        // String测试
        jedis.set("test", "hello world, redis");
        log.info("redis存储的字符串为："+jedis.get("test"));
        // List测试
        String siteList = "site-list";
        jedis.lpush(siteList, "Runoob");
        jedis.lpush(siteList, "Google");
        jedis.lpush(siteList, "Taobao");
        List<String> list = jedis.lrange(siteList, 0, 2);
        for (int i=0; i<list.size(); i++) {
            log.info("列表项为："+list.get(i));
        }
        // Keys测试
        Set<String> keys = jedis.keys("*");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            log.info("key:"+it.next());
        }
    }

    public JedisPool getJedisPool(String host, int port, int maxTotal, int maxIdle, long maxWait) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWait);
        return new JedisPool(config, host, port);
    }

    public JedisCluster getJedisCluster(String clusterNodes, int maxTotal, int maxIdle) {
        if (StringUtils.isNotBlank(clusterNodes)) {

        } else {
            return null;
        }
        return null;
    }
    public String get(String key) {
        if (jedisCluster != null) {
            return jedisCluster.get(key);
        } else {
            Jedis jedis = jedisPool.getResource();
            try {
                return jedis.get(key);
            } catch (Exception e) {
                log.error(e, e);
                jedis.close();
                jedis = null;
                throw e;
            } finally {
                if (jedis != null) {
                    jedis.close();//释放jedis实例
                }
            }
        }
    }

    public void set(String key, Serializable value, int seconds) {
        byte[] keyBytes = key.getBytes();
        if (jedisCluster != null) {
            jedisCluster.set(keyBytes, SerializationUtils.serialize(value));
            jedisCluster.expire(keyBytes, seconds);
        } else {
            Jedis jedis = jedisPool.getResource();
            try {
                jedis.set(keyBytes, SerializationUtils.serialize(value));
                jedis.expire(keyBytes, seconds);
            } catch (Exception e) {
                log.error(e, e);
                jedis.close();
                jedis = null;
                throw e;
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
    }

    public void set(String key, Serializable value) {
        byte[] keyBytes = key.getBytes();
        if (jedisCluster != null) {
            jedisCluster.set(keyBytes, SerializationUtils.serialize(value));
        } else {
            Jedis jedis = jedisPool.getResource();
            try {
                jedis.set(keyBytes, SerializationUtils.serialize(value));
            } catch (Exception e) {
                log.error(e, e);
                jedis.close();
                jedis = null;
                throw e;
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
    }

    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return clazz.cast(ois.readObject());
        } catch (Exception e) {
            log.error(e, e);
        }
        return null;
    }

    public <T> T get(String key, Class<T> clazz) {
        byte[] keyBytes = key.getBytes();
        if (jedisCluster != null) {
            byte[] data = jedisCluster.get(keyBytes);
            try {
                return data == null ? null : deserialize(keyBytes, clazz);
            } catch (Exception e) {
                log.error(e, e);
            }
        } else {
            Jedis jedis = jedisPool.getResource();
            try {
                byte[] data = jedis.get(keyBytes);
                return data == null ? null : deserialize(keyBytes, clazz);
            } catch (Exception e) {
                log.error(e, e);
                jedis.close();
                jedis = null;
                throw e;
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
        return null;
    }

    public <T> T gas(String key, Class<T> clazz, int seconds) {
        byte[] keyBytes = key.getBytes();
        if (jedisCluster != null) {
            byte[] data = jedisCluster.get(keyBytes);
            if (data != null) {
                jedisCluster.expire(keyBytes, seconds);
                return deserialize(data, clazz);
            } else {
                return null;
            }
        } else {
            Jedis jedis = jedisPool.getResource();
            try {
                byte[] data = jedis.get(keyBytes);
                if (data != null) {
                    jedis.expire(keyBytes, seconds);
                    return deserialize(data, clazz);
                } else {
                    return null;
                }
            } catch (Exception e) {
                log.error(e, e);
                jedis.close();
                jedis = null;
                throw e;
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
    }

    public String gas(String key, int seconds) {
        if (jedisCluster != null) {
            String data = jedisCluster.get(key);
            if (data != null) {
                jedisCluster.expire(data, seconds);
            }
            return data;
        } else {
            Jedis jedis = jedisPool.getResource();
            try {
                String data = jedis.get(key);
                if (data != null) {
                    jedis.expire(data, seconds);
                }
                return data;
            } catch (Exception e) {
                log.error(e, e);
                jedis.close();
                jedis = null;
                throw e;
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
    }

    public void del(String... keys) {
        if (jedisCluster != null) {
            jedisCluster.del(keys);
        } else {
            Jedis jedis = jedisPool.getResource();
            try {
                jedis.del(keys);
            } catch (Exception e) {
                log.error(e, e);
                jedis.close();
                jedis = null;
                throw e;
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
    }

    public boolean exists(String key) {
        if (jedisCluster != null) {
            return jedisCluster.exists(key);
        } else {
            Jedis jedis = jedisPool.getResource();
            try {
                return jedis.exists(key);
            } catch (Exception e) {
                log.error(e, e);
                jedis.close();
                jedis = null;
                throw e;
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
    }
    public static void main(String[] args) {
        String host = SiteConfig.get("redis.host");
        int port = Integer.parseInt(SiteConfig.get("redis.port"));
        int maxTotal = Integer.parseInt(SiteConfig.get("redis.maxTotal"));
        int maxIdle = Integer.parseInt(SiteConfig.get("redis.maxIdle"));
        long maxWait = Long.parseLong(SiteConfig.get("redis.maxWait"));
        String clusterNodes = SiteConfig.get("redis.nodes");

        RedisUtils redisUtils = new RedisUtils();
        redisUtils.basic_test(host, port);

        jedisPool = redisUtils.getJedisPool(host, port, maxTotal, maxIdle, maxWait);
        jedisCluster = redisUtils.getJedisCluster(clusterNodes, maxTotal, maxIdle);
        log.info("RedisUtils 测试：");
        log.info("get:"+redisUtils.get("test"));
        redisUtils.set("test1", "ni hao");
        log.info("exists:"+redisUtils.exists("test1"));
        log.info("set:"+redisUtils.get("test1"));
        redisUtils.del("test1");
        log.info("exists:"+redisUtils.exists("test1"));
    }
}
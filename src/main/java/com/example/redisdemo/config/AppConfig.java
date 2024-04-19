package com.example.redisdemo.config;

import java.time.Duration;

import java.util.Set;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@EnableCaching
@Configuration
public class AppConfig {

    public static final String CACHE_MANAGER = "30SecCacheManager:";

    private static final String REDIS_PREFIX = "integration:";

    private static final Duration CACHE_EXPIRE_TIME = Duration.ofMinutes(5);
    //private final RedisConfigProperties configProperties;


/*    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }*/

/*    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory);
        return template;
    }*/

/*    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("students");
    }*/

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        var jedisClientConfigurationBuilder = JedisClientConfiguration.builder();

        var redisStandaloneConfiguration =
            new RedisStandaloneConfiguration(
                "localhost", 6379);

        return new JedisConnectionFactory(
            redisStandaloneConfiguration, jedisClientConfigurationBuilder.build());
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(
        RedisConnectionFactory redisConnectionFactory) {
        var redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Bean(CACHE_MANAGER)
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return RedisCacheManager.builder(redisConnectionFactory)
            .cacheDefaults(
                RedisCacheConfiguration.defaultCacheConfig()
                    .prefixCacheNameWith(REDIS_PREFIX)
                    .entryTtl(CACHE_EXPIRE_TIME))
            .initialCacheNames(Set.of("Students"))
            .build();
    }
}

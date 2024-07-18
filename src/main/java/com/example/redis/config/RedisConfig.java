package com.example.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.core.env.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.util.Arrays;
import java.util.List;

import java.time.Duration;
import java.util.stream.Collectors;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
//      @Value("${spring.data.redis.host}")
//      private String host;
//      @Value("${spring.data.redis.port}")
//      private int port;
//      @Value("${spring.data.redis.cluster.nodes}")
//      private List<String> clusterNodes;
//      @Value("${spring.data.redis.cluster.password}")
//      private String password;

//    @Autowired
//    private Environment env;

//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration(clusterNodes);
//        clusterConfiguration.setPassword(password);
//        return new LettuceConnectionFactory(clusterConfiguration);
//    }
//

//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        List<String> clusterNodes = Arrays.stream(env.getProperty("spring.data.redis.cluster.nodes").split(","))
//                .map(String::trim)
//                .collect(Collectors.toList());
//
//        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration(clusterNodes);
//        clusterConfiguration.setPassword(env.getProperty("spring.data.redis.cluster.password"));
//        return new LettuceConnectionFactory(clusterConfiguration);
//    }
//
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheManager.RedisCacheManagerBuilder builder =
                RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory);

        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())) // Value Serializer 변경
                .disableCachingNullValues()
                .entryTtl(Duration.ofMinutes(30L));

        builder.cacheDefaults(configuration);

        return builder.build();
    }
}
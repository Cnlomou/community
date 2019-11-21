package group.jsjxh.community.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.LinkedHashSet;
import java.util.List;

@Configuration
@EnableConfigurationProperties(CacheProperties.class)
public class RedisConfiguration {

    @Bean
    RedisCacheManager cacheManager(CacheProperties cacheProperties, ObjectProvider<RedisCacheConfiguration> redisCacheConfiguration, ObjectProvider<RedisCacheManagerBuilderCustomizer> redisCacheManagerBuilderCustomizers, RedisConnectionFactory redisConnectionFactory, ResourceLoader resourceLoader) {
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(this.determineConfiguration(cacheProperties, redisCacheConfiguration, resourceLoader.getClassLoader()));
        List<String> cacheNames = cacheProperties.getCacheNames();
        if (!cacheNames.isEmpty()) {
            builder.initialCacheNames(new LinkedHashSet(cacheNames));
        }

        redisCacheManagerBuilderCustomizers.orderedStream().forEach((customizer) -> {
            customizer.customize(builder);
        });
        return builder.build();
    }

    private org.springframework.data.redis.cache.RedisCacheConfiguration determineConfiguration(CacheProperties cacheProperties, ObjectProvider<org.springframework.data.redis.cache.RedisCacheConfiguration> redisCacheConfiguration, ClassLoader classLoader) {
        return (org.springframework.data.redis.cache.RedisCacheConfiguration)redisCacheConfiguration.getIfAvailable(() -> {
            return this.createConfiguration(cacheProperties, classLoader);
        });
    }

    private org.springframework.data.redis.cache.RedisCacheConfiguration createConfiguration(CacheProperties cacheProperties, ClassLoader classLoader) {
        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        org.springframework.data.redis.cache.RedisCacheConfiguration config = org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig();
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
        serializer.setObjectMapper(objectMapper);
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer));
        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }else{
            config=config.entryTtl(Duration.ofMinutes(3));     //设置缓存的过期时间3分钟
        }

        if (redisProperties.getKeyPrefix() != null) {
            config = config.prefixKeysWith(redisProperties.getKeyPrefix());
        }

        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }

        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }

        return config;
    }
}

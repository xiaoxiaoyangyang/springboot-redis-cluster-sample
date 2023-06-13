package com.sample.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author szl
 *
 */
@SpringBootConfiguration
public class RedisClusterConfig {
	@Autowired
	private RedisClusterConfigProperties clusterProperties;

	@Bean
	public RedisClusterConfiguration redisClusterConfiguration() {
		RedisClusterConfiguration rcc = new RedisClusterConfiguration(clusterProperties.getNodes());
		rcc.setMaxRedirects(clusterProperties.getMaxRedirects().intValue());
		rcc.setPassword(RedisPassword.of("redis-pass".toCharArray()));
		return rcc;
	}

	@Bean
	public RedisConnectionFactory redisConnectionFactory(RedisClusterConfiguration redisClusterConfiguration) {
		return new LettuceConnectionFactory(redisClusterConfiguration);
	}

	@Bean
	public RedisTemplate getRedisTemplate(RedisConnectionFactory factory) {
		RedisTemplate redisTemplate = new RedisTemplate();
		redisTemplate.setConnectionFactory(factory);
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(redisSerializer);
		return redisTemplate;
	}

	@Bean
	public StringRedisTemplate getStringRedisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate stringTemplate = new StringRedisTemplate();
		stringTemplate.setConnectionFactory(factory);
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();// Long���Ͳ����Ի�����쳣��Ϣ;
		stringTemplate.setKeySerializer(redisSerializer);
		stringTemplate.setHashKeySerializer(redisSerializer);
		stringTemplate.setValueSerializer(redisSerializer);
		return stringTemplate;
	}

}

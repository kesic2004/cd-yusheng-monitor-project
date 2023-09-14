package com.redis;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
   @Bean(
      name = {"template"}
   )
   public RedisTemplate<String, Object> template(RedisConnectionFactory factory) {
      RedisTemplate<String, Object> template = new RedisTemplate();
      template.setConnectionFactory(factory);
      Jackson2JsonRedisSerializer<Object> jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);
      ObjectMapper om = new ObjectMapper();
      om.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
      om.enableDefaultTyping(DefaultTyping.NON_FINAL);
      jacksonSeial.setObjectMapper(om);
      StringRedisSerializer stringSerial = new StringRedisSerializer();
      template.setKeySerializer(stringSerial);
      template.setValueSerializer(jacksonSeial);
      template.setHashKeySerializer(stringSerial);
      template.setHashValueSerializer(jacksonSeial);
      template.afterPropertiesSet();
      return template;
   }
}

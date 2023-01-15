package rocks.danielw.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

@Configuration
@EnableCaching
public class CacheConfig {

  public static final String STUDENTS_CACHE_NAME = "studentsCache";
  public static final String AREA_OF_CIRCLE_CACHE_NAME = "areaOfCircleCache";
  public static final String MULTIPLY_CACHE_NAME = "multiplyCache";

  @Bean
  public CacheManager cacheManager() {
    return new ConcurrentMapCacheManager(STUDENTS_CACHE_NAME, AREA_OF_CIRCLE_CACHE_NAME, MULTIPLY_CACHE_NAME);
  }

  @Bean
  public KeyGenerator multiplyKeyGenerator() {
    return (Object target, Method method, Object... params) ->
        // you can build your own key based on given parameters
        method.getName() + "_" + Arrays.toString(params);
  }
}

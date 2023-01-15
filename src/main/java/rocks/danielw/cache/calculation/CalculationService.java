package rocks.danielw.cache.calculation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CalculationService {

  @Cacheable(value = "areaOfCircleCache", key = "#radius", condition = "#radius > 5")
  public double areaOfCircle(int radius) {
    log.info("calculate the area of a circle with a radius of {}", radius);
    return Math.PI * Math.pow(radius, 2);
  }

  @Cacheable(value = "multiplyCache", keyGenerator = "multiplyKeyGenerator")
  public double multiply(int factor1, int factor2) {
    log.info("Multiply {} with {}", factor1, factor2);
    return factor1 * factor2;
  }

  @CacheEvict(cacheNames = {"areaOfCircleCache", "multiplyCache"}, allEntries = true)
  public void evictCache() {
    log.info("Evict all cache entries...");
  }
}

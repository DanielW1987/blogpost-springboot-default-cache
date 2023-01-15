package rocks.danielw.cache.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static rocks.danielw.cache.CacheConfig.STUDENTS_CACHE_NAME;

@Component
@Slf4j
public class CacheClearingTask {

  private static final long ONE_HOUR_IN_MS = 3_600_000;

  private final CacheManager cacheManager;

  public CacheClearingTask(CacheManager cacheManager) {
    this.cacheManager = cacheManager;
  }

  @Scheduled(fixedRate = ONE_HOUR_IN_MS)
  public void clearStudentsCache() {
    Cache cache = cacheManager.getCache(STUDENTS_CACHE_NAME);
    if (cache != null) {
      cache.clear();
      log.info("Cache '{}' successfully cleared", STUDENTS_CACHE_NAME);
    }
  }
}

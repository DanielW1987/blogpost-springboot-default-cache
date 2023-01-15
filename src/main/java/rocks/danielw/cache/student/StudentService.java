package rocks.danielw.cache.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static rocks.danielw.cache.CacheConfig.STUDENTS_CACHE_NAME;

@Service
@Slf4j
@CacheConfig(cacheNames = STUDENTS_CACHE_NAME)
public class StudentService {

  private final StudentRepositoryMock repository;

  public StudentService(StudentRepositoryMock repository) {
    this.repository = repository;
  }

  @Cacheable
  public Student find(UUID id) {
    log.info("Fetching student with id {}", id.toString());
    return repository.fetch(id);
  }

  @CachePut(key = "#result.id")
  public Student create(String firstName, String lastName, String courseOfStudies) {
    log.info("Creating student with firstName={}, lastName={} and courseOfStudies={}",
        firstName, lastName, courseOfStudies
    );

    Student newStudent = new Student(firstName, lastName, courseOfStudies);
    return repository.save(newStudent);
  }

  @CacheEvict
  public void evict(UUID id) {
    log.info("Removing student with id {} from cache", id.toString());
  }

  @Cacheable
  @CacheEvict(beforeInvocation = true)
  public Student findAndRefresh(UUID id) {
    log.info("Refreshing student with id {}", id.toString());
    return repository.fetch(id);
  }
}

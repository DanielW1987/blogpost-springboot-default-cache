package rocks.danielw.cache.student;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class StudentRepositoryMock {

  // using HashMap only for simplicity
  private final Map<UUID, Student> internalDatabase = new HashMap<>();

  public Student save(Student student) {
    internalDatabase.put(student.getId(), student);
    return student;
  }

  public Student fetch(UUID id) {
    return internalDatabase.get(id);
  }
}

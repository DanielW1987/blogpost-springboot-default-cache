package rocks.danielw.cache.student;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Student {
  private UUID id;
  private String firstName;
  private String lastName;
  private String courseOfStudies;

  public Student(String firstName, String lastName, String courseOfStudies) {
    id = UUID.randomUUID();
    this.firstName = firstName;
    this.lastName = lastName;
    this.courseOfStudies = courseOfStudies;
  }
}

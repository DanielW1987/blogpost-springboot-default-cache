package rocks.danielw.cache.student;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class StudentRestController {

  private final StudentService studentService;

  public StudentRestController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping(path = "/api/students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Student get(@PathVariable UUID id) {
    return studentService.find(id);
  }

  @PostMapping(path = "/api/students", produces = MediaType.APPLICATION_JSON_VALUE)
  public Student create(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String courseOfStudies) {
    return studentService.create(firstName, lastName, courseOfStudies);
  }
}
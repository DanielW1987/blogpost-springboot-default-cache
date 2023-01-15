package rocks.danielw.cache.calculation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CalculationRestController {

  private final CalculationService calculationService;

  public CalculationRestController(CalculationService calculationService) {
    this.calculationService = calculationService;
  }

  @GetMapping(path = "/api/area-of-circle", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Double> areaOfCircle(@RequestParam int radius) {
    log.info("Requesting area of circle calculation for radius {}", radius);
    double result = calculationService.areaOfCircle(radius);
    return ResponseEntity.ok(result);
  }

  @GetMapping(path = "/api/multiply", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Double> multiply(@RequestParam int factor1, @RequestParam int factor2) {
    double result = calculationService.multiply(factor1, factor2);
    return ResponseEntity.ok(result);
  }
}

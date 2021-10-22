package pl.edu.zsel.contestbackend.sensor.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.zsel.contestbackend.sensor.model.Sensor;
import pl.edu.zsel.contestbackend.sensor.service.SensorService;

import java.util.List;

@RequestMapping("api/v1/sensor")
@RestController
public class SensorController {
    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping
    public ResponseEntity<List<Sensor>> getAllSensors() {
        return sensorService.getAllSensors();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Sensor> getSensor(@PathVariable("id") Integer sensorId) {
        return sensorService.getSensor(sensorId);
    }

}

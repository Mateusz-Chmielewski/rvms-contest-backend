package pl.edu.zsel.contestbackend.sensor.api;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.zsel.contestbackend.sensor.model.Sensor;
import pl.edu.zsel.contestbackend.sensor.service.SensorService;

import java.util.List;

@RequestMapping("api/v1/sensor")
@RestController
@CrossOrigin(origins = "*")
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

    @PostMapping(path = "active/{id}")
    public ResponseEntity<Void> setSensorActivity(
            @PathVariable("id") Integer sensorId,
            @RequestBody ObjectNode requestBody
    ) {
        return sensorService.setSensorActivity(sensorId, requestBody.get("isActive").asBoolean());
    }

    @PostMapping(path = "describe/{id}")
    public ResponseEntity<Void> setSensorIpAddressAndRoomName(
            @PathVariable("id") Integer sensorId,
            @RequestBody ObjectNode requestBody
    ) {
        return sensorService.setSensorIpAddressAndRoomName(
                sensorId,
                requestBody.get("ipAddress").asText(),
                requestBody.get("roomName").asText()
        );
    }

    @PostMapping
    public ResponseEntity<Void> addSensor(@RequestBody Sensor sensor) {
        return sensorService.addSensor(sensor);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable("id") Integer sensorId) {
        return sensorService.deleteSensor(sensorId);
    }
}

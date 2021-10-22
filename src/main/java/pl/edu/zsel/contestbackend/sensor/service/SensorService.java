package pl.edu.zsel.contestbackend.sensor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.zsel.contestbackend.sensor.dao.SensorRepository;
import pl.edu.zsel.contestbackend.sensor.model.Sensor;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public ResponseEntity<List<Sensor>> getAllSensors() {
        List<Sensor> sensors = sensorRepository.findAll();
        return ResponseEntity.ok(sensors);
    }

    public ResponseEntity<Sensor> getSensor(Integer sensorId) {
        Optional<Sensor> sensorOptional = sensorRepository.findById(sensorId);

        if (sensorOptional.isPresent()) {
            return ResponseEntity.ok(sensorOptional.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

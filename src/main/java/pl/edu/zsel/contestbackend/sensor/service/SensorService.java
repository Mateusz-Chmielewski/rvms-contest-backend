package pl.edu.zsel.contestbackend.sensor.service;

import org.springframework.beans.factory.annotation.Autowired;
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

        return sensorOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> setSensorActivity(Integer sensorId, Boolean isActive) {
        Optional<Sensor> optionalSensor = sensorRepository.findById(sensorId);

        if (optionalSensor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Sensor sensor = optionalSensor.get();
        sensor.setIsActive(isActive);
        sensorRepository.save(sensor);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> setSensorIpAddressAndRoomName(Integer sensorId, String ipAddress, String roomName) {
        Optional<Sensor> optionalSensor = sensorRepository.findById(sensorId);

        if (optionalSensor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Sensor sensor = optionalSensor.get();
        sensor.setIpAddress(ipAddress);
        sensor.setRoomName(roomName);
        sensorRepository.save(sensor);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> addSensor(Sensor sensor) {
        sensorRepository.save(sensor);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> deleteSensor(Integer sensorId) {
        if (sensorRepository.findById(sensorId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        sensorRepository.deleteById(sensorId);
        return ResponseEntity.ok().build();
    }
}
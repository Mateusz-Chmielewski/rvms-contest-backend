package pl.edu.zsel.contestbackend.reading.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.zsel.contestbackend.reading.dao.ReadingRepository;
import pl.edu.zsel.contestbackend.reading.model.Reading;
import pl.edu.zsel.contestbackend.sensor.dao.SensorRepository;
import pl.edu.zsel.contestbackend.sensor.model.Sensor;

import java.util.List;
import java.util.Optional;

@Service
public class ReadingService {
    public final ReadingRepository readingRepository;
    public final SensorRepository sensorRepository;

    @Autowired
    public ReadingService(ReadingRepository readingRepository, SensorRepository sensorRepository) {
        this.readingRepository = readingRepository;
        this.sensorRepository = sensorRepository;
    }

    public ResponseEntity<List<Reading>> getReadingsForSensor(Integer sensorID) {
        Optional<Sensor> sensorOptional = sensorRepository.findById(sensorID);

        if (sensorOptional.isPresent()) {
            List<Reading> readings = readingRepository.findBySensorId(sensorOptional.get());
            return ResponseEntity.ok(readings);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

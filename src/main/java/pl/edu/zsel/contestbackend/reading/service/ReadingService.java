package pl.edu.zsel.contestbackend.reading.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.zsel.contestbackend.reading.dao.ReadingRepository;
import pl.edu.zsel.contestbackend.reading.model.Reading;
import pl.edu.zsel.contestbackend.sensor.dao.SensorRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReadingService {
    public final ReadingRepository readingRepository;
    public final SensorRepository sensorRepository;

    @Autowired
    public ReadingService(ReadingRepository readingRepository, SensorRepository sensorRepository) {
        this.readingRepository = readingRepository;
        this.sensorRepository = sensorRepository;
    }

    public ResponseEntity<List<Reading>> getReadingsForSensor(Integer sensorId) {
        List<Reading> readings = readingRepository.findBySensorId(sensorId);
        return ResponseEntity.ok(readings);
    }

    public ResponseEntity<List<Reading>> getReadingsForSensorInLastTime(Integer sensorId, Integer minutes) {
        System.out.println(Instant.now().minus(minutes, ChronoUnit.MINUTES));
        List<Reading> readings = readingRepository.findBySensorIdAndWhenDateIsGreater(
                sensorId,
                Instant.now().minus(minutes, ChronoUnit.MINUTES)
        );

        return ResponseEntity.ok(readings);
    }
}

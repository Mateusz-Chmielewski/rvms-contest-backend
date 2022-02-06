package pl.edu.zsel.contestbackend.collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.edu.zsel.contestbackend.reading.dao.ReadingRepository;
import pl.edu.zsel.contestbackend.sensor.dao.SensorRepository;
import pl.edu.zsel.contestbackend.sensor.model.Sensor;

import java.util.ArrayList;
import java.util.List;

@Component
public class Collector {

    private final SensorRepository sensorRepository;
    private final ReadingRepository readingRepository;
    private static final int SECONDS = 30;

    @Autowired
    public Collector(SensorRepository sensorRepository, ReadingRepository readingRepository) {
        this.sensorRepository = sensorRepository;
        this.readingRepository = readingRepository;
    }

    @Scheduled(fixedRate = SECONDS * 1000, initialDelay = SECONDS * 1000)
    public void collector() {
        List<Sensor> all = sensorRepository.findAll();

        List<CollectorThreat> collectorThreats = new ArrayList<>();

        all.forEach(sensor -> {
            if (sensor.getIsActive()) {
                collectorThreats.add(new CollectorThreat(readingRepository, sensor));
            }
        });

        collectorThreats.parallelStream().forEach(CollectorThreat::run);
    }
}

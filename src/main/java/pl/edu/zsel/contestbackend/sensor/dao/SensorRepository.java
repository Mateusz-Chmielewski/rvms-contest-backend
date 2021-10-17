package pl.edu.zsel.contestbackend.sensor.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.zsel.contestbackend.sensor.model.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {
}
package pl.edu.zsel.contestbackend.reading.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.zsel.contestbackend.reading.model.Reading;
import pl.edu.zsel.contestbackend.sensor.model.Sensor;

import java.util.List;

public interface ReadingRepository extends JpaRepository<Reading, Integer> {
    @Query("select r from Reading r where r.sensorId = ?1")
    public List<Reading> findBySensorId(Sensor sensorId);
}
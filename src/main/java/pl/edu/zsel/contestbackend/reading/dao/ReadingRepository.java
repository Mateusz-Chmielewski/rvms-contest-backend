package pl.edu.zsel.contestbackend.reading.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.zsel.contestbackend.reading.model.Reading;

import java.time.Instant;
import java.util.List;

public interface ReadingRepository extends JpaRepository<Reading, Integer> {
    @Query("select r from Reading r where r.sensorId.id = ?1")
    public List<Reading> findBySensorId(Integer sensorId);

    @Query("select r from Reading r where r.sensorId.id = ?1 and r.date >= ?2")
    public List<Reading> findBySensorIdAndWhenDateIsGreater(Integer sensorId, Instant time);
}
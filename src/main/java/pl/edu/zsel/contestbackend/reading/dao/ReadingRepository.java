package pl.edu.zsel.contestbackend.reading.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.zsel.contestbackend.reading.model.Reading;

public interface ReadingRepository extends JpaRepository<Reading, Integer> {

}
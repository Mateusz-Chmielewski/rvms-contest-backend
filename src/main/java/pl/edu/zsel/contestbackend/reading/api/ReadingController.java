package pl.edu.zsel.contestbackend.reading.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.zsel.contestbackend.reading.model.Reading;
import pl.edu.zsel.contestbackend.reading.service.ReadingService;

import java.nio.file.Path;
import java.util.List;

@RequestMapping("api/v1/reading")
@RestController
public class ReadingController {
    public final ReadingService readingService;

    @Autowired
    public ReadingController(ReadingService readingService) {
        this.readingService = readingService;
    }

    @GetMapping(path = "{sensorId}")
    public ResponseEntity<List<Reading>> getReadingsForSensor(@PathVariable("sensorId") Integer sensorID) {
        return readingService.getReadingsForSensor(sensorID);
    }

}

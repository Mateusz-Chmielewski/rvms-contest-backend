package pl.edu.zsel.contestbackend.reading.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.zsel.contestbackend.reading.model.Reading;
import pl.edu.zsel.contestbackend.reading.service.ReadingService;

import java.util.List;

@RequestMapping("api/v1/reading")
@RestController
@CrossOrigin(origins = "*")
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

    @GetMapping(path = "{sensorId}/{minutes}")
    public ResponseEntity<List<Reading>> getReadingsForSensorInLastTime(
            @PathVariable("sensorId") Integer sensorId,
            @PathVariable("minutes") Integer minutes
    ) {
        return readingService.getReadingsForSensorInLastTime(sensorId, minutes);
    };


}

package pl.edu.zsel.contestbackend.collector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import pl.edu.zsel.contestbackend.reading.dao.ReadingRepository;
import pl.edu.zsel.contestbackend.reading.model.Reading;
import pl.edu.zsel.contestbackend.sensor.model.Sensor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;

public class CollectorThreat extends Thread{
    private final ReadingRepository readingRepository;
    private final Sensor sensor;

    public CollectorThreat(ReadingRepository readingRepository, Sensor sensor) {
        this.readingRepository = readingRepository;
        this.sensor = sensor;
    }

    @Override
    public void run() {
        Reading reading = new Reading(Instant.now(), 0, 0, sensor);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://%s".formatted(sensor.getIpAddress())))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8)
            );

            Element body = Jsoup.parse(response.body()).body();
            String deletedNumbersTags = body.toString().replaceAll("</?h\\d", " ");

            List<Integer> integers = FindNumbers.findIntegers(deletedNumbersTags);

            if (integers.size() >= 2) {
                reading.setQuality(integers.get(0));
                reading.setTemperature(integers.get(1));
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            System.out.println(reading);
            readingRepository.save(reading);
        }
    }
}

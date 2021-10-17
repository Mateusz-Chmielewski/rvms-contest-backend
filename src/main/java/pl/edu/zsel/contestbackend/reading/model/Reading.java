package pl.edu.zsel.contestbackend.reading.model;

import lombok.*;
import pl.edu.zsel.contestbackend.sensor.model.Sensor;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "readings", indexes = {
        @Index(name = "fk_reading_sensors_sensorId_idx", columnList = "sensorId")
})
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "quality", nullable = false)
    private Integer quality;

    @Column(name = "temperature", nullable = false)
    private Integer temperature;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensor sensorId;
}
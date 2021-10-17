package pl.edu.zsel.contestbackend.sensor.model;

import lombok.*;

import javax.persistence.*;

@Table(name = "sensors")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ip_address", nullable = false, length = 15)
    private String ipAddress;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    public Sensor(String ipAddress, String roomName, Boolean isActive) {
        this.ipAddress = ipAddress;
        this.roomName = roomName;
        this.isActive = isActive;
    }
}
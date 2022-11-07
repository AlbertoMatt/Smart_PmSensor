package it.synclab.pmsensor.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "temperature")

public class Temperature {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String address;
    private String latitude;
    private String longitude;
    private Date timestamp;

    @Column(name = "value")
    private String value;

    // @Column(name = "fk_sensor_id")
    // private Long fkSensorId;

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "fk_ai", referencedColumnName = "id")
    @JsonIgnore
    @OneToOne(mappedBy = "temp")
    private AmbientInfos ambientInfo;

}

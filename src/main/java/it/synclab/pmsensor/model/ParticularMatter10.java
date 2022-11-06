package it.synclab.pmsensor.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "particular_matter10", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "fk_sensor_id" }) })
public class ParticularMatter10 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String address;
    private String latitude;
    private String longitude;
    private Date timestamp;
    
    @Column(name = "value")
    private String value;

    @Column(name = "fk_sensor_id")
    private Long fkSensorId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pm10", referencedColumnName = "value")
    private List<AmbientInfos> ambient_infos;

}

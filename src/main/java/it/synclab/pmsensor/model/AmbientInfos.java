package it.synclab.pmsensor.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="ambient_infos")

public class AmbientInfos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    
    @Column(name="pm2_5")
    private Double pm2_5;
    
    @Column(name = "pm10")
    private Double pm10;
    
    @Column(name= "temperature")
    private Double temperature;
    
    @Column(name="umidity")
    private Double umidity; 

}

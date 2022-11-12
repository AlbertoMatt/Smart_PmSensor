package it.synclab.pmsensor.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "ambient_infos")
public class AmbientInfos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;

    // @OneToOne(mappedBy = "ambientInfo")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_particular_matter_2_5", referencedColumnName = "id")
    private ParticularMatter25 pMatter25;

    // @OneToOne(mappedBy = "ambientInfo")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_particular_matter_10", referencedColumnName = "id")
    private ParticularMatter10 pMatter10;

    // @OneToOne(mappedBy = "ambientInfo")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_temperature", referencedColumnName = "id")
    private Temperature temp;

    // @OneToOne(mappedBy = "ambientInfo")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_humidity", referencedColumnName = "id")
    private Humidity humidity;

}

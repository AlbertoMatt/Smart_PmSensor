// package it.synclab.pmsensor.model;

// import java.util.Date;
// import java.util.List;

// import javax.persistence.CascadeType;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.OneToMany;
// import javax.persistence.Table;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import lombok.ToString;

// @Entity
// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// @ToString
// @Table(name = "sensors")
// public class Sensor {
//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Long id;
//     private String name;
//     private String battery;
//     private String charge;
//     private String type;
//     private Boolean is_active;
//     private Date last_survey;

//     @OneToMany(cascade = CascadeType.ALL)
//     @JoinColumn(name = "fk_sensor_id", referencedColumnName = "id")
//     private List<Humidity> humidities;

//     @OneToMany(cascade = CascadeType.ALL)
//     @JoinColumn(name = "fk_sensor_id", referencedColumnName = "id")
//     private List<Temperature> temperatures;

//     @OneToMany(cascade = CascadeType.ALL)
//     @JoinColumn(name = "fk_sensor_id", referencedColumnName = "id")
//     private List<ParticularMatter10> Pm10;

//     @OneToMany(cascade = CascadeType.ALL)
//     @JoinColumn(name = "fk_sensor_id", referencedColumnName = "id")
//     private List<ParticularMatter25> Pm25;



// }

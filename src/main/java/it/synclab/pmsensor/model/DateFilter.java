package it.synclab.pmsensor.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DateFilter {
    private Date startDate;
    private Date endDate;


}

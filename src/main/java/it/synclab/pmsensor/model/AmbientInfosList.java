package it.synclab.pmsensor.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AmbientInfosList {
     private List<AmbientInfos> ail= new ArrayList<AmbientInfos>();
}

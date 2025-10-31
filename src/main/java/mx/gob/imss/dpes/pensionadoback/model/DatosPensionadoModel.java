/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.pensionadoback.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.model.BaseModel;
import mx.gob.imss.dpes.interfaces.pensionado.model.Pensionado;
import mx.gob.imss.dpes.interfaces.serviciosdigitales.model.Persona;
import mx.gob.imss.dpes.interfaces.sistrap.model.ConsultaPensionadoResponse;
import mx.gob.imss.dpes.interfaces.sistrap.model.Pension;

/**
 *
 * @author cesar.leon
 */
public class DatosPensionadoModel extends BaseModel{

  // Input
  @Getter @Setter private String nss;
  @Getter @Setter private String grupoFamiliar;
    
  // Inter    
  @Getter @Setter private ConsultaPensionadoResponse consultaPensionadoResponse = new ConsultaPensionadoResponse();
  @Getter @Setter private Persona persona = new Persona();
  @Getter @Setter private List<Pension> pensiones = new ArrayList<>();
  
  // Output
  @Getter @Setter private Pensionado pensionado = new Pensionado();    

}

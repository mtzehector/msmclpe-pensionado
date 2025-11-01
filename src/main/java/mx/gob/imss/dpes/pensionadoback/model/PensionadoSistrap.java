/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.pensionadoback.model;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.model.BaseModel;

/**
 *
 * @author cesar.leon
 */
public class PensionadoSistrap extends BaseModel{
    
    @Getter @Setter private Long idNss;
    @Getter @Setter private String idGrupoFamiliar;
    @Getter @Setter private String nomApellidoPaterno;
    @Getter @Setter private String nomApellidoMaterno;
    @Getter @Setter private String nomNombre;
    @Getter @Setter private String cveCurp;
    @Getter @Setter private String cveRfc;
    @Getter @Setter private String fecNacimiento;
    @Getter @Setter private String cveCtaPago;
    @Getter @Setter private String numClabe;
    @Getter @Setter private Integer idTipoCuentaPago;
    @Getter @Setter private String cveEntidadFederativa;
    @Getter @Setter private String sexo;
    @Getter @Setter private String cveSudelegacion;
    @Getter @Setter private String cveDelegacion;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.pensionadoback.assembler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.baseback.assembler.BaseAssembler;
import mx.gob.imss.dpes.common.enums.SexoEnum;
import mx.gob.imss.dpes.common.model.BaseModel;
import mx.gob.imss.dpes.common.model.EntidadFederativa;
import mx.gob.imss.dpes.interfaces.pensionado.model.Pensionado;
import mx.gob.imss.dpes.interfaces.sistrap.model.Pension;
import mx.gob.imss.dpes.pensionadoback.model.DatosPensionadoModel;
/**
 *
 * @author Diego Velazquez
 */
@Provider
public class PensionadoAssembler extends BaseAssembler<DatosPensionadoModel, Pensionado, BaseModel, BaseModel> {

  @Override
  public DatosPensionadoModel toEntity(Pensionado source) {
    return new DatosPensionadoModel();
  }

  @Override
  public BaseModel toPKEntity(BaseModel source) {
    return source; 
  }

  @Override
  public Pensionado assemble(DatosPensionadoModel source) {
      
      //source.getPensionado().setCuentaClabe( source.getConsultaPensionadoResponse().getPensionado().getNumClabe() );
      source.getPensionado().getDelegacion().setCveDelegacion( source.getConsultaPensionadoResponse().getPensionado().getCveDelegacion() );
      source.getPensionado().getDelegacion().setDescDelegacion( source.getConsultaPensionadoResponse().getPensionado().getDescDelegacion() );
      //source.getPensionado().getEntidadFederativa().setCveEntidadFederativa( source.getConsultaPensionadoResponse().getPensionado().getCveEntidadFederativa() );
      //source.getPensionado().getEntidadFederativa().setDescEntidadFederativa( source.getConsultaPensionadoResponse().getPensionado().getDescEntidadFederativa() );
      
      //source.getPensionado().setSubDelegacion(source.getConsultaPensionadoResponse().getPensionado().getCveSudelegacion() );
      //source.getPensionado().setNss( source.getNss() );
      //source.getPensionado().setGrupoFamiliar( source.getGrupoFamiliar() );
      
      for( Pension pension : source.getPensiones() ){
        if( pension.getIdNss().equals( source.getConsultaPensionadoResponse().getPensionado().getIdNss() ) 
          && pension.getIdGrupoFamiliar().equals( source.getConsultaPensionadoResponse().getPensionado().getIdGrupoFamiliar() )
        ){
          source.getPensionado().setTipoPension( pension.getIdTipoPension() );
        }
      }         
      
      //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
      
      //String fechaSistrap = source.getConsultaPensionadoResponse().getPensionado().getFecNacimiento();
      
      //source.getPensionado().setSexo(SexoEnum.forValue( Short.parseShort(
              //source.getConsultaPensionadoResponse().getPensionado().getSexo() ) ) );
      
      //source.getPensionado().setNombre( source.getConsultaPensionadoResponse().getPensionado().getNomNombre() );
      //source.getPensionado().setPrimerApellido(source.getConsultaPensionadoResponse().getPensionado().getNomApellidoPaterno() );
      //source.getPensionado().setSegundoApellido( source.getConsultaPensionadoResponse().getPensionado().getNomApellidoMaterno() );
      //source.getPensionado().setCurp(source.getConsultaPensionadoResponse().getPensionado().getCveCurp());
      //source.getPensionado().setCorreoElectronico( source.getPersona().getCorreoElectronico() );
      //source.getPensionado().setTelefono(source.getPersona().getTelefono() );
      
//    try {
//      source.getPensionado().setFechaNacimiento( sdf.parse(fechaSistrap) );
//    } catch (ParseException ex) {
//      log.log(Level.SEVERE, null, ex);
//    }
      
    return source.getPensionado(); 
  }
  
  @Override
  public BaseModel assemblePK(BaseModel source) {
    return source;
  } 
}

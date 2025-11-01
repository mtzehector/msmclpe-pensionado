package mx.gob.imss.dpes.pensionadoback.service;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import mx.gob.imss.dpes.interfaces.serviciosdigitales.model.Persona;
import mx.gob.imss.dpes.interfaces.serviciosdigitales.model.PersonaRequest;
import mx.gob.imss.dpes.pensionadoback.model.DatosPensionadoModel;
import mx.gob.imss.dpes.pensionadoback.restclient.PersonaClient;

@Provider
public class ReadPersonaService extends ServiceDefinition<DatosPensionadoModel, DatosPensionadoModel>{

  
  @Inject
  @RestClient
  private PersonaClient client;  
  
  @Override
  public Message<DatosPensionadoModel> execute(Message<DatosPensionadoModel> request) throws BusinessException {
      
    PersonaRequest cr = new PersonaRequest();
    cr.setCurp( request.getPayload().getConsultaPensionadoResponse().getPensionado().getCveCurp() );      
    
    Response response = client.load(cr);
    
    return response(response, request);
        
  }
  
  @Override
  protected Message<DatosPensionadoModel> onOk(Response response, Message<DatosPensionadoModel> request){
    request.getPayload().setPersona(response.readEntity(Persona.class));      
    return request; 
  }
  
}

package mx.gob.imss.dpes.pensionadoback.service;

//import java.util.logging.Level;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.common.exception.BusinessException;

import mx.gob.imss.dpes.common.exception.VariableMessageException;
import mx.gob.imss.dpes.common.model.ErrorInfo;
import mx.gob.imss.dpes.common.model.Message;

//import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.pensionadoback.exception.PensionadoException;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
//import mx.gob.imss.dpes.interfaces.sistrap.model.ConsultaPensionadoResponse;
import mx.gob.imss.dpes.interfaces.sistrap.model.Pensionado;
import mx.gob.imss.dpes.pensionadoback.model.DatosPensionadoModel;
import mx.gob.imss.dpes.interfaces.sistrap.model.PensionadoRequest;
import mx.gob.imss.dpes.pensionadoback.restclient.PensionadoSistrapClient;

import java.util.logging.Level;


@Provider
public class ReadPensionadoSistrapService extends ServiceDefinition<DatosPensionadoModel, DatosPensionadoModel>{

  @Inject
  @RestClient
  private PensionadoSistrapClient pensionadoSistrapClient;

  @Override
  public Message<DatosPensionadoModel> execute(Message<DatosPensionadoModel> request) throws BusinessException {
    try {
      PensionadoRequest pr = new PensionadoRequest();
      pr.setIdGrupoFamiliar(request.getPayload().getGrupoFamiliar());
      pr.setIdNss(request.getPayload().getNss());

      //log.log(Level.INFO, "Servicio de consulta de pensionado que va al servicio de sistrap {0}", pr);
      Response load = pensionadoSistrapClient.load(pr);

      if (load.getStatus() == Response.Status.OK.getStatusCode())
        return response(load, request);

      if (load.getStatus() == Response.Status.PARTIAL_CONTENT.getStatusCode())
        throw new VariableMessageException((load.readEntity(ErrorInfo.class)).getMessage());

    } catch (VariableMessageException e) {
      throw e;
    } catch (BusinessException e) {
      log.log(Level.SEVERE, ">>>>ERROR ReadPensionadoSistrapService.execute = {0}", e);
      return response(null, ServiceStatusEnum.EXCEPCION, e, null);
    } catch (Exception e) {
      log.log(Level.SEVERE, ">>>>ERROR ReadPensionadoSistrapService.execute = {0}", e);
    }

    return response(null, ServiceStatusEnum.EXCEPCION,
      new PensionadoException(PensionadoException.ERROR_DESCONOCIDO_SERVICIO_READ_PENSIONADO_SISTRAP_SERVICE),
            null);
  }
  
  @Override
  protected Message<DatosPensionadoModel> onOk(Response response, Message<DatosPensionadoModel> request){
    request.getPayload().getConsultaPensionadoResponse().setPensionado(response.readEntity(Pensionado.class) );
    //log.log(Level.INFO, "Respuesta sistrap {0}", request.getPayload().getConsultaPensionadoResponse() );
    return request;
  }
  
}

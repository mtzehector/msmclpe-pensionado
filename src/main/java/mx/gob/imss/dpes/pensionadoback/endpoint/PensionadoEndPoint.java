/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.pensionadoback.endpoint;

import java.util.logging.Level;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.PartialContentFlowException;
import mx.gob.imss.dpes.common.exception.VariableMessageException;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.pensionadoback.exception.PensionadoException;
import org.eclipse.microprofile.openapi.annotations.Operation;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.interfaces.pensionado.model.Pensionado;
import mx.gob.imss.dpes.pensionadoback.assembler.PensionadoAssembler;
import mx.gob.imss.dpes.pensionadoback.model.DatosPensionadoModel;
import mx.gob.imss.dpes.interfaces.sistrap.model.PensionadoRequest;
import mx.gob.imss.dpes.pensionadoback.service.ReadPensionService;
//import mx.gob.imss.dpes.pensionadoback.service.ReadPersonaService;
import mx.gob.imss.dpes.pensionadoback.service.ReadPensionadoSistrapService;

/**
 *
 * @author cesar.leon
 */
@Path("/pensionado")
@RequestScoped
public class PensionadoEndPoint extends BaseGUIEndPoint<PensionadoRequest, PensionadoRequest, PensionadoRequest> {
    //@Inject
    //ReadPersonaService readPersonaService;

    @Inject
    ReadPensionadoSistrapService readPensionadoSistrapService;

    @Inject
    ReadPensionService pensionService;

    @Inject
    PensionadoAssembler pensionadoAssembler;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obtener los datos de un pensionado",
        description = "Obtener los datos de un pensionado")
    public Response load(DatosPensionadoModel request) {
        try {
            ServiceDefinition[] steps = {readPensionadoSistrapService, pensionService};
            Message<DatosPensionadoModel> pensionadoResponse =
                readPensionadoSistrapService.executeSteps(steps, new Message<>(request));

            Pensionado pensionado = pensionadoAssembler.assemble(pensionadoResponse.getPayload());
            return Response.ok(pensionado).build();
        } catch (VariableMessageException e) {
            return toResponse(new Message(null, ServiceStatusEnum.PARTIAL_CONTENT,
                new PartialContentFlowException(e.getMessage()), null));
        } catch (BusinessException e) {
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION, e, null));
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR PensionadoEndPoint.load = ", e);
            return toResponse(new Message(null, ServiceStatusEnum.EXCEPCION,
                new PensionadoException(PensionadoException.ERROR_DESCONOCIDO_EN_EL_SERVICIO), null));
        }
    }
}

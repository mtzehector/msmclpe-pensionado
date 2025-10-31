/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.pensionadoback.exception;

import mx.gob.imss.dpes.common.exception.BusinessException;


/**
 *
 * @author antonio
 */
public class PensionadoException extends BusinessException{
  public final static String ERROR_DESCONOCIDO_EN_EL_SERVICIO = "msg000";
  public final static String ERROR_DESCONOCIDO_SERVICIO_READ_PENSIONADO_SISTRAP_SERVICE = "msg001";
  public final static String ERROR_DESCONOCIDO_SERVICIO_READ_PENSION_SERVICE = "msg002";

  /*
  public PensionadoException() {
    super(KEY);
  }
*/
  public PensionadoException(String msg) {
    super(msg);
  }
}

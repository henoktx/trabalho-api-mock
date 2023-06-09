package apimock.ApiClima;

import apimock.Coordenada;

public interface IApiClimaCliente {
  public RespostaApiParser getDadosPoluicaoAr(Coordenada coordenada) throws ApiClimaException;
}

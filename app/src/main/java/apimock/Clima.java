package apimock;

import apimock.ApiClima.ApiClimaException;
import apimock.ApiClima.IApiClimaCliente;
import apimock.ApiClima.RespostaApiParser;
import apimock.ApiClima.RespostaApiParser.ListItem;

public class Clima {
  private IApiClimaCliente apiClimaCliente;

  public Clima(IApiClimaCliente apiClimaCliente) {
    this.apiClimaCliente = apiClimaCliente;
  }

  public String getQualidadeAr(Coordenada coordenada) {
    RespostaApiParser resposta = new RespostaApiParser();
    
    try {
      resposta = this.getDadosPoluicaoAr(coordenada);
    } catch (Exception e) {
      return "Algo de errado aconteceu: " + e.getMessage();
    }
    
    ListItem[] lista = resposta.getList();
    String qualidadeAr = new String();
    int aqi = lista[0].getMain().getAqi();

    switch (aqi) {
      case 1:
        qualidadeAr = "Ar de boa qualidade";
        break;
      case 2: 
        qualidadeAr = "Ar de qualidade razoável";
        break;
      case 3: 
        qualidadeAr = "Ar de média qualidade";
        break;
      case 4: 
        qualidadeAr = "Ar de ruim qualidade";
        break;
      case 5:
        qualidadeAr = "Ar de péssima qualidade";
        break;
      default:
        break;
    }

    return qualidadeAr;
  }

  private RespostaApiParser getDadosPoluicaoAr(Coordenada coordenada) throws ApiClimaException {
    try {
      return apiClimaCliente.getDadosPoluicaoAr(coordenada);
    } catch (Exception e) {
      throw new ApiClimaException(e.getMessage());
    }
  }
}

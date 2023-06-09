package apimock;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.google.gson.Gson;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import apimock.ApiClima.ApiClimaCliente;
import apimock.ApiClima.IApiClimaCliente;
import apimock.ApiClima.RespostaApiParser;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith (MockitoExtension.class)
class ApiClimaClienteTest {
    static final Double JDN_LAT = -7.215069;
    static final Double JDN_LON = -39.315157;
    Gson gson = new Gson();

    @Disabled
    @Test void testaPoluicaoArCorretamenteConcreta() {
      IApiClimaCliente apiClimaCliente = new ApiClimaCliente();

      assertDoesNotThrow(() -> apiClimaCliente.getDadosPoluicaoAr(new Coordenada(JDN_LAT, JDN_LON)));
    }

    @Test
    public void testaPoluicaoArCorretamenteStubArcomBoaQualidade() {
      IApiClimaCliente apiClimaCliente = Mockito.mock(IApiClimaCliente.class);
      Clima clima = new Clima(apiClimaCliente);
      
      String respostaApi = "{\"coord\":{\"lon\":-39.3152,\"lat\":-7.2151},\"list\":[{\"main\":{\"aqi\":1},\"components\":{\"co\":230.31,\"no\":0.21,\"no2\":2.79,\"o3\":31.47,\"so2\":0.89,\"pm2_5\":0.78,\"pm10\":1.48,\"nh3\":0.97},\"dt\":1686255128}]}";
      RespostaApiParser respostaFormatada = gson.fromJson(respostaApi, RespostaApiParser.class);

      Mockito.when(apiClimaCliente.getDadosPoluicaoAr(new Coordenada(JDN_LAT, JDN_LON))).thenReturn(respostaFormatada);

      String resultadoObtido = clima.getQualidadeAr(new Coordenada(JDN_LAT, JDN_LON));

      assertEquals("Ar de boa qualidade", resultadoObtido);
    }

    @Test
    public void testaPoluicaoArCorretamenteStubArcomPessimaQualidade() {
      IApiClimaCliente apiClimaCliente = Mockito.mock(IApiClimaCliente.class);
      Clima clima = new Clima(apiClimaCliente);
      
      String respostaApi = "{\"coord\":{\"lon\":-39.3152,\"lat\":-7.2151},\"list\":[{\"main\":{\"aqi\":5},\"components\":{\"co\":230.31,\"no\":0.21,\"no2\":2.79,\"o3\":31.47,\"so2\":0.89,\"pm2_5\":0.78,\"pm10\":1.48,\"nh3\":0.97},\"dt\":1686255128}]}";
      RespostaApiParser respostaFormatada = gson.fromJson(respostaApi, RespostaApiParser.class);

      Mockito.when(apiClimaCliente.getDadosPoluicaoAr(new Coordenada(JDN_LAT, JDN_LON))).thenReturn(respostaFormatada);

      String resultadoObtido = clima.getQualidadeAr(new Coordenada(JDN_LAT, JDN_LON));

      assertEquals("Ar de péssima qualidade", resultadoObtido);
    }

    @Test
    void testaPoluicaiArCorretamenteMock() {
      IApiClimaCliente apiClimaCliente = Mockito.mock(IApiClimaCliente.class);
      Clima clima = new Clima(apiClimaCliente);

      String respostaApi = "{\"coord\":{\"lon\":-39.3152,\"lat\":-7.2151},\"list\":[{\"main\":{\"aqi\":5},\"components\":{\"co\":230.31,\"no\":0.21,\"no2\":2.79,\"o3\":31.47,\"so2\":0.89,\"pm2_5\":0.78,\"pm10\":1.48,\"nh3\":0.97},\"dt\":1686255128}]}";
      RespostaApiParser respostaFormatada = gson.fromJson(respostaApi, RespostaApiParser.class);

      ArgumentCaptor<Coordenada> coordenada = ArgumentCaptor.forClass(Coordenada.class);

      Mockito.when(apiClimaCliente.getDadosPoluicaoAr(coordenada.capture())).thenReturn(respostaFormatada);

      clima.getQualidadeAr(new Coordenada(JDN_LAT, JDN_LON));

      verify(apiClimaCliente, times(1)).getDadosPoluicaoAr(new Coordenada(JDN_LAT, JDN_LON));
      assertEquals(JDN_LAT, coordenada.getValue().getLat());
      assertEquals(JDN_LON, coordenada.getValue().getLon());
    }
}
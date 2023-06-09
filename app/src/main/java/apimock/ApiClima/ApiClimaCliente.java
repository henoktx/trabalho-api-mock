package apimock.ApiClima;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

import apimock.Coordenada;

public class ApiClimaCliente implements IApiClimaCliente {
  private static final String API_URL = "http://api.openweathermap.org/data/2.5/air_pollution";
  private static final String API_KEY = "ff49990051fcb4fbb043a34f617e15d0";
  private HttpClient httpClient;

  public ApiClimaCliente() {
    this.httpClient = HttpClient.newHttpClient();
  }

  @Override
  public RespostaApiParser getDadosPoluicaoAr(Coordenada coordenada) throws ApiClimaException {
    HttpRequest httpRequest = HttpRequest.newBuilder()
      .uri(URI.create(API_URL + "?lat=" + coordenada.getLat() + "&lon=" + coordenada.getLon() + "&appid=" + API_KEY))
      .build();

      try {
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        int statusCode = response.statusCode();
        String responseBody = response.body();

        if (statusCode != 200) {
          throw new ApiClimaException("A requisição retornou um código de resposta inválido: " + statusCode);
        }

        Gson gson = new Gson();
        RespostaApiParser resposta = gson.fromJson(responseBody, RespostaApiParser.class);

        return resposta;
      } catch (IOException | InterruptedException e) {
        throw new ApiClimaException("Ocorreu um erro durante a requisição HTTP: " + e.getMessage(), e);
      }
  }
}

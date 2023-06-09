package apimock.ApiClima;

public class ApiClimaException extends RuntimeException {

  public ApiClimaException(String message) {
    super(message);
  }

  public ApiClimaException(String message, Throwable cause) {
    super(message, cause);
  }
}

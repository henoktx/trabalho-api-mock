package apimock;

public class Coordenada {
  private double lat;
  private double lon;

  public Coordenada(double lat, double lon) {
    this.lat = lat;
    this.lon = lon;
  }

  public double getLat() {
    return this.lat;
  }

  public double getLon() {
    return this.lon;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Coordenada other = (Coordenada) obj;

    return Double.compare(lat, other.lat) == 0 && Double.compare(lon, other.lon) == 0;
  }
}

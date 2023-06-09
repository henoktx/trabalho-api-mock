package apimock.ApiClima;

public class RespostaApiParser {
  private Coord coord;
  private ListItem[] list;

  public Coord getCoord() {
    return coord;
  }

  public ListItem[] getList() {
    return list;
  }

  public class Coord {
    private double lon;
    private double lat;

    public double getLon() {
      return lon;
    }

    public double getLat() {
      return lat;
    }
  }

  public class ListItem {
    private MainData main;
    private Components components;
    private long dt;

    public MainData getMain() {
      return main;
    }

    public Components getComponents() {
      return components;
    }

    public long getDt() {
      return dt;
    }
  }

  public class MainData {
    private int aqi;

    public int getAqi() {
      return aqi;
    }
  }

  public class Components {
    private double co;
    private double no;
    private double no2;
    private double o3;
    private double so2;
    private double pm2_5;
    private double pm10;
    private double nh3;

    public double getCo() {
      return this.co;
    }

    public double getNo() {
      return no;
    }

    public double getNo2() {
      return no2;
    }

    public double getO3() {
      return o3;
    }

    public double getSo2() {
      return so2;
    }

    public double getPm2_5() {
      return pm2_5;
    }

    public double getPm10() {
      return pm10;
    }

    public double getNh3() {
      return nh3;
    }
  }
}

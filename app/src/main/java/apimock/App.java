package apimock;

import apimock.ApiClima.ApiClimaCliente;

public class App {
    static final double JDN_LAT = -7.215069;
    static final double JDN_LON = -39.315157;
    public static void main(String[] args) {
        ApiClimaCliente apiClimaCliente = new ApiClimaCliente();
        Clima clima = new Clima(apiClimaCliente);

        System.out.println(clima.getQualidadeAr(new Coordenada(JDN_LAT, JDN_LON)));
    }
}

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyConverter {
    private static final String API_KEY = "10839ae7ef7b7ea7b10b177e";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public Double obtenerTasaDeCambio(String monedaOrigen, String monedaDestino) {
        HttpClient httpClient = new HttpClient();
        String urlStr = BASE_URL + API_KEY + "/latest/" + monedaOrigen;
        String response = httpClient.sendGetRequest(urlStr);

        // Usar Gson para parsear el JSON
        Gson gson = new Gson();
        JsonObject json = JsonParser.parseString(response).getAsJsonObject();
        JsonObject rates = json.getAsJsonObject("conversion_rates");

        return rates.get(monedaDestino).getAsDouble();
    }

    public Double convertirCantidad(Double cantidad, String monedaOrigen, String monedaDestino) {
        Double tasa = obtenerTasaDeCambio(monedaOrigen, monedaDestino);
        if (tasa != null) {
            // Calcular la cantidad convertida y redondearla a dos decimales
            BigDecimal cantidadConvertida = BigDecimal.valueOf(cantidad * tasa)
                    .setScale(2, RoundingMode.HALF_UP);
            return cantidadConvertida.doubleValue();
        } else {
            return null;
        }
    }
}

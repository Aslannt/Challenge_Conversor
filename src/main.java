import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido a Conversor de moneda");
        System.out.println("Ingresa la moneda de origen (ej: USD)");
        String monedaOrigen = scanner.next().toUpperCase();
        System.out.println("Ingrese la moneda de destino (Ej: EUR)");
        String monedaDestino = scanner.next().toUpperCase();
        System.out.println("Ingrese la cantidad a convertir: ");
        Double cantidad = scanner.nextDouble();

        CurrencyConverter converter = new CurrencyConverter();
        Double cantidadConvertida = converter.convertirCantidad(cantidad, monedaOrigen, monedaDestino);
        if (cantidadConvertida != null){
            System.out.println(cantidad + " " + monedaOrigen + " es igual a " + cantidadConvertida + " " + monedaDestino);
        }
        else {
            System.out.println("No se pudo obtener la tasa de cambio");
        }
        scanner.close();
    }
}

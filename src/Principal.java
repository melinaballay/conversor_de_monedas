import com.google.gson.JsonObject;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] monedas = {"EUR", "ARS", "BRL", "MXN", "GBP"};

        while (true) {
            System.out.println("\n=== Conversor de Monedas ===");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Convertir a USD");
            System.out.println("2. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();

            if (opcion == 2) {
                System.out.println("¡Gracias por usar el conversor!");
                break;
            } else if (opcion == 1) {
                System.out.print("\nIngrese el monto: ");
                double monto = scanner.nextDouble();

                System.out.println("\nSeleccione la moneda de ORIGEN:");
                for (int i = 0; i < monedas.length; i++) {
                    System.out.println((i + 1) + ". " + monedas[i]);
                }
                System.out.print("Opción: ");
                int indice = scanner.nextInt();
                String moneda = monedas[indice - 1];

                ConsultaMoneda consulta = new ConsultaMoneda();
                JsonObject json = consulta.obtenerTasas();
                JsonObject conversionRates = json.getAsJsonObject("conversion_rates");

                if (conversionRates.has(moneda)) {
                    double tasa = conversionRates.get(moneda).getAsDouble();
                    double resultado = monto / tasa;
                    System.out.printf("\n%.2f %s equivale a %.2f USD\n", monto, moneda, resultado);
                } else {
                    System.out.println("No se encontró la tasa para " + moneda);
                }
            } else {
                System.out.println("¡Opción inválida! Intente de nuevo.");
            }
        }
    }

}

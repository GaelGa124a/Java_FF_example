package io.harness.ff.examples;

import io.harness.cf.client.api.CfClient;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GettingStarted {
    private static final String apiKey = System.getProperty("FF_API_KEY"); // Leer como propiedad JVM
    private static final String flagName = "Java_flag"; // Nombre de tu Feature Flag

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {
        System.out.println("Iniciando SDK de Harness...");

        // Validar que el API Key esté configurado
        if (apiKey == null || apiKey.isEmpty()) {
            System.err.println("Error: FF_API_KEY no está configurado. Por favor, exporta la clave del SDK o pásala como propiedad JVM.");
            return;
        }

        try (CfClient cfClient = new CfClient(apiKey)) {
            cfClient.waitForInitialization();

            // Verificar el valor de la Feature Flag periódicamente
            scheduler.scheduleAtFixedRate(() -> {
                boolean result = cfClient.boolVariation(flagName, null, false); // No se pasa Target
                System.out.println("El estado del Feature Flag '" + flagName + "' es: " + result);
            }, 0, 10, TimeUnit.SECONDS);

            // Dejar correr el programa por 15 minutos
            TimeUnit.MINUTES.sleep(15);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

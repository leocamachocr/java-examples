package com.leoc.javafxthreads.provider;

import java.util.Random;

public class SMSProviderAPIImpl implements SMSProviderAPI {

    private static Random random = new Random();
    private static int SUCCESS_RATE = 30;

    @Override
    public boolean recharge(String service, Integer amount) {
        //enviar correo
        //mandamos a imprimir la factura
        boolean attempt = random.nextInt(100) < SUCCESS_RATE;
        if (attempt) {
            System.out.printf("Recarga exitosa al servicio %s con el monto %d\n", service, amount);
        } else {
            System.out.printf("Fuera de servicio, intente más tarde\n");
        }
        return attempt;
    }


}

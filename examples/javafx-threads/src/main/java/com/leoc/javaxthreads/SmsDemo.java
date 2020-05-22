package com.leoc.javaxthreads;

import com.leoc.javaxthreads.service.RechargeService;
import com.leoc.javaxthreads.thread.ThreadPool;
import com.leoc.javaxthreads.provider.SMSProviderAPIImpl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class SmsDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RechargeService rechargeService = new RechargeService(new SMSProviderAPIImpl());
        rechargeService.recharge("88112233", 1000);
        Future<Boolean> result = ThreadPool.getPool().submit(() -> {
            RechargeService.Status status;
            do {
                status = rechargeService.status("88112233");

            } while (status == RechargeService.Status.PROGRESS);
            return status == RechargeService.Status.COMPLETED;
        });
        if (result.get()) {
            System.out.println("Recarga exitosa");
        } else {
            System.out.println("Recarga insatisfactoria");
        }
        ThreadPool.getPool().shutdownNow();
    }
}

package com.leoc.javafxthreads.service;

import com.leoc.javafxthreads.thread.ThreadPool;
import com.leoc.javafxthreads.provider.SMSProviderAPI;

import java.util.HashMap;
import java.util.Map;

public class RechargeService {
    private SMSProviderAPI provider;

    public enum Status {PROGRESS, COMPLETED_WITH_ERROR, COMPLETED}

    Map<String, Status> recharges = new HashMap<>();

    public RechargeService(SMSProviderAPI provider) {
        this.provider = provider;
    }

    public String recharge(final String service, final Integer amount) {
        recharges.put(service, Status.PROGRESS);
        ThreadPool.getPool().execute(() -> runRecharge(service, amount));
        return service;
    }

    public Status status(String service) {
        return recharges.get(service);
    }

    private void runRecharge(String service, Integer amount) {
        boolean attempt;
        int retries = 0;
        do {
            attempt = provider.recharge(service, amount);
            retries++;
            ThreadPool.pause();
        } while (!attempt && retries < 10);
        recharges.put(service, attempt ? Status.COMPLETED : Status.COMPLETED_WITH_ERROR);


    }



}

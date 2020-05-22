package com.leoc.threads.synchronizedobject;

import java.util.ArrayList;
import java.util.List;

public class NumberBucket {
    private List<Integer> numbers;

    public NumberBucket() {
        numbers = new ArrayList<>();
    }

    public void add(Integer value) {//segundo hilo
        synchronized (this) {// segundo ? se puede acceder? monitor-lock es true
            numbers.add(value);//primer hilo en progreso
        }//primer sale monitor-lock es false
        // System.out.println("added: " + value);
    }

    public void remove(int index) {
        synchronized (this) {//tercero pidiendo acceso??
            numbers.remove(index);
        }
    }

    public int size() {
        return numbers.size();
    }
}

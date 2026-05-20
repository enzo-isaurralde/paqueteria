package com.elmensajero.demo.domain.service;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class TrackingNumberGenerator {

    private static final AtomicLong counter = new AtomicLong(0);

    public String generar() {
        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        long seq = counter.incrementAndGet();
        return String.format("EMS-%s-%06d", fecha, seq);
    }
}
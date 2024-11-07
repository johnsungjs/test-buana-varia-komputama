package com.buana.technical_test_backend.component;

import org.springframework.stereotype.Component;

@Component
public class StatusComponent {

    public String valueOf(int statusInt) {
        return switch (statusInt) {
            case 0 -> "Unhandled";
            case 1 -> "Approved";
            case 2 -> "Rejected";
            default -> "Invalid Status";
        };
    }
}

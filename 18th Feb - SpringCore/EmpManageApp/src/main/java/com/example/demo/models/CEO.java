package com.example.demo.models;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public final class CEO extends Emp {
    static private CEO ceo1 = null;

    private CEO() {
        super(500000, "CEO");
    }

    public void raiseSalary() {
        salary += 50000;
    }

    public static CEO getInstance() {
        if (ceo1 == null) {
            ceo1 = new CEO();
        }
        return ceo1;
    }
}

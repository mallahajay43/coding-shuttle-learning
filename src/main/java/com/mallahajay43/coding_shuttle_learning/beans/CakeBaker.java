package com.mallahajay43.coding_shuttle_learning.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CakeBaker {
    private final Frosting frosting;
    private final Syrup syrup;

    CakeBaker(@Qualifier("chocolate") Frosting frosting, Syrup syrup) {
        this.frosting = frosting;
        this.syrup = syrup;
    }

    public void bakeCake() {
        System.out.println("Baking Cake...");
        System.out.println("Syrup: " + this.syrup.getSyrupType());
        System.out.println("Frosting: " + this.frosting.getFrostingType());
    }
}

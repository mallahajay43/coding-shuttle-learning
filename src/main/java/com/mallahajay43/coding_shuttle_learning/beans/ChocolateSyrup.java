package com.mallahajay43.coding_shuttle_learning.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "syrup.type", havingValue = "chocolate")
public class ChocolateSyrup implements Syrup {

    @Override
    public String getSyrupType() {
        return "Chocolate";
    }
}

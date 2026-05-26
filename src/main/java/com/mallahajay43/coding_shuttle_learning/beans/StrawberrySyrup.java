package com.mallahajay43.coding_shuttle_learning.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
@ConditionalOnProperty(name = "syrup.type", havingValue = "strawberry") // if in application.property contain syrup.type then this will be injected.
public class StrawberrySyrup implements Syrup {

    @Override
    public String getSyrupType() {
        return "Strawberry";
    }
}

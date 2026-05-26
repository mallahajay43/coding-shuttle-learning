package com.mallahajay43.coding_shuttle_learning.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary // IF not Conditional or Qualifier annotation is used to Inject Frosting dependency the by Default this class object will be injected.
@Component // Stereotype annotation, mark this class for component scan and IOC container treat this class as bean.
@Qualifier("strawberry") // Bean identifier for Frosting type.
public class StrawberryFrosting implements Frosting {

    @Override
    public String getFrostingType() {
        return "Strawberry";
    }
}

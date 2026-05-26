package com.mallahajay43.coding_shuttle_learning.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("chocolate") // Based on the qualifier, dependency will be decided.
public class ChocolateFrosting implements Frosting {

    @Override
    public String getFrostingType() {
        return "Chocolate";
    }
}

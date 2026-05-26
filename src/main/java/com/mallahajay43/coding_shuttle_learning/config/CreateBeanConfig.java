package com.mallahajay43.coding_shuttle_learning.config;

import com.mallahajay43.coding_shuttle_learning.beans.ChocolateFrosting;
import com.mallahajay43.coding_shuttle_learning.beans.Frosting;
import com.mallahajay43.coding_shuttle_learning.beans.StrawberryFrosting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateBeanConfig {

    // Example: how bean can be created using configuration.
    // Mainly this is used on creating bean for class which is not custom.
//    @Bean
//    public Frosting getFrosting(){
//        return new StrawberryFrosting();
//    }
}

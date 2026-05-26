package com.mallahajay43.coding_shuttle_learning;

import com.mallahajay43.coding_shuttle_learning.beans.CakeBaker;
import com.mallahajay43.coding_shuttle_learning.beans.Frosting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodingShuttleLearningApplication implements CommandLineRunner {

    private final CakeBaker cakeBaker;

    CodingShuttleLearningApplication(CakeBaker cakeBaker){
        this.cakeBaker = cakeBaker;
    }

	public static void main(String[] args) {
		SpringApplication.run(CodingShuttleLearningApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        cakeBaker.bakeCake();
    }
}

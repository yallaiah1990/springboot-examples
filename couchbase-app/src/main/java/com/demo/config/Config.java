package com.demo.config;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;


@Configuration
public class Config extends AbstractCouchbaseConfiguration {

 

    @Override
    protected List<String> getBootstrapHosts() {
        return Collections.singletonList("localhost");
    }

 

    @Override
    protected String getBucketName() {
        return "customer360";
    }

 

    @Override
    protected String getBucketPassword() {
        return "admin123";
    }

 

    @Override
    protected String getUsername() {
        return "admin";
    }
}
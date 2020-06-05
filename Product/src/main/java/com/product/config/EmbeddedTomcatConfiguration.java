/*
package com.product.config;


import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class EmbeddedTomcatConfiguration {

    @Value("${server.http-port:0}")
    private Integer serverHttpPort;

    public class TomcatWebServerHttpPortCustomizer implements WebServerFactoryCustomizer {

      */
/*  @Override
        public void customize(TomcatServletWebServerFactory factory) {
           // log.info("serverHttpPort property configured as {}", serverHttpPort);

            if (serverHttpPort > 0) {
            //    log.info("Configuring Http Port to {}", serverHttpPort);
                Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
                connector.setPort(serverHttpPort);
                factory.addAdditionalTomcatConnectors(connector);
            }
        }*//*


        @Override
        public void customize(WebServerFactory factory) {
            if (serverHttpPort > 0) {
                //    log.info("Configuring Http Port to {}", serverHttpPort);
                Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
                connector.setPort(serverHttpPort);
                factory.addAdditionalTomcatConnectors(connector);
            }
        }
    }

    @Bean
    public WebServerFactoryCustomizer containerCustomizer() {
        return new TomcatWebServerHttpPortCustomizer();
    }


}*/

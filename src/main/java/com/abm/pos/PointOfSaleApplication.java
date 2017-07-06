package com.abm.pos;

import com.abm.pos.com.abm.pos.bl.View;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.PlusScopes;
import com.google.api.services.plus.model.Activity;
import com.google.api.services.plus.model.ActivityFeed;
import com.google.api.services.plus.model.Person;
import com.itextpdf.text.DocumentException;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.krysalis.barcode4j.impl.upcean.UPCA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

import static org.glassfish.jersey.server.ServerProperties.APPLICATION_NAME;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class PointOfSaleApplication {
    public static void main(String[] args) throws IOException, DocumentException {



        SpringApplication.run(PointOfSaleApplication.class, args);
    }

//    @Bean
//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.addDialect(new LayoutDialect());
//        templateEngine.addTemplateResolver(new UrlTemplateResolver());
//
//        return templateEngine;
//    }

}










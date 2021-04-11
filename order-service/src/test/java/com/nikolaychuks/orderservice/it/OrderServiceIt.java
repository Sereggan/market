package com.nikolaychuks.orderservice.it;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrderServiceIt {

    @BeforeAll
    void setUp(){
        WireMockServer wireMockServer = new WireMockServer( 8089);
        wireMockServer.start();
    }

    private void initAll(){

    }

    private void initDb(){}

}

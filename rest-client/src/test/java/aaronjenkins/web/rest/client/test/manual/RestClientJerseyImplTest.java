/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aaronjenkins.web.rest.client.test.manual;

import java.util.List;
import javax.ws.rs.core.MediaType;

import aaronjenkins.model.Meter;
import org.junit.Test;
import static org.junit.Assert.*;

import aaronjenkins.model.ReplyMessage;
import aaronjenkins.web.rest.client.RestClientJerseyImpl;

/**
 *
 * @author cgallen
 */
public class RestClientJerseyImplTest {

    String baseUrl = "http://localhost:8680/";

    MediaType mediaType = MediaType.APPLICATION_XML_TYPE;

    @Test
    public void restClientRetreiveTest() {

        RestClientJerseyImpl restClient = new RestClientJerseyImpl(baseUrl, mediaType);

        // try to retreive an unknown meter
        ReplyMessage replyMessage = restClient.retrieveEntity(Integer.SIZE);
        assertNotNull(replyMessage);
        assertTrue(replyMessage.getMeterList().getMeters().isEmpty());

        // try to retreive meter with id 1
        ReplyMessage replyMessage2 = restClient.retrieveEntity(1);
        assertNotNull(replyMessage2);
        assertEquals(1, replyMessage2.getMeterList().getMeters().size());

        Meter meter = replyMessage2.getMeterList().getMeters().get(0);
        System.out.println("Received Meter: " + meter);

    }

    @Test
    public void restClientRetreiveTemplateTest() {

        RestClientJerseyImpl restClient = new RestClientJerseyImpl(baseUrl, mediaType);

        Meter meterTempate = new Meter();
        meterTempate.setLocation("abcd");

        // try to retreive an unknown entity
        ReplyMessage replyMessage = restClient.retrieveMatchingEntites(meterTempate);
        assertNotNull(replyMessage);

        List<Meter> meterList =  replyMessage.getMeterList().getMeters();
        System.out.println("Received "
                + meterList.size()
                + " Entities");
        
       for(Meter e: meterList){
           System.out.println("   "+ e);
       }
        
    }
}

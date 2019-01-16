/* ***************************************************************************
 * Copyright 2018 Craig Gallen
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ****************************************************************************/
package aaronjenkins.model.test;

import java.io.File;
import java.io.StringWriter;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import aaronjenkins.model.Meter;
import aaronjenkins.model.Schedule;
import org.junit.Test;
import static org.junit.Assert.*;

import aaronjenkins.model.ReplyMessage;

/**
 *
 * @author gallenc
 */
public class ModelJaxbTest {

    @Test
    public void testJaxb() {

        try {

            // test file we will write and read. 
            // Note in target so that will be delted on each run and will not be saved to git
            File file = new File("target/testData.xml");
            System.out.println("writing test file to " + file.getAbsolutePath());

            // jaxb context needs jaxb.index file to be in same classpath
            // this contains a list of Jaxb annotated classes for the context to parse
            JAXBContext jaxbContext = JAXBContext.newInstance("aaronjenkins.model");

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // create some mock objects to test marshalling and unmarshalling
            ReplyMessage replyMessage1 = new ReplyMessage();
            replyMessage1.setCode(200);
            replyMessage1.setDebugMessage("debug message 1");

            List<Meter> meterList = replyMessage1.getMeterList().getMeters();

            for (int intityId = 0; intityId < 3; intityId++) {
                Meter meter = new Meter();
                meter.setId(intityId);
                meter.setLocation("Location" + intityId);
                //create 3 schedules
                Schedule schedule1 = new Schedule();
                schedule1.setStartTime("13:00");
                schedule1.setRate(5.00);
                meter.addSchedule(schedule1);
                Schedule schedule2 = new Schedule();
                schedule2.setStartTime("13:30");
                schedule2.setRate(2.50);
                meter.addSchedule(schedule2);
                //test schedule 2 is overwritten
                Schedule schedule3 = new Schedule();
                schedule3.setStartTime("13:30");
                schedule3.setRate(2.00);
                meter.addSchedule(schedule3);
                meterList.add(meter);
            }

            // marshal the object lists to system out, a file and a stringWriter
            jaxbMarshaller.marshal(replyMessage1, System.out);
            jaxbMarshaller.marshal(replyMessage1, file);

            // string writer is used to compare received object
            StringWriter sw1 = new StringWriter();
            jaxbMarshaller.marshal(replyMessage1, sw1);

            // having written the file we now read in the file for test
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            ReplyMessage receivedMessage = (ReplyMessage) jaxbUnMarshaller.unmarshal(file);

            StringWriter sw2 = new StringWriter();
            jaxbMarshaller.marshal(receivedMessage, sw2);

            // check transmitted and recieved message are the same
            assertEquals(sw1.toString(), sw2.toString());

        } catch (JAXBException e) {
            throw new RuntimeException("problem testing jaxb marshalling", e);
        }
    }

}

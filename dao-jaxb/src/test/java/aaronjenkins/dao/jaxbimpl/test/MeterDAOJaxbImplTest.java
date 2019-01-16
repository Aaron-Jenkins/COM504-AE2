/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aaronjenkins.dao.jaxbimpl.test;

import java.io.File;
import java.util.List;

import aaronjenkins.model.MeterDAO;
import aaronjenkins.model.Schedule;
import aaronjenkins.model.ScheduleList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import aaronjenkins.dao.jaxbimpl.MeterDAOJaxbImpl;
import aaronjenkins.model.Meter;

/**
 * tests for entityDao.createMeter(entity) entityDao.deleteMeter(Id) entityDao.retrieveAllMeters() entityDao.retrieveMeter(Id)
 * entityDao.retrieveMatchingEntites(entityTempate) entityDao.updateMeter(entity)
 *
 * @author cgallen
 */
public class MeterDAOJaxbImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(MeterDAOJaxbImplTest.class);

    public final String TEST_DATA_FILE_LOCATION = "target/testDaofile.xml";

    @Test
    public void testDestinationsDAOJaxb() {

        // delete test file at start of test
        File file = new File(TEST_DATA_FILE_LOCATION);
        file.delete();
        assertFalse(file.exists());

        // create dao
        MeterDAO meterDao = new MeterDAOJaxbImpl(TEST_DATA_FILE_LOCATION);

        // check that new file created
        assertTrue(file.exists());

        // check there are no entities
        assertTrue(meterDao.retrieveAllMeters().isEmpty());

        // add a 3 entities
        int ENTITY_NUMBER = 4;
        for (int intityId = 0; intityId < ENTITY_NUMBER; intityId++) {
            Meter meter = new Meter();
            meter.setLocation("Location" + intityId);
            ScheduleList scheduleList = new ScheduleList();
            meter.setScheduleList(scheduleList);
            LOG.debug("adding meter:" + meter);
            Meter e = meterDao.createMeter(meter);
            assertNotNull(e);
        }

        // check 3 entities added
        assertTrue(ENTITY_NUMBER == meterDao.retrieveAllMeters().size());

        // check return false for delete unknown entity
        assertFalse(meterDao.deleteMeter(Integer.SIZE));

        // find an entity to delete
        List<Meter> elist = meterDao.retrieveAllMeters();
        Integer idToDelete = elist.get(1).getId();
        LOG.debug("deleting  entity:" + idToDelete);

        // check found and deleted
        assertTrue(meterDao.deleteMeter(idToDelete));

        // check no longer found after deletion
        assertNull(meterDao.retrieveMeter(idToDelete));

        // check entities size decremeted
        List<Meter> elist2 = meterDao.retrieveAllMeters();
        assertTrue(ENTITY_NUMBER - 1 == elist2.size());

        // update entity
        Meter meterToUpdate = elist2.get(1);
        LOG.debug("updating entity: " + meterToUpdate);

        // add 2 newProperties for entity
        meterToUpdate.setLocation("Location_update");
        ScheduleList scheduleList = new ScheduleList();
        meterToUpdate.setScheduleList(scheduleList);
        LOG.debug("update template: " + meterToUpdate);

        Meter updatedMeter = meterDao.updateMeter(meterToUpdate);
        LOG.debug("updated entity: " + updatedMeter);
        assertNotNull(updatedMeter);

        // check entity updated
        Meter retrievedMeter = meterDao.retrieveMeter(updatedMeter.getId());
        LOG.debug("retreived entity: " + retrievedMeter);
        assertEquals(meterToUpdate.getLocation(), retrievedMeter.getLocation());
        assertEquals(meterToUpdate.getLocation(), retrievedMeter.getLocation());

        // test retrieve matching entities
        /*
        List<Meter> meterList = meterDao.retrieveAllMeters();
        Meter searchfor = meterList.get(2);
        LOG.debug("searching for: " + searchfor);

        Meter template = new Meter();
        template.setScheduleList(searchfor.getScheduleList());
        LOG.debug("using template : " + template);

        List<Meter> retrievedList = meterDao.retrieveMatchingMeters(template);
        assertEquals(1, retrievedList.size());

        LOG.debug("found : " + retrievedList.get(0));
        assertEquals(searchfor, retrievedList.get(0));
        */

    }

}

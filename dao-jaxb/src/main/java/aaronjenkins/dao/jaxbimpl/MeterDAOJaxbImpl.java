/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aaronjenkins.dao.jaxbimpl;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import aaronjenkins.model.Meter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import aaronjenkins.model.MeterDAO;
import aaronjenkins.model.MeterList;

/**
 *
 * @author cgallen
 */
public class MeterDAOJaxbImpl implements MeterDAO {

    private static final Logger LOG = LoggerFactory.getLogger(MeterDAOJaxbImpl.class);

    // jaxb context needs jaxb.index jaxbFile to be in same classpath
    // this path contains a list of Jaxb annotated classes for the context to parse
    private static final String CONTEXT_PATH = "aaronjenkins.model";

    // you must obtain lock before accessing objects and / or saving file
    public final Object Lock = new Object();

    private String dataFileLocation = null;

    private File jaxbFile = null;

    private MeterList meterList = null;

    private JAXBContext jaxbContext = null;

    public MeterDAOJaxbImpl(String dataFileLocation) {
        super();
        if (dataFileLocation == null) {
            throw new IllegalArgumentException("dataFile cannot be null");
        }
        this.dataFileLocation = dataFileLocation;
        load();
    }

    @Override
    public Meter createMeter(Meter meter) {
        if (meter == null) {
            throw new IllegalArgumentException("meter cannot be null");
        }
        synchronized (Lock) {
            // set initial id if not set or increment by 1
            Integer id = (meterList.getLastMeterId() == null) ? 1 : meterList.getLastMeterId() + 1;

            meterList.setLastMeterId(id);
            Meter ecopy = copy(meter);
            ecopy.setId(id);
            meterList.getMeters().add(ecopy);
            save();
            return ecopy;
        }
    }

    @Override
    public boolean deleteMeter(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        synchronized (Lock) {
            Iterator<Meter> it = meterList.getMeters().iterator();
            while (it.hasNext()) {
                Meter en = it.next();
                if (id.equals(en.getId())) {
                    it.remove();
                    save();
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public void deleteAllMeters() {
        synchronized (Lock) {
            meterList.getMeters().clear();
        }
    }

    @Override
    public Meter retrieveMeter(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        synchronized (Lock) {
            for (Meter en : meterList.getMeters()) {
                if (id.equals(en.getId())) {
                    return copy(en);
                }
            }
        }
        return null;
    }

    @Override
    public List<Meter> retrieveAllMeters() {
        synchronized (Lock) {
            List<Meter> returnList = new ArrayList<Meter>();
            for (Meter meter : meterList.getMeters()) {
                returnList.add(copy(meter));
            };
            return returnList;
        }
    }

    /**
     * Returns a list of all Entities which match all of the set (i.e. not null)
     * fields of meterTemplate
     *
     * @param meterTemplate
     * @return
     */
    @Override
    public List<Meter> retrieveMatchingMeters(Meter meterTemplate) {
        if (meterTemplate == null) {
            throw new IllegalArgumentException("meterTemplate cannot be null");
        }
        List<Meter> returnList = new ArrayList<Meter>();
        synchronized (Lock) {
            for (Meter meter : meterList.getMeters()) {
                boolean match = true;
                if (meterTemplate.getId() != null) {
                    if (!meterTemplate.getId().equals(meter.getId())) {
                        match = false;
                    }
                };
                if (meterTemplate.getLocation() != null) {
                    if (!meterTemplate.getLocation().equals(meter.getLocation())) {
                        match = false;
                    }
                };
                if (meterTemplate.getScheduleList() != null) {
                    if (!meterTemplate.getScheduleList().equals(meter.getScheduleList())) {
                        match = false;
                    }
                }
                if (match) {
                    returnList.add(copy(meter));
                }
            }
            return returnList;
        }
    }

    @Override
    public Meter updateMeter(Meter meterTemplate) {
        if (meterTemplate == null) {
            throw new IllegalArgumentException("entity cannot be null");
        }
        synchronized (Lock) {
            for (Meter en : meterList.getMeters()) {
                if (meterTemplate.getId().equals(en.getId())) {
                    boolean changedfield = false;

                    // update properties fields if only if meterTemplate field is set
                    if (meterTemplate.getLocation() != null) {
                        en.setLocation(meterTemplate.getLocation());
                        changedfield = true;
                    }
                    if (meterTemplate.getScheduleList() != null) {
                        en.setScheduleList(meterTemplate.getScheduleList());
                        changedfield = true;
                    }
                    // save if anything changed
                    if (changedfield) {
                        save();
                    }
                    return copy(en);
                }

            }
        }
        return null; //entity not found
    }

    /**
     * copies new Meter data transfer objects to create detached object and so
     * avoid problems with indirect object modification
     *
     * @param meter
     * @return independent copy of Meter
     */
    private Meter copy(Meter meter) {
        try {
            StringWriter sw1 = new StringWriter();
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(meter, sw1);

            StringReader sr1 = new StringReader(sw1.toString());
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            Meter newAccount = (Meter) jaxbUnMarshaller.unmarshal(sr1);
            return newAccount;
        } catch (JAXBException ex) {
            throw new RuntimeException("problem copying jaxb object", ex);
        }
    }

    /**
     * loads jaxb file at beginning of program
     */
    private void load() {

        try {

            // jaxb context needs jaxb.index jaxbFile to be in same classpath
            // this contains a list of Jaxb annotated classes for the context to parse
            jaxbContext = JAXBContext.newInstance(CONTEXT_PATH);

            // try to load dataFileLocation
            jaxbFile = new File(dataFileLocation);
            LOG.debug("using dataFile:" + jaxbFile.getAbsolutePath());

            if (jaxbFile.exists()) {
                LOG.debug("dataFile exists loading:" + jaxbFile.getAbsolutePath());
                // load jaxbFile
                Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
                meterList = (MeterList) jaxbUnMarshaller.unmarshal(jaxbFile);
            } else {
                // create annd save an empty jaxbFile
                LOG.debug("dataFile does not exist creating new " + jaxbFile.getAbsolutePath());

                meterList = new MeterList();

                // make directories if dont exist
                jaxbFile.getParentFile().mkdirs();

                // save empty data to new file
                save();
            }

        } catch (JAXBException ex) {
            throw new RuntimeException("problem creating persistor", ex);
        }

    }

    /**
     * saves data to datafile on updates
     */
    private void save() {
        try {
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(meterList, jaxbFile);
            if (LOG.isDebugEnabled()) {
                StringWriter sw1 = new StringWriter();
                jaxbMarshaller.marshal(meterList, sw1);
                LOG.debug("saving xml to file:" + sw1.toString());
            }

        } catch (JAXBException ex) {
            throw new RuntimeException("problem persisting dao", ex);
        }
    }

}

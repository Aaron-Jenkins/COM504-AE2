/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aaronjenkins.service;

import aaronjenkins.dao.jaxbimpl.MeterDAOJaxbImpl;
import aaronjenkins.model.MeterDAO;
import aaronjenkins.model.ServiceFacade;
import aaronjenkins.model.ServiceFactory;

/**
 *
 * @author cgallen
 */
public class ServiceFactoryImpl implements ServiceFactory {

    ServiceFacade serviceFacade = null;

    String dataFileUri = null;

    public ServiceFactoryImpl(String dataFileUri) {
        if (dataFileUri == null) {
            throw new IllegalArgumentException("dataFileUri must not be null");
        }
        
        MeterDAO meterDao = new MeterDAOJaxbImpl(dataFileUri);
        ServiceFacadeImpl serviceFacadeImpl = new ServiceFacadeImpl();
        serviceFacadeImpl.setMeterDAO(meterDao);
        serviceFacade = serviceFacadeImpl;
        
    }

    @Override
    public ServiceFacade getServiceFacade() {
        return serviceFacade;
    }

}

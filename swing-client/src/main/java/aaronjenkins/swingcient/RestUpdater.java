/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aaronjenkins.swingcient;

import aaronjenkins.model.MeterDAO;

/**
 *
 * @author cgallen
 */
public class RestUpdater {
    MeterDAO meterDAO = null;
    String updatechron ="";
    String baseUrl = "";
    
    public MeterDAO getMeterDAO() {
        return meterDAO;
    }

    public void setMeterDAO(MeterDAO meterDAO) {
        this.meterDAO = meterDAO;
    }

    public String getUpdatechron() {
        return updatechron;
    }

    public void setUpdatechron(String updatechron) {
        this.updatechron = updatechron;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    
    public boolean immediateUpdate(){
        return false;
    }
            
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aaronjenkins.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import aaronjenkins.model.*;

/**
 *
 * @author cgallen
 */
public class ServiceFacadeImpl implements ServiceFacade {
    
    private MeterDAO meterDAO = null;

    public void setMeterDAO(MeterDAO meterDAO) {
        this.meterDAO = meterDAO;
    }

    @Override
    public void deleteAllMeters() {
       meterDAO.deleteAllMeters();
    }

    @Override
    public Meter createMeter(Meter meter) {
        Double rate = 2.00;
        String myTime = "11:30";
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date d = null;
        try {
            d = df.parse(myTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, 30);
        String Time = df.format(cal.getTime());
        for (int i = 0; i < 24; i++) {
            Schedule schedule = new Schedule(Time, rate);
            meter.addSchedule(schedule);
            cal.add(Calendar.MINUTE, 30);
            Time = df.format(cal.getTime());
        }
        return meterDAO.createMeter(meter);
    }

    @Override
    public boolean deleteMeter(Integer id) {
        return meterDAO.deleteMeter(id);
    }

    @Override
    public Meter retrieveMeter(Integer id) {
        return meterDAO.retrieveMeter(id);
    }

    @Override
    public List<Meter> retrieveAllMeters() {
        return meterDAO.retrieveAllMeters();
    }

    @Override
    public List<Meter> retrieveMatchingMeters(Meter meterTemplate) {
        return meterDAO.retrieveMatchingMeters(meterTemplate);
    }

    @Override
    public Meter updateMeter(Meter meter) {
        return meterDAO.updateMeter(meter);
    }

}

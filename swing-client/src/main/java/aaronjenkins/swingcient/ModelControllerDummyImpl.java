/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aaronjenkins.swingcient;


import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import aaronjenkins.model.Meter;

/**
 *
 * @author cgallen
 */
public class ModelControllerDummyImpl implements ModelController {

    private static final Logger LOG = LoggerFactory.getLogger(ModelControllerDummyImpl.class);

    private EntityListTableModel entityListTableModel = null;

    private void initialiseTableModel() {

        entityListTableModel = new EntityListTableModel();
        List<Meter> elist = new ArrayList<Meter>();

        int ENTITY_NUMBER = 40;
        for (int intityId = 0; intityId < ENTITY_NUMBER; intityId++) {
            Meter meter = new Meter();
            meter.setId(intityId);
            meter.setLocation("field_A_" + intityId);
            meter.setScheduleList("field_B_" + intityId);;
            meter.setField_C("field_C_" + intityId);;
            elist.add(meter);
        }
        entityListTableModel.setEntities(elist);
    }

    @Override
    public EntityListTableModel getEntityListTableModel() {
        if (entityListTableModel == null) {
            synchronized (ModelControllerDummyImpl.class) {
                if (entityListTableModel == null) {
                    initialiseTableModel();
                }
            }
        }
        return entityListTableModel;

    }
    


    @Override
    public void clearSearch() {
        LOG.debug("clear search selected");
    }

    @Override
    public void findMatchingSearch(Meter templateMeter) {
        LOG.debug("find matching with templateMeter="+ templateMeter);
    }

    @Override
    public boolean forceReloadData() {
        return false;
    }



}

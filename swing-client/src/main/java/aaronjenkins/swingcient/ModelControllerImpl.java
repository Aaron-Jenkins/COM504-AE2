/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aaronjenkins.swingcient;

import java.util.List;

import aaronjenkins.model.Meter;
import aaronjenkins.model.MeterDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cgallen
 */
public class ModelControllerImpl implements ModelController {

    private static final Logger LOG = LoggerFactory.getLogger(ModelControllerImpl.class);

    private MeterDAO meterDAO = null;

    private EntityListTableModel entityListTableModel = new EntityListTableModel();

    private EntityClientLoader entityClientLoader = null;

    public ModelControllerImpl(MeterDAO meterDAO, EntityClientLoader entityClientLoader) {
        this.entityClientLoader = entityClientLoader;
        this.meterDAO = meterDAO;
        List<Meter> entities = meterDAO.retrieveAllMeters();
        entityListTableModel.setEntities(entities);
    }

    @Override
    public EntityListTableModel getEntityListTableModel() {
        return entityListTableModel;
    }

    @Override
    public void clearSearch() {
        LOG.debug("clear search selected");

        List<Meter> entities = meterDAO.retrieveAllMeters();
        entityListTableModel.setEntities(entities);
    }

    @Override
    public void findMatchingSearch(Meter templateMeter) {
        LOG.debug("find matching with templateMeter=" + templateMeter);

        List<Meter> entities = meterDAO.retrieveMatchingMeters(templateMeter);
        LOG.debug("found " + entities.size() + " matching with templateMeter=" + templateMeter);
        entityListTableModel.setEntities(entities);

    }

    @Override
    public boolean forceReloadData() {
        LOG.debug("forceReloadData called");
        boolean success = false;
        if (entityClientLoader != null) {
            success = entityClientLoader.restClientRetrieveAll();
        }
        LOG.debug("forceReloadData result=" + success);
        return success;
    }

}

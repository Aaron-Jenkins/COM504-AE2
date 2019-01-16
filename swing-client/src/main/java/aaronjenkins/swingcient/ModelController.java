/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aaronjenkins.swingcient;

import aaronjenkins.model.Meter;

/**
 *
 * @author cgallen
 */
public interface ModelController {

    void clearSearch();

    void findMatchingSearch(Meter templateMeter);

    EntityListTableModel getEntityListTableModel();
    
    boolean forceReloadData();
    
}

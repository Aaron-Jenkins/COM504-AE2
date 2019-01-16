/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aaronjenkins.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MeterList {

    // only used by persistance layer
    private Integer lastMeterId = null;

    //@XmlElementWrapper(name = "meters")
    @XmlElement(name = "Meter")
    private List<Meter> meters = new ArrayList<>();

    public List<Meter> getMeters() {
        return meters;
    }

    public Integer getLastMeterId() {
        return lastMeterId;
    }

    public void setLastMeterId(Integer lastMeterId) {
        this.lastMeterId = lastMeterId;
    }

    @Override
    public String toString() {
        return "MeterList{" + "lastMeterId=" + lastMeterId + ", meters=" + meters + '}';
    }

}

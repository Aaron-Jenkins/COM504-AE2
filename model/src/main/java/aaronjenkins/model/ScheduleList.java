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
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ScheduleList {

/*    @XmlElementWrapper(name = "schedules")*/
    @XmlElement(name = "schedule")
    private List<Schedule> schedules = new ArrayList<>();

    public List<Schedule> getSchedules() {
        return schedules;
    }

    //public void setSchedules(List<Schedule> schedules) { this.schedules = schedules; }

    @Override
    public String toString() {
        return "ScheduleList{" + "schedules=" + schedules + '}';
    }

}

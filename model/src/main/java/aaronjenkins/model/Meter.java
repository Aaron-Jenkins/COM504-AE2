package aaronjenkins.model;


import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Meter {

    private Integer id;

    private String location = null;

    private ScheduleList scheduleList = new ScheduleList();

    //private String field_C = null;

    public Meter() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ScheduleList getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(ScheduleList scheduleList) {
        this.scheduleList = scheduleList;
    }

    //public String getField_C() { return field_C; }

    //public void setField_C(String field_C) { this.field_C = field_C; }

    public void addSchedule(Schedule schedule) {
        for (int i = 0; i < scheduleList.getSchedules().size(); i++) {
            if(scheduleList.getSchedules().get(i).getStartTime().equals(schedule.getStartTime())) {
                scheduleList.getSchedules().remove(i);
            }
        }
        scheduleList.getSchedules().add(schedule);
    }
    @Override
    public String toString() {
        return "Meter{" + "id=" + id
                + ", location=" + location
                + ", scheduleList=" + scheduleList
                +  '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Meter other = (Meter) obj;
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.scheduleList, other.scheduleList)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}

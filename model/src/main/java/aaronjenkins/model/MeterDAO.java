package aaronjenkins.model;

import java.util.List;

public interface MeterDAO {

    Meter createMeter(Meter meter);

    boolean deleteMeter(Integer id);

    void deleteAllMeters();

    Meter retrieveMeter(Integer id);

    List<Meter> retrieveAllMeters();

    List<Meter> retrieveMatchingMeters(Meter meterTemplate);

    Meter updateMeter(Meter meter);
}

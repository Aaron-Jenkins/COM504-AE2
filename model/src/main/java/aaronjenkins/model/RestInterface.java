package aaronjenkins.model;

public interface RestInterface {

    public ReplyMessage retrieveMatchingEntites(Meter meterTempate);
    
    public ReplyMessage retrieveEntity(Integer id);
    
}

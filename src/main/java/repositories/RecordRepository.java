package repositories;

import models.entities.Record;

import java.util.List;

public interface RecordRepository  {

    public List<Record> findAllRecords();
    public Record findRecord(String  id);
    public Record findRecordByPPSNumber(String name);
    public Record createRecord(Record data);


}

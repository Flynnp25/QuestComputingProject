import models.entities.Record;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import services.RecordService;
import java.util.Calendar;
import java.util.List;

@Transactional
public class RecordServiceTest extends AbstractClass {

    @Autowired
    private RecordService recordService;

    public Record record;

    private String RECORD_NAME = "TestName";
    private String PPS = "123456D";
    private String MOBILE_NUM = "0812345678";

    @Before
    public void setUp(){
        record = new Record();
        record.setName(RECORD_NAME);
        record.setPpsNumber(PPS);
        Calendar cal = Calendar.getInstance();
        cal.set(1992,Calendar.MARCH, 21);
        record.setDateOfBirth(cal.getTime());
        record.setMobileNumber(MOBILE_NUM);
    }

    @Test
    public void testCreateRecord(){
        recordService.createNewRecord(record);
        Assert.assertNotNull("FAILED - record is not null",record);
        Assert.assertNotNull("FAILED - record id is not null",record.getId());
        Assert.assertEquals("FAILED - record name is not equal", RECORD_NAME,record.getName());
        Assert.assertEquals("FAILED - record pps is not equal", PPS,record.getPpsNumber());
        Assert.assertEquals("FAILED - record mobile number is not equal", MOBILE_NUM,record.getMobileNumber());

        List<Record> records = recordService.findAllRecords();
        Assert.assertNotNull("FAILED - records is not null",records);
        Assert.assertEquals("FAILED - records size is 1",1,records.size());
    }
}

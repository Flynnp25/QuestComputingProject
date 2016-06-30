package repositories.jpa;

import models.entities.Record;
import org.springframework.stereotype.Repository;
import repositories.RecordRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaRecordRepository implements RecordRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Record> findAllRecords() {
        Query query = em.createQuery("SELECT r FROM Record r");
        return query.getResultList();
    }

    @Override
    public Record findRecord(String id) {
        return em.find(Record.class, id);
    }

    @Override
    public Record findRecordByPPSNumber(String ppsNumber) {
        Query query = em.createQuery("SELECT r FROM Record r WHERE r.ppsNumber = ?1");
        query.setParameter(1,ppsNumber);

        List<Record> records = query.getResultList();
        if(records.size() == 0) {
            return null;
        } else {
            return records.get(0);
        }
    }

    @Override
    public Record createRecord(Record data) {
        em.persist(data);
        return data;    }
}

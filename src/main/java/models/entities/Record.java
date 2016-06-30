package models.entities;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table
public class Record {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min=2, max=25)
    private String name;

    @NotNull
    private String ppsNumber;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private Date dateOfBirth;

    private String mobileNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPpsNumber() {
        return ppsNumber;
    }

    public void setPpsNumber(String ppsNumber) {
        this.ppsNumber = ppsNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Record{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", ppsNumber='").append(ppsNumber).append('\'');
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", mobileNumber='").append(mobileNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

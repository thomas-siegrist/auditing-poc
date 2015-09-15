//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package hello;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@MappedSuperclass
public class DefaultRevisionEntity implements Serializable {
    private static final long serialVersionUID = 8530213963961662300L;
    @Id
    @GeneratedValue
    @RevisionNumber
    private Integer id;
    @RevisionTimestamp
    private long timestamp;

    public DefaultRevisionEntity() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Transient
    public Date getRevisionDate() {
        return new Date(this.timestamp);
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof DefaultRevisionEntity)) {
            return false;
        } else {
            DefaultRevisionEntity that = (DefaultRevisionEntity) o;
            return this.id == that.id && this.timestamp == that.timestamp;
        }
    }

    public int hashCode() {
        int result = this.id;
        result = 31 * result + (int) (this.timestamp ^ this.timestamp >>> 32);
        return result;
    }

    public String toString() {
        return "DefaultRevisionEntity(id = " + this.id + ", revisionDate = " + DateFormat.getDateTimeInstance().format(this.getRevisionDate()) + ")";
    }
}

package hello;

import javax.persistence.Entity;

import org.hibernate.envers.RevisionEntity;

@Entity
@RevisionEntity(AuditingRevisionListener.class)
public class AuditedRevisionEntity extends DefaultRevisionEntity {
    private static final long serialVersionUID = 1L;

    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

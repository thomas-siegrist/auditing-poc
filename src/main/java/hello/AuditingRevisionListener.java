package hello;

import org.hibernate.envers.RevisionListener;

public class AuditingRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        AuditedRevisionEntity auditedRevisionEntity = (AuditedRevisionEntity) revisionEntity;
        auditedRevisionEntity.setUser("Unauthorized User");
    }

}

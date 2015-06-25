
package com.createidea.scrumfriend.dao.attachment;

import com.createidea.scrumfriend.dao.BaseDaoImpl;
import com.createidea.scrumfriend.to.AttachmentTo;

public class AttachmentDaoImpl extends BaseDaoImpl implements AttachmentDao {

	@Override
	public void saveAttachment(AttachmentTo attachment) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(attachment);
	}

}

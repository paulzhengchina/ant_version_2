package com.createidea.scrumfriend.service.attachment;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import com.createidea.scrumfriend.dao.attachment.AttachmentDao;
import com.createidea.scrumfriend.to.AttachmentTo;
import com.createidea.scrumfriend.utils.FileUtil;



public class AttachmentServiceImpl implements AttachmentService {
    private AttachmentDao attachmentDao;
	@Override
	public AttachmentTo saveAttachment(File image,String imageContentType, String imageFileName, String realPath) {
		// TODO Auto-generated method stub
		
		if(image!=null){
			imageFileName=imageFileName.substring(imageFileName.indexOf("."), imageFileName.length());
			AttachmentTo attachment=new AttachmentTo();
			try {
				String filename=new Date().getTime()+imageFileName;
				FileUtil.uploadFile(image, realPath+"/upload",filename);
				String realpath="upload" + "/" +filename;
				attachment.setPath(realpath);
				attachment.setName(filename);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String id=UUID.randomUUID().toString();
			attachment.setId(id);
			attachmentDao.saveAttachment(attachment);
			return attachment;			
		}
		return null;
		
	}

	@Override
	public void updateAttachment(AttachmentTo attachment, File image,String imageContentType, String imageFileName, String realPath) {
		// TODO Auto-generated method stub
		imageFileName=imageFileName.substring(imageFileName.indexOf("."), imageFileName.length());
		if(image!=null){
			try {
				String filename=new Date().getTime()+imageFileName;
				FileUtil.uploadFile(image, realPath+"upload",filename);
				String realpath="upload" + "/" +filename;
				attachment.setPath(realpath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
									
		}
		attachmentDao.saveAttachment(attachment);
	}

	public AttachmentDao getAttachmentDao() {
		return attachmentDao;
	}

	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}
	
	
}

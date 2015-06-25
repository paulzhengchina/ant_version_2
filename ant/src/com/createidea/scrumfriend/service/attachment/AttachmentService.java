package com.createidea.scrumfriend.service.attachment;

import java.io.File;

import com.createidea.scrumfriend.to.AttachmentTo;

public interface AttachmentService {

	public AttachmentTo saveAttachment(File image,String imageContentType, String imageFileName, String realPath);

	public void updateAttachment(AttachmentTo attachment, File image,String imageContentType, String imageFileName, String realPath);
      
	
}

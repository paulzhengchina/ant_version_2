package com.createidea.scrumfriend.struts.action;

import java.io.File;
import java.util.List;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import com.createidea.scrumfriend.service.attachment.AttachmentService;
import com.createidea.scrumfriend.to.AttachmentTo;

public class UploadAction extends BaseAction {

	private File image;
	private String imageContentType;
	private String imageFileName;
	private AttachmentService attachmentService;
	private String uploadedFilePath;
	private String attachmentId;

	
	public String uploadFile(){
		AttachmentTo logo=attachmentService.saveAttachment(image, imageContentType, imageFileName, getApplicationRootPath());
		uploadedFilePath="http://localhost/"+logo.getPath();
		attachmentId=logo.getId();
		return SUCCESS;
	}


	public File getImage() {
		return image;
	}


	public void setImage(File image) {
		this.image = image;
	}


	public String getImageContentType() {
		return imageContentType;
	}


	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}


	public String getImageFileName() {
		return imageFileName;
	}


	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}


	public AttachmentService getAttachmentService() {
		return attachmentService;
	}


	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}


	public String getUploadedFilePath() {
		return uploadedFilePath;
	}


	public void setUploadedFilePath(String uploadedFilePath) {
		this.uploadedFilePath = uploadedFilePath;
	}


	public String getAttachmentId() {
		return attachmentId;
	}


	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	
	


}

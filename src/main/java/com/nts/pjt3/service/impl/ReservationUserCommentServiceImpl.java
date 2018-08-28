package com.nts.pjt3.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nts.pjt3.dao.FileInfoDao;
import com.nts.pjt3.dao.ReservationUserCommentDao;
import com.nts.pjt3.dao.ReservationUserCommentImageDao;
import com.nts.pjt3.dto.FileInfo;
import com.nts.pjt3.dto.ReservationUserComment;
import com.nts.pjt3.dto.ReservationUserCommentImage;
import com.nts.pjt3.service.ReservationUserCommentService;
import com.nts.pjt3.util.ImageUtil;

@Service
public class ReservationUserCommentServiceImpl implements ReservationUserCommentService {

	@Autowired
	private ReservationUserCommentDao reservationUserCommentDao;

	@Autowired
	private ReservationUserCommentImageDao reservationUserCommentImageDao;

	@Autowired
	private FileInfoDao fileInfoDao;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

	@Override
	public List<ReservationUserComment> getAllByProductId(int productId) {
		List<ReservationUserComment> comments = reservationUserCommentDao.getAllByProductId(productId);
		for (ReservationUserComment comment : comments) {
			comment.setReservationUserCommentImages(
				reservationUserCommentImageDao.getAllByReservationUserCommentId(comment.getId()));

			String modifiredReservationEmail = getModifidReservationEmail(comment.getReservationEmail());
			comment.setReservationEmail(modifiredReservationEmail);
		}
		return comments;
	}

	@Override
	public double getAvgScoreFromComments(List<ReservationUserComment> comments) {
		double sumScore = 0;
		for (ReservationUserComment comment : comments) {
			sumScore += comment.getScore();
		}
		double avgScore = Math.round(sumScore / comments.size() * 10d) / 10d;
		return avgScore;
	}

	private String getModifidReservationEmail(String reservationEmail) {
		if (reservationEmail.length() > 4) {
			return reservationEmail.substring(0, 4).concat("****");
		} else {
			return "****";
		}
	}

	@Transactional
	@Override
	public ReservationUserComment createComment(ReservationUserComment comment, MultipartFile commentImageFile) {
		reservationUserCommentDao.createComment(comment);

		if (commentImageFile != null) {
			String nowDate = LocalDateTime.now().format(formatter);
			String fileName = commentImageFile.getOriginalFilename();
			String saveFileName = "img_review/" + nowDate + fileName;
			
			ImageUtil.getInstance().saveImageFileAtFileSystem(commentImageFile, saveFileName);
			int imageFileInfoId = createFileInfo(fileName, saveFileName, commentImageFile.getContentType());
			createCommentImage(comment.getReservationInfoId(), comment.getId(), imageFileInfoId);
		}
		return comment;
	}

	private int createFileInfo(String fileName, String saveFileName, String contentType) {
		FileInfo imageFileInfo = new FileInfo();
		imageFileInfo.setFileName(fileName);
		imageFileInfo.setSaveFileName(saveFileName);
		imageFileInfo.setContentType(contentType);
		fileInfoDao.createFileInfo(imageFileInfo);
		return imageFileInfo.getId();
	}

	private int createCommentImage(int reservInfoId, int commentId, int fileId) {
		ReservationUserCommentImage commentImage = new ReservationUserCommentImage();
		commentImage.setReservationInfoId(reservInfoId);
		commentImage.setReservationUserCommentId(commentId);
		commentImage.setFileId(fileId);
		reservationUserCommentImageDao.createCommentImage(commentImage);
		return commentImage.getId();
	}

}

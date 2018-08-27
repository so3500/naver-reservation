package com.nts.pjt3.service.impl;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nts.pjt3.dao.FileDao;
import com.nts.pjt3.dao.ReservationUserCommentDao;
import com.nts.pjt3.dao.ReservationUserCommentImageDao;
import com.nts.pjt3.dto.File;
import com.nts.pjt3.dto.ReservationUserComment;
import com.nts.pjt3.dto.ReservationUserCommentImage;
import com.nts.pjt3.service.ReservationUserCommentService;

@Service
public class ReservationUserCommentServiceImpl implements ReservationUserCommentService {

	@Autowired
	private ReservationUserCommentDao reservationUserCommentDao;

	@Autowired
	private ReservationUserCommentImageDao reservationUserCommentImageDao;

	@Autowired
	private FileDao fileDao;
	
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
		if(reservationEmail.length() > 4) {
			return reservationEmail.substring(0, 4).concat("****");			
		} else {
			return "****";
		}
	}

	@Transactional
	@Override
	public ReservationUserComment createComment(ReservationUserComment review, MultipartFile reviewImage) {
		reservationUserCommentDao.createComment(review);
		
		if(reviewImage != null) {
			File reviewImageFile = createReviewImageFile(reviewImage);
			createReviewImage(review.getReservationInfoId(), review.getId(),
				reviewImageFile.getId());
		}
		
		return review;
	}

	private File createReviewImageFile(MultipartFile reviewImage) {
		String nowDate = LocalDateTime.now().format(formatter);
		String fileName = reviewImage.getOriginalFilename();
		String saveFileName = "img_review/" + nowDate + fileName;
		String filePath = "C:/reservation/" + saveFileName;
		saveAtFileSystem(reviewImage, filePath);
		
		File reviewImageFile = new File();
		reviewImageFile.setFileName(fileName);
		reviewImageFile.setSaveFileName(saveFileName);
		reviewImageFile.setContentType(reviewImage.getContentType());
		fileDao.createFile(reviewImageFile);

		return reviewImageFile;
	}

	private void saveAtFileSystem(MultipartFile reviewImage, String saveFileName) {
		try (
			FileOutputStream fos = new FileOutputStream(saveFileName);
			InputStream is = reviewImage.getInputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024 * 1024 * 10];
			while ((readCount = is.read(buffer)) != -1) {
				fos.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("file Save Error");
		}
	}

	private ReservationUserCommentImage createReviewImage(int reservInfoId, int commentId, int fileId) {
		ReservationUserCommentImage reviewImage = new ReservationUserCommentImage();
		reviewImage.setReservationInfoId(reservInfoId);
		reviewImage.setReservationUserCommentId(commentId);
		reviewImage.setFileId(fileId);
		reservationUserCommentImageDao.createCommentImage(reviewImage);
		return reviewImage;
	}

}

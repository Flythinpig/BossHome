package com.raymey.controller;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.dcm4che3.imageio.plugins.dcm.DicomImageReadParam;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.io.DicomInputStream;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import com.raymey.domain.DicomFile;

@Controller
public class DicomParse {

	@RequestMapping("/upload")
	public String uploadFile(@RequestParam MultipartFile file,
			Map<String, Object> model) {
		DicomFile dicomfile = new DicomFile();
		Attributes attributes;
		BufferedImage myImage;
		DicomInputStream dicomInputStream;

		try {

			dicomInputStream = new DicomInputStream(file.getInputStream());
			attributes = dicomInputStream.readDataset(-1, Tag.PixelData);
			dicomfile
					.setPatientName(attributes.getString(Tag.PatientName) == null ? "无"
							: attributes.getString(Tag.PatientName));
			dicomfile
					.setPatientAge(attributes.getString(Tag.PatientAge) == null ? "无"
							: attributes.getString(Tag.PatientAge));
			dicomfile
					.setPatientSex(attributes.getString(Tag.PatientSex) == null ? "无"
							: attributes.getString(Tag.PatientSex));
			dicomfile
					.setStudyDate(attributes.getDate(Tag.StudyDate).toString() == null ? "无"
							: attributes.getDate(Tag.StudyDate).toString());
			dicomfile
					.setWindowCenter(attributes.getString(Tag.WindowCenter) == null ? "无"
							: attributes.getString(Tag.WindowCenter));
			dicomfile
					.setWindowWidth(attributes.getString(Tag.WindowWidth) == null ? "无"
							: attributes.getString(Tag.WindowWidth));
			dicomInputStream.close();

			Iterator<ImageReader> iterator = ImageIO
					.getImageReadersByFormatName("DICOM");
			ImageReader imageReader = (ImageReader) iterator.next();
			DicomImageReadParam dicomImageReadParam = (DicomImageReadParam) imageReader
					.getDefaultReadParam();

			ImageInputStream iis = ImageIO.createImageInputStream(file);
			imageReader.setInput(iis, false);
			myImage = imageReader.read(0, dicomImageReadParam);

			iis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "error";
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}

		if (myImage == null) {
			model.put("dicomimage", "图像不存在！");
		}

		else {
			File desFile = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "temp.jpg");
			try {
				OutputStream outputStream = new BufferedOutputStream(
						new FileOutputStream(desFile));
				JPEGImageEncoder encoder = JPEGCodec
						.createJPEGEncoder(outputStream);
				encoder.encode(myImage);
				outputStream.close();
				model.put("dicomimage", desFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		model.put("dicomfile", dicomfile);
		return "show";
	}
}

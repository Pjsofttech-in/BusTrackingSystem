package com.bus.tracking.bus_tracking_system.service;

import com.cloudinary.Cloudinary;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Map;

@Service
public class QrCodeService {

    private final Cloudinary cloudinary;

    @Value("${app.base-url}")
    private String baseUrl;

    public QrCodeService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String generateAndUploadQR(Long studentId) throws Exception {

        // The QR will open student HTML page
        String qrText = baseUrl + studentId;

        // Generate QR image in memory
        BitMatrix matrix = new MultiFormatWriter()
                .encode(qrText, BarcodeFormat.QR_CODE, 300, 300);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", baos);

        byte[] imageBytes = baos.toByteArray();

        // Upload QR to Cloudinary
        Map uploadResult = cloudinary.uploader().upload(imageBytes, Map.of(
                "folder", "student_qr_codes"
        ));

        return uploadResult.get("secure_url").toString();
    }
}

package com.CrackCode.BarCodeQrCodeReadWrite.QrCode;


import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
  @TODO For This Operation below 2 maven
  Core image dependency
  <dependency>
  <groupId>com.google.zxing</groupId>
  <artifactId>core</artifactId>
  <version>3.3.0</version>
  </dependency>

  Java Client dependency.
  <dependency>
  <groupId>com.google.zxing</groupId>
  <artifactId>javase</artifactId>
  <version>3.3.0</version>
  </dependency>
 */
public class QrCodeExample {
    public static String QRCODE_PATH = "./";

    public static void main(String[] args) throws Exception {
        //For generate QR CODE
        QrCodeExample codeGenerator = new QrCodeExample();
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setUserName("Golam Kibria");
        paymentRequest.setPin("5290");
        paymentRequest.setMobileNo("01531921892");
        paymentRequest.setAccountType("Software Engineer");
        paymentRequest.setAccountNo("7645367876543");
        System.out.println(codeGenerator.writeQRCode(paymentRequest));



        //For Read the generated QR CODE
        QRCODE_PATH+="Golam Kibria-5290.png";
        System.out.println(codeGenerator.readQRCode(QRCODE_PATH));

    }

    public String writeQRCode(PaymentRequest paytmRequestBody) throws Exception {
        String qrcode = QRCODE_PATH + paytmRequestBody.getUserName() + "-"+paytmRequestBody.getPin()+".png";
        if (!isDirectoryExists(QRCODE_PATH)){
            Files.createDirectories(Paths.get(QRCODE_PATH));
        }
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = writer.encode(
                "Name: "+paytmRequestBody.getUserName() + "("+paytmRequestBody.getPin()+") "+ "\n Account: " + paytmRequestBody.getAccountNo() + "\n Account Type: "
                        + paytmRequestBody.getAccountType() + "\n Cell: " + paytmRequestBody.getMobileNo(),
                BarcodeFormat.QR_CODE, 350, 350);
        Path path = FileSystems.getDefault().getPath(qrcode);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        return "QRCODE is generated successfully....";
    }

    public String readQRCode(String qrcodeImage) throws Exception {
        BufferedImage bufferedImage = ImageIO.read(new File(qrcodeImage));
        LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
        Result result = new MultiFormatReader().decode(binaryBitmap);
        return result.getText();
    }

    public static boolean isDirectoryExists(String directoryPath) {
        if (!Paths.get(directoryPath).toFile().isDirectory()) {
            return false;
        }
        return true;
    }


}



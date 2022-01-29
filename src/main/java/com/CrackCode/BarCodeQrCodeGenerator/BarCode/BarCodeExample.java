package com.CrackCode.BarCodeQrCodeGenerator.BarCode;

import com.CrackCode.BarCodeQrCodeGenerator.QrCode.QrCodeExample;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.GenericMultipleBarcodeReader;
import com.google.zxing.multi.MultipleBarcodeReader;
import com.google.zxing.oned.Code128Writer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Hashtable;

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
public class BarCodeExample {


    public static void main(String[] args) throws Exception {
        String text = "https://github.com/MdGolam-Kibria";
        String path = "./";

        //For GenerateBarCode
        System.out.println(generateBarCode(text, path, "generatedBarCodeImage"));

        //For Read Single Bar Code Image Info
        System.out.println(readSingleBarcodeImageData(path + "generatedBarCodeImage.jpg"));

        //For Read Multiple Bar Code Image Info
        System.out.println(Arrays.toString(readMultipleBarcodeImageData(path + "multipleBarCodeImageDemo.png")));
    }

    private static String generateBarCode(String text, String path, String barCodeFileName) {
        try {
            if (!QrCodeExample.isDirectoryExists(path)) {
                Files.createDirectories(Paths.get(path));
            }
            Code128Writer writer = new Code128Writer();
            BitMatrix matrix = writer.encode(text, BarcodeFormat.CODE_128, 400, 100);
            MatrixToImageWriter.writeToPath(matrix, "jpg", FileSystems.getDefault().getPath(path + barCodeFileName + ".jpg"));
            return "Barcode created....";
        } catch (Exception e) {
            return "Error while creating barcode ???????????";
        }
    }

    private static String readSingleBarcodeImageData(String singleImagePath) throws NotFoundException, IOException {
        BufferedImage img = ImageIO.read(new File(singleImagePath));
        BinaryBitmap bb = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(img)));
        MultipleBarcodeReader mbReader = new GenericMultipleBarcodeReader(new MultiFormatReader());
        Hashtable<DecodeHintType, Object> hints = new Hashtable<>();
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        /*List<BarcodeInfo> list = new ArrayList<>();//if have any custom data then convert to dto like this
        for (Result result : mbReader.decodeMultiple(bb, hints)) {
            list.add(new BarcodeInfo(result.getText(), result.getBarcodeFormat().name()));
        }
        return list;*/
        Result[] currentBarCodeResult = mbReader.decodeMultiple(bb, hints);
        return currentBarCodeResult[0].getText();
    }

    private static Result[] readMultipleBarcodeImageData(String multipleImagePath /* if have multiple barcode in an image then*/) throws NotFoundException, IOException {//
        BufferedImage img = ImageIO.read(new File(multipleImagePath));
        BinaryBitmap bb = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(img)));
        MultipleBarcodeReader mbReader = new GenericMultipleBarcodeReader(new MultiFormatReader());
        Hashtable<DecodeHintType, Object> hints = new Hashtable<>();
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        /*List<BarcodeInfo> list = new ArrayList<>();//if have any custom data then convert to dto like this
        for (Result result : mbReader.decodeMultiple(bb, hints)) {
            list.add(new BarcodeInfo(result.getText(), result.getBarcodeFormat().name()));
        }
        return list;*/
        Result[] currentBarCodeResult = mbReader.decodeMultiple(bb, hints);//every result represent a bar code
        return currentBarCodeResult;
    }


}

package com.qudan.qingcloud.msqudan.util;

import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;

import com.google.zxing.common.BitMatrix;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.qudan.qingcloud.msqudan.config.CommonConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 二维码的生成需要借助MatrixToImageWriter类，该类是由Google提供的，可以将该类直接拷贝到源码中使用
 */
public class MatrixToImageWriter {
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    protected static final Log logger = LogFactory.getLog(MatrixToImageWriter.class);

    private MatrixToImageWriter() {
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    public static void writeToFile(BitMatrix matrix, String format, File file)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format "
                    + format + " to " + file);
        }
    }

    public static void writeToStream(BitMatrix matrix, String format,
                                     OutputStream stream) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }

    public static String getWeixinTx(CommonConfig config, String imgURL){
        byte[] data = ImageUtils.GetImageDataStrFromUrl(imgURL);
        String imgKey =  new UploadToQiniu(config, "qudan", "img", "images/users/face/", RandomUtils.generateMixString(12), data).upload();
        return ComUtils.addPrefixToImg(imgKey, config.getQiniuImageUrl());
    }

    public static String getQrcodeUrl(CommonConfig config, String url){
        String text = url; // 二维码内容
        int width = 200; // 二维码图片宽度
        int height = 200; // 二维码图片高度
        String format = "jpg";// 二维码的图片格式
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(text,
                    BarcodeFormat.QR_CODE, width, height, hints);
            BufferedImage image = toBufferedImage(bitMatrix);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( image, "jpg", baos );
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            String imgKey =  new UploadToQiniu(config, "qudan", "img", "images/users/share/", RandomUtils.generateMixString(12), imageInByte).upload();
            return ComUtils.addPrefixToImg(imgKey, config.getQiniuImageUrl());
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("获取二维码失败", ex);
            logger.info("获取二维码失败");
        }
        return null;
    }
}
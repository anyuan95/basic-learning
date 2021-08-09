package org.example.basic.foo.image.gif;

import com.madgag.gif.fmsware.AnimatedGifEncoder;
import com.madgag.gif.fmsware.GifDecoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-08-09 17:27
 */
public class GifUtils {

    public static void main(String[] args) throws IOException {
//        String outputPath = "F:\\Temp\\god_eht_semoc_erehw.gif";
//        String imagePath = "F:\\Temp\\where_comes_the_dog.gif";
        String outputPath = "F:\\Temp\\nogeip.gif";
        String imagePath = "F:\\Temp\\piegon.gif";
        reverseGif(imagePath, outputPath);
//        // Gif转图片
//        String dirPath = "/home/lab/test/22/";
//        gifToImages(imagePath, dirPath);
//        List<BufferedImage> images = new ArrayList<>();
//        for (int i = 0; i < 111; i++) {
//            File outFile = new File(dirPath + i + ".png");
//            BufferedImage image = ImageIO.read(outFile);
//            images.add(image);
//        }
//        imagesToGif(images, "/home/lab/test/res.gif");
    }

    /**
     * 多图片转gif
     *
     * @param imageList
     * @param outputPath
     * @throws IOException
     */
    static void imagesToGif(List<BufferedImage> imageList, String outputPath) throws IOException {
        // 拆分一帧一帧的压缩之后合成
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        encoder.start(outputPath);
        encoder.setRepeat(0);
        for (BufferedImage bufferedImage :
                imageList) {
            encoder.setDelay(100);
            int height = bufferedImage.getHeight();
            int width = bufferedImage.getWidth();
            BufferedImage zoomImage = new BufferedImage(width, height, 3);
            Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            Graphics gc = zoomImage.getGraphics();
            gc.setColor(Color.WHITE);
            gc.drawImage(image, 0, 0, null);
            encoder.addFrame(zoomImage);
        }
        encoder.finish();
        File outFile = new File(outputPath);
        BufferedImage image = ImageIO.read(outFile);
        ImageIO.write(image, outFile.getName(), outFile);
    }

    /**
     * Gif转图片集
     *
     * @param imagePath
     * @param outputDirPath
     * @throws IOException
     */
    static void gifToImages(String imagePath, String outputDirPath) throws IOException {
        GifDecoder decoder = new GifDecoder();
        int status = decoder.read(imagePath);
        if (status != GifDecoder.STATUS_OK) {
            throw new IOException("read image " + imagePath + " error!");
        }
        for (int i = 0; i < decoder.getFrameCount(); i++) {
            BufferedImage bufferedImage = decoder.getFrame(i);// 获取每帧BufferedImage流
            File outFile = new File(outputDirPath + i + ".png");
            ImageIO.write(bufferedImage, "png", outFile);
        }
    }

    /**
     * 视频倒放
     *
     * @param imagePath
     * @param outputPath
     * @throws IOException
     */
    public static void reverseGif(String imagePath, String outputPath) throws IOException {
        GifDecoder decoder = new GifDecoder();
        int status = decoder.read(imagePath);
        if (status != GifDecoder.STATUS_OK) {
            throw new IOException("read image " + imagePath + " error!");
        }
        // 拆分一帧一帧的压缩之后合成
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        encoder.start(outputPath);
        encoder.setRepeat(decoder.getLoopCount());
        for (int i = decoder.getFrameCount() - 1; i >= 0; i--) {
            encoder.setDelay(decoder.getDelay(i));// 设置播放延迟时间
            BufferedImage bufferedImage = decoder.getFrame(i);// 获取每帧BufferedImage流
            int height = bufferedImage.getHeight();
            int width = bufferedImage.getWidth();
            BufferedImage zoomImage = new BufferedImage(width, height, bufferedImage.getType());
            Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            Graphics gc = zoomImage.getGraphics();
            gc.setColor(Color.WHITE);
            gc.drawImage(image, 0, 0, null);
            encoder.addFrame(zoomImage);
        }
        encoder.finish();
        File outFile = new File(outputPath);
        BufferedImage image = ImageIO.read(outFile);
        ImageIO.write(image, outFile.getName(), outFile);
    }
}

package com.lcz.util;

import com.lcz.constant.ConstString;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.*;
import java.text.NumberFormat;
import java.util.*;

/**
 * @author qinzx
 * @date 2020/10/14 14:38
 */
public class VerifyImageUtil {
    /**
     * 源文件宽度
     */
    private static final int ORI_WIDTH = 590;
    /**
     * 源文件高度
     */
    private static final int ORI_HEIGHT = 360;
    /**
     * 抠图坐标x
     */
    private static int X;
    /**
     * 抠图坐标y
     */
    private static int Y;
    /**
     * 模板图宽度
     */
    private static int WIDTH;
    /**
     * 模板图高度
     */
    private static int HEIGHT;

    public static int getX() {
        return X;
    }

    public static int getY() {
        return Y;
    }

    /**
     * 根据模板切图
     *
     * @param templateFile 模板文件
     * @param targetFile   目标文件
     * @param templateType 模板文件类型
     * @param targetType   目标文件类型
     * @return 切图map集合
     * @throws Exception 异常
     */
    public static Map<String, String> pictureTemplatesCut(File templateFile, File targetFile, String templateType, String targetType) throws IOException {

        if (StringUtils.isEmpty(templateType) || StringUtils.isEmpty(targetType)) {
            throw new RuntimeException("file type is empty");
        }
        InputStream targetIs = new FileInputStream(targetFile);
        // 模板图
        BufferedImage imageTemplate = ImageIO.read(templateFile);
        WIDTH = imageTemplate.getWidth();
        HEIGHT = imageTemplate.getHeight();
        // 随机生成抠图坐标
        generateCutoutCoordinates();
        // 最终图像
        String blockImg = getBlockImgStr(targetType, targetIs, imageTemplate);
        // 源图生成遮罩
        String oriCopyImages = dealOriPictureByTemplate(ImageIO.read(targetFile), imageTemplate, X, Y);
        Map<String, String> pictureMap = new HashMap<>(2);
        pictureMap.put("newImage", blockImg);
        pictureMap.put("oriCopyImage", oriCopyImages);
        return pictureMap;
    }

    private static String getBlockImgStr(String targetType, InputStream targetIs, BufferedImage imageTemplate) throws IOException {
        BufferedImage newImage = new BufferedImage(WIDTH, HEIGHT, imageTemplate.getType());
        Graphics2D graphics = newImage.createGraphics();
        graphics.setBackground(Color.white);

        // 获取感兴趣的目标区域
        BufferedImage targetImageNoDeal = getTargetArea(X, Y, WIDTH, HEIGHT, targetIs, targetType);
        // 根据模板图片抠图
        dealCutPictureByTemplate(targetImageNoDeal, imageTemplate, newImage);

        // 设置“抗锯齿”的属性
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
        graphics.drawImage(newImage, 0, 0, null);
        graphics.dispose();
        //新建流
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //利用ImageIO类提供的write方法，将png图片的数据写入流。
        ImageIO.write(newImage, ConstString.IMAGE_TYPE_PNG, os);
        return Base64.getEncoder().encodeToString(os.toByteArray());
    }

    /**
     * 修改图片尺寸
     *
     * @param source
     * @param targetW 目标宽度
     * @param targetH 目标高度
     * @return
     */
    private static BufferedImage resizeBufferedImage(BufferedImage source, int targetW, int targetH) {
        int type = source.getType();
        BufferedImage target = null;
        double sx = (double) targetW / source.getWidth();
        double sy = (double) targetH / source.getHeight();
        if (type == BufferedImage.TYPE_CUSTOM) {
            // handmade
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        } else {
            target = new BufferedImage(targetW, targetH, type);
        }
        Graphics2D g = target.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        g.dispose();
        return target;
    }

    /**
     * 抠图后原图生成
     *
     * @param oriImage      原始图片
     * @param templateImage 模板图片
     * @param x             坐标X
     * @param y             坐标Y
     * @return 添加遮罩层后的原始图片
     * @throws Exception 异常
     */
    private static String dealOriPictureByTemplate(BufferedImage oriImage, BufferedImage templateImage, int x, int y) throws IOException {
        // 源文件备份图像矩阵 支持alpha通道的rgb图像
        BufferedImage oriCopyImage = new BufferedImage(oriImage.getWidth(), oriImage.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        // 源文件图像矩阵
        int[][] oriImageData = getData(oriImage);
        // 模板图像矩阵
        int[][] templateImageData = getData(templateImage);

        //copy 源图做不透明处理
        for (int i = 0; i < oriImageData.length; i++) {
            for (int j = 0; j < oriImageData[0].length; j++) {
                int rgb = oriImage.getRGB(i, j);
                int r = (0xff & rgb);
                int g = (0xff & (rgb >> 8));
                int b = (0xff & (rgb >> 16));
                //无透明处理
                rgb = r + (g << 8) + (b << 16) + (255 << 24);
                oriCopyImage.setRGB(i, j, rgb);
            }
        }

        for (int i = 0; i < templateImageData.length; i++) {
            for (int j = 0; j < templateImageData[0].length - 5; j++) {
                int rgb = templateImage.getRGB(i, j);
                //对源文件备份图像(x+i,y+j)坐标点进行透明处理
                if (rgb <= 0) {
                    int rgb_ori = oriCopyImage.getRGB(x + i, y + j);
                    int r = (0xff & rgb_ori);
                    int g = (0xff & (rgb_ori >> 8));
                    int b = (0xff & (rgb_ori >> 16));
                    rgb_ori = r + (g << 8) + (b << 16) + (150 << 24);
                    oriCopyImage.setRGB(x + i, y + j, rgb_ori);
                }
            }
        }
        //新建流
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //利用ImageIO类提供的write方法，将bi以png图片的数据模式写入流
        ImageIO.write(oriCopyImage, "png", os);
        //从流中获取数据数组
        return Base64.getEncoder().encodeToString(os.toByteArray());
    }


    /**
     * 根据模板图片抠图
     *
     * @param oriImage      原始图片
     * @param templateImage 模板图片
     * @return 扣了图片之后的原始图片
     */
    private static void dealCutPictureByTemplate(BufferedImage oriImage, BufferedImage templateImage, BufferedImage targetImage) {
        // 源文件图像矩阵
        int[][] oriImageData = getData(oriImage);
        // 模板图像矩阵
        int[][] templateImageData = getData(templateImage);
        // 模板图像宽度

        for (int i = 0; i < templateImageData.length; i++) {
            // 模板图片高度
            for (int j = 0; j < templateImageData[0].length; j++) {
                // 如果模板图像当前像素点不是白色 copy源文件信息到目标图片中
                int rgb = templateImageData[i][j];
                if (rgb <= 0) {
                    targetImage.setRGB(i, j, oriImageData[i][j]);
                }
            }
        }
    }

    /**
     * 获取目标区域
     *
     * @param x            随机切图坐标x轴位置
     * @param y            随机切图坐标y轴位置
     * @param targetWidth  切图后目标宽度
     * @param targetHeight 切图后目标高度
     * @param ois          源文件输入流
     * @return 返回目标区域
     * @throws Exception 异常
     */
    private static BufferedImage getTargetArea(int x, int y, int targetWidth, int targetHeight, InputStream ois,
                                               String fileType) throws IOException {
        Iterator<ImageReader> imageReaderList = ImageIO.getImageReadersByFormatName(fileType);
        ImageReader imageReader = imageReaderList.next();
        // 获取图片流
        ImageInputStream iis = ImageIO.createImageInputStream(ois);
        // 输入源中的图像将只按顺序读取
        imageReader.setInput(iis, true);

        ImageReadParam param = imageReader.getDefaultReadParam();
        Rectangle rec = new Rectangle(x, y, targetWidth, targetHeight);
        param.setSourceRegion(rec);
        return imageReader.read(0, param);
    }

    /**
     * 生成图像矩阵
     *
     * @param bufferedImage 图片流
     * @return 图像矩阵
     */
    private static int[][] getData(BufferedImage bufferedImage) {
        int[][] data = new int[bufferedImage.getWidth()][bufferedImage.getHeight()];
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                data[i][j] = bufferedImage.getRGB(i, j);
            }
        }
        return data;
    }

    /**
     * 随机生成抠图坐标
     */
    private static void generateCutoutCoordinates() {
        Random random = new Random();
        // ORI_WIDTH：590  ORI_HEIGHT：360
        // WIDTH：93 HEIGHT：360
        int widthDifference = ORI_WIDTH - WIDTH;
        int heightDifference = ORI_HEIGHT - HEIGHT;

        if (widthDifference <= 0) {
            X = 5;
        } else {
            X = random.nextInt(ORI_WIDTH - 3 * WIDTH) + 2 * WIDTH + 5;
        }

        if (heightDifference <= 0) {
            Y = 5;
        } else {
            Y = random.nextInt(ORI_HEIGHT - HEIGHT) + 5;
        }

        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
    }
}

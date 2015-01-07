package utils;

import net.sourceforge.htmlunit.corejs.javascript.commonjs.module.provider.UrlConnectionExpiryCalculator;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URISyntaxException;
import java.net.URLConnection;

/**
 * 文件名称: MakeContentImage.java
 * 编写人: yh.zeng
 * 编写时间: 12-1-8
 * 文件描述: todo
 */
public class MakeContentImage
{

    private BufferedImage image = null;


    /**
     * 制作图片
     * @param content 文字内容
     * @param file    背景图片
     * @param x       文字相对于图片的左上角X坐标轴的位置，默认200
     * @param y       文字相对于图片的左上角Y坐标轴的位置，默认200
     * @throws IOException
     * @throws URISyntaxException
     */
    public MakeContentImage(String content, File file,Integer x, Integer y) throws IOException, URISyntaxException {
        image = ImageIO.read( file );
        if(content != null){

            x = x == null ? 200 : x;
            y = y == null ? 200 : y;

            Graphics g = image.getGraphics();
            g.setColor(Color.black);
            g.setFont(new Font("宋体",Font.PLAIN,22));
            g.drawString(content, x, y);
        }
    }


    /**
     * 制作图片
     * @param content   文字内容
     * @param url       背景图片URL
     * @param x         文字相对于图片左上角的X坐标轴的位置，默认200
     * @param y         文字相对于图片左上角的Y坐标轴的位置，默认200
     * @throws IOException
     * @throws URISyntaxException
     */
    public MakeContentImage(String content, URL url, Integer x, Integer y) throws IOException, URISyntaxException {
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        image = ImageIO.read( urlConnection.getInputStream() );
        if(content != null){
            x = x == null ? 200 : x;
            y = y == null ? 200 : y;

            Graphics g = image.getGraphics();
            g.setColor(Color.black);
            g.setFont(new Font("宋体",Font.PLAIN,22));
            g.drawString(content, x, y);
        }
    }


    /**
     * 制作图片
     * @param content       文字内容
     * @param width         图片的宽度
     * @param heigth        图片的高度
     * @param x             文字相对于图片左上角的X坐标轴的位置，默认200
     * @param y             文字相对于图片左上角的Y坐标轴的位置，默认200
     * @throws IOException
     */
    public MakeContentImage(String content,int width,int heigth, Integer x, Integer y) throws IOException {
        image = new BufferedImage(width, heigth,BufferedImage.TYPE_INT_ARGB);
        if(content != null){
            x = x == null ? 200 : x;
            y = y == null ? 200 : y;

            Graphics g = image.getGraphics();
            g.setColor(Color.black);
            g.setFont(new Font("宋体",Font.PLAIN,22));
            g.drawString(content, x, y);

        }
    }

    public void write(OutputStream os ) throws IOException {
        ImageIO.write(image, "JPEG", os);
        os.flush();
        os.close();
    }

}

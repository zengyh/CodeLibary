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
     * @throws IOException
     * @throws URISyntaxException
     */
    public MakeContentImage(String content, File file) throws IOException, URISyntaxException {
        image = ImageIO.read( file );
        Graphics g = image.getGraphics();
        g.setColor(Color.black);
        g.setFont(new Font("宋体",Font.PLAIN,22));
        g.drawString(content, 200, 200);
    }


    /**
     * 制作图片
     * @param content   文字内容
     * @param url       背景图片URL
     * @throws IOException
     * @throws URISyntaxException
     */
    public MakeContentImage(String content, URL url) throws IOException, URISyntaxException {
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        image = ImageIO.read( urlConnection.getInputStream() );
        Graphics g = image.getGraphics();
        g.setColor(Color.black);
        g.setFont(new Font("宋体",Font.PLAIN,22));
        g.drawString(content, 200, 200);
    }


    /**
     * 制作图片
     * @param content       文字内容
     * @param width         宽度
     * @param heigth        高度
     * @throws IOException
     */
    public MakeContentImage(String content,int width,int heigth) throws IOException {
        image = new BufferedImage(width, heigth,BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.black);
        g.setFont(new Font("宋体",Font.PLAIN,22));
        g.drawString(content, 200, 200);
    }

    public void write(OutputStream os ) throws IOException {
        ImageIO.write(image, "JPEG", os);
        os.flush();
        os.close();
    }

}

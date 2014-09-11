package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 文件名称: MakeContentImage.java
 * 编写人: yh.zeng
 * 编写时间: 12-1-8
 * 文件描述: todo
 */
public class MakeContentImage
{

    public MakeContentImage(String content, OutputStream os) throws IOException {
        File file = new File("E:/图片/background.jpg");
        BufferedImage image = ImageIO.read( file );
        Graphics g = image.getGraphics();
        g.setColor(Color.black);
        g.setFont(new Font("宋体",Font.PLAIN,22));
        g.drawString(content, 200, 200);
        ImageIO.write(image, "JPEG", os);
        os.flush();
        os.close();
    }

}

package de.toor.gangshit.objects;

import de.toor.gangshit.utils.AvatarHandler;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JoinLeaveImage {

    private Member member;
    private Message msg;
    private Graphics2D g2d;


    Font customFont;

    {
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font.otf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            customFont = customFont.deriveFont(Font.BOLD, 56);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


    private Font f = new Font("TimesRoman", Font.BOLD+Font.ITALIC, 48);
    private Rectangle rectangle = new Rectangle(1600, 900);

    private BufferedImage avatar;
    private File file;
    private BufferedImage background;

    public JoinLeaveImage(Member member, String message) {
        this.member = member;
        this.avatar = AvatarHandler.getAvatar(member);
        try {
            background = toBufferedImage(ImageIO.read(new File("background.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2d = background.createGraphics();
        createImage(avatar, rectangle);
        createString(g2d, message, rectangle, customFont);
        createFile();
    }

    public void createString(Graphics g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = (rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent()) + 200;
        g.setFont(font);
        g.drawString(text, x, y);
    }

    private void createImage(BufferedImage image, Rectangle rectangle) {
        int x = (background.getWidth() - image.getWidth(null)) / 2;
        int y = (background.getHeight() - image.getHeight(null)) / 2;
        g2d.drawImage(image, x, y-100, null);
    }

    private BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }

    private void createFile() {
        g2d.dispose();
        file = new File(member.getId() + ".png");
        try {
            ImageIO.write(background, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File toFile() {
        return file;
    }

    public void deleteFile() {
        file.delete();
    }
}

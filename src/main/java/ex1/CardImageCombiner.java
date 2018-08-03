package ex1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

class CardImageCombiner {
	private final byte[] backgroundImage;
	private final String overlayImageUrl;

	CardImageCombiner(byte[] backgroundImage, String overlayImageUrl) {
		this.backgroundImage = backgroundImage;
		this.overlayImageUrl = overlayImageUrl;
	}

	byte[] combine() throws IOException {
		InputStream background = new ByteArrayInputStream(backgroundImage);
		InputStream overlay = getOverlayByteStream();
		BufferedImage image = addImageOverlay(background, overlay);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "png", baos);
		overlay.close();
		background.close();
		final byte[] result = baos.toByteArray();
		baos.close();
		return result;
	}

	protected InputStream getOverlayByteStream() throws IOException {
		return new URL(this.overlayImageUrl).openStream();
	}

	private BufferedImage addImageOverlay(InputStream backgroundFile, InputStream overlayFile) throws IOException {
		BufferedImage backgroundImage = ImageIO.read(backgroundFile);
		BufferedImage overlayImage = ImageIO.read(overlayFile);

		int w = Math.max(backgroundImage.getWidth(), overlayImage.getWidth());
		int h = Math.max(backgroundImage.getHeight(), overlayImage.getHeight());
		BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		Graphics2D graphics = combined.createGraphics();
		graphics.setComposite(AlphaComposite.Src);
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setColor(Color.WHITE);
		graphics.fill(new RoundRectangle2D.Float(0, 0, w, h, 35, 35));
		graphics.setComposite(AlphaComposite.SrcAtop);

		graphics.drawImage(backgroundImage, 0, 0, w, h, null);
		graphics.drawImage(overlayImage, 0, 0, w, h, null);
		return combined;
	}
}

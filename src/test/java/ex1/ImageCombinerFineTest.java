package ex1;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.Mockito.doReturn;

public class ImageCombinerFineTest {
	@Test
	public void test() throws Exception {
		URI backgroundImage = getClass().getResource("/backgroundImage.jpg").toURI();
		Path backgroundPath = Paths.get(backgroundImage);


		URI overlayImage = getClass().getResource("/cc_overlay.png").toURI();
		CardImageCombiner cardImageCombiner = new CardImageCombiner(Files.readAllBytes(backgroundPath), "");
		CardImageCombiner spyCombiner = Mockito.spy(cardImageCombiner);
		doReturn(new FileInputStream(new File(overlayImage))).when(spyCombiner).getOverlayByteStream();

		byte[] result = spyCombiner.combine();

		Assert.assertNotNull(result);

		URI resultImage = getClass().getResource("/result.png").toURI();
		byte[] expectedImageBytes = Files.readAllBytes(Paths.get(resultImage));
		Assert.assertArrayEquals(expectedImageBytes, result);
	}

}

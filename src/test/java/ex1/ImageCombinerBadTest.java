package ex1;

import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageCombinerBadTest {
	@Test
	public void test() throws Exception {
		URI backgroundImage = getClass().getResource("/backgroundImage.jpg").toURI();
		Path backgroundPath = Paths.get(backgroundImage);

		byte[] result = new CardImageCombiner(Files.readAllBytes(backgroundPath), "http://s3.amazonaws.com/bh-network/overlays/cc_overlay.png").combine();

		Assert.assertNotNull(result);

		URI resultImage = getClass().getResource("/result.png").toURI();
		byte[] expectedImageBytes = Files.readAllBytes(Paths.get(resultImage));
		Assert.assertArrayEquals(expectedImageBytes, result);
	}
}


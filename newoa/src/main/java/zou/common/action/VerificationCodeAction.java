package zou.common.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import zou.common.shiro.TokenManager;
import zou.common.until.CaptchaServiceSingleton;
@Controller
@RequestMapping("/code")
public class VerificationCodeAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GetMapping
	public ResponseEntity<byte[]> getCode(HttpServletRequest req) throws IOException
	{
		byte[] imgae = this.toImgae(req);
		BodyBuilder builder = ResponseEntity.ok();
		builder.header("Context-Type", "image/jpg");
		builder.contentLength(imgae.length);
		ResponseEntity<byte[]> body = builder.body(imgae);
		return body;
		
	}
	
	
	

	
	
	private byte[] toImgae(HttpServletRequest req) throws IOException {
		// 获取验证码背景图片的路径，这路径放了很多作为背景的图像
		 String captcha_backgrounds =
		 req.getSession().getServletContext().getRealPath("/image/captcha/");
		System.out.println(captcha_backgrounds);
		String id = TokenManager.getSessionId();
		System.out.println("图片验证码的id"+id);
		
		CaptchaServiceSingleton.getInstance().removeCaptcha(id);
		
		BufferedImage image = (BufferedImage) CaptchaServiceSingleton.getInstance().getChallengeForID(id, req.getLocale());
		
	
		// 获取验证码图片，这是未压缩的位图
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		// 应缩成jpg并写到输出流中
		ImageIO.write(image, "jpg", bao);

		return bao.toByteArray();

	}

	
}

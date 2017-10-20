package org.zjl.bolg.common.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zjl.bolg.common.until.CaptchaServiceSingleton;

/**
 * @ClassName: VerificationCodeAction
 * @Description: 生成验证码的Action
 * @author zou
 * @date 2017年10月15日 下午1:17:06
 * 
 */
@Controller
@RequestMapping("/code")
public class VerificationCodeAction {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<byte[]> getCode(HttpServletRequest req,Model model) throws IOException {
		//拿到图片
		byte[] imgae = toImgae(req,model);
		//设置200
		BodyBuilder bodyBuilder = ResponseEntity.ok();
		//设置响应头，跟类型
		bodyBuilder.header("Context-Type", "image/jpg");
		//设置内容的长度
		bodyBuilder.contentLength(imgae.length);
		//设置内容的内容
		ResponseEntity<byte[]> body = bodyBuilder.body(imgae);
		return body;
	}

	private byte[] toImgae(HttpServletRequest req,Model model) throws IOException {
		// 获取验证码背景图片的路径，这路径放了很多作为背景的图像
		 String captcha_backgrounds =
		 req.getSession().getServletContext().getRealPath("/image/captcha/");
		System.out.println(captcha_backgrounds);
		String id = req.getSession().getId();
	
		
		BufferedImage image = CaptchaServiceSingleton.getInstance().getImageChallengeForID(id,req.getLocale());
		
		
		// 获取验证码图片，这是未压缩的位图
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		// 应缩成jpg并写到输出流中
		ImageIO.write(image, "jpg", bao);

		return bao.toByteArray();

	}
}

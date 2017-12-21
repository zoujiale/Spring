package zou.common.until;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

/**   
* @Title: CaptchaServiceSingleton.java 
* @Package org.zjl.bolg.common.until 
* @Description:获取验证码图片
* @author 704572528@qq.com 
* @date 2017年10月18日 上午12:41:18 
* @version V1.0   
*/
public class CaptchaServiceSingleton extends DefaultManageableImageCaptchaService  {
	
	 // 不允许构造实例  
    public CaptchaServiceSingleton(FastHashMapCaptchaStore fastHashMapCaptchaStore, ImageCaptchaEngineExtend imageCaptchaEngineExtend, int i, int j, int k) {  
    	super(fastHashMapCaptchaStore, imageCaptchaEngineExtend, i, j, k);
    }  
    public CaptchaServiceSingleton() {};
    private static CaptchaServiceSingleton captchaservicesingleton = null;
    static {
    	captchaservicesingleton =new CaptchaServiceSingleton(  
    			new FastHashMapCaptchaStore(), new ImageCaptchaEngineExtend(),  
                180, 100000, 75000);  
    }
   
    public static CaptchaServiceSingleton getInstance() {  
    	if (captchaservicesingleton !=null) {
    	 	return captchaservicesingleton;
		}
		return null;
   
       
    }
    @Override
    public Object getChallengeForID(String ID) throws CaptchaServiceException {
    	// TODO Auto-generated method stub
    	return super.getChallengeForID(ID);
    }
    @Override
    public Boolean validateResponseForID(String ID, Object response) throws CaptchaServiceException {
    	if (!this.store.hasCaptcha(ID)) {
            throw new CaptchaServiceException(
                    "Invalid ID, could not validate unexisting or already validated captcha");
        }
        Boolean valid = this.store.getCaptcha(ID).validateResponse(response);
        //源码的这一句是没被注释的，这里我们注释掉，在下面暴露一个方法给我们自己来移除sessionId
        //this.store.removeCaptcha(ID);
        return valid;
    }
    
    public  void removeCaptcha(String sessionId){  
        if(sessionId!=null && this.store.hasCaptcha(sessionId)){  
            this.store.removeCaptcha(sessionId);  
        }  
    }  

    
}

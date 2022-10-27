package com.lcz;

import com.lcz.constant.ConstString;
import com.lcz.util.VerifyImageUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RestController
public class ElasticSearchHouseApplication {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchHouseApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello,ls";
    }

    @RequestMapping("createImgValidate")
    public Map createImgValidate(HttpServletRequest request) {
        int templateNum = new Random().nextInt(4) + 1;
        int targetNum = new Random().nextInt(20) + 1;
		try {
			File templateFile = ResourceUtils.getFile("classpath:static/images/validate/template/" + templateNum + ".png");
			File targetFile = ResourceUtils.getFile("classpath:static/images/validate/target/" + targetNum + ".jpg");
			Map<String, String> pictureMap = VerifyImageUtil.pictureTemplatesCut(templateFile, targetFile,
					ConstString.IMAGE_TYPE_PNG, ConstString.IMAGE_TYPE_JPG);
			// 将生成的偏移位置信息设置到redis中
			String key = ConstString.WEB_VALID_IMAGE_PREFIX + request.getSession().getId();
			boolean verified = stringRedisTemplate.hasKey(key);
			if (verified) {
				stringRedisTemplate.delete(key);
			}
			stringRedisTemplate.opsForValue().set(key, (VerifyImageUtil.getX() + 67) + "", 3, TimeUnit.MINUTES);
			return pictureMap;
		} catch (IOException e) {
			return null;
		}
	}
}

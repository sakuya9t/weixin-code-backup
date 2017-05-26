package util;

import java.util.Date;
import java.util.Random;

import bean.QiuqianBean;

public class QiuqianUtil {
	public static String Qiuqian(){
		Date d = new Date();
		Random random = new Random(d.getTime());
		int i = random.nextInt(100);
		//System.out.println("Qianid:" + i);
		String qian = QiuqianBean.pick(i);
		
		return qian;
	}
	public static String Qiuqian(String context){
		int sum = TextUtil.SumString(context);
		//System.out.println(sum);
		
		Date d = new Date();
		Random random = new Random(d.getTime() * sum);
		int i = random.nextInt(100);
		String qian = QiuqianBean.pick(i);
		
		return qian;
	}
}

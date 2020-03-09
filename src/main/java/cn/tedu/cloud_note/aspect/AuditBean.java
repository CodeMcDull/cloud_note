package cn.tedu.cloud_note.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuditBean {
	@Around("within(cn.tedu.cloud_note.service..*)")
	public Object audit(ProceedingJoinPoint point) throws Throwable{
		Object obj=null;
		
		try {
			long timeStart=System.currentTimeMillis();
			obj=point.proceed();
			long timeEnd=System.currentTimeMillis();
			String str=point.getSignature().toString();
			System.out.println(str+"ºÄÊ±"+(timeEnd-timeStart));
		} catch (Throwable e) {
			e.printStackTrace();
			throw e;
		}
		return obj;
	}
}

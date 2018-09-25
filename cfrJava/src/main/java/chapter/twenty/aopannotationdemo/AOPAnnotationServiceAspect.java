package chapter.twenty.aopannotationdemo;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

import chapter.twenty.aopdeepdemo.AOPDeepVO;

@Service
@Aspect
public class AOPAnnotationServiceAspect {
	@Before(value="execution(* chapter.twenty..*.*(..))")
	public void serviceBefore() {
		System.out.println("[��־����]");
	}
	
	@Before(value="execution(* chapter.twenty..*.*(..)) and args(param)", argNames="param")
	public void beforeParaIntercept(Object param) {
		System.out.println("�������أ�" + param);
	}
	
	@After(value="execution(* chapter.twenty..*.*(..))")
	public void serviceAfter() {
		System.out.println("[������]");
	}
	
	@AfterReturning(value="execution(* chapter.twenty..*.*(..)) and args(param)", returning="ret", argNames="ret")
	public void afterReturningResultIntercept(Object ret) {
		System.out.println("���ؽ�����أ�" + ret);
	}
	
	@AfterThrowing(value="execution(* chapter.twenty..*.*(..)) and args(param)", throwing="e", argNames="e")
	public void afterThrowingExceptionIntercept(Exception e) {
		System.out.println("�쳣���أ�" + e);
	}
	
	@Around(value="execution(* chapter.twenty..*.*(..)) and args(param)")
	public Object around(ProceedingJoinPoint p) throws Throwable {
		System.out.println("����֪ͨ�������в������������أ�" + Arrays.toString(p.getArgs()));
		AOPDeepVO vo = new AOPDeepVO();
		vo.setMid("222");
		vo.setName("���ڻ�������ʱ�޸Ĳ���");
		Object retVal = p.proceed(new Object[] {vo});
		System.out.println("���ؽ�����أ�" + retVal);
		return false; // ����֪ͨ���޸ķ��ؽ��
	}
}

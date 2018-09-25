package chapter.twenty.aopdeepdemo;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Service;

@Service
public class AOPDeepServiceAspect {
	public void beforeParaIntercept(Object arg) {
		System.out.println("�������أ�" + arg);
	}
	public void afterReturningResultIntercept(Object ret) {
		System.out.println("���ؽ�����أ�" + ret);
	}
	public void afterThrowingExceptionIntercept(Exception e) {
		System.out.println("�쳣���أ�" + e);
	}
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

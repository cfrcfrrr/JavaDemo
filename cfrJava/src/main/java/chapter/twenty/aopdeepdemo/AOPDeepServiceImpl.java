package chapter.twenty.aopdeepdemo;

import org.springframework.stereotype.Service;

@Service
public class AOPDeepServiceImpl implements AOPDeepIService {
	@Override
	public boolean insert(AOPDeepVO vo) {
//		throw new NullPointerException("�ֶ��׸��쳣");
		System.out.println("[���Ĳ���]AOPDeepVO��" + vo);
		return true;
	}
}

package chapter.twenty.aopdemo;

import org.springframework.stereotype.Service;

/**
 * ҵ���ֻ��ע���Ĺ��ܣ������Թ���ͨ���������
 * @author Administrator
 *
 */
@Service
public class AOPServiceImpl implements AOPIService {
	@Override
	public boolean insert(AOPVO vo) {
		System.out.println("[���Ĳ���]AOPMember" + vo);
		return true;
	}
}

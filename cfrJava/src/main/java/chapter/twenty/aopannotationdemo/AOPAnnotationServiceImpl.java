package chapter.twenty.aopannotationdemo;

public class AOPAnnotationServiceImpl implements AOPAnnotationIService {

	@Override
	public boolean insert(AOPAnnotationVO vo) {
//		throw new NullPointerException("�ֶ��׸��쳣");
		System.out.println("[���Ĳ���]AOPAnnotationVO��" + vo);
		return true;
	}

}

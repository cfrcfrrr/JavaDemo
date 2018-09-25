package chapter.twenty.annotationdemo;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


@Service
public class AnnotationServiceImpl implements AnnotationIService {
	@Resource // ��ʾ��Ϊע�����Դ���൱��xml�ļ��ж���property����
	private AnnotationIDAO DAO;
//	����Ҫ�ٶ���setter����������@Resource����
//	public void setDAO(AnnotationIDAO DAO) {
//		this.DAO = DAO;
//	}
	@Override
	public void print() {
		this.DAO.print();
	}

}
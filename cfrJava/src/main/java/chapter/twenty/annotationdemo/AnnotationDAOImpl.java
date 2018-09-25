package chapter.twenty.annotationdemo;

import org.springframework.stereotype.Component;

@Component // ��ע���൱����xml�ļ��ж�����bean����ʱ���ʴ����������ΪannotationAdminDAOImpl��
public class AnnotationDAOImpl implements AnnotationIDAO {
	@Override
	public void print() {
		System.out.println("success");
	}
}

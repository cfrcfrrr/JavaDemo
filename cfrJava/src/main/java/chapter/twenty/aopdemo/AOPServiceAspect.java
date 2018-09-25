package chapter.twenty.aopdemo;

import org.springframework.stereotype.Service;

@Service
public class AOPServiceAspect {
	public void serviceBefore() {
		System.out.println("[��־����]");
	}
	public void serviceAfter() {
		System.out.println("[������]");
	}
}

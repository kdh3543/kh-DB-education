
//Thread ��� ��� Steps
//1. Thread Ŭ������ ��ӹ޴� ����� ���� Ŭ������ ����
//2. Thread �κ��� ��ӹ��� public void run �޼��带 �������̵��Ͽ� ����ó�� �ڵ带 �ۼ��Ѵ�.
//3. �ۼ��� Ŭ������ �ν��Ͻ��� �����Ѵ�.
//4. ������ �ν��Ͻ��κ��� start method�� ȣ���Ѵ�.

class Worker extends Thread{	
	public void run() {
		for(int i = 0;i<100;i++) {
			System.out.print(i + " ");
		}
	}	
}

public class Exam_01 {
	public static void main(String[] args) {
		
		Worker w = new Worker();
		w.start();
		for(int i = 0; i<100; i++) {
			
			System.out.print(i+ " ");
		}

	}

}

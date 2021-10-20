
//Thread 사용 방법 Steps
//1. Thread 클래스를 상속받는 사용자 정의 클래스를 생성
//2. Thread 로부터 상속받은 public void run 메서드를 오버라이드하여 병렬처리 코드를 작성한다.
//3. 작성된 클래스로 인스턴스를 생성한다.
//4. 생성된 인스턴스로부터 start method를 호출한다.

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

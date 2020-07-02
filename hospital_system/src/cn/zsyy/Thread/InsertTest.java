package cn.zsyy.Thread;

public class InsertTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("¿ªÊ¼");
		for (int i = 1; i <=3; i++) {
            new InsertThread().start();
          }
		
	}

}

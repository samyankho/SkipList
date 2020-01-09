import java.util.Random;

public class test {


	public static void main(String[] args) {
		
		int x;
		x=3;
		int b = ++x;
		int c = x--;
		int a=(b ) + ( c );

		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(x);


//		SkipList sl = new SkipList();
//		Random r = new Random();
//		
//		for (int i = 0; i < 10; i++) {
//			int j = Math.abs(r.nextInt());
//			if (i == 0)
//				sl.add(i);
//			else {
//				j = j % i;
//				System.out.println("add " + String.valueOf(i) + " to " + String.valueOf(j));
//				sl.add(j, i);
//				sl.printSkipList();
//			}
//		}
	}
	

}

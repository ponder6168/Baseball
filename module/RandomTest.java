package module;
import java.util.*;

public class RandomTest {
	long seed=1;
	public RandomTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomTest rt = new RandomTest();
		Random rand = new Random(rt.seed);
		for (int i=0;i<5;i++) System.out.println(rand.nextInt(10));
			System.out.println("restart");
			 rand = new Random(rt.seed);
		for (int i=0;i<5;i++) System.out.println(rand.nextInt(10));

	}

}

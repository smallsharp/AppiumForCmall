package top.temp;

import top.play.pages.ActivityList;

public class Temp2 {
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 6; i++) {
			
			System.out.println(i+":hhhh");
			if (ActivityList.HOME_ACTIVITY.contains(".activity.main.HomeActivity")) {
			}
			
			System.out.println("2222222222");
			
		}
		
		String string = "com.play.android.activity.member.LoginActivity";
		System.out.println(string.equals(ActivityList.LOGIN_ACTIVITY));
	}

}

package d210927;

public class AreaCheckUtils {
	public static String checkArea(int area) {
		String area_name = null;
		
		switch(area/10) {
		case 0:
			if(area==9) 
				area_name="부산";
			else
				area_name="서울";
			break;
		case 1:
			if(area%10>=0&&area%10<3)
				area_name="부산";
			else if(area%10>=3&&area%10<6)
				area_name="인천";
			else
				area_name="경기";
			break;
		case 2:
			if(area%10>=0&&area%10<6)
				area_name="경기";
			else
				area_name="강원";
			break;
		case 3:
			if(area%10>=0&&area%10<5)
				area_name="강원";
			else
				area_name="충북";
			break;
		case 4:
			if(area%10==0)
				area_name="대전";
			else if(area%10==4) 
				area_name="세종";
			else if(area%10>0&&area%10<8)
				area_name="충남";
			else
				area_name="전북";
			break;
		case 5:
			if(area%10>=0&&area%10<5)
				area_name="전북";
			else 
				area_name="전남";
			break;
		case 6:
			if(area%10>=0&&area%10<5)
				area_name="전남";
			else if(area%10==5||area%10==6)
				area_name="광주";
			else
				area_name="대구";
			break;
		case 7:
			if(area%10==0)
				area_name="대구";
			else 
				area_name="경북";
			break;
		case 8:
			if(area%10==0)
				area_name="경북";
			else if(area%10==5)
				area_name="울산";
			else 
				area_name="경남";
			break;
		case 9:
			if(area%10==0)
				area_name="경남";
			else if(area%10>=1&&area%10<6)
				area_name="제주";
			else if(area%10==6)
				area_name="세종";
			break;
		default:
			area_name="잘못된 지역번호 입니다.";
		}
		
		return area_name;
	}
}

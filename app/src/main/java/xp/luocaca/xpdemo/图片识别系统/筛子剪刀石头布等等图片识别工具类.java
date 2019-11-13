package xp.luocaca.xpdemo.图片识别系统;

public class 筛子剪刀石头布等等图片识别工具类 {
    String num1 = "da1c289d4e363f3ce1ff36538903b92f";
    String num2 = "9e3f303561566dc9342a3ea41e6552a6";
    String num3 = "dbcc51db2765c1d0106290bae6326fc4";
    String num4 = "9a21c57defc4974ab5b7c842e3232671";
    String num5 = "3a8e16d650f7e66ba5516b2780512830";
    String num6 = "5ba8e9694b853df10b9f2a77b312cc09";


    public static enum 图片枚举 {


        剪刀("514914788fc461e7205bf0b6ba496c49", "剪刀", 2),
        石头("f790e342a02e0f99d34b316547f9aeab", "石头", 10),
        布("091577322c40c05aa3dd701da29d6423", "布", 5),


        骰子1点("da1c289d4e363f3ce1ff36538903b92f", "骰子1点", 1),
        骰子2点("9e3f303561566dc9342a3ea41e6552a6", "骰子2点", 2),
        骰子3点("dbcc51db2765c1d0106290bae6326fc4", "骰子3点", 3),
        骰子4点("9a21c57defc4974ab5b7c842e3232671", "骰子4点", 4),
        骰子5点("3a8e16d650f7e66ba5516b2780512830", "骰子5点", 5),
        骰子6点("5ba8e9694b853df10b9f2a77b312cc09", "骰子6点", 6);


        图片枚举(String md5Path, String desc, int rate) {
            this.md5Path = md5Path;
            this.desc = desc;
            this.rate = rate;
        }


        public String md5Path;
        public String desc;
        public int rate;//倍数

    }


    public static boolean 判断是否敏感图片() {

        return false;
    }


}

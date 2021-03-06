
package com.frms.lexer;

/**
 * BillBill bv号和av号互转.
 * 项目名称 ： app
 * 创建人 ： Frms
 * 创建人邮箱 ： 3505826836@qq.com
 * 创建时间 ：2020/3/23 23:46(ydt)
 */
public class Av2bv
{
    static final String table = "fZodR9XQDSUm21yCkr6zBqiveYah8bt4xsWpHnJE7jL5VG3guMTKNPAwcF";
    static int[] tr = new int[0xffff];
    
    static int[] s = new int[]{
        11,10,3,8,4,6
    };
    
    static long xor = 177451812L, add = 8728348608L;
    
    /**
     * 初始化
     */
    
    public void init()
    {
        for(int i = 0; i< 58; i++)
        {
            tr[table.charAt(i)] = i;
        }
    }
    
    /**
     * Bv 号
     * @param bv
     * @return
     */
    public String toAV(String bv)
    {
        long r = -add;
        for(int i=0; i<6; i++)
        {
            r += tr[ bv.charAt(s[i])] * Math.pow(58 , i) ;
        }
        return "av" + (r ^ xor);
    }
    
    /**
     * av号，去掉 ”av"
     * @param av
     * @return
     */
    public String toBV(long av)
    {
        av = (av ^ xor) + add;
        char[] list = new char[]{'B', 'V', '1', ' ', ' ', '4', ' ', '1', ' ', '7', ' ', ' '};
        for(int i=0; i<6; i++)
        {
            list[s[i]] = table .charAt((int) Math.floor(av / (Math.pow(58, i) )% 58 ));
        }
        return String.valueOf(list);
    }
    
    public static void main(String[] args)
    {
        Av2bv av2bv = new Av2bv();
        av2bv.init();
        System.out.println(av2bv.toAV("BV17x411w7KC"));
        System.out.println(av2bv.toBV(170001));
    }
}

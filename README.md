starbill (星星记账系统)
=

[作者博客](https://blog.csdn.net/weixin_43967679)

系统主要功能
-

* 主要用于记录平时生活使用账单,并且能够把账单保存到Excel或者是mysql

项目运行环境要求
-
* jre+jdk 1.8上下
* mysql 5.7

特别说明
-


* 下载整个项目运行前,需要安装mysql5.7上下的版本
* [安装mysql教程](https://blog.csdn.net/Dopamy_BusyMonkey/article/details/49099489)


项目导入到Eclipse中或者是IDEA中
-

* 直接下载这个项目的zip压缩包,然后解压到你想解压的路径下,然后导入到Eclipse或者是IDEA中就可以使用了
* [如何将外部项目导入到Eclipse中](https://baijiahao.baidu.com/s?id=1552537328458860&wfr=spider&for=pc)

导入之后进行配置
-

* 只需要设置自己的mysql登陆名和登陆密码即可使用
配置教程如下:

* 1.打开 constant 包下的 TagXml.java 文件
* 2.找到 loginName 和 password 变量,设置为自己的mysql登录名和密码
* 3.如果Mysql是第一次安装,则 password = "" (空)
```
package constant;

import javax.swing.ImageIcon;

public class TagXml {
	
	private final static int income = 1;
	private final static int cost = 2;
	public final static int addCostPanel = cost;
	public final static int addEarnPanel = income;
	public final static int incomeCategoryPanel = income;
	public final static int costCategoryPanel = cost;
	public final static int costDetailsPanel = cost;
	public final static int incomeDetailsPanel = income;
	public final static int statisticsEarnPanel = income;
	public final static int statisticsCostPanel = cost;
	public final static int findIncomeRecordPanel = income;
	public final static int findCostRecordPanel = cost;
	public final static int analysisCostPanel = cost;
	public final static int analysisIncomePanel = income;
	
	/*
	 * 主框架的窗口图案
	 */
	public final static ImageIcon icon = new ImageIcon("img/mainFrameIcon.jpg");
	/*
	 * 登陆后所有panel的背景图片
	 */
	public final static String BACKGROUND = "allPanelImg.jpg";
	/*
	 * ip 为本电脑的ip
	 */
	public final static String ip = "127.0.0.1";
	
	/*
	 * port 为你安装的mysql 占用的端口 一般默认为 3306
	 */
	public final static int port = 3306;
	
	/*
	 * startbill为数据库名
	 */
	public final static String database = "starbill";
	
	/*
	 * 数据库编码方式为UTF-8
	 */
	public final static String encoding = "UTF-8";
	
	/*
	 * loginName 为mysql登陆名,一般默认为 root
	 */
	public final static String loginName = "root";        这一行为你自己的 mysql 登录名
	
	/*
	 * password 为mysql登陆密码,一般为空 ""
	 * 也可以自行修改
	 */
	public final static String password = "";             这一行为你自己的 mysql 登陆密码
	
}
```

项目展示
-

![星星记账系统](https://github.com/819879012/starbill/blob/master/starbill.png)




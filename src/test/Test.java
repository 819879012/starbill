package test;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
 * 6������Ŀ����д��Java������м��������ܹ�д�˶����д���
 * ����ָ���ļ��У����ļ���ǰ�����ж��ļ��ĺ�׺���ǲ���java,�����java����io����ȥͳ������
 */
public class Test {
	private static int i;//����������
	private static int j;//�ļ�����
	public static void main(String[] args) throws IOException {
		File file = new File("D:\\JavaExperiment\\starbill");//��Ҫͳ���������ļ���·��
		traverseFiles(file);//���õݹ鷽���鿴.java�ļ�������ͳ������
		System.out.println("��д�ļ�������"+j);
		System.out.println("��д������������"+i);
	}
	public static void traverseFiles(File file) throws IOException{	
		if(!file.exists()){//�ļ�������
			return;
		}
		
		if(!file.isDirectory()){//�ж��Ƿ�Ϊ�ļ�
			String filename = file.getName();
			if(filename.endsWith(".java")){//�ж��Ƿ���.java�ļ�
				j++;
				@SuppressWarnings("resource")
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
				@SuppressWarnings("unused")
				String string =null;
				while ((string = bufferedReader.readLine()) != null) {
					i++;//��ȡ����
				}
			}else
			   return;
		}
		
		File[] files =file.listFiles();//��ȡ�ļ��е����ļ������ļ���
		if (files == null || files.length == 0) {
			return;
		}
		
		for(File file2 : files){//������ļ��еݹ���÷��������ļ�
			traverseFiles(file2);
		}
		
	}
}
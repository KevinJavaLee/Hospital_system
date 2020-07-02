package cn.zsyy.spider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PrintText {

	public static void main(String[] args) throws IOException {
		String directory = "E:\\test";
        String filename = "test.txt";

        //2.  创建文件夹对象     创建文件对象
        File file = new File(directory);
        //如果文件夹不存在  就创建一个空的文件夹
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(directory, filename);
        //如果文件不存在  就创建一个空的文件
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //3.写入数据
        //创建文件字节输出流
        FileOutputStream fos;
		try {
			fos = new FileOutputStream(directory + "\\" + filename);
			 String str = "测试数据";
		        byte[] bytes = str.getBytes();
		        //将byte数组中的所有数据全部写入
		        fos.write(bytes);
		        //关闭流
		        fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

	}

}

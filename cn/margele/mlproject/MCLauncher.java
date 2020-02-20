package cn.margele.mlproject;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import net.minecraft.client.main.ModifyMain;

public class MCLauncher {
	File mcFile;
	String[] args;

	public MCLauncher(File mcFile, String[] args) {
		this.mcFile = mcFile;
		this.args = args;
	}

	public void launch() {
		try {
			loadJar(mcFile);
			
			ModifyMain.launch(concat(new String[] { "--version", "mcp", "--accessToken", "0", "--assetsDir", "assets",
					"--assetIndex", "1.8", "--userProperties", "{}" }, args));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public <T> T[] concat(T[] first, T[] second) {
		T[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}
	
    private static void loadJar(File jarFile) throws MalformedURLException {
        if (jarFile.exists() == false) {
            System.out.println("jar file not found.");
            return;
        }

        //��ȡ���������addURL������׼����̬����
        Method method = null;
        try {
            method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        } catch (NoSuchMethodException | SecurityException e1) {
            e1.printStackTrace();
        } 
        
        // ��ȡ�����ķ���Ȩ�ޣ�����ԭʼֵ
        boolean accessible = method.isAccessible();
        try {
            //�޸ķ���Ȩ��Ϊ��д
            if (accessible == false) {
                method.setAccessible(true);
            }

            // ��ȡϵͳ�������
            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            
            //��ȡjar�ļ���url·��
            java.net.URL url = jarFile.toURI().toURL();
            
            //jar·�����뵽ϵͳurl·����
            method.invoke(classLoader, url);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //��д����Ȩ��
            method.setAccessible(accessible);
        }
    }

}

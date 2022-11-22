package org.javaboy.vhr.pythonutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @author sam
 * @ClassName ExecPython
 * @description: 调用python脚本
 * @datetime 2022年 10月 07日 21:50
 * @version: 1.0
 */

@Component
public class ExecPython {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExecPython.class);
    //设置默认值为空置
    @Value("${python.command:#{null}}")
    private String command;

    @Value("${python.basePath:#{null}}")
    private String basePath;

    @Value("${python.apiFile:#{null}}")
    private String apiFile;



    public void runPython(String[] parameter) {
        try {
        	/*
			附加：
			String[] args1=new String[]{"/home/huan/anaconda2/bin/python","/home/huan/myfile/pythonfile/helloword.py"};
            Process pr=Runtime.getRuntime().exec(args1);
			String数组里的那一行很重要
			首先一定要设置好你所使用的python的位置，切记不要直接使用python，因为系统会默认使用自带的python，所以一定要设置好你所使用的python
			的位置，否则可能会出现意想不到的问题（比如说我使用的是anaconda中的python，而ubuntu系统会默认调用自带的python，
			而我自带的python中并没有numpy库，所以会造成相应的代码不会执行的问题，所以设置好python的位置是很重要的）。
			还有就是要设置好py文件的位置，使用绝对路径。在这里插入代码片.还有就是可以看出，此方法可以满足我们python代码中调用第三方库的情况，
			简单实用。

			file: basePath + "/web/mytest.py"
			*/
            LOGGER.info("begin invoking python {}", parameter);
            String[] params = new String[2+parameter.length];
            params[0] = command;
            params[1] = basePath + apiFile;
            if (parameter != null) {
                for (int i = 0; i < parameter.length; i++) {
                    params[i+2] = parameter[i];
                }
            }

            Process proc = Runtime.getRuntime().exec(params);

            /* 为"错误输出流"单独开一个线程读取之,否则会造成标准输出流的阻塞 */
            Thread t = new Thread(new InputStreamRunnable(proc.getErrorStream(), "ErrorStream"));
            t.start();

            /* "标准输出流"就在当前方法中读取 */
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                LOGGER.info("the result of invoking python: {}", line);
            }
            in.close();
            int result = proc.waitFor(); //返回值为0表示我们调用python脚本成功，1表示失败
            LOGGER.info("the status of invoking python: {}", result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*public static void main(String[] args) {
        try {

//            String[] params = new String[]{"python3", "/Users/sam/Documents/IdeaProjects/stock/web/main_api.py", "load_a_stock"};
            String[] params = new String[]{"/Library/Frameworks/Python.framework/Versions/3.9/bin/python3",
                    "/Users/sam/Documents/IdeaProjects/stock/web/main_api.py", "load_a_stock"};
            Process proc = Runtime.getRuntime().exec(params);

            *//* 为"错误输出流"单独开一个线程读取之,否则会造成标准输出流的阻塞 *//*
            Thread t = new Thread(new InputStreamRunnable(proc.getErrorStream(), "ErrorStream"));
            t.start();

            *//* "标准输出流"就在当前方法中读取 *//*
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                LOGGER.info("the result of invoking python: {}", line);
            }
            in.close();
            int result = proc.waitFor(); //返回值为0表示我们调用python脚本成功，1表示失败
            LOGGER.info("the status of invoking python: {}", result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}

class InputStreamRunnable implements Runnable {
    BufferedReader bReader = null;

    public InputStreamRunnable(InputStream is, String _type) {
        try {
            bReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(is), "UTF-8"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void run() {
        String line;
        int num = 0;
        try {
            while ((line = bReader.readLine()) != null) {
                System.out.println("---->"+String.format("%02d",num++)+" "+line);
            }
            bReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

package test;

import java.io.*;

class  test2{
    public static void main(String[] args) {
        InputStream inputStream;
        OutputStream outputStream;
        try {
            inputStream=new FileInputStream(new File("d:/d.txt"));
           outputStream=new FileOutputStream("d:/d.txt");
             outputStream.write(Integer.parseInt("helllo baby"));
             byte [] b=new byte[1024];
             while (inputStream.read(b)!=-1){

             }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

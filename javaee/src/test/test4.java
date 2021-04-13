package test;

public class test4 {
        private static  test4 t=null;
         private test4(){}
         public  static  synchronized  test4  ss(){
               if(t==null){
                    t=new test4();
               }
               return  t;
         }

    public static void main(String[] args) {
         test4 t2=new test4();
             ss();
             if(t2.equals(t)){
                 System.out.println("是同一个对象");
             }else{
                 System.out.println("不是同一个对象");
             }

    }

}


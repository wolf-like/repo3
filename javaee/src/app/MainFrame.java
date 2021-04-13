package app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class  MainFrame extends JFrame implements KeyListener , ActionListener {

    // 准备二维数组存储图片编号
    int[][] datas = {
            {0, 1, 2, 3},
            {4, 5, 6, 7},
            {8, 9, 10, 11},
            {12, 13, 14, 15}
    };

    // 定义计数器变量, 用于统计走了多少步
    int count = 0;

    // 存储胜利数据的二维数组
    int[][] winData = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    // 定义两个变量, 记录0号元素在二维数组中的坐标位置.
    int x0;
    int y0;

    /**
     * 构造方法
     */
    public MainFrame() {
        // 初始化窗体
        initFrame();
        // 初始化菜单
        initMenu();
        // 初始化数据
        initData();
        // 绘制界面
        paintView();
        // 设置窗体可见
        setVisible(true);
    }

    /**
     * 此方法用于初始化窗体
     */
    public void initFrame() {
        // 设置窗体宽和高
        setSize(514, 595);
        // 设置窗体Title
        setTitle("石头迷阵单机版 v1.0");
        // 取消窗体默认布局
        setLayout(null);
        // 设置关闭模式
        setDefaultCloseOperation(3);
        // 设置窗体居中
        setLocationRelativeTo(null);
        // 设置窗体置顶
        setAlwaysOnTop(true);
        // 为当前的窗体注册键盘监听
        this.addKeyListener(this);
    }

    /**
     * 此方法用于绘制界面
     */
    public void paintView() {

        // 加载资源之前, 先将现有组件清空
        getContentPane().removeAll();

        JLabel scoreLabel = new JLabel("步数:" + count);
        scoreLabel.setBounds(50,20,100,20);
        getContentPane().add(scoreLabel);

        if(victory()){
            // 胜利的状态
            JLabel winLabel = new JLabel(new ImageIcon("F:\\mimage\\win.png"));
            winLabel.setBounds(124,230,266,88);
            getContentPane().add(winLabel);
        }

        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                // 在遍历的过程中, 创建JLabel对象加载并展示图片资源
                JLabel imageLabel = new JLabel(new ImageIcon("F:\\mimage\\" + datas[i][j] + ".png"));
                imageLabel.setBounds(50 + 100 * j, 90 + 100 * i, 100, 100);
                getContentPane().add(imageLabel);
            }
        }

        // 加载背景图资源
        JLabel background = new JLabel(new ImageIcon("F:\\mimage\\background.png"));
        background.setBounds(26, 30, 450, 484);
        getContentPane().add(background);

        // 加载完成之后, 刷新界面
        getContentPane().repaint();
    }

    /**
     * 此方法用于初始化数据
     */
    public void initData() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        Random r = new Random();

        for (int i = 0; i < arr.length; i++) {
            // 获取随机的索引值
            int index = r.nextInt(arr.length); // 0 ~ 15
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }

        // 遍历打乱后的一维数组, 取出内部的每一个元素, 存入二维数组当中.
        for (int i = 0; i < arr.length; i++) {
            datas[i / 4][i % 4] = arr[i];
            // 查找0号元素
            if (arr[i] == 0) {
                x0 = i / 4;
                y0 = i % 4;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * 此方法用于触发键盘被按下的事件
     */
    @Override
    public void keyPressed(KeyEvent e) {
        /** 移动逻辑思路
         上移动: 0号元素，和下方位置元素（x0 + 1），进行交换
         左移动: 0号元素，和右侧位置元素（y0 + 1），进行交换
         右移动: 0号元素，和左侧位置元素（y0 - 1），进行交换
         下移动: 0号元素，和上方位置元素（x0 - 1），进行交换
         注意: 每一次交换完成，都要重新记录 0 号元素的所在位置
         注意: 移动后需要重新绘制界面
         */

        // 需要注意的边界值 y0 = 3 ，x0 = 3，y0 = 0，x0 = 0
        int keyCode = e.getKeyCode();
        if (keyCode == 37) {
            // 左移动
            if (y0 == 3) {
                return;
            }
            moveToLeft();
            count++;
        } else if (keyCode == 38) {
            // 上移动
            if (x0 == 3) {
                return;
            }
            moveToTop();
            count++;
        } else if (keyCode == 39) {
            // 右移动
            if (y0 == 0) {
                return;
            }
            moveToRight();
            count++;
        } else if (keyCode == 40) {
            // 下移动
            if (x0 == 0) {
                return;
            }
            moveToBottom();
            count++;
        } else if(keyCode == 87){
            // 如果是w按键, 直接改为胜利数据
            datas = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
        }

        // 移动后需要重新绘制界面
        paintView();
    }

    private void moveToBottom() {
        datas[x0][y0] = datas[x0 - 1][y0];
        datas[x0 - 1][y0] = 0;
        x0--;
    }

    private void moveToRight() {
        datas[x0][y0] = datas[x0][y0 - 1];
        datas[x0][y0 - 1] = 0;
        y0--;
    }

    private void moveToTop() {
        datas[x0][y0] = datas[x0 + 1][y0];
        datas[x0 + 1][y0] = 0;
        x0++;
    }

    private void moveToLeft() {
        datas[x0][y0] = datas[x0][y0 + 1];
        datas[x0][y0 + 1] = 0;
        y0++;
    }

    /**
     * 此方法用于初始化窗体
     */
    public void initMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("功能");
        JMenuItem item = new JMenuItem("重新游戏");
        menuBar.add(menu);
        menu.add(item);
        setJMenuBar(menuBar);
        item.addActionListener(this);
    }

    /**
     * 此方法判断游戏是否胜利, 返回true或false
     */
    public boolean victory(){
        // 使用两个数组逐个比较元素
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                if(datas[i][j] != winData[i][j]){
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 当重新游戏被点击, 触发此方法
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        count = 0;
        // 重新初始化数据.
        initData();
        // 重新绘制界面的方法
        paintView();
    }
}

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

@SuppressWarnings({"serial", "unused"})
public class View extends JFrame {

    File file = null;
    Color color = Color.black;
    FileManage fileManage = null;

    public View(FileManage fileManage) {
        this.fileManage = fileManage;
        Menu();
        Panel();
        AboutDialog();
    }

    //制作一个可以滑动的文本框
    JTextPane text = new JTextPane();

    private void Panel() {
        getContentPane().add(new JScrollPane(text));
    }

    JMenuBar menubar = new JMenuBar();
    JFileChooser filechooser = new JFileChooser();
    JDialog about = new JDialog();

    JMenu[] menus = new JMenu[]{
            new JMenu("文件"),
            new JMenu("编辑"),
            new JMenu("帮助"),
            new JMenu("退出"),
    };
    JMenuItem[][] menuitems = new JMenuItem[][]{
            {
                    new JMenuItem("打开"),
                    new JMenuItem("保存"),
            },
            {
                    new JMenuItem("复制"),
                    new JMenuItem("剪切"),
                    new JMenuItem("粘贴"),
            },
            {
                    new JMenuItem("关于"),
            },
            {
                    new JMenuItem("退出"),
            },
    };
    /**
    *建造菜单，这是一个二级菜单
     */
    void Menu() {
        for (int i = 0; i < menus.length; i++) {
            menubar.add(menus[i]);
            for (int j = 0; j < menuitems[i].length; j++) {
                menus[i].add(menuitems[i][j]);
                menuitems[i][j].addActionListener(action);
            }
        }
        this.setJMenuBar(menubar);
    }

    ActionListener action = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            JMenuItem mi = (JMenuItem) e.getSource();
            String id = mi.getText();
            if ("打开".equals(id)) {
                filechooser.setDialogTitle("打开文件");
                if (file != null) {
                    filechooser.setSelectedFile(file);
                }
                int returnVal = filechooser.showOpenDialog(
                        View.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    file = filechooser.getSelectedFile();
                    openFile();
                }
                /*第二种方法打开文件选择框
                filechooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
                filechooser.showDialog(new JLabel(), "打开");
                if(file.isDirectory()){
                    System.out.println("文件夹:"+file.getAbsolutePath());
                }else if(file.isFile()){
                    System.out.println("文件:"+file.getAbsolutePath());
                }
                System.out.println(filechooser.getSelectedFile().getName());
                 */
            } else if ("保存".equals(id)) {
                filechooser.setDialogTitle("保存文件");
                if (file != null) {
                    filechooser.setSelectedFile(file);
                }
                int returnVal = filechooser.showOpenDialog(
                        View.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    file = filechooser.getSelectedFile();
                    saveFile();
                }
            } else if ("退出".equals(id)) {
                System.exit(0);
            } else if ("剪切".equals(id)) {
                text.cut();
            } else if ("复制".equals(id)) {
                text.copy();
            } else if ("粘贴".equals(id)) {
                text.paste();
            } else if ("关于".equals(id)) {
                AboutDialog();
                about.setSize(200, 100);
                about.setVisible(true);
            }
        }
    };

    void openFile() {
        String content = fileManage.read(file);
        text.setText(content);
    }

    void saveFile() {
        String content = text.getText();
        fileManage.save(file, content);
    }

    void AboutDialog(){
        about.getContentPane().add(new JLabel("https://www.microsoft.com/zh-cn/"));
        about.setModal(true);
        about.setSize(100, 50);
    }
}
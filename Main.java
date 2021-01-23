import javax.swing.*;

@SuppressWarnings("unused")
class Main  //应用程序
{
	public static void main( String [] args){

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				FileManage dal = new FileManage();
				View f = new View(dal);
				f.setTitle( "文本编辑器");
				f.setSize( 800, 600 );
				f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				f.setVisible(true);
			}
		});
	}
}
package startup;

import java.awt.EventQueue;

import gui_frame.MainFrame;
import gui_panel.LoginPanel;
import util.DBUtil;
import util.GUIUtil;

public class StartProgram {
    public static void main(String[] args) throws Exception{
        GUIUtil.useLNF();
        EventQueue.invokeLater(new Runnable() {
            @SuppressWarnings("unused")
			@Override
            public void run() {
            	DBUtil init = new DBUtil();
            	MainFrame.getInstance().setContentPane(LoginPanel.getInstance());
            	MainFrame.getInstance().setVisible(true);
            }
        });
    }
}
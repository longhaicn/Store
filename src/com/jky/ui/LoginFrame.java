package com.jky.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.jky.biz.MemberBiz;
import com.jky.biz.WorkRecordBiz;
import com.jky.entity.Member;
import com.jky.entity.WorkRecord;
import com.jky.utils.Tools;
import java.awt.Toolkit;

public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField accountField;
	private JPasswordField passwordField;
	private JTextField captchaField;
	private JLabel lblCaptcha;
	private JLabel lblPrompt;
	private boolean clearLblPrompt = false;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Dijkstra\\workspace\\Store\\Store\\shoppingcar.jpg"));
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(180, 30, 1000, 600);
		setTitle("标签打印中间件-登录");
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelMain = new JPanel();
		panelMain.setBackground(Color.WHITE);
		contentPane.add(panelMain, BorderLayout.WEST);
		panelMain.setLayout(new BorderLayout(0, 0));

		JLabel lblLeft = new JLabel("");
		lblLeft.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeft.setIcon(new ImageIcon("Store\\newrocktech.jpg"));
		panelMain.add(lblLeft);

		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(Color.WHITE);
		contentPane.add(panelLogin, BorderLayout.CENTER);
		panelLogin.setLayout(null);

		JLabel lblTitile = new JLabel("欢迎登录-标签打印中间件！");
		lblTitile.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblTitile.setBounds(99, 50, 271, 30);
		panelLogin.add(lblTitile);

		JLabel lblAccount = new JLabel("登录账户：");
		lblAccount.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblAccount.setBounds(99, 120, 70, 30);
		panelLogin.add(lblAccount);

		accountField = new JTextField();
		accountField.setBounds(169, 120, 201, 30);
		accountField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				super.keyTyped(arg0);
				if (clearLblPrompt) {
					lblPrompt.setText("");
					clearLblPrompt = false;
				}
			}
		});
		;
		panelLogin.add(accountField);
		accountField.setColumns(10);

		lblPrompt = new JLabel("");
		lblPrompt.setBackground(Color.WHITE);
		lblPrompt.setForeground(Color.RED);
		lblPrompt.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblPrompt.setBounds(169, 80, 200, 30);
		panelLogin.add(lblPrompt);

		JLabel lblPassword = new JLabel("账户密码：");
		lblPassword.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblPassword.setBounds(99, 170, 70, 30);
		panelLogin.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(169, 170, 200, 30);
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				super.keyTyped(arg0);
				if (clearLblPrompt) {
					lblPrompt.setText("");
					clearLblPrompt = false;

				}
			}
		});
		panelLogin.add(passwordField);

		JLabel label = new JLabel("验证码为：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(99, 220, 70, 30);
		panelLogin.add(label);

		captchaField = new JTextField();
		captchaField.setColumns(10);
		captchaField.setBounds(169, 220, 100, 30);
		captchaField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				super.keyTyped(arg0);
				if (clearLblPrompt) {
					lblPrompt.setText("");
					clearLblPrompt = false;
				}
			}
		});
		panelLogin.add(captchaField);

		lblCaptcha = new JLabel(Tools.getCaptcha());
		lblCaptcha.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblCaptcha.setBounds(279, 220, 62, 30);
		panelLogin.add(lblCaptcha);

		JButton btnNewCaptcha = new JButton("");
		btnNewCaptcha.setIcon(new ImageIcon("C:\\Users\\Dijkstra\\workspace\\Store\\Store\\newCaptcha.jpg"));
		btnNewCaptcha.setBounds(340, 220, 30, 30);
		btnNewCaptcha.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				lblCaptcha.setText(Tools.getCaptcha());
			}
		});
		panelLogin.add(btnNewCaptcha);

		JButton btnLogin = new JButton("登录");
		btnLogin.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.DARK_GRAY);
		btnLogin.setBounds(169, 292, 200, 37);
		btnLogin.setFocusable(false);
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String account = accountField.getText().trim();
				// System.out.println(account);
				String password = new String(passwordField.getPassword());
				String captcha = captchaField.getText().trim();
				if (Tools.isEmpty(account)) {
					lblPrompt.setText("*用户名不能为空！");
					clearLblPrompt = true;
					return;
				} else if (Tools.isEmpty(password)) {
					lblPrompt.setText("*密码不能为空！");
					clearLblPrompt = true;
					return;
				} else if (Tools.isEmpty(captcha)) {
					lblPrompt.setText("*验证码不能为空！");
					clearLblPrompt = true;
					return;
				} else if (captcha.equals(lblCaptcha.getText())) {
					lblPrompt.setText("*验证码错误！");
					captchaField.setText("");
					return;
				} else {
					MemberBiz biz = new MemberBiz();
					Member member = biz.isCheckLogin(account, password);
					WorkRecordBiz wbiz = new WorkRecordBiz();
					WorkRecord workRecord = new WorkRecord();
					workRecord.setMemeberId(member.getMemberId());
					workRecord.setStartTime(Tools.getDateTime());
					workRecord.setLastTime("00:00:00");
					wbiz.addWorkRecord(workRecord);
					if (member.getMemberId() == 0) {
						lblPrompt.setText("*账户或密码名错误！");
						accountField.setText("");
						passwordField.setText("");
						return;
					} else {
						dispose();
						MemberBiz mbiz = new MemberBiz();
						if (member.getRole() == 0) {
							// System.out.println(member.getMemberId());
							mbiz.updMember(member.getMemberId(), true);
							new CasherFrame(member.getMemberId(), member.getName());

						} else if (member.getRole() == 1) {
							mbiz.updMember(member.getMemberId(), true);
							new KeeperFrame(member.getMemberId(), member.getName());
						} else if (member.getRole() == 2) {
							mbiz.updMember(member.getMemberId(), true);
							new BossFrame(member.getMemberId(), member.getName());
						}
					}
				}
			}
		});
		panelLogin.add(btnLogin);

		JLabel lblButtom = new JLabel("版权所有");
		lblButtom.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblButtom, BorderLayout.SOUTH);
		lblButtom.setFont(new Font("黑体", Font.BOLD, 20));
		lblButtom.setBackground(Color.WHITE);

		JLabel lblTop = new JLabel("标签打印中间件");
		lblTop.setFont(new Font("黑体", Font.PLAIN, 80));
		lblTop.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTop, BorderLayout.NORTH);
		lblTop.setBackground(Color.WHITE);
	}
}

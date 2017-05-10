package com.jky.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.jky.biz.MemberBiz;
import com.jky.biz.SaleRecordBiz;
import com.jky.biz.WorkRecordBiz;
import com.jky.entity.Member;
import com.jky.entity.SaleRecord;
import com.jky.entity.WorkRecord;
import com.jky.utils.Tools;
import java.awt.Toolkit;

public class BossFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldMemberSearch;
	private JTextField textFieldAddUserName;
	private JTextField textFieldAddPassword;
	private JTextField textFieldAddPasswordd;
	private JTextField textFieldAddName;
	private JTextField textFieldUpdByUserName;
	private JTextField textFieldUpdPassword;
	private JTextField textFieldUpdName;
	private JTextField textFieldDelUserName;
	private MemberBiz mbiz = new MemberBiz();
	private WorkRecordBiz wbiz = new WorkRecordBiz();
	private SaleRecordBiz sbiz = new SaleRecordBiz();
	private boolean actionAllow = true;
	private JTable tableMember;
	private JTable tableWork;
	private JTable tableSale;
	private List<Member> listsMember = mbiz.queryAllOder("memberId");
	private List<WorkRecord> listsWork = wbiz.queryAllWork("workrecordId");
	private List<SaleRecord> listsSale = sbiz.queryAllSale("salecordId");
	private String condition;
	private JComboBox cboAddRole;
	private JTextField textFieldDelCaptcha;
	private JLabel lblCaptcha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BossFrame frame = new BossFrame(20160003, "柯正东");
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
	public BossFrame(final int memberId, String name) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Store\\logo.jpg"));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				WorkRecordBiz wbiz = new WorkRecordBiz();
				List<WorkRecord> queryAllWork = wbiz.queryAllWork("" + memberId);
				WorkRecord workRecord = queryAllWork.get(0);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat sdt = new SimpleDateFormat("HH:mm:ss");
				try {
					Date dateOn = sdf.parse(workRecord.getStartTime());
					Calendar cal = Calendar.getInstance();
					cal.setTime(dateOn);
					long timeOn = cal.getTimeInMillis();
					long timeOff = new Date().getTime();
//					long timeOff = System.currentTimeMillis();
					long lasttime = timeOff-timeOn-28800000;
					Date dateOff = new Date(lasttime);
					String str = sdt.format(dateOff);
					workRecord.setLastTime(str);
					wbiz.updWorkRecord(workRecord);
					mbiz.updMember(memberId,false);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					System.out.println("erro");
					e1.printStackTrace();
				}
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(180, 30, 1000, 600);
		setTitle("乐尔乐商城——工号：" + memberId + " - " + name + "-系统管理员已登录......");
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panelMember = new JPanel();
		panelMember.setBackground(Color.WHITE);
		tabbedPane.addTab("管理员工", null, panelMember, null);
		panelMember.setLayout(null);

		final JScrollPane scrollPaneMember = new JScrollPane();
		scrollPaneMember.setBounds(10, 70, 600, 300);
		panelMember.add(scrollPaneMember);

		final DefaultTableModel dtm = new DefaultTableModel();
		dtm.setDataVector(getRows(listsMember), getColumn());
		tableMember = new JTable();
		tableMember.setModel(dtm);
		tableMember.setRowHeight(26);
		scrollPaneMember.setViewportView(tableMember);

		DefaultTableModel dtmw = new DefaultTableModel();
		dtmw.setDataVector(getRowsWork(listsWork), getColumnWork());

		DefaultTableModel dtms = new DefaultTableModel();
		dtms.setDataVector(getRowsSale(listsSale), getColumnSale());

		JLabel label = new JLabel("搜索：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(Color.BLUE);
		label.setBackground(Color.WHITE);
		label.setBounds(290, 30, 40, 30);
		panelMember.add(label);

		textFieldMemberSearch = new JTextField();
		textFieldMemberSearch.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldMemberSearch.setText("请输入工号/姓名/角色");
		textFieldMemberSearch.setForeground(Color.GRAY);
		textFieldMemberSearch.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textFieldMemberSearch.setBounds(340, 30, 190, 30);
		textFieldMemberSearch.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
				condition = textFieldMemberSearch.getText().trim();
				if (Tools.isEmpty(condition)) {
					textFieldMemberSearch.setText("请输入工号/姓名/角色");
					textFieldMemberSearch.setForeground(Color.GRAY);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if ("请输入工号/姓名/角色".equals(textFieldMemberSearch.getText().trim())) {
					textFieldMemberSearch.setText("");
				}
				textFieldMemberSearch.setForeground(Color.BLACK);
			}
		});
		textFieldMemberSearch.setColumns(10);
		textFieldMemberSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				super.keyReleased(arg0);
				condition = textFieldMemberSearch.getText();

				if (!condition.equals("请输入工号/姓名/角色")) {
					listsMember = mbiz.queryAllCondition(condition);
					dtm.setDataVector(getRows(listsMember), getColumn());
					tableMember.setModel(dtm);
					tableMember.setFont(new Font("微软雅黑", Font.PLAIN, 12));
					tableMember.setRowHeight(26);
					scrollPaneMember.setViewportView(tableMember);
				}
			}
		});
		panelMember.add(textFieldMemberSearch);

		JLabel label_1 = new JLabel("排序：");
		label_1.setForeground(Color.BLUE);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(10, 30, 40, 30);
		panelMember.add(label_1);

		final JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "按工号排列", "按角色排列", "按姓氏排列" }));
		comboBox.setBounds(60, 30, 150, 30);
		comboBox.addItemListener(new ItemListener() {
			private DefaultTableModel dtm = new DefaultTableModel();;

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (actionAllow) {
					mbiz = new MemberBiz();
					int index = comboBox.getSelectedIndex();
					switch (index) {
					case 0:
						listsMember = mbiz.queryAllOder("memberId");
						break;
					case 1:
						listsMember = mbiz.queryAllOder("role");
						break;
					case 2:
						listsMember = mbiz.queryAllOder("name DESC");
						break;
					default:
						break;
					}
					dtm.setDataVector(getRows(listsMember), getColumn());
					tableMember.setModel(dtm);
					tableMember.setFont(new Font("微软雅黑", Font.PLAIN, 12));
					tableMember.setRowHeight(26);
					scrollPaneMember.setViewportView(tableMember);
					actionAllow = false;
				} else {
					actionAllow = true;
				}
			}
		});
		panelMember.add(comboBox);

		JButton btnNewButton = new JButton("搜索");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!condition.equals("请输入工号/姓名/角色")) {
					listsMember = mbiz.queryAllCondition(condition);
					dtm.setDataVector(getRows(listsMember), getColumn());
					tableMember.setModel(dtm);
					tableMember.setFont(new Font("微软雅黑", Font.PLAIN, 12));
					tableMember.setRowHeight(26);
					scrollPaneMember.setViewportView(tableMember);
				}
			}
		});
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(540, 30, 70, 30);
		panelMember.add(btnNewButton);

		JLabel lblPrompt = new JLabel("*开始管理你的员工吧！");
		lblPrompt.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblPrompt.setForeground(Color.RED);
		lblPrompt.setBackground(Color.WHITE);
		lblPrompt.setBounds(10, 0, 280, 30);
		panelMember.add(lblPrompt);

		JTabbedPane tabbedPaneUDR = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneUDR.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		tabbedPaneUDR.setBounds(630, 20, 330, 350);
		panelMember.add(tabbedPaneUDR);

		JPanel panelAddMember = new JPanel();
		panelAddMember.setBackground(Color.WHITE);
		tabbedPaneUDR.addTab("添加员工", null, panelAddMember, null);
		panelAddMember.setLayout(null);

		JLabel lblNewLabel = new JLabel("账户名：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel.setBounds(60, 40, 60, 30);
		panelAddMember.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("角   色：");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(60, 200, 60, 30);
		panelAddMember.add(lblNewLabel_1);

		textFieldAddUserName = new JTextField();
		textFieldAddUserName.setBounds(120, 40, 150, 30);
		panelAddMember.add(textFieldAddUserName);
		textFieldAddUserName.setColumns(10);

		JLabel label_2 = new JLabel("密   码：");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_2.setBounds(60, 80, 60, 30);
		panelAddMember.add(label_2);

		textFieldAddPassword = new JTextField();
		textFieldAddPassword.setColumns(10);
		textFieldAddPassword.setBounds(120, 80, 150, 30);
		panelAddMember.add(textFieldAddPassword);

		JLabel label_3 = new JLabel("(重复)密   码：");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_3.setBounds(30, 120, 90, 30);
		panelAddMember.add(label_3);

		textFieldAddPasswordd = new JTextField();
		textFieldAddPasswordd.setColumns(10);
		textFieldAddPasswordd.setBounds(120, 120, 150, 30);
		panelAddMember.add(textFieldAddPasswordd);

		final JLabel lblAddMemberPrompt = new JLabel("提示：添加员工");
		lblAddMemberPrompt.setForeground(Color.RED);
		lblAddMemberPrompt.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblAddMemberPrompt.setBackground(Color.WHITE);
		lblAddMemberPrompt.setBounds(60, 0, 255, 30);
		panelAddMember.add(lblAddMemberPrompt);

		cboAddRole = new JComboBox();
		cboAddRole.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		cboAddRole.setBackground(Color.WHITE);
		cboAddRole.setModel(new DefaultComboBoxModel(new String[] { "收银员", "仓管员", "高层" }));
		cboAddRole.setBounds(120, 200, 150, 30);
		panelAddMember.add(cboAddRole);

		JButton btnAddSubmit = new JButton("提交");
		btnAddSubmit.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnAddSubmit.setForeground(Color.WHITE);
		btnAddSubmit.setBackground(Color.RED);
		btnAddSubmit.setBounds(120, 250, 150, 30);
		btnAddSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String AddUserName = textFieldAddUserName.getText().trim();
				if (Tools.isEmpty(AddUserName)) {
					lblAddMemberPrompt.setText("*账户名不能为空！");
					return;
				}
				String AddPassword = textFieldAddPassword.getText();
				if (Tools.isEmpty(AddPassword)) {
					lblAddMemberPrompt.setText("*密码不能为空！");
					return;
				}
				String AddPasswordd = textFieldAddPasswordd.getText();
				if (Tools.isEmpty(AddPasswordd) || !AddPasswordd.equals(AddPassword)) {
					lblAddMemberPrompt.setText("*两次密码不相同，请重新输入！");
					return;
				}
				String AddName = textFieldAddName.getText();
				if (Tools.isEmpty(AddName)) {
					lblAddMemberPrompt.setText("*密码不能为空！");
					return;
				}
				int index = cboAddRole.getSelectedIndex();
				if (mbiz.userNameExisted(AddUserName)) {
					lblAddMemberPrompt.setText("*该用户名已存在，请换一个用户名！");
					return;
				}
				mbiz = new MemberBiz();
				if (mbiz.addMember(AddUserName, AddPassword, AddName, index)) {
					textFieldAddUserName.setText("");
					textFieldAddPassword.setText("");
					textFieldAddPasswordd.setText("");
					textFieldAddName.setText("");
					listsMember = mbiz.queryAllCondition(AddUserName);
					dtm.setDataVector(getRows(listsMember), getColumn());
					tableMember.setModel(dtm);
					tableMember.setFont(new Font("微软雅黑", Font.PLAIN, 12));
					tableMember.setRowHeight(26);
					scrollPaneMember.setViewportView(tableMember);
					lblAddMemberPrompt.setText("*添加成功！可继续添加！");
				}
			}
		});
		panelAddMember.add(btnAddSubmit);

		textFieldAddName = new JTextField();
		textFieldAddName.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textFieldAddName.setBackground(Color.WHITE);
		textFieldAddName.setColumns(10);
		textFieldAddName.setBounds(120, 160, 150, 30);
		panelAddMember.add(textFieldAddName);

		JLabel label_5 = new JLabel("姓   名：");
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_5.setBounds(60, 160, 60, 30);
		panelAddMember.add(label_5);

		JPanel panelUpdMember = new JPanel();
		panelUpdMember.setBackground(Color.WHITE);
		tabbedPaneUDR.addTab("修改员工", null, panelUpdMember, null);
		panelUpdMember.setLayout(null);

		final JLabel lblUpdPrompt = new JLabel("提示：修改员工");
		lblUpdPrompt.setForeground(Color.RED);
		lblUpdPrompt.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblUpdPrompt.setBackground(Color.WHITE);
		lblUpdPrompt.setBounds(60, 0, 210, 30);
		panelUpdMember.add(lblUpdPrompt);

		JLabel label_7 = new JLabel("(必填)账户名：");
		label_7.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_7.setBounds(29, 40, 91, 30);
		panelUpdMember.add(label_7);

		textFieldUpdByUserName = new JTextField();
		textFieldUpdByUserName.setColumns(10);
		textFieldUpdByUserName.setBounds(120, 40, 150, 30);
		panelUpdMember.add(textFieldUpdByUserName);

		JLabel label_8 = new JLabel("新密码：");
		label_8.setForeground(Color.RED);
		label_8.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_8.setBounds(60, 80, 60, 30);
		panelUpdMember.add(label_8);

		textFieldUpdPassword = new JTextField();
		textFieldUpdPassword.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textFieldUpdPassword.setForeground(Color.LIGHT_GRAY);
		textFieldUpdPassword.setText("不填默认不做修改");
		textFieldUpdPassword.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (Tools.isEmpty(textFieldUpdPassword.getText().trim())) {
					textFieldUpdPassword.setText("不填默认不做修改");
					textFieldUpdPassword.setForeground(Color.GRAY);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if ("不填默认不做修改".equals(textFieldUpdPassword.getText().trim())) {
					textFieldUpdPassword.setText("");
				}
				textFieldUpdPassword.setForeground(Color.BLACK);
			}
		});
		textFieldUpdPassword.setColumns(10);
		textFieldUpdPassword.setBounds(120, 80, 150, 30);
		panelUpdMember.add(textFieldUpdPassword);

		JLabel label_10 = new JLabel("新姓名：");
		label_10.setForeground(Color.RED);
		label_10.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_10.setBounds(60, 120, 60, 30);
		panelUpdMember.add(label_10);

		textFieldUpdName = new JTextField();
		textFieldUpdName.setForeground(Color.LIGHT_GRAY);
		textFieldUpdName.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textFieldUpdName.setText("不填默认不做修改");
		textFieldUpdName.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (Tools.isEmpty(textFieldUpdName.getText().trim())) {
					textFieldUpdName.setText("不填默认不做修改");
					textFieldUpdName.setForeground(Color.GRAY);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if ("不填默认不做修改".equals(textFieldUpdName.getText().trim())) {
					textFieldUpdName.setText("");
				}
				textFieldUpdName.setForeground(Color.BLACK);
			}
		});
		textFieldUpdName.setColumns(10);
		textFieldUpdName.setBounds(120, 120, 150, 30);
		panelUpdMember.add(textFieldUpdName);

		JLabel label_11 = new JLabel("(必选)新权限：");
		label_11.setForeground(Color.RED);
		label_11.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_11.setBounds(29, 160, 91, 30);
		panelUpdMember.add(label_11);

		final JComboBox cboUpdRole = new JComboBox();
		cboUpdRole.setBackground(Color.WHITE);
		cboUpdRole.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		cboUpdRole.setModel(new DefaultComboBoxModel(new String[] { "收银员", "仓管员", "高层" }));
		cboUpdRole.setBounds(120, 160, 150, 30);
		panelUpdMember.add(cboUpdRole);

		JButton button = new JButton("提交");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String condition[] = new String[4];
				String textUpdByUserName = textFieldUpdByUserName.getText();
				if (Tools.isEmpty(textUpdByUserName) || !mbiz.userNameExisted(textUpdByUserName)) {
					lblUpdPrompt.setText("找不到此用户名！");
					return;
				}
				String textUpdPassword = textFieldUpdPassword.getText();
				if (!Tools.isEmpty(textUpdPassword) && !"不填默认不做修改".equals(textUpdPassword)) {
					// UPDATE `store`.`member` SET `password`='321',
					// `name`='李家', `role`='1' WHERE `memberId`='10000006';
					condition[0] = textUpdPassword;
				}
				String textdUpdName = textFieldUpdName.getText();
				if (!Tools.isEmpty(textdUpdName) && !"不填默认不做修改".equals(textdUpdName)) {
					condition[1] = textdUpdName;
				}
				int index = cboUpdRole.getSelectedIndex();
				condition[2] = index + "";
				condition[3] = textUpdByUserName;
				mbiz.updMember(condition);
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button.setBackground(Color.RED);
		button.setBounds(120, 250, 150, 30);
		panelUpdMember.add(button);

		JPanel panelDelMember = new JPanel();
		panelDelMember.setBackground(Color.WHITE);
		tabbedPaneUDR.addTab("删除员工", null, panelDelMember, null);
		panelDelMember.setLayout(null);

		textFieldDelUserName = new JTextField();
		textFieldDelUserName.setColumns(10);
		textFieldDelUserName.setBounds(80, 40, 200, 30);
		panelDelMember.add(textFieldDelUserName);

		JLabel label_12 = new JLabel("删除账户：");
		label_12.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_12.setBounds(10, 40, 70, 30);
		panelDelMember.add(label_12);

		JLabel lblNewLabel_2 = new JLabel("*删除后不能恢复，请谨慎操作！");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(80, 134, 204, 30);
		panelDelMember.add(lblNewLabel_2);

		JLabel label_13 = new JLabel("验证码为：");
		label_13.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_13.setBounds(10, 90, 70, 30);
		panelDelMember.add(label_13);

		textFieldDelCaptcha = new JTextField();
		textFieldDelCaptcha.setColumns(10);
		textFieldDelCaptcha.setBounds(80, 90, 100, 30);
		panelDelMember.add(textFieldDelCaptcha);

		lblCaptcha = new JLabel(Tools.getCaptcha());
		lblCaptcha.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblCaptcha.setBounds(190, 94, 62, 30);
		panelDelMember.add(lblCaptcha);

		final JLabel lblDelPrompt = new JLabel("提示：修改员工");
		lblDelPrompt.setForeground(Color.RED);
		lblDelPrompt.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblDelPrompt.setBackground(Color.WHITE);
		lblDelPrompt.setBounds(80, 0, 120, 30);
		panelDelMember.add(lblDelPrompt);
		JButton btnDelConfirm = new JButton("确定删除");
		btnDelConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textDelUserName = textFieldDelUserName.getText().trim();
				String textDelCaptcha = textFieldDelCaptcha.getText();
				String lblDelCaptcha = lblCaptcha.getText();
				if (Tools.isEmpty(textDelUserName) || !mbiz.userNameExisted(textDelUserName)) {
					lblDelPrompt.setText("用户名错误或不存在！");
					return;
				}
				if (!textDelCaptcha.equals(lblDelCaptcha)) {
					lblDelPrompt.setText("验证码错误！");
					return;
				}
				if (mbiz.delMember(textDelUserName)) {
					lblDelPrompt.setText("删除成功！可继续删除！");
					textFieldDelUserName.setText("");
					textFieldDelCaptcha.setText("");
				} else {
					lblDelPrompt.setText("删除失败！请尽快联系技术人员：15266668888");
				}

			}
		});
		btnDelConfirm.setBackground(Color.RED);
		btnDelConfirm.setForeground(Color.WHITE);
		btnDelConfirm.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnDelConfirm.setBounds(90, 230, 150, 30);
		panelDelMember.add(btnDelConfirm);

		JButton btnRefreshCaptcha = new JButton("");
		btnRefreshCaptcha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblCaptcha.setText(Tools.getCaptcha());

			}
		});
		btnRefreshCaptcha.setIcon(new ImageIcon("Store\\newCaptcha.jpg"));
		btnRefreshCaptcha.setBounds(250, 90, 30, 30);
		panelDelMember.add(btnRefreshCaptcha);

		JPanel panelWorkRecord = new JPanel();
		panelWorkRecord.setBackground(Color.WHITE);
		tabbedPane.addTab("出勤记录", null, panelWorkRecord, null);
		panelWorkRecord.setLayout(null);

		JLabel label_19 = new JLabel("*严格要求每个员工同时也关爱体量他们！");
		label_19.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_19.setForeground(Color.RED);
		label_19.setBackground(Color.WHITE);
		label_19.setBounds(10, 0, 280, 30);
		panelWorkRecord.add(label_19);

		JScrollPane scrollPaneWork = new JScrollPane();
		scrollPaneWork.setBounds(10, 40, 935, 330);
		panelWorkRecord.add(scrollPaneWork);
		tableWork = new JTable();
		tableWork.setModel(dtmw);
		tableWork.setRowHeight(26);
		scrollPaneWork.setViewportView(tableWork);

		JPanel panelSaleRecord = new JPanel();
		panelSaleRecord.setBackground(Color.WHITE);
		tabbedPane.addTab("销售记录", null, panelSaleRecord, null);
		panelSaleRecord.setLayout(null);

		JLabel label_14 = new JLabel("*仔细查看销售记录能帮您更加了解市场变化！");
		label_14.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_14.setForeground(Color.RED);
		label_14.setBackground(Color.WHITE);
		label_14.setBounds(10, 0, 280, 30);
		panelSaleRecord.add(label_14);

		JScrollPane scrollPaneSale = new JScrollPane();
		scrollPaneSale.setBounds(10, 40, 600, 330);
		panelSaleRecord.add(scrollPaneSale);
		tableSale = new JTable();
		tableSale.setModel(dtms);
		tableSale.setRowHeight(26);
		scrollPaneSale.setViewportView(tableSale);
		
		JButton btnPie = new JButton("生成销售商品类别图表");
		btnPie.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnPie.setBackground(Color.LIGHT_GRAY);
		btnPie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MyJFreeChartPie freeChart =  new MyJFreeChartPie();
				
			}
		});
		btnPie.setBounds(638, 70, 289, 76);
		panelSaleRecord.add(btnPie);
		
		JButton btnHistogram = new JButton("生成商品各个种类销售额比例柱状图");
		btnHistogram.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnHistogram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MyJFreeChartHistogram mh = new MyJFreeChartHistogram();

			}
		});
		btnHistogram.setBackground(Color.LIGHT_GRAY);
		btnHistogram.setBounds(638, 209, 289, 76);
		panelSaleRecord.add(btnHistogram);

		JPanel panelTop = new JPanel();
		panelTop.setBackground(Color.WHITE);
		contentPane.add(panelTop, BorderLayout.NORTH);

		JLabel label_9 = new JLabel("");
		label_9.setIcon(new ImageIcon("Store\\titleboss.jpg"));
		panelTop.add(label_9);

		JPanel panelButtom = new JPanel();
		panelButtom.setBackground(Color.WHITE);
		contentPane.add(panelButtom, BorderLayout.SOUTH);

		JLabel label_20 = new JLabel("版权所有");
		label_20.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label_20, BorderLayout.SOUTH);
		label_20.setFont(new Font("新宋体", Font.BOLD, 20));
		label_20.setBackground(Color.WHITE);

		setVisible(true);
	}

	private Vector<String> getColumnSale() {
		Vector<String> v = new Vector<String>();
		v.add("销售编号");
		v.add("订单号");
		v.add("货物编号");
		v.add("售出数量");
		v.add("小计");
		return v;
	}

	private Vector<String> getColumnWork() {
		Vector<String> v = new Vector<String>();
		v.add("编号");
		v.add("工号");
		v.add("上班时间");
		v.add("上班时长");
		return v;
	}

	private Vector<String> getColumn() {
		Vector<String> v = new Vector<String>();
		v.add("编号");
		v.add("用户名");
		v.add("姓名");
		v.add("角色");
		v.add("状态");
		return v;
	}

	private Vector<Vector<Object>> getRowsWork(List<WorkRecord> listsWork) {
		WorkRecordBiz wbiz = new WorkRecordBiz();
		return wbiz.queryAllWorkToVector(listsWork);
	}

	private Vector<Vector<Object>> getRowsSale(List<SaleRecord> listsSale) {
		SaleRecordBiz sbiz = new SaleRecordBiz();
		return sbiz.queryAllSaleToVector(listsSale);
	}

	// 行的数据
	private Vector<Vector<Object>> getRows(List<Member> listsMember) {
		MemberBiz mbiz = new MemberBiz();

		return mbiz.querAllMembers(listsMember);
	}
}

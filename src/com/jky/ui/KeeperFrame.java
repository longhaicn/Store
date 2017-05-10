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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

import com.jky.biz.GoodsBiz;
import com.jky.biz.KindBiz;
import com.jky.biz.MemberBiz;
import com.jky.biz.PositionBiz;
import com.jky.biz.WorkRecordBiz;
import com.jky.entity.Goods;
import com.jky.entity.GoodsPlus;
import com.jky.entity.Kind;
import com.jky.entity.Position;
import com.jky.entity.WorkRecord;
import com.jky.utils.Tools;
import java.awt.Toolkit;

public class KeeperFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldKindName;
	private JTextField textFieldGoodsSearch;
	private GoodsBiz gbiz = new GoodsBiz();
	private KindBiz kbiz = new KindBiz();
	private PositionBiz pbiz = new PositionBiz();
	private String condition;
	private boolean actionAllow = true;
	private JTable tableGoods;
	private JTable tableKind;
	private JTable tablePosition;
	private List<GoodsPlus> listsGoods = gbiz.queryAllGoods("0");
	private List<Kind> listsKind = kbiz.queryAllKind("0");
	private List<Position> listsPosition = pbiz.queryAllPosition("0");
	private JComboBox cboAddRole;
	private JTextField textFieldAddGoodsName;
	private JTextField textFieldAddGoodsBarcode;
	private JTextField textFieldAddGoodsPrice;
	private JTextField textFieldAddGoodsNumber;
	private JComboBox cboPosition;
	private JTextField textFieldUpdGoodsBarCode;
	private JTextField textFieldUpdGoodsName;
	private JTextField textFieldUpdGoodsPrice;
	private JTextField textFieldUpdGoodsNumber;
	private JTextField textFieldBarCode;
	private int selectedRow;
	private int selectedRowKind;
	private JTextField textFieldPosition;
	private int selectedRowPosition;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KeeperFrame frame = new KeeperFrame(20160002, "陈世美");
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
	public KeeperFrame(final int memberId, String name) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Store\\logo.jpg"));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
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
					MemberBiz mbiz = new MemberBiz();
					mbiz.updMember(memberId,false);
				} catch (ParseException e1) {
					System.out.println("erro");
					e1.printStackTrace();
				}
			}
		});

		setBackground(Color.WHITE);
		setTitle("欢迎登陆乐尔乐商城智慧仓库！");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("乐尔乐商城——工号：" + memberId + " - " + name + "-仓管员正在服务......");
		setBounds(180, 30, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPaneMain = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneMain.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		tabbedPaneMain.setBackground(Color.WHITE);
		contentPane.add(tabbedPaneMain, BorderLayout.CENTER);

		JPanel panelGoods = new JPanel();
		panelGoods.setBackground(Color.WHITE);
		tabbedPaneMain.addTab("管理商品", null, panelGoods, null);
		panelGoods.setLayout(null);

		JScrollPane scrollPaneGoods = new JScrollPane();
		scrollPaneGoods.setBounds(10, 70, 600, 300);
		panelGoods.add(scrollPaneGoods);

		final DefaultTableModel dtmGoods = new DefaultTableModel();
		dtmGoods.setDataVector(getGoodsRows(listsGoods), getGoodsColumn());
		tableGoods = new JTable();
		tableGoods.setModel(dtmGoods);
		tableGoods.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableGoods.setRowHeight(26);
		tableGoods.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				super.mouseClicked(arg0);

				selectedRow = tableGoods.getSelectedRow();
				Object valueAt = tableGoods.getModel().getValueAt(selectedRow, 2);
				textFieldBarCode.setText("" + valueAt);
				System.out.println(selectedRow + "" + valueAt);
			}
		});
		scrollPaneGoods.setViewportView(tableGoods);

		JLabel lblGoodsPrompt = new JLabel("*开始管理商品吧！");
		lblGoodsPrompt.setForeground(Color.RED);
		lblGoodsPrompt.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblGoodsPrompt.setBackground(Color.WHITE);
		lblGoodsPrompt.setBounds(10, 0, 280, 30);
		panelGoods.add(lblGoodsPrompt);

		JLabel label_10 = new JLabel("排序：");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setForeground(Color.BLUE);
		label_10.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_10.setBounds(10, 30, 40, 30);
		panelGoods.add(label_10);

		final JComboBox cboGoodsOrder = new JComboBox();
		cboGoodsOrder.setModel(new DefaultComboBoxModel(new String[] { "按编号排序", "按单价排序", "按库存排序", "按货架排序" }));
		cboGoodsOrder.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		cboGoodsOrder.setBackground(Color.WHITE);
		cboGoodsOrder.setBounds(60, 30, 150, 30);
		cboGoodsOrder.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (actionAllow) {
					int index = cboGoodsOrder.getSelectedIndex();
					condition = "" + index;
					listsGoods = gbiz.queryAllGoods(condition);
					dtmGoods.setDataVector(getGoodsRows(listsGoods), getGoodsColumn());
					tableGoods.getColumnModel().getColumn(2).setPreferredWidth(150);
					actionAllow = false;
				} else {
					actionAllow = true;
				}

			}
		});
		panelGoods.add(cboGoodsOrder);

		textFieldGoodsSearch = new JTextField();
		textFieldGoodsSearch.setText("请输入商品名称/二维码/货架名");
		textFieldGoodsSearch.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldGoodsSearch.setForeground(Color.GRAY);
		textFieldGoodsSearch.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textFieldGoodsSearch.setColumns(10);
		textFieldGoodsSearch.setBounds(340, 30, 190, 30);
		textFieldGoodsSearch.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
				condition = textFieldGoodsSearch.getText().trim();
				if (Tools.isEmpty(condition)) {
					textFieldGoodsSearch.setText("请输入商品名称/二维码/货架名");
					textFieldGoodsSearch.setForeground(Color.GRAY);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if ("请输入商品名称/二维码/货架名".equals(textFieldGoodsSearch.getText().trim())) {
					textFieldGoodsSearch.setText("");
				}
				textFieldGoodsSearch.setForeground(Color.BLACK);
			}
		});
		panelGoods.add(textFieldGoodsSearch);

		JLabel label_11 = new JLabel("搜索：");
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		label_11.setForeground(Color.BLUE);
		label_11.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_11.setBackground(Color.WHITE);
		label_11.setBounds(290, 30, 40, 30);
		panelGoods.add(label_11);

		JButton btnGoodsSearch = new JButton("搜索");
		btnGoodsSearch.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnGoodsSearch.setBackground(Color.LIGHT_GRAY);
		btnGoodsSearch.setBounds(540, 30, 70, 30);
		btnGoodsSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (!condition.equals("请输入商品名称/二维码/货架名")) {
					listsGoods = gbiz.queryAllGoods(condition);
					dtmGoods.setDataVector(getGoodsRows(listsGoods), getGoodsColumn());
					tableGoods.getColumnModel().getColumn(2).setPreferredWidth(150);
				}
			}
		});
		panelGoods.add(btnGoodsSearch);

		JTabbedPane tabbedPaneGoods = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneGoods.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		tabbedPaneGoods.setBackground(Color.WHITE);
		tabbedPaneGoods.setBounds(630, 20, 330, 350);
		panelGoods.add(tabbedPaneGoods);

		JPanel panel = new JPanel();
		tabbedPaneGoods.addTab("添加商品", null, panel, null);
		panel.setLayout(null);

		final JLabel lblAddGoodsPrompt = new JLabel("提示：以下内容不能为空");
		lblAddGoodsPrompt.setForeground(Color.RED);
		lblAddGoodsPrompt.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblAddGoodsPrompt.setBackground(Color.WHITE);
		lblAddGoodsPrompt.setBounds(40, 0, 255, 30);
		panel.add(lblAddGoodsPrompt);

		JLabel label_13 = new JLabel("商品名称：");
		label_13.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_13.setBounds(40, 80, 60, 30);
		panel.add(label_13);

		textFieldAddGoodsName = new JTextField();
		textFieldAddGoodsName.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textFieldAddGoodsName.setColumns(10);
		textFieldAddGoodsName.setBounds(100, 80, 150, 30);
		panel.add(textFieldAddGoodsName);

		textFieldAddGoodsBarcode = new JTextField();
		textFieldAddGoodsBarcode.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textFieldAddGoodsBarcode.setColumns(10);
		textFieldAddGoodsBarcode.setBounds(100, 40, 150, 30);
		panel.add(textFieldAddGoodsBarcode);

		JLabel label_14 = new JLabel("   二维码：");
		label_14.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_14.setBounds(40, 40, 60, 30);
		panel.add(label_14);

		JLabel label_15 = new JLabel("商品单价：");
		label_15.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_15.setBounds(40, 120, 60, 30);
		panel.add(label_15);

		textFieldAddGoodsPrice = new JTextField();
		textFieldAddGoodsPrice.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textFieldAddGoodsPrice.setColumns(10);
		textFieldAddGoodsPrice.setBounds(100, 120, 150, 30);
		panel.add(textFieldAddGoodsPrice);

		JLabel label_16 = new JLabel("商品数量：");
		label_16.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_16.setBounds(40, 160, 60, 30);
		panel.add(label_16);

		textFieldAddGoodsNumber = new JTextField();
		textFieldAddGoodsNumber.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textFieldAddGoodsNumber.setColumns(10);
		textFieldAddGoodsNumber.setBackground(Color.WHITE);
		textFieldAddGoodsNumber.setBounds(100, 160, 150, 30);
		panel.add(textFieldAddGoodsNumber);

		JLabel label_17 = new JLabel("放置货架：");
		label_17.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_17.setBounds(40, 200, 60, 30);
		panel.add(label_17);

		int length = listsKind.size();
		int i = 0;
		String kindCbo[] = new String[length];
		for (Kind kind : listsKind) {
			// 一包烟
			kindCbo[i] = kind.getName();
			i++;

		}

		cboPosition = new JComboBox();
		cboPosition.setModel(new DefaultComboBoxModel(kindCbo));
		cboPosition.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		cboPosition.setBackground(Color.WHITE);
		cboPosition.setBounds(100, 241, 150, 30);
		cboPosition.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				System.out.println(cboPosition.getSelectedIndex());
			}
		});
		panel.add(cboPosition);

		final JComboBox cboKind = new JComboBox();
		int lengthPosition = listsPosition.size();
		int indexPosition = 0;
		String positionCbo[] = new String[lengthPosition];
		for (Position position : listsPosition) {
			// 一包烟
			positionCbo[indexPosition] = position.getName();

			indexPosition++;

		}
		cboKind.setModel(new DefaultComboBoxModel(positionCbo));
		cboKind.setFont(new Font("微软雅黑", Font.PLAIN, 12));

		cboKind.setBackground(Color.WHITE);
		cboKind.setBounds(100, 200, 150, 30);
		cboKind.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				System.out.println(cboKind.getSelectedIndex());

			}
		});

		panel.add(cboKind);

		JButton btnAddGoods = new JButton("提交");
		btnAddGoods.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String textAddGoodsName = textFieldAddGoodsName.getText();
				if (Tools.isEmpty(textAddGoodsName)) {
					textFieldAddGoodsName.setText("");
					lblAddGoodsPrompt.setText("*商品名称不能为空！");
					return;
				}
				String textAddGoodsBarcode = textFieldAddGoodsBarcode.getText();
				if (Tools.isEmpty(textAddGoodsBarcode) || textAddGoodsBarcode.length() != 21) {
					textFieldAddGoodsBarcode.setText("");
					lblAddGoodsPrompt.setText("*二维码错误，请检查填写！");
					return;
				}
				String textAddGoodsNumber = textFieldAddGoodsNumber.getText();
				if (Tools.isEmpty(textAddGoodsNumber)) {
					textFieldAddGoodsNumber.setText("");
					lblAddGoodsPrompt.setText("*商品数量不能为空！");
					return;
				}
				String textAddGoodsPrice = textFieldAddGoodsPrice.getText();
				if (Tools.isEmpty(textAddGoodsPrice)) {
					textFieldAddGoodsPrice.setText("");
					lblAddGoodsPrompt.setText("*商品单价不能为空！");
					return;
				}
				Goods goods = new Goods();
				goods.setBarCode(textAddGoodsBarcode);
				goods.setName(textAddGoodsName);
				goods.setPositionId(cboPosition.getSelectedIndex() + 1);
				goods.setKindId(cboKind.getSelectedIndex() + 1);
				goods.setPrice(new Float(textAddGoodsPrice));
				goods.setNumber(Integer.parseInt(textAddGoodsNumber));
				gbiz.addGoods(goods);
				List<GoodsPlus> listsGoods = gbiz.queryAllGoods(textAddGoodsBarcode);
				textFieldAddGoodsName.setText("");
				textFieldAddGoodsBarcode.setText("");
				textFieldAddGoodsNumber.setText("");
				textFieldAddGoodsPrice.setText("");
			}
		});
		btnAddGoods.setForeground(Color.WHITE);
		btnAddGoods.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnAddGoods.setBackground(Color.RED);
		btnAddGoods.setBounds(100, 281, 150, 30);
		panel.add(btnAddGoods);

		JLabel label_18 = new JLabel("所属类别：");
		label_18.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_18.setBounds(40, 240, 60, 30);
		panel.add(label_18);

		JPanel panel_1 = new JPanel();
		tabbedPaneGoods.addTab("修改/删除商品", null, panel_1, null);
		panel_1.setLayout(null);

		final JPanel panelUpdGoods = new JPanel();
		panelUpdGoods.setBounds(0, 0, 325, 321);
		panel_1.add(panelUpdGoods);
		panelUpdGoods.setLayout(null);
		panelUpdGoods.setVisible(false);

		JLabel label_19 = new JLabel("提示：以下内容不能为空");
		label_19.setForeground(Color.RED);
		label_19.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_19.setBackground(Color.WHITE);
		label_19.setBounds(40, 0, 174, 30);
		panelUpdGoods.add(label_19);

		JLabel label_20 = new JLabel("   二维码：");
		label_20.setForeground(Color.BLACK);
		label_20.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_20.setBounds(40, 40, 60, 30);
		panelUpdGoods.add(label_20);

		textFieldUpdGoodsBarCode = new JTextField();
		textFieldUpdGoodsBarCode.setBackground(Color.WHITE);
		textFieldUpdGoodsBarCode.setEditable(false);
		textFieldUpdGoodsBarCode.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textFieldUpdGoodsBarCode.setColumns(10);
		textFieldUpdGoodsBarCode.setBounds(100, 40, 150, 30);
		panelUpdGoods.add(textFieldUpdGoodsBarCode);

		JLabel label_21 = new JLabel("商品名称：");
		label_21.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_21.setBounds(40, 80, 60, 30);
		panelUpdGoods.add(label_21);

		textFieldUpdGoodsName = new JTextField();
		textFieldUpdGoodsName.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textFieldUpdGoodsName.setColumns(10);
		textFieldUpdGoodsName.setBounds(100, 80, 150, 30);
		panelUpdGoods.add(textFieldUpdGoodsName);

		JLabel label_22 = new JLabel("商品单价：");
		label_22.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_22.setBounds(40, 120, 60, 30);
		panelUpdGoods.add(label_22);

		textFieldUpdGoodsPrice = new JTextField();
		textFieldUpdGoodsPrice.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textFieldUpdGoodsPrice.setColumns(10);
		textFieldUpdGoodsPrice.setBounds(100, 120, 150, 30);
		panelUpdGoods.add(textFieldUpdGoodsPrice);

		JLabel label_23 = new JLabel("商品数量：");
		label_23.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_23.setBounds(40, 160, 60, 30);
		panelUpdGoods.add(label_23);

		textFieldUpdGoodsNumber = new JTextField();
		textFieldUpdGoodsNumber.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textFieldUpdGoodsNumber.setColumns(10);
		textFieldUpdGoodsNumber.setBackground(Color.WHITE);
		textFieldUpdGoodsNumber.setBounds(100, 160, 150, 30);
		panelUpdGoods.add(textFieldUpdGoodsNumber);

		JLabel label_24 = new JLabel("放置货架：");
		label_24.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_24.setBounds(40, 200, 60, 30);
		panelUpdGoods.add(label_24);

		final JComboBox cboUpdGoodsPosition = new JComboBox();
		cboUpdGoodsPosition.setModel(new DefaultComboBoxModel(positionCbo));
		cboUpdGoodsPosition.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		cboUpdGoodsPosition.setBackground(Color.WHITE);
		cboUpdGoodsPosition.setBounds(100, 200, 150, 30);
		panelUpdGoods.add(cboUpdGoodsPosition);

		JLabel label_25 = new JLabel("所属类别：");
		label_25.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_25.setBounds(40, 240, 60, 30);
		panelUpdGoods.add(label_25);

		final JComboBox cboUpdGoodsKind = new JComboBox();
		cboUpdGoodsKind.setModel(new DefaultComboBoxModel(kindCbo));
		cboUpdGoodsKind.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		cboUpdGoodsKind.setBackground(Color.WHITE);
		cboUpdGoodsKind.setBounds(100, 240, 150, 30);
		panelUpdGoods.add(cboUpdGoodsKind);

		final JPanel panelUD = new JPanel();
		panelUD.setBounds(0, 0, 325, 321);
		panel_1.add(panelUD);
		panelUD.setLayout(null);

		final JLabel lblUpdGoodsSucces = new JLabel("");
		lblUpdGoodsSucces.setBounds(10, 40, 300, 30);
		panelUD.add(lblUpdGoodsSucces);

		JButton btnUpdConfirm = new JButton("保存修改");
		btnUpdConfirm.setForeground(Color.WHITE);
		btnUpdConfirm.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnUpdConfirm.setBackground(Color.RED);
		btnUpdConfirm.setBounds(170, 280, 100, 30);
		btnUpdConfirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String textUpdGoodsName = textFieldUpdGoodsName.getText().trim();
				if (Tools.isEmpty(textUpdGoodsName)) {
					textFieldUpdGoodsBarCode.setText("");
					lblAddGoodsPrompt.setText("*商品名称不能为空！");
					return;
				}
				String textUpdGoodsPrice = textFieldUpdGoodsPrice.getText();
				if (Tools.isEmpty(textUpdGoodsPrice)) {
					textFieldUpdGoodsPrice.setText("");
					lblAddGoodsPrompt.setText("*商品单价不能为空！");
					return;
				}
				String textUpdGoodsNumber = textFieldUpdGoodsNumber.getText();
				if (Tools.isEmpty(textUpdGoodsNumber)) {
					textFieldUpdGoodsNumber.setText("");
					lblAddGoodsPrompt.setText("*商品数量不能为空！");
					return;
				}
				Goods goods = new Goods();
				goods.setBarCode(textFieldUpdGoodsBarCode.getText());
				goods.setKindId(cboUpdGoodsKind.getSelectedIndex() + 1);
				goods.setName(textUpdGoodsName);
				goods.setPrice(new Float(textUpdGoodsPrice));
				goods.setPositionId(cboUpdGoodsPosition.getSelectedIndex() + 1);
				goods.setNumber(Integer.parseInt(textUpdGoodsNumber));
				if (gbiz.UpdGoods(goods)) {
					panelUpdGoods.setVisible(false);
					panelUD.setVisible(true);
					lblUpdGoodsSucces.setText("恭喜！修改成功！可继续选择操作！");
				}
			}
		});
		panelUpdGoods.add(btnUpdConfirm);
		JButton btnBack = new JButton("返回重选");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelUpdGoods.setVisible(false);
				panelUD.setVisible(true);
			}
		});
		btnBack.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnBack.setBackground(Color.GREEN);
		btnBack.setForeground(Color.WHITE);
		btnBack.setBounds(50, 280, 100, 30);
		panelUpdGoods.add(btnBack);

		JLabel lblNewLabel_2 = new JLabel("*请在左侧选中一行记录或者在下方输入商品二维码");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(10, 10, 300, 30);
		panelUD.add(lblNewLabel_2);

		textFieldBarCode = new JTextField();
		textFieldBarCode.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textFieldBarCode.setBounds(70, 80, 210, 30);
		panelUD.add(textFieldBarCode);
		textFieldBarCode.setColumns(10);

		JLabel label_26 = new JLabel("二维码：");
		label_26.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_26.setBounds(10, 80, 60, 30);
		panelUD.add(label_26);

		JButton btnNewButton = new JButton("修改");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelUpdGoods.setVisible(true);
				panelUD.setVisible(false);
				String textBarCode = textFieldBarCode.getText();
				if (Tools.isEmpty(textBarCode)) {
					return;
				}
				Goods good = gbiz.getTheGood(textBarCode);

				textFieldUpdGoodsName.setText(good.getName());
				textFieldUpdGoodsBarCode.setText(good.getBarCode());
				textFieldUpdGoodsPrice.setText("" + good.getPrice());
				textFieldUpdGoodsNumber.setText("" + good.getNumber());
				cboUpdGoodsKind.setSelectedIndex(good.getKindId() - 1);
				cboUpdGoodsPosition.setSelectedIndex(good.getPositionId() - 1);
			}
		});
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBounds(70, 200, 100, 30);
		panelUD.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("删除");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String barCode = textFieldBarCode.getText();
				if (Tools.isEmpty(barCode)) {
					return;
				}
				if (gbiz.delGoods(barCode)) {
					lblUpdGoodsSucces.setText("恭喜！删除成功！可继续选择操作！");
				}
			}
		});
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.setBounds(180, 200, 100, 30);
		panelUD.add(btnNewButton_1);

		JPanel panelKind = new JPanel();
		panelKind.setBackground(Color.WHITE);
		tabbedPaneMain.addTab("管理品类", null, panelKind, null);
		panelKind.setLayout(null);

		JLabel lblKindPrompt = new JLabel("*开始管理商品类型吧！");
		lblKindPrompt.setForeground(Color.RED);
		lblKindPrompt.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblKindPrompt.setBackground(Color.WHITE);
		lblKindPrompt.setBounds(10, 0, 280, 30);
		panelKind.add(lblKindPrompt);

		JScrollPane scrollPaneKind = new JScrollPane();
		scrollPaneKind.setBounds(10, 70, 600, 300);
		panelKind.add(scrollPaneKind);

		DefaultTableModel dtmKind = new DefaultTableModel();
		dtmKind.setDataVector(getKindRows(listsKind), getKindColumn());
		tableKind = new JTable();
		tableKind.setModel(dtmKind);
		tableKind.setRowHeight(26);
		tableKind.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);

				selectedRowKind = tableKind.getSelectedRow();
				Object valueAt1 = tableKind.getModel().getValueAt(selectedRowKind, 1);
				textFieldKindName.setText("" + valueAt1);

			}
		});
		scrollPaneKind.setViewportView(tableKind);

		JTabbedPane tabbedPaneKind = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneKind.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		tabbedPaneKind.setBackground(Color.WHITE);
		tabbedPaneKind.setBounds(630, 20, 330, 350);
		panelKind.add(tabbedPaneKind);

		JPanel panelKindUDR = new JPanel();
		tabbedPaneKind.addTab("添加/修改/删除商品种类", null, panelKindUDR, null);
		panelKindUDR.setLayout(null);
		panelKindUDR.setBackground(Color.WHITE);

		final JLabel lblKind = new JLabel("提示：");
		lblKind.setForeground(Color.RED);
		lblKind.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblKind.setBackground(Color.WHITE);
		lblKind.setBounds(60, 0, 255, 30);
		panelKindUDR.add(lblKind);

		JButton btnDelKind = new JButton("删除");
		btnDelKind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textKindName = textFieldKindName.getText();
				if (Tools.isEmpty(textKindName)) {
					return;
				}
				String kindId = tableKind.getModel().getValueAt(selectedRowKind, 0) + "";

				String condition[] = { "2", kindId, textKindName };
				if (kbiz.udrKind(condition)) {
					lblKind.setText("成功！");
				}

			}
		});
		btnDelKind.setForeground(Color.WHITE);
		btnDelKind.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnDelKind.setBackground(Color.RED);
		btnDelKind.setBounds(219, 254, 90, 30);
		panelKindUDR.add(btnDelKind);

		textFieldKindName = new JTextField();
		textFieldKindName.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textFieldKindName.setColumns(10);
		textFieldKindName.setBackground(Color.WHITE);
		textFieldKindName.setBounds(80, 81, 215, 30);
		panelKindUDR.add(textFieldKindName);

		JLabel label_8 = new JLabel("种类名称：");
		label_8.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_8.setBounds(10, 80, 60, 30);
		panelKindUDR.add(label_8);

		JButton btnAddKind = new JButton("添加");
		btnAddKind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textKindName = textFieldKindName.getText();
				if (Tools.isEmpty(textKindName)) {
					return;
				}
				String kindId = tableKind.getModel().getValueAt(selectedRowKind, 0) + "";

				String condition[] = { "0", kindId, textKindName };
				if (kbiz.udrKind(condition)) {
					lblKind.setText("成功！");
				}

			}
		});
		btnAddKind.setForeground(Color.WHITE);
		btnAddKind.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnAddKind.setBackground(Color.GREEN);
		btnAddKind.setBounds(13, 254, 90, 30);
		panelKindUDR.add(btnAddKind);

		JButton btnUpdKind = new JButton("修改");
		btnUpdKind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textKindName = textFieldKindName.getText();
				if (Tools.isEmpty(textKindName)) {
					return;
				}
				String kindId = tableKind.getModel().getValueAt(selectedRowKind, 0) + "";

				String condition[] = { "1", kindId, textKindName };
				if (kbiz.udrKind(condition)) {
					lblKind.setText("成功！");
				}

			}
		});
		btnUpdKind.setForeground(Color.WHITE);
		btnUpdKind.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnUpdKind.setBackground(Color.YELLOW);
		btnUpdKind.setBounds(116, 254, 90, 30);
		panelKindUDR.add(btnUpdKind);

		JPanel panelPosition = new JPanel();
		panelPosition.setBackground(Color.WHITE);
		tabbedPaneMain.addTab("管理货架", null, panelPosition, null);
		panelPosition.setLayout(null);

		JScrollPane scrollPanePosition = new JScrollPane();
		scrollPanePosition.setBounds(10, 70, 600, 300);
		panelPosition.add(scrollPanePosition);

		DefaultTableModel dtmPosition = new DefaultTableModel();
		dtmPosition.setDataVector(getPositionRows(listsPosition), getPositionColumn());
		tablePosition = new JTable();
		tablePosition.setModel(dtmPosition);
		tablePosition.setRowHeight(26);
		tablePosition.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				super.mouseClicked(arg0);
				selectedRowPosition = tablePosition.getSelectedRow();
				Object valueAt1 = tablePosition.getModel().getValueAt(selectedRowPosition, 1);
				textFieldPosition.setText("" + valueAt1);
			}
		});
		scrollPanePosition.setViewportView(tablePosition);

		JLabel label = new JLabel("*开始管理商品货架吧！");
		label.setBounds(10, 0, 280, 30);
		label.setForeground(Color.RED);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBackground(Color.WHITE);
		panelPosition.add(label);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(630, 20, 330, 350);
		panelPosition.add(tabbedPane);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("添加/修改/删除货架", null, panel_2, null);

		final JLabel lblPosition = new JLabel("提示：");
		lblPosition.setForeground(Color.RED);
		lblPosition.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblPosition.setBackground(Color.WHITE);
		lblPosition.setBounds(60, 0, 255, 30);
		panel_2.add(lblPosition);

		JButton btnDelPosition = new JButton("删除");
		btnDelPosition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textPosition = textFieldPosition.getText();
				if (Tools.isEmpty(textPosition)) {
					return;
				}
				String kindId = tablePosition.getModel().getValueAt(selectedRowKind, 0) + "";

				String condition[] = { "2", kindId, textPosition };
				if (pbiz.udrPosition(condition)) {
					lblPosition.setText("成功！");
				}

			}
		});
		btnDelPosition.setForeground(Color.WHITE);
		btnDelPosition.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnDelPosition.setBackground(Color.RED);
		btnDelPosition.setBounds(219, 254, 90, 30);
		panel_2.add(btnDelPosition);

		textFieldPosition = new JTextField();
		textFieldPosition.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textFieldPosition.setColumns(10);
		textFieldPosition.setBackground(Color.WHITE);
		textFieldPosition.setBounds(80, 81, 215, 30);
		panel_2.add(textFieldPosition);

		JLabel label_3 = new JLabel("货架名称：");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_3.setBounds(10, 80, 60, 30);
		panel_2.add(label_3);

		JButton btnAddPosition = new JButton("添加");
		btnAddPosition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textPosition = textFieldPosition.getText();
				if (Tools.isEmpty(textPosition)) {
					return;
				}
				String kindId = tablePosition.getModel().getValueAt(selectedRowKind, 0) + "";

				String condition[] = { "0", kindId, textPosition };
				if (pbiz.udrPosition(condition)) {
					lblPosition.setText("成功！");
				}

			}
		});

		btnAddPosition.setForeground(Color.WHITE);
		btnAddPosition.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnAddPosition.setBackground(Color.GREEN);
		btnAddPosition.setBounds(13, 254, 90, 30);
		panel_2.add(btnAddPosition);

		JButton btnUpdPosition = new JButton("修改");
		btnUpdPosition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textPosition = textFieldPosition.getText();
				if (Tools.isEmpty(textPosition)) {
					return;
				}
				String kindId = tablePosition.getModel().getValueAt(selectedRowKind, 0) + "";

				String condition[] = { "1", kindId, textPosition };
				if (pbiz.udrPosition(condition)) {
					lblPosition.setText("成功！");
				}

			}
		});
		btnUpdPosition.setForeground(Color.WHITE);
		btnUpdPosition.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnUpdPosition.setBackground(Color.YELLOW);
		btnUpdPosition.setBounds(116, 254, 90, 30);
		panel_2.add(btnUpdPosition);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Store\\keeper0.jpg"));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);

		JLabel lblNewLabel_1 = new JLabel("版权所有");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("新宋体", Font.BOLD, 20));
		contentPane.add(lblNewLabel_1, BorderLayout.SOUTH);
		setVisible(true);
	}

	private Vector<String> getPositionColumn() {
		Vector<String> v = new Vector<String>();
		v.add("货架编号");
		v.add("货架名称");

		return v;
	}

	private Vector<Vector<Object>> getPositionRows(List<Position> listsPosition) {
		PositionBiz pbiz = new PositionBiz();
		return pbiz.queryAllPositionToVector(listsPosition);
	}

	private Vector<String> getKindColumn() {
		Vector<String> v = new Vector<String>();
		v.add("品类编号");
		v.add("品类类名");

		return v;
	}

	private Vector<Vector<Object>> getKindRows(List<Kind> listsKind) {
		KindBiz kbiz = new KindBiz();
		return kbiz.queryAllKindToVector(listsKind);
	}

	private Vector<String> getGoodsColumn() {
		Vector<String> v = new Vector<String>();
		v.add("商品编号");
		v.add("商品名称");
		v.add("二维码");
		v.add("商品单价/元");
		v.add("商品库存/件");
		v.add("所在货架");
		v.add("商品种类");
		return v;
	}

	private Vector<Vector<Object>> getGoodsRows(List<GoodsPlus> listsGoods) {
		GoodsBiz gbiz = new GoodsBiz();
		return gbiz.queryAllGoodsToVector(listsGoods);
	}
}

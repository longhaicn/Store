package com.jky.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLayeredPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KeeperFrameDemo extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSearch;
	private JTextField textFieldGoodsName;
	private JTextField textFieldGoodsBarcode;
	private JTextField textFieldGoodsPrice;
	private JTextField textFieldGoodsNumber;
	private JTextField textFieldGoodsPosition;
	private JTextField textFieldGoodsKind;
	private JTable tablePosition;
	private JTextField textFieldPositionId;
	private JTextField textFieldPositionName;
	private JPanel panelGoods;
	private JPanel panelPosition;
	
	String[] tableRows =new String[] {
			"ID", "NAME"
		};
	
	
	/**0：初始化，1：管理货架，2：管理品类，3：管理商品*/
	private int action = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KeeperFrameDemo frame = new KeeperFrameDemo(10000002,"iKeeper");
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
	public KeeperFrameDemo(int memberId,String name) {
		setTitle("欢迎登陆乐尔乐商城智慧仓库！");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("乐尔乐商城——工号："+memberId+" - "+name+"仓管员正在服务......");
		setBounds(180, 30, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelTop = new JPanel();
		panelTop.setBackground(Color.WHITE);
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		JLabel lblTop = new JLabel("");
		lblTop.setIcon(new ImageIcon("C:\\Users\\Dijkstra\\workspace\\Store\\Store\\keeper.jpg"));
		panelTop.add(lblTop);
		
		JPanel panelBottom = new JPanel();
		panelBottom.setBackground(Color.WHITE);
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		
		JLabel lblBottom = new JLabel("");
		lblBottom.setIcon(new ImageIcon("C:\\Users\\Dijkstra\\workspace\\Store\\Store\\button.jpg"));
		panelBottom.add(lblBottom);
		
		JPanel panelCenter = new JPanel();
		panelCenter.setBackground(Color.WHITE);
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);
		//管理货架
		JButton btnPosition = new JButton("管理货架");
		btnPosition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				action=1;
				panelGoods.setVisible(false);
				panelPosition.setVisible(true);
			}
		});
		btnPosition.setFont(new Font("微软雅黑", Font.BOLD, 12));
		btnPosition.setForeground(Color.WHITE);
		btnPosition.setBackground(Color.DARK_GRAY);
		btnPosition.setBounds(59, 52, 90, 30);
		panelCenter.add(btnPosition);
		//管理品类
		JButton btnKind = new JButton("管理品类");
		btnKind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				action=2;
				panelGoods.setVisible(false);
				panelPosition.setVisible(true);
			}
		});
		btnKind.setForeground(Color.WHITE);
		btnKind.setBackground(Color.BLACK);
		btnKind.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnKind.setBounds(59, 131, 90, 30);
		panelCenter.add(btnKind);
		//管理商品
		JButton btnGoods = new JButton("管理商品");
		btnGoods.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				action=3;
				panelPosition.setVisible(false);
				panelGoods.setVisible(true);
			}
		});
		btnGoods.setForeground(Color.WHITE);
		btnGoods.setBackground(Color.BLACK);
		btnGoods.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnGoods.setBounds(59, 210, 90, 30);
		panelCenter.add(btnGoods);
		
		JButton btnNewButton = new JButton("修改");
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(824, 210, 90, 30);
		panelCenter.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("删除");
		btnNewButton_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setBounds(824, 131, 90, 30);
		panelCenter.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("添加");
		btnNewButton_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnNewButton_3.setBackground(Color.LIGHT_GRAY);
		btnNewButton_3.setBounds(824, 52, 90, 30);
		panelCenter.add(btnNewButton_3);
		
		panelGoods = new JPanel();
		panelGoods.setBackground(Color.WHITE);
		panelGoods.setBounds(187, 0, 600, 326);
		panelCenter.add(panelGoods);
		panelGoods.setLayout(null);
		panelGoods.setVisible(false);
		
		textFieldSearch = new JTextField();
		textFieldSearch.setBounds(100, 0, 400, 30);
		panelGoods.add(textFieldSearch);
		textFieldSearch.setBackground(Color.WHITE);
		textFieldSearch.setColumns(10);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setBounds(0, 0, 100, 30);
		panelGoods.add(lblIsbn);
		lblIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		lblIsbn.setFont(new Font("Perpetua Titling MT", Font.BOLD, 26));
		lblIsbn.setForeground(Color.BLACK);
		lblIsbn.setBackground(Color.WHITE);
		
		JButton btnNewButton_4 = new JButton("查询");
		btnNewButton_4.setBounds(510, 0, 90, 30);
		panelGoods.add(btnNewButton_4);
		btnNewButton_4.setBackground(Color.RED);
		btnNewButton_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnNewButton_4.setForeground(Color.WHITE);
		
		textFieldGoodsName = new JTextField();
		textFieldGoodsName.setBounds(100, 58, 400, 30);
		panelGoods.add(textFieldGoodsName);
		textFieldGoodsName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("二维码：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 102, 80, 30);
		panelGoods.add(lblNewLabel);
		
		JLabel label = new JLabel("商品名称：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(10, 58, 80, 30);
		panelGoods.add(label);
		
		textFieldGoodsBarcode = new JTextField();
		textFieldGoodsBarcode.setColumns(10);
		textFieldGoodsBarcode.setBounds(100, 102, 400, 30);
		panelGoods.add(textFieldGoodsBarcode);
		
		JLabel label_1 = new JLabel("商品单价：");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_1.setBounds(10, 146, 80, 30);
		panelGoods.add(label_1);
		
		textFieldGoodsPrice = new JTextField();
		textFieldGoodsPrice.setColumns(10);
		textFieldGoodsPrice.setBounds(100, 146, 400, 30);
		panelGoods.add(textFieldGoodsPrice);
		
		JLabel label_2 = new JLabel("商品数量：");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_2.setBounds(10, 190, 80, 30);
		panelGoods.add(label_2);
		
		textFieldGoodsNumber = new JTextField();
		textFieldGoodsNumber.setColumns(10);
		textFieldGoodsNumber.setBounds(100, 190, 400, 30);
		panelGoods.add(textFieldGoodsNumber);
		
		JLabel label_3 = new JLabel("所在货架：");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_3.setBounds(10, 234, 80, 30);
		panelGoods.add(label_3);
		
		textFieldGoodsPosition = new JTextField();
		textFieldGoodsPosition.setColumns(10);
		textFieldGoodsPosition.setBounds(100, 234, 400, 30);
		panelGoods.add(textFieldGoodsPosition);
		
		JLabel label_4 = new JLabel("商品类别：");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_4.setBounds(10, 278, 80, 30);
		panelGoods.add(label_4);
		
		textFieldGoodsKind = new JTextField();
		textFieldGoodsKind.setColumns(10);
		textFieldGoodsKind.setBounds(100, 278, 400, 30);
		panelGoods.add(textFieldGoodsKind);
		
		panelPosition = new JPanel();
		panelPosition.setBackground(Color.WHITE);
		panelPosition.setBounds(187, 0, 600, 326);
		panelCenter.add(panelPosition);
		panelPosition.setLayout(null);
		panelPosition.setVisible(false);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 600, 260);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setToolTipText("商品货架");
		scrollPane.setPreferredSize(new Dimension(600, 260));
		panelPosition.add(scrollPane);
		
		tablePosition = new JTable();
		tablePosition.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			tableRows
		));
		tablePosition.setRowHeight(30);
		scrollPane.setViewportView(tablePosition);
		
		textFieldPositionId = new JTextField();
		textFieldPositionId.setBackground(Color.YELLOW);
		textFieldPositionId.setBounds(0, 274, 292, 30);
		panelPosition.add(textFieldPositionId);
		textFieldPositionId.setColumns(10);
		
		textFieldPositionName = new JTextField();
		textFieldPositionName.setBackground(Color.YELLOW);
		textFieldPositionName.setBounds(292, 274, 292, 30);
		panelPosition.add(textFieldPositionName);
		textFieldPositionName.setColumns(10);
		setVisible(true);
		}
}

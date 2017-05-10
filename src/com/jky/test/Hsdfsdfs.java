//package com.jky.ui;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.EventQueue;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JDialog;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.SwingConstants;
//import javax.swing.border.EmptyBorder;
//
//import com.jky.biz.CheckBiz;
//import com.jky.biz.GoodsBiz;
//import com.jky.biz.SaleRecordBiz;
//import com.jky.entity.Goods;
//import com.jky.utils.Tools;
//
//public class CasherFrame extends JFrame {
//
//	private JPanel contentPane;
//	private JTextField textFieldBarCode;
//	private JTextField textFieldGoodsName;
//	private JTextField textFieldGoodsPrice;
//	private JTextField textFieldBuyNumber;
//	/**saleRecord用于存储购物车商品列表信息0：商品ID	1：商品单价	2：商品卖出数量	3：商品存货数量*/
//	protected static float saleRecord[][]=new float[9][4];
//	protected static int i=0;
//	protected static Goods goods;
//	private static float sum=0;
//	private static String goodsShowString="";
//	private JLabel lblGoodsShow;
//	private JLabel lblGoodsSum;
//	private static String goodsShow[]={"①","②","③","④","⑤","⑥","⑦","⑧","⑨"};
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CasherFrame frame = new CasherFrame(10000001,"iCasher");
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 * @param string 
//	 * @param i 
//	 * @param i 
//	 * @param casherName 
//	 */
//	public CasherFrame(int casherId, String casherName) {
//		
//		setBackground(Color.WHITE);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(180, 30, 1000, 600);
//		setTitle("乐尔乐商城——工号："+casherId+" - "+casherName+"收银员正在服务......");
//		contentPane = new JPanel();
//		contentPane.setBackground(Color.WHITE);
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(new BorderLayout(0, 0));
//		
//		JPanel panelTop = new JPanel();
//		panelTop.setBackground(Color.WHITE);
//		contentPane.add(panelTop, BorderLayout.NORTH);
//		
//		JLabel label = new JLabel("");
//		label.setBackground(Color.WHITE);
//		label.setIcon(new ImageIcon("C:\\Users\\Dijkstra\\workspace\\Store\\Store\\title.jpg"));
//		panelTop.add(label);
//		
//		JPanel panel = new JPanel();
//		panel.setBackground(Color.WHITE);
//		contentPane.add(panel, BorderLayout.SOUTH);
//		
//		JLabel lblNewLabel = new JLabel("");
//		lblNewLabel.setBackground(Color.WHITE);
//		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Dijkstra\\workspace\\Store\\Store\\button.jpg"));
//		panel.add(lblNewLabel);
//		
//		JPanel panel_1 = new JPanel();
//		panel_1.setBackground(Color.WHITE);
//		contentPane.add(panel_1, BorderLayout.WEST);
//		
//		JLabel label_1 = new JLabel("");
//		label_1.setBackground(Color.WHITE);
//		label_1.setIcon(new ImageIcon("C:\\Users\\Dijkstra\\workspace\\Store\\Store\\barcodejpg.jpg"));
//		panel_1.add(label_1);
//		
//		JPanel panel_2 = new JPanel();
//		panel_2.setBackground(Color.WHITE);
//		contentPane.add(panel_2, BorderLayout.CENTER);
//		panel_2.setLayout(null);
//		
//		JLabel lblNewLabel_1 = new JLabel("二维码：");
//		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
//		lblNewLabel_1.setBounds(10, 0, 60, 30);
//		panel_2.add(lblNewLabel_1);
//		
//		textFieldBarCode = new JTextField();
//		textFieldBarCode.setText("ISBN978-7-5502-627-01");//测试数据
//		textFieldBarCode.setFont(new Font("微软雅黑", Font.PLAIN, 12));
//		textFieldBarCode.setBounds(70, 0, 300, 30);
//		panel_2.add(textFieldBarCode);
//		textFieldBarCode.setColumns(10);
//		
//		JButton btnGoodsConfirm = new JButton("输入商品");
//		btnGoodsConfirm.setForeground(Color.WHITE);
//		btnGoodsConfirm.setBackground(Color.RED);
//		btnGoodsConfirm.setFont(new Font("微软雅黑", Font.PLAIN, 20));
//		btnGoodsConfirm.setBounds(370, 0, 170, 30);
//		btnGoodsConfirm.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				String barCode = textFieldBarCode.getText().trim();
//				GoodsBiz gbz = new GoodsBiz();
//				goods = gbz.getTheGood(barCode);
//				System.out.println(goods.getPrice());
//				textFieldGoodsName.setText(goods.getName());
//				textFieldGoodsPrice.setText("￥"+goods.getPrice());
//				textFieldBuyNumber.setText("1");
//				
//				
//			}
//		});
//		panel_2.add(btnGoodsConfirm);
//		
//		JLabel lblGoodsName = new JLabel("商品名称：");
//		lblGoodsName.setFont(new Font("微软雅黑", Font.PLAIN, 12));
//		lblGoodsName.setBounds(10, 60, 70, 30);
//		panel_2.add(lblGoodsName);
//		
//		JLabel lblGoodsPrice = new JLabel("商品单价：");
//		lblGoodsPrice.setFont(new Font("微软雅黑", Font.PLAIN, 12));
//		lblGoodsPrice.setBounds(10, 100, 70, 30);
//		panel_2.add(lblGoodsPrice);
//		
//		textFieldGoodsName = new JTextField();
//		textFieldGoodsName.setBackground(Color.WHITE);
//		textFieldGoodsName.setEditable(false);
//		textFieldGoodsName.setFont(new Font("微软雅黑", Font.PLAIN, 12));
//		textFieldGoodsName.setBounds(80, 60, 170, 30);
//		panel_2.add(textFieldGoodsName);
//		textFieldGoodsName.setColumns(10);
//		
//		textFieldGoodsPrice = new JTextField();
//		textFieldGoodsPrice.setBackground(Color.WHITE);
//		textFieldGoodsPrice.setEditable(false);
//		textFieldGoodsPrice.setBounds(80, 100, 170, 30);
//		panel_2.add(textFieldGoodsPrice);
//		textFieldGoodsPrice.setColumns(10);
//		
//		JLabel label_3 = new JLabel("购买数量：");
//		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
//		label_3.setBounds(10, 140, 70, 30);
//		panel_2.add(label_3);
//		
//		textFieldBuyNumber = new JTextField();
//		textFieldBuyNumber.setFont(new Font("微软雅黑", Font.PLAIN, 12));
//		textFieldBuyNumber.setBounds(120, 140, 70, 30);
//		textFieldBuyNumber.setColumns(10);
//		textFieldBuyNumber.addKeyListener(new KeyListener() {
//			
//			@Override
//			public void keyTyped(KeyEvent e) {
//				
//			}
//			
//			@Override
//			public void keyReleased(KeyEvent e) {
//			}
//			
//			@Override
//			public void keyPressed(KeyEvent e) {
//			}
//		});
//		panel_2.add(textFieldBuyNumber);
//		
//		JLabel label_4 = new JLabel("件");
//		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
//		label_4.setBounds(200, 140, 20, 30);
//		panel_2.add(label_4);
//		JButton btnBuyAnother = new JButton("+加入购物车");
//		btnBuyAnother.addActionListener(new ActionListener() {
//			
//			public void actionPerformed(ActionEvent arg0) {
//				if (i<21) {
//					saleRecord[i][0]=goods.getGoodsId();
//					saleRecord[i][1]=goods.getPrice();
//					saleRecord[i][2]=Integer.parseInt(textFieldBuyNumber.getText().trim());
//					saleRecord[i][3]=goods.getNumber();
//					i++;
//				}
//				for (int n = 0; n < saleRecord.length; n++) {
//					sum += saleRecord[n][1]*saleRecord[n][2];
//				}
//				lblGoodsSum.setText("总价:￥"+sum);
//				sum=0;
//				for (int m = 0; m < i; m++) {
//					goodsShowString+=goodsShow[m];
//				}
//				lblGoodsShow.setText(goodsShowString);
//				goodsShowString="";
//			}
//		});
//		btnBuyAnother.setFont(new Font("微软雅黑", Font.PLAIN, 20));
//		btnBuyAnother.setForeground(Color.WHITE);
//		btnBuyAnother.setBackground(Color.RED);
//		btnBuyAnother.setBounds(370, 120, 170, 30);
//		panel_2.add(btnBuyAnother);
//		
//		JButton btnNewButton = new JButton("结账");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				for (int n = 0; n < saleRecord.length; n++) {
//					sum += saleRecord[n][1]*saleRecord[n][2];
//				}
//				
//				String s=JOptionPane.showInputDialog("应付￥"+sum+"，实付￥:");
////				String b=JOptionPane.s
//				
//				System.out.println(s);
//				
//				CheckBiz ciz = new CheckBiz();
//				String date = Tools.getDateTime();
//				
//				
//				ciz.addCheck(casherId, date, sum);
//				int checkId = ciz.getCheckId(date);
//				GoodsBiz biz = new GoodsBiz();
//				for (int j = 0; j < 9; j++) {//如果同一件商品刷两次以上就会使得最后一次数据覆盖前面所有数据
//					if (saleRecord[j][1]!=0) {
//						int goodsId = (int)saleRecord[j][0];
//						int number = (int) (saleRecord[j][3]-saleRecord[j][2]);
//						System.out.println(biz.subGoodsNumber(goodsId, number));
//					}
//				}
//				SaleRecordBiz srbiz = new SaleRecordBiz();
//				
//				for (int j = 0; j < 9; j++) {
//					if (saleRecord[j][1]!=0) {
//					int goodsId = (int)saleRecord[j][0];
//					int number = (int)saleRecord[j][2];
//					float sumUp =saleRecord[j][1]*saleRecord[j][2] ;
//					srbiz.addSaleRecord(checkId,goodsId,number,sumUp);
//					}
//				}
//				
//				
//				textFieldBarCode.setText("");
//				textFieldBuyNumber.setText("");
//				textFieldGoodsName.setText("");
//				textFieldGoodsPrice.setText("");
//				lblGoodsShow.setText("__");
//				lblGoodsSum.setText("总价：￥0.00");
//				saleRecord = new float[9][4];
//				i=0;
//				sum=0;
//				goodsShowString="";
//			}
//		});
//		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 25));
//		btnNewButton.setForeground(Color.WHITE);
//		btnNewButton.setBackground(Color.RED);
//		btnNewButton.setBounds(370, 220, 170, 60);
//		panel_2.add(btnNewButton);
//		
//		JButton btnX = new JButton("撤销订单");
//		btnX.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				textFieldBarCode.setText("");
//				textFieldBuyNumber.setText("");
//				textFieldGoodsName.setText("");
//				textFieldGoodsPrice.setText("");
//				lblGoodsShow.setText("__");
//				lblGoodsSum.setText("总价：￥0.00");
//				saleRecord = new float[9][4];
//				i=0;
//				sum=0;
//				goodsShowString="";
//			}
//		});
//		btnX.setBackground(Color.LIGHT_GRAY);
//		btnX.setForeground(Color.WHITE);
//		btnX.setFont(new Font("微软雅黑", Font.BOLD, 20));
//		btnX.setBounds(10, 220, 170, 60);
//		panel_2.add(btnX);
//		
//		lblGoodsShow = new JLabel("__");
//		lblGoodsShow.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblGoodsShow.setFont(new Font("微软雅黑", Font.BOLD, 26));
//		lblGoodsShow.setForeground(Color.GREEN);
//		lblGoodsShow.setBackground(Color.WHITE);
//		lblGoodsShow.setBounds(274, 49, 265, 40);
//		panel_2.add(lblGoodsShow);
//		
//		lblGoodsSum = new JLabel("总价:￥0.0");
//		lblGoodsSum.setFont(new Font("微软雅黑", Font.BOLD, 20));
//		lblGoodsSum.setBackground(Color.WHITE);
//		lblGoodsSum.setForeground(Color.RED);
//		lblGoodsSum.setBounds(370, 160, 170, 50);
//		panel_2.add(lblGoodsSum);
//		
//		JButton btnBuyNumSub = new JButton("");
//		btnBuyNumSub.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (textFieldBuyNumber.getText().trim().length()==0) {
//					textFieldBuyNumber.setText("1");
//				}else{
//				int parseInt = Integer.parseInt(textFieldBuyNumber.getText().trim());
//				if (parseInt>1) {
//					parseInt--;
//				}
//				textFieldBuyNumber.setText(""+parseInt);
//			}
//			}
//		});
//		btnBuyNumSub.setIcon(new ImageIcon("C:\\Users\\Dijkstra\\workspace\\Store\\Store\\sub.jpg"));
//		btnBuyNumSub.setBounds(80, 140, 30, 30);
//		panel_2.add(btnBuyNumSub);
//		
//		JButton btnBuyNumAdd = new JButton("");
//		btnBuyNumAdd.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (textFieldBuyNumber.getText().trim().length()==0) {
//					textFieldBuyNumber.setText("1");
//				}else{
//				int parseInt = Integer.parseInt(textFieldBuyNumber.getText().trim());
//				if (parseInt<10) {
//					parseInt++;
//				}
//				textFieldBuyNumber.setText(""+parseInt);
//			}
//			}
//		});
//		btnBuyNumAdd.setIcon(new ImageIcon("C:\\Users\\Dijkstra\\workspace\\Store\\Store\\add.jpg"));
//		btnBuyNumAdd.setBounds(220, 140, 30, 30);
//		panel_2.add(btnBuyNumAdd);
//		setVisible(true);
//	}
//}

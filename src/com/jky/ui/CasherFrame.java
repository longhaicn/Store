package com.jky.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.jky.biz.CheckBiz;
import com.jky.biz.GoodsBiz;
import com.jky.biz.KindBiz;
import com.jky.biz.MemberBiz;
import com.jky.biz.SaleRecordBiz;
import com.jky.biz.WorkRecordBiz;
import com.jky.entity.Goods;
import com.jky.entity.Kind;
import com.jky.entity.WorkRecord;
import com.jky.utils.Tools;
import java.awt.Toolkit;

public class CasherFrame extends JFrame {
	JPanel panelRight;
	JScrollPane scrollPane;
	private JPanel contentPane;
	private JTextField textFieldBarCode;
	private JTextField textFieldGoodsName;
	private JTextField textFieldGoodsPrice;
	private JTextField textFieldBuyNumber;
	/** saleRecord用于存储购物车商品列表信息0：商品ID 1：商品单价 2：商品卖出数量 3：商品存货数量 */
	protected static float saleRecord[][] = new float[9][4];
	protected static int i = 0;
	protected static Goods goods;
	private static float sum = 0;
	private static String goodsShowString = "";
	private JLabel lblGoodsShow;
	private JLabel lblGoodsSum;
	private static String goodsShow[] = { "①", "②", "③", "④", "⑤", "⑥", "⑦", "⑧", "⑨" ,"⑩"};
	private JTextField textFieldPayment;
	private JLabel lblPaymentErro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CasherFrame frame = new CasherFrame(20160001, "蔡少芬");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param string
	 * @param i
	 * @param i
	 * @param casherName
	 */
	public CasherFrame(final int memberId, String casherName) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Dijkstra\\workspace\\Store\\Store\\shoppingcar.jpg"));
		
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
					MemberBiz mbiz = new MemberBiz();
					mbiz.updMember(memberId,false);
				} catch (ParseException e1) {
					System.out.println("erro");
					e1.printStackTrace();
				}
			}
		});
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(180, 30, 1000, 600);
		setTitle("乐尔乐商城——工号：" + memberId + " - " + casherName + "-收银员正在服务......");
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelTop = new JPanel();
		panelTop.setBackground(Color.WHITE);
		contentPane.add(panelTop, BorderLayout.NORTH);

		JLabel label = new JLabel("***乐尔乐超市收银系统***");
		label.setFont(new Font("华文行楷", Font.PLAIN, 80));
		label.setBackground(Color.WHITE);
		panelTop.add(label);

		JPanel panelButtom = new JPanel();
		panelButtom.setBackground(Color.WHITE);
		contentPane.add(panelButtom, BorderLayout.SOUTH);

		JLabel lblNewLabel = new JLabel("版权所有");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lblNewLabel.setBackground(Color.WHITE);
		panelButtom.add(lblNewLabel);

		JPanel panelMain = new JPanel();
		panelMain.setBackground(Color.WHITE);
		contentPane.add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("二维码：");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(402, 37, 60, 30);
		panelMain.add(lblNewLabel_1);

		textFieldBarCode = new JTextField();
		textFieldBarCode.setText("ISBN978-7-5502-627-01");// 测试数据
		textFieldBarCode.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textFieldBarCode.setBounds(462, 37, 300, 30);
		panelMain.add(textFieldBarCode);
		textFieldBarCode.setColumns(10);

		JButton btnGoodsConfirm = new JButton("输入商品");
		btnGoodsConfirm.setForeground(Color.WHITE);
		btnGoodsConfirm.setBackground(Color.RED);
		btnGoodsConfirm.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnGoodsConfirm.setBounds(762, 37, 170, 30);
		btnGoodsConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String barCode = textFieldBarCode.getText().trim();
				GoodsBiz gbz = new GoodsBiz();
				goods = gbz.getTheGood(barCode);
				System.out.println(goods.getPrice());
				textFieldGoodsName.setText(goods.getName());
				textFieldGoodsPrice.setText("￥" + goods.getPrice());
				textFieldBuyNumber.setText("1");

			}
		});
		panelMain.add(btnGoodsConfirm);

		JLabel lblGoodsName = new JLabel("商品名称：");
		lblGoodsName.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblGoodsName.setBounds(402, 97, 70, 30);
		panelMain.add(lblGoodsName);

		JLabel lblGoodsPrice = new JLabel("商品单价：");
		lblGoodsPrice.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblGoodsPrice.setBounds(402, 137, 70, 30);
		panelMain.add(lblGoodsPrice);

		textFieldGoodsName = new JTextField();
		textFieldGoodsName.setBackground(Color.WHITE);
		textFieldGoodsName.setEditable(false);
		textFieldGoodsName.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textFieldGoodsName.setBounds(472, 97, 170, 30);
		panelMain.add(textFieldGoodsName);
		textFieldGoodsName.setColumns(10);

		textFieldGoodsPrice = new JTextField();
		textFieldGoodsPrice.setBackground(Color.WHITE);
		textFieldGoodsPrice.setEditable(false);
		textFieldGoodsPrice.setBounds(472, 137, 170, 30);
		panelMain.add(textFieldGoodsPrice);
		textFieldGoodsPrice.setColumns(10);

		JLabel label_3 = new JLabel("购买数量：");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_3.setBounds(402, 177, 70, 30);
		panelMain.add(label_3);

		textFieldBuyNumber = new JTextField();
		textFieldBuyNumber.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textFieldBuyNumber.setBounds(512, 177, 70, 30);
		textFieldBuyNumber.setColumns(10);
		textFieldBuyNumber.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		panelMain.add(textFieldBuyNumber);

		JLabel label_4 = new JLabel("件");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_4.setBounds(592, 177, 20, 30);
		panelMain.add(label_4);
		JButton btnBuyAnother = new JButton("+加入购物车");
		btnBuyAnother.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (i < 21) {
					saleRecord[i][0] = goods.getGoodsId();
					saleRecord[i][1] = goods.getPrice();
					saleRecord[i][2] = Integer.parseInt(textFieldBuyNumber.getText().trim());
					saleRecord[i][3] = goods.getNumber();
					i++;
				}
				for (int n = 0; n < saleRecord.length; n++) {
					sum += saleRecord[n][1] * saleRecord[n][2];
				}
				DecimalFormat df = new DecimalFormat("#.##");
				sum=new Float(df.format(sum));
				lblGoodsSum.setText("总价:￥" + sum);
				sum = 0;
				for (int m = 0; m < i; m++) {
					goodsShowString += goodsShow[m];
				}
				lblGoodsShow.setText(goodsShowString);
				goodsShowString = "";
				textFieldBarCode.setText("");
				textFieldBuyNumber.setText("");
				textFieldGoodsName.setText("");
				textFieldGoodsPrice.setText("");
			}
		});
		btnBuyAnother.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnBuyAnother.setForeground(Color.WHITE);
		btnBuyAnother.setBackground(Color.RED);
		btnBuyAnother.setBounds(762, 157, 170, 30);
		panelMain.add(btnBuyAnother);

		JButton btnNewButton = new JButton("结账");
		btnNewButton.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				String textPayment = textFieldPayment.getText();
				if (Tools.isEmpty(textPayment)) {
					lblPaymentErro.setText("*应付金额不能为空！");
					return;
				}
				 double pay=0;
				try {
					pay = new Double(textPayment);
				} catch (Exception e2) {
					lblPaymentErro.setText("*应付金额应为数字！");
					return;
				}
				for (int n = 0; n < saleRecord.length; n++) {
					sum += saleRecord[n][1] * saleRecord[n][2];
				}
				if (pay-sum<0) {
					lblPaymentErro.setText("*应付金额不能低于商品总价！");
					pay=0;
					return;
				}
				double change = pay-sum;
				DecimalFormat df = new DecimalFormat("#.##");
				pay=0;
				lblPaymentErro.setText("结账成功！找零:￥"+df.format(change));
				CheckBiz ciz = new CheckBiz();
				String date = Tools.getDateTime();
				ciz.addCheck(memberId, date, sum);
				int checkId = ciz.getCheckId(date);
				GoodsBiz biz = new GoodsBiz();
				for (int j = 0; j < 9; j++) {// 如果同一件商品刷两次以上就会使得最后一次数据覆盖前面所有数据
					if (saleRecord[j][1] != 0) {
						int goodsId = (int) saleRecord[j][0];
						int number = (int) (saleRecord[j][3] - saleRecord[j][2]);
						System.out.println(biz.subGoodsNumber(goodsId, number));
					}
				}
				
				SaleRecordBiz srbiz = new SaleRecordBiz();

				for (int j = 0; j < 9; j++) {
					if (saleRecord[j][1] != 0) {
						int goodsId = (int) saleRecord[j][0];
						int number = (int) saleRecord[j][2];
						float sumUp = saleRecord[j][1] * saleRecord[j][2];
						srbiz.addSaleRecord(checkId, goodsId, number, sumUp);
					}
				}

				textFieldBarCode.setText("");
				textFieldBuyNumber.setText("");
				textFieldGoodsName.setText("");
				textFieldGoodsPrice.setText("");
				textFieldPayment.setText("");
				lblGoodsShow.setText("__");
				lblGoodsSum.setText("总价：￥0.00");
				saleRecord = new float[9][4];
				i = 0;
				sum = 0;
				goodsShowString = "";
			}
		});
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setBounds(762, 329, 170, 60);
		panelMain.add(btnNewButton);

		JButton btnX = new JButton("撤销订单");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldBarCode.setText("");
				textFieldBuyNumber.setText("");
				textFieldGoodsName.setText("");
				textFieldGoodsPrice.setText("");
				lblGoodsShow.setText("__");
				lblGoodsSum.setText("总价：￥0.00");
				saleRecord = new float[9][4];
				i = 0;
				sum = 0;
				goodsShowString = "";
			}
		});
		btnX.setBackground(Color.LIGHT_GRAY);
		btnX.setForeground(Color.WHITE);
		btnX.setFont(new Font("微软雅黑", Font.BOLD, 20));
		btnX.setBounds(402, 329, 170, 60);
		panelMain.add(btnX);

		lblGoodsShow = new JLabel("__");
		lblGoodsShow.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGoodsShow.setFont(new Font("微软雅黑", Font.BOLD, 26));
		lblGoodsShow.setForeground(Color.GREEN);
		lblGoodsShow.setBackground(Color.WHITE);
		lblGoodsShow.setBounds(666, 86, 265, 40);
		panelMain.add(lblGoodsShow);

		lblGoodsSum = new JLabel("总价:￥0.00");
		lblGoodsSum.setFont(new Font("微软雅黑", Font.BOLD, 20));
		lblGoodsSum.setBackground(Color.WHITE);
		lblGoodsSum.setForeground(Color.RED);
		lblGoodsSum.setBounds(472, 228, 170, 50);
		panelMain.add(lblGoodsSum);

		JButton btnBuyNumSub = new JButton("");
		btnBuyNumSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldBuyNumber.getText().trim().length() == 0) {
					textFieldBuyNumber.setText("1");
				} else {
					int parseInt = Integer.parseInt(textFieldBuyNumber.getText().trim());
					if (parseInt > 1) {
						parseInt--;
					}
					textFieldBuyNumber.setText("" + parseInt);
				}
			}
		});
		btnBuyNumSub.setIcon(new ImageIcon("Store\\sub.jpg"));
		btnBuyNumSub.setBounds(472, 177, 30, 30);
		panelMain.add(btnBuyNumSub);

		JButton btnBuyNumAdd = new JButton("");
		btnBuyNumAdd.setIcon(new ImageIcon("C:\\Users\\Dijkstra\\workspace\\Store\\Store\\add.jpg"));
		btnBuyNumAdd.setBackground(Color.WHITE);
		btnBuyNumAdd.setForeground(Color.RED);
		btnBuyNumAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldBuyNumber.getText().trim().length() == 0) {
					textFieldBuyNumber.setText("1");
				} else {
					int parseInt = Integer.parseInt(textFieldBuyNumber.getText().trim());
					if (parseInt < 10) {
						parseInt++;
					}
					textFieldBuyNumber.setText("" + parseInt);
				}
			}
		});
		btnBuyNumAdd.setBounds(612, 177, 30, 30);
		panelMain.add(btnBuyNumAdd);

		JPanel goodsSelPanel = new JPanel();
		goodsSelPanel.setBounds(10, 10, 350, 380);
		panelMain.add(goodsSelPanel);
		goodsSelPanel.setLayout(new BorderLayout(0, 0));
		{

			JSplitPane splitPane = new JSplitPane();
			goodsSelPanel.add(splitPane, BorderLayout.CENTER);
			{
				// 右边
				scrollPane = new JScrollPane();
				splitPane.setRightComponent(scrollPane);
				{
					panelRight = new JPanel();
					panelRight.setBackground(Color.LIGHT_GRAY);
					scrollPane.setViewportView(panelRight);
					panelRight.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				}
			}
			{
				// 左边
				final JScrollPane scrollPane = new JScrollPane();
				splitPane.setLeftComponent(scrollPane);
				{
					JPanel panel = new JPanel();
					scrollPane.setViewportView(panel);
					KindBiz kbiz = new KindBiz();
					List<Kind> allKinds = kbiz.queryAllKind(null);

					panel.setLayout(new GridLayout(allKinds.size(), 1, 0, 0));
					
					textFieldPayment = new JTextField();
					textFieldPayment.setFont(new Font("微软雅黑", Font.PLAIN, 20));
					textFieldPayment.setBackground(Color.WHITE);
					textFieldPayment.setBounds(762, 228, 170, 50);
					textFieldPayment.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							
						}
					});
					panelMain.add(textFieldPayment);
					textFieldPayment.setColumns(10);
					
					JLabel lblNewLabel_2 = new JLabel("实付：￥");
					lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
					lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 20));
					lblNewLabel_2.setBounds(652, 228, 100, 50);
					panelMain.add(lblNewLabel_2);
					
					lblPaymentErro = new JLabel("");
					lblPaymentErro.setFont(new Font("微软雅黑", Font.PLAIN, 12));
					lblPaymentErro.setForeground(Color.RED);
					lblPaymentErro.setBounds(762, 288, 170, 30);
					panelMain.add(lblPaymentErro);

					for (final Kind kind : allKinds) {
						final JButton btn = new JButton(kind.getName());
						btn.setFont(new Font("楷体", Font.PLAIN, 20));
						btn.setFocusable(false);
						
						btn.setBackground(Color.WHITE);
						btn.setForeground(Color.BLACK);
						
						btn.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseExited(MouseEvent e) {
								// TODO Auto-generated method stub
								super.mouseExited(e);
								btn.setBackground(Color.WHITE);
							}
							@Override
							public void mouseEntered(MouseEvent e) {
								// TODO Auto-generated method stub
								super.mouseEntered(e);
								btn.setBackground(Color.PINK);
								int kindId = kind.getKindId();
								GoodsBiz gbiz = new GoodsBiz();
								List<Goods> allGoodsName = gbiz.queryAllGoods(kindId);
								panelRight.removeAll();// 先清空面板中的控件
								for (final Goods goods : allGoodsName) {
									JButton btn = new JButton(goods.getName());
									btn.setFont(new Font("楷体", Font.PLAIN, 20));
									btn.setFocusable(false);
									btn.setBackground(Color.WHITE);
									btn.setForeground(Color.BLACK);
									btn.addActionListener(new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent e) {
											System.out.println(goods.getBarCode());
											textFieldBarCode.setText(goods.getBarCode());

										}
									});
									panelRight.add(btn);
								}
								panelRight.updateUI();
							
							}
							
						});
//						btn.addActionListener(new ActionListener() {
//							@Override
//							public void actionPerformed(ActionEvent e) {
//								int kindId = kind.getKindId();
//								GoodsBiz gbiz = new GoodsBiz();
//								List<Goods> allGoodsName = gbiz.queryAllGoods(kindId);
//								panelRight.removeAll();// 先清空面板中的控件
//								for (final Goods goods : allGoodsName) {
//									JButton btn = new JButton(goods.getName());
//									btn.setFont(new Font("楷体", Font.PLAIN, 20));
//									btn.setFocusable(false);
//									btn.setBackground(Color.WHITE);
//									btn.setForeground(Color.BLACK);
//									btn.addActionListener(new ActionListener() {
//
//										@Override
//										public void actionPerformed(ActionEvent e) {
//											System.out.println(goods.getBarCode());
//											textFieldBarCode.setText(goods.getBarCode());
//
//										}
//									});
//									panelRight.add(btn);
//								}
//								panelRight.updateUI();
//							}
//						});
						panel.add(btn);
					}
				}
			}
		}
		setVisible(true);
	}
}

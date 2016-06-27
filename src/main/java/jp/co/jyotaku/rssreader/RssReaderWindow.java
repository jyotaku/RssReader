package jp.co.jyotaku.rssreader;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.apache.commons.lang3.math.NumberUtils;

import jp.co.jyotaku.rssreader.convert.dto.ConvertConditionDto;
import jp.co.jyotaku.rssreader.output.dto.OutputConditionDto;

public class RssReaderWindow extends JFrame {

	private static final long serialVersionUID = -843618369377969193L;

	private JPanel contentPane;
	private JButton btnLoad;

	// Show,hide toggle button
	private JToggleButton toggleBtnTitle;
	private JToggleButton toggleBtnDescription;
	private JToggleButton toggleBtnDate;
	private JToggleButton toggleBtnUrl;

	// Text length
	private JTextField txtFieldTitleLength;
	private JTextField txtFieldDescriptionLength;

	// Input URLs
	private JTextArea textAreaInputURLs;

	// Output TextArea
	private JTextArea textAreaResult;

	/**
	 * MAIN 関数
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RssReaderWindow rssReaderFrame = new RssReaderWindow();
					rssReaderFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public RssReaderWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		Box boxInputUrls = Box.createHorizontalBox();
		boxInputUrls.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "RSS\u30BD\u30FC\u30B9", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		textAreaInputURLs = new JTextArea();
		JScrollPane scrollpaneInputUrls = new JScrollPane(textAreaInputURLs);
		textAreaInputURLs.setToolTipText("一行一URLで入力してください");
		textAreaInputURLs.setText("http://tech.uzabase.com/rss");
		boxInputUrls.add(scrollpaneInputUrls);

		JPanel pnlShow = new JPanel();
		pnlShow.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"\u8868\u793A/\u975E\u8868\u793A", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		toggleBtnTitle = new JToggleButton("タイトル");
		toggleBtnTitle.setSelected(true);
		pnlShow.add(toggleBtnTitle);

		toggleBtnDescription = new JToggleButton("本文");
		toggleBtnDescription.setSelected(true);
		pnlShow.add(toggleBtnDescription);

		toggleBtnDate = new JToggleButton("日付");
		toggleBtnDate.setToolTipText("");
		pnlShow.add(toggleBtnDate);

		toggleBtnUrl = new JToggleButton("URL");
		pnlShow.add(toggleBtnUrl);

		btnLoad = new JButton("読み込み");
		RssReaderWindow self = this;
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RssReader rssReader = new RssReaderImpl(self);
				rssReader.run();
			}
		});

		JPanel pnlShowLength = new JPanel();
		pnlShowLength.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"\u8868\u793A\u6587\u5B57\u6570", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JLabel lblNewLabel = new JLabel("タイトル");
		pnlShowLength.add(lblNewLabel);

		txtFieldTitleLength = new JTextField();
		txtFieldTitleLength.setText("10");
		txtFieldTitleLength.setToolTipText("0の場合全ての文字が出力されます");
		pnlShowLength.add(txtFieldTitleLength);
		txtFieldTitleLength.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("本文");
		pnlShowLength.add(lblNewLabel_1);

		txtFieldDescriptionLength = new JTextField();
		txtFieldDescriptionLength.setText("30");
		txtFieldDescriptionLength.setToolTipText("0の場合全ての文字が出力されます");
		pnlShowLength.add(txtFieldDescriptionLength);
		txtFieldDescriptionLength.setColumns(10);

		Box boxResult = Box.createHorizontalBox();
		boxResult.setBorder(new TitledBorder(null, "\u8AAD\u307F\u8FBC\u307F\u7D50\u679C", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(boxInputUrls, GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
					.addGap(6))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(pnlShowLength, GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(pnlShow, GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(boxResult, GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(553, Short.MAX_VALUE)
					.addComponent(btnLoad, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(1)
					.addComponent(pnlShow, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pnlShowLength, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(boxInputUrls, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLoad, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(boxResult, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
					.addContainerGap())
		);

		textAreaResult = new JTextArea();
		textAreaResult.setToolTipText("");
		JScrollPane scrollpaneResult = new JScrollPane(textAreaResult);

		textAreaResult.setLineWrap(true);
		boxResult.add(scrollpaneResult);
		contentPane.setLayout(gl_contentPane);
	}

	public void setLoadButtonEvent(ActionListener listener) {
		btnLoad.addActionListener(listener);
	}

	public void appendResultText(String text) {
		textAreaResult.append(text);
		this.repaint();
	}
	
	public void clearResultText(){
		textAreaResult.setText("");
	}

	public Optional<ConvertConditionDto> getConvertConditionDto() {
		Optional<Integer> descLength = getMoreThanZeroNumber(txtFieldDescriptionLength.getText(), "「表示文字数 → 本文」");
		Optional<Integer> titleLength = getMoreThanZeroNumber(txtFieldTitleLength.getText(), "「表示文字数 → タイトル」");
		if (!descLength.isPresent() || !titleLength.isPresent()) {
			return Optional.empty();
		}

		ConvertConditionDto dto = new ConvertConditionDto(
				titleLength.get().intValue(),
				descLength.get().intValue());
		return Optional.of(dto);
	}

	public OutputConditionDto getOutputConditionDto() {
		OutputConditionDto dto = new OutputConditionDto(
				toggleBtnTitle.isSelected(),
				toggleBtnDescription.isSelected(),
				toggleBtnUrl.isSelected(),
				toggleBtnDate.isSelected()
				);
		return dto;
		
	}
	
	public List<String> getInputSourceList() {
		return Arrays.asList(textAreaInputURLs.getText().split("\n"));
	}

	private Optional<Integer> getMoreThanZeroNumber(String originStr, String itemName) {

		if (!NumberUtils.isNumber(originStr)) {
			showMessage(itemName + "に数字を入れてください");
			return Optional.empty();
		}

		int number = Integer.parseInt(originStr);

		if (number < 0) {
			showMessage(itemName + "に0以上の数字を入力してください");
			return Optional.empty();
		}

		return Optional.of(Integer.valueOf(number));
	}
	
	public void showMessage(String message){
		JOptionPane.showMessageDialog(this, message, "エラー", 
				  JOptionPane.ERROR_MESSAGE);
	}
}

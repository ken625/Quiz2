package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CSV {

	private static final String CSV = "C:/Users/kengo/Documents/Quiz.csv";	// 読み込むCSVファイルのパス
	private static final String SQL = "C:/Users/kengo/Documents/Quiz.sql";	//SQL : 書き込むsqlファイルのパス(無ければ作成されます)
	private static final String TABLE = "quiz";		//TABLE : INSERTするテーブル名
	private static final String COLUMN1 = "TEXT";
	private static final String COLUMN2 = "ANSWER";
	private static final String COLUMN3 = "LEVEL";
	private static final String COLUMN4 = "COMMENT";
	private static final String MARU = "〇";
	private static final String BATSU = "×";
	private static final int MARU_NUM = 1;
	private static final int BATSU_NUM = 0;
	private static final int MAX_LEVEL = 4;
	private static final int MIN_LEVEL = 1;
	private static final int DATA_SIZE = 4;

	public static void main(String[] args){
		List<String> lines = read();
		List<String[]> datalist = split(lines);
		check(datalist);
		List<String> sql = createSQL(datalist);
		write(sql);
	}

	public static void check(List<String[]> datalist){
		for(int i = 0; i < datalist.size(); i++){
			for(int j = 0; j < datalist.get(i).length; j++){
				switch(j){
					case 0:
					case 3:
						if(datalist.get(i)[j] == null){
							System.out.println("文字列がないか、csvの形式がおかしいか、プログラミングミスです。");
							System.out.println(i + "行目:" + j + "列目=> null");
							System.out.println();
						}
						break;
					case 1:
						if(!(datalist.get(i)[j].equals(MARU) || datalist.get(i)[j].equals(BATSU))){
							System.out.println("answerに「" + MARU + "」か「" + BATSU + "」以外の文字が使われているか、csvの形式がおかしいか、プログラミングミスです。");
							System.out.println(i + "行目:" + j + "列目=>" + datalist.get(i)[j]);
							System.out.println();
						}
						break;
					case 2:
						try{
							if(!(Integer.parseInt(datalist.get(i)[j]) >= MIN_LEVEL && Integer.parseInt(datalist.get(i)[j]) <= MAX_LEVEL)){
								System.out.println("levelに" + MIN_LEVEL + "から" + MAX_LEVEL + "以外の数字が使われているか、csvの形式がおかしいか、プログラミングミスです。");
								System.out.println(i + "行目:" + j + "列目=>" + datalist.get(i)[j]);
								System.out.println();
							}
						}catch(NumberFormatException e){
							e.printStackTrace();
							System.out.println("levelに半角数字以外の文字が使われているか、プログラミングミスです。");
							System.out.println(i + "行目:" + j + "列目=>" + datalist.get(i)[j]);
							System.out.println();
							System.exit(1);
						}
						break;
					default:
						System.out.println("csvの形式がおかしいか、プログラミングミスです。");
						System.out.println(i + ":" + j);
						System.out.println();
						break;
				}
			}
		}
	}

	public static List<String> read(){
		List<String> data = new ArrayList<>();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(CSV)), "SJIS"));
			String str = null;
			while((str = br.readLine()) != null){
				data.add(str);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	public static void write(List<String> sql){
		File file = new File(SQL);
		if(file.exists()){
			file.delete();
		}
		try {
			file.createNewFile();
			PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8")));
			for(String str : sql){
				pw.print(str);
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<String[]> split(List<String> lines){
		List<String[]> datalist = new ArrayList<>();

		int count = 0;
		String tmp = "";
		String[] data = null;
		int idx = 0;
		for(String str : lines){
			if(count == 0){
				data = new String[DATA_SIZE];
				idx = 0;
				datalist.add(data);
			}else{
				tmp += "\n";
			}
			char[] ch = str.toCharArray();
			for(int i = 0; i < ch.length; i++){
				switch(ch[i]){
					case '\"':
						if(i + 1 < ch.length && ch[i + 1] == '\"'){
							tmp += ch[i];
							i++;
						}else{
							if(count == 0){
								count = 1;
							}else{
								count = 0;
							}
						}
						break;
					case ',':
						if(count == 1){
							tmp += ch[i];
						}else{
							if(idx < DATA_SIZE){
								data[idx] = tmp;
							}else{
								System.out.println("csvファイルの列の数が多いか、プログラミングミスです。");
								System.out.println("SQL文がおかしくなるかもしない");
								System.out.println();
							}
							idx++;
							tmp = "";
						}
						break;
					default:
						tmp += ch[i];
						break;
				}
			}
			if(count == 0){
				if(idx < DATA_SIZE){
					data[idx] = tmp;
				}else{
					System.out.println("csvファイルの列の数が多いか、プログラミングミスです。");
					System.out.println("SQL文がおかしくなるかもしない");
					System.out.println();
				}
				tmp = "";
			}
		}

		return datalist;
	}

	public static List<String> createSQL(List<String[]> data){
		List<String> sql = new ArrayList<>();

		sql.add("INSERT INTO `" + TABLE + "`(" + COLUMN1 + "," + COLUMN2 + "," + COLUMN3 + "," + COLUMN4 + ") VALUES");
		StringBuffer tmp = null;
		for(int i = 0; i < data.size(); i++){
			tmp = new StringBuffer();
			tmp.append("(\'")
			   .append(data.get(i)[0] == null ? "null" : data.get(i)[0].replace("\\", "\\\\").replace("\'", "\'\'").replace("\r\n", "\n").replace("\r", "\n").replace("\n", "\\n"))
			   .append("\',")
			   .append(data.get(i)[1].equals(MARU) ? MARU_NUM : BATSU_NUM)
			   .append(",")
			   .append(data.get(i)[2])
			   .append(",\'")
			   .append(data.get(i)[3] == null ? "null" : data.get(i)[3].replace("\\", "\\\\").replace("\'", "\'\'").replace("\r\n", "\n").replace("\r", "\n").replace("\n", "\\n"))
			   .append("\')");
			if(i != data.size() - 1){
				tmp.append(",");
			}else{
				tmp.append(";");
			}
			sql.add(tmp.toString());
		}

		return sql;
	}

}
package org.fancy.socket.bio.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client
{
	public static void main(String[] args)
	{
		Client client = new Client();
		client.send(testXML());

	}

	public static String testXML()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<bocb2e version=\"110\" security=\"true\" locale=\"zh_CN\">")
		.append("<table-tc-app-main><app_no>20130531103308546</app_no><card_app_no></card_app_no><card_no></card_no>")
		.append("<status>0</status><manu_date></manu_date><get_date></get_date><id_no></id_no><cus_nm></cus_nm>")
		.append("<batch>0</batch><submit_dt>20130531</submit_dt><submit_time>10:33:08</submit_time><lupteller></lupteller>")
		.append("<lupdate></lupdate><luptime></luptime></table-tc-app-main><table-tc-customer>")
		.append("<app_no>20130531103308546</app_no><card_app_no></card_app_no><surname>刘</surname><firstname>洋</firstname>")
		.append("<sex>1</sex><char_nm>LIU YANG</char_nm><birth_yr>1983</birth_yr><birth_month>04</birth_month>")
		.append("<birth_day>22</birth_day><state>CHN</state><id_type>1</id_type><cus_id_no>220303198304223012</cus_id_no>")
		.append("<idlst_dt>2013-05-31</idlst_dt><marry>未婚</marry><degree>本科</degree><address1>天津市</address1>")
		.append("<address2>和平</address2><address3>大沽南路250号</address3><zip_code>300000</zip_code><yrs>3</yrs>")
		.append("<house_type>0001</house_type><cus_area_no>022</cus_area_no><cus_telno>1231231</cus_telno>")
		.append("<cus_telno_br></cus_telno_br><cus_mobile_no>13502080307</cus_mobile_no><mother_surname>邢</mother_surname>")
		.append("<mschl>一中</mschl><submit_dt>20130531</submit_dt><submit_time>10:33:08</submit_time></table-tc-customer>")
		.append("<table-tc-work><app_no>20130531103308546</app_no><card_app_no></card_app_no><comp_name></comp_name><address1>")
		.append("</address1><address2></address2><address3></address3><zip_code></zip_code><area_no></area_no><telno></telno>")
		.append("<telno_br></telno_br><buss_att></buss_att><econ_type></econ_type><degree></degree><yrs></yrs><income></income>")
		.append("<submit_dt>20130531</submit_dt><submit_time>10:33:08</submit_time></table-tc-work><table-tc-address-getcard>")
		.append("<app_no>20130531103308546</app_no><card_app_no></card_app_no><br_no>02519</br_no><br_name>静海支行</br_name>")
		.append("<br_address>天津市静海胜利大街31号</br_address><submit_dt>20130531</submit_dt><submit_time>10:33:08</submit_time>")
		.append("</table-tc-address-getcard><table-tc-contacts><app_no>20130531103308546</app_no><card_app_no></card_app_no>")
		.append("<name>刘刘</name><relation>11</relation><comp_name>水利局</comp_name><area_no>022</area_no><telno>66666666</telno>")
		.append("<telno_br></telno_br><mobile_no>13666666666</mobile_no><submit_dt>20130531</submit_dt>")
		.append("<submit_time>10:33:08</submit_time></table-tc-contacts><table-tc-service><app_no>20130531103308546</app_no>")
		.append("<card_app_no></card_app_no><area>4344899</area><bill>1</bill><email>29608922@qq.com</email><paperbill>2</paperbill>")
		.append("<check_pass>1</check_pass><cny_pay></cny_pay><pay_type>1</pay_type><acct_type>1000</acct_type>")
		.append("<submit_dt>20130531</submit_dt><submit_time>10:33:08</submit_time></table-tc-service></bocb2e>");
		return sb.toString();
	}

	public String send(String message)
	{
		Socket socket = null;
		InputStream is = null;
		ByteArrayOutputStream bos = null;
		OutputStream os = null;
		String returnMsg = null;
		try
		{
			socket = new Socket("22.23.20.19", 9888);
			socket.setTcpNoDelay(true);
			socket.setReceiveBufferSize(10000);

			// ----------发送报文----------
			os = socket.getOutputStream();
			os.write(message.getBytes("UTF-8"));
			os.flush();

			is = socket.getInputStream();
			bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int length = 0;
			while ((length = is.read(b)) != -1)
			{
				bos.write(b, 0, length);
			}

			returnMsg = new String(bos.toByteArray(), "UTF-8");
			// ------------接收报文----------
			// BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), ENCODE_UFT8));
			// for (String rec = in.readLine(); rec != null;)
			// {
			// returnMsg.append(rec);
			// rec = in.readLine();
			// }

		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (socket != null && !socket.isClosed())
				try
				{
					socket.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
		}
		return returnMsg;
	}
}

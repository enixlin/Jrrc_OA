package enixlin.jrrc.OA.client.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import javax.swing.JOptionPane;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Login extends Thread {
	private URI uri;
	private String result="";
	enixlin.jrrc.OA.client.UI.Login login_window;

	public URI getUri() {
		return uri;
	}

	public enixlin.jrrc.OA.client.UI.Login getLogin_window() {
		return login_window;
	}

	public void setLogin_window(enixlin.jrrc.OA.client.UI.Login login_window) {
		this.login_window = login_window;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String submit(URI uri) {
		// 建立HTTP连接
		CloseableHttpClient client = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(uri.toString());
		System.out.println(uri.toString());
		try {
			CloseableHttpResponse response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream inputStream = entity.getContent();
				
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
				String line="";
			
				while ((line = bufferedReader.readLine()) != null) {
					result = result + line+"\n";
				}
			
			bufferedReader.close();
			inputStream.close();
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result.equals("null")){
			result="";
		}
		return result;

	}

	@Override
	public void run() {

		// System.out.println(submit(uri));
		submit(uri);
		if (result != null) {
			//System.out.println(result);
			JOptionPane.showMessageDialog(null, result, "alert", JOptionPane.ERROR_MESSAGE);

		}

	}

}
